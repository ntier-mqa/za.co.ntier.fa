package za.co.ntier.fa.process.api;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.adempiere.base.annotation.Parameter;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.POWrapper;
import org.adempiere.webui.apps.AEnv;
import org.apache.commons.lang3.StringUtils;
import org.compiere.model.MClient;
import org.compiere.model.MMailText;
import org.compiere.model.MNote;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTable;
import org.compiere.model.MUser;
import org.compiere.model.MUserRoles;
import org.compiere.model.PO;
import org.compiere.model.X_AD_User;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

public abstract class AbstractDocApproveProcess<T extends PO> extends SvrProcess{
	private static final CLogger log = CLogger.getCLogger(AbstractDocApproveProcess.class);
	@Parameter(name="ZZ_Approve_Rej_LM")
	protected String pApproveRejLM;
	@Parameter(name="ZZ_Approve_Rej_SAF")
	protected String pApproveRejSAF;
	@Parameter(name="ZZ_Approve_Rej_MFC")  // Manager Finance Consumables
	protected String pApprove_Rej_MFC;
	@Parameter(name="ZZ_Approve_Rej_SDL")  // SDL Fin Manager Consumables
	protected String pApprove_Rej_SDL;
	
	protected String currentDocAction;
	protected String currentDocStatus;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void prepare() {
		MTable table = MTable.get(getCtx(), getTable_ID());
		poObj = (T)table.getPO(getRecord_ID(), get_TrxName());
		initDocApproveObj();
		now = new Timestamp(System.currentTimeMillis());
		currentDocAction = docApprove.getZZ_DocAction();
		currentDocStatus = docApprove.getZZ_DocStatus();
	}
	
	/**
	 * overrider on child call docApprove = poObj to help docApprove can use direct interface on (T)poObj faster than POWrapper
	 */
	protected void initDocApproveObj() {
		docApprove = POWrapper.create(poObj, IDocApprove.class);
	}
	
	protected T poObj;
	protected IDocApprove docApprove;
	protected Timestamp now = null;
	
	protected void doSubmitDocForSnrAdminFinanceManage(boolean isBypassLineManage) {
		docApprove.setZZ_DocStatus(IDocApprove.ZZ_DOCSTATUS_InProgress);
		docApprove.setZZ_DocAction(IDocApprove.ZZ_DOCACTION_FinalApprovalDoNotApprove);
		docApprove.setZZ_Date_LM_Approved(now);
		if (isBypassLineManage && docApprove.getZZ_Date_Submitted() == null)
			docApprove.setZZ_Date_Submitted(now);
		
		queueNotifyForRole(queueNotifis, IDocApprove.SNR_ADMIN_FIN_ROLE_ID, getTable_ID(), getRecord_ID(), docApprove.getZZMailRequestSnr());
	}
	
	protected void doSubmitDocForLineManage(){
		if (docApprove.getLine_Manager_ID() <= 0)
			throw new AdempiereException(Msg.getMsg(getCtx(), "ZZ_DocApproveMissingLineManager"));
		
		docApprove.setZZ_DocStatus(IDocApprove.ZZ_DOCSTATUS_Submitted);
		docApprove.setZZ_DocAction(IDocApprove.ZZ_DOCACTION_ApproveDoNotApprove);
		docApprove.setZZ_Date_Submitted(now);
		
		AbstractDocApproveProcess.queueNotify(queueNotifis, 
				docApprove.getLine_Manager_ID(), getTable_ID(), getRecord_ID(), docApprove.getZZMailRequestLine());
	}
	
	protected void doLineManageApprove() {
		if("Y".equals(pApproveRejLM)){
			if (docApprove.isZZ_AllowSnrAdminFinanceApproved()) {
				doSubmitDocForSnrAdminFinanceManage(false);
			}else {
				docApprove.setZZ_Date_Approved(now);
				docApprove.setZZ_Date_LM_Approved(now);
				docApprove.setZZ_Date_Completed(now);
				AbstractDocApproveProcess.queueNotify(queueNotifis, 
						docApprove.getCreatedBy(), getTable_ID(), getRecord_ID(), docApprove.getZZMailLineApproved());
			}
		}else{
			docApprove.setZZ_DocStatus(IDocApprove.ZZ_DOCSTATUS_NotApprovedByLM);
			docApprove.setZZ_Date_Not_Approved_by_LM(now);
			AbstractDocApproveProcess.queueNotify(queueNotifis, docApprove.getCreatedBy(), getTable_ID(), getRecord_ID(), docApprove.getZZMailLineReject());
		}
	}
	
	protected void doSnrAdminFinanceApprove() {
		if (pApproveRejSAF.equals("Y")) {
			docApprove.setZZ_DocStatus(IDocApprove.ZZ_DOCSTATUS_Approved);
			docApprove.setZZ_Date_Approved(now);
			docApprove.setZZ_Snr_Admin_Fin_ID(Env.getAD_User_ID(getCtx()));
			AbstractDocApproveProcess.queueNotify(queueNotifis, 
					docApprove.getCreatedBy(), getTable_ID(), getRecord_ID(), docApprove.getZZMailSnrApproved());
		} else {
			docApprove.setZZ_DocStatus(IDocApprove.ZZ_DOCSTATUS_NotApprovedBySnrAdminFinance);
			docApprove.setZZ_Date_Not_Approved_by_Snr_Adm_Fin(now);
			if (docApprove.isZZ_AllowLineManageApproved())
				AbstractDocApproveProcess.queueNotify(queueNotifis, docApprove.getCreatedBy(), getTable_ID(), getRecord_ID(), docApprove.getZZMailSnrRejectLine());
			
			AbstractDocApproveProcess.queueNotify(queueNotifis, docApprove.getLine_Manager_ID(), getTable_ID(), getRecord_ID(), docApprove.getZZMailSnrRejectSubmitor());
		}
	}
	
	protected void doFinalDocState(String currentDocAction) {
		boolean isFinalApprove = false;
		if (docApprove.getZZ_FinalWorkflowStateValue() == null)
            return;
		
		isFinalApprove = docApprove.isZZ_AllowLineManageApproved() && !docApprove.isZZ_AllowSnrAdminFinanceApproved() &&
				IDocApprove.ZZ_DOCACTION_ApproveDoNotApprove.equals(currentDocAction) &&
				"Y".equals(pApproveRejLM);
		
		if (!isFinalApprove)
			isFinalApprove = docApprove.isZZ_AllowSnrAdminFinanceApproved() &&
					IDocApprove.ZZ_DOCACTION_FinalApprovalDoNotApprove.equals(currentDocAction) &&
					"Y".equals(pApproveRejSAF);
		
		if (isFinalApprove && docApprove.getZZ_FinalWorkflowStateValue() != null) {
			docApprove.setZZ_DocStatus(docApprove.getZZ_FinalWorkflowStateValue());
			if(IDocApprove.ZZ_DOCSTATUS_Completed.equals(docApprove.getZZ_FinalWorkflowStateValue()))
				docApprove.setZZ_Date_Completed(now);
			if(IDocApprove.ZZ_DOCSTATUS_Approved.equals(docApprove.getZZ_FinalWorkflowStateValue()))
				docApprove.setZZ_Date_Approved(now);
		}
		
	}
	
	protected void validateData() {
		// overrider on child to implement validation
	}
	
	protected void defaultValidate() {
		if (poObj.getCreatedBy() == docApprove.getLine_Manager_ID() ||
				poObj.getCreatedBy() == docApprove.getZZ_Snr_Admin_Fin_ID()) {
			
			throw new AdempiereException(Msg.getMsg(getCtx(), "ZZ_DocApproveAssignSelfApprove"));
		}
		
		if (!IDocApprove.ZZ_DOCACTION_SubmitToLineManager.equals(currentDocAction) && poObj.getCreatedBy() == Env.getAD_User_ID(getCtx())) {
			throw new AdempiereException(Msg.getMsg(getCtx(), "ZZ_DocApproveSelfApprove"));
		}
	}
	
	@Override
	protected String doIt() throws Exception {
		defaultValidate();
		validateData();
		if(docApprove.isZZ_AllowLineManageApproved() && 
				IDocApprove.ZZ_DOCACTION_SubmitToLineManager.equals(currentDocAction)) {

			doSubmitDocForLineManage();
			
		}else if(docApprove.isZZ_AllowLineManageApproved() &&
				IDocApprove.ZZ_DOCACTION_ApproveDoNotApprove.equals(currentDocAction) && 
				IDocApprove.ZZ_DOCSTATUS_Submitted.equals(currentDocStatus)) {
			
			doLineManageApprove();
		}else if(!docApprove.isZZ_AllowLineManageApproved() &&
				docApprove.isZZ_AllowSnrAdminFinanceApproved() &&
				IDocApprove.ZZ_DOCACTION_SubmitToLineManager.equals(currentDocAction)) {
			doSubmitDocForSnrAdminFinanceManage(true);
		}else if(docApprove.isZZ_AllowSnrAdminFinanceApproved() &&
				IDocApprove.ZZ_DOCACTION_FinalApprovalDoNotApprove.equals(currentDocAction) && 
				IDocApprove.ZZ_DOCSTATUS_InProgress.equals(currentDocStatus)) {
			
			doSnrAdminFinanceApprove();
			
		}else {
			throw new AdempiereException(Msg.getMsg(getCtx(), "ZZ_DocApproveWrongWorkflowState", 
					new Object [] {docApprove.isZZ_AllowLineManageApproved(),
					docApprove.isZZ_AllowSnrAdminFinanceApproved(),
					currentDocStatus,
					currentDocAction}));
		}
		doFinalDocState(currentDocAction);
		poObj.saveEx();

		if (!Util.isEmpty(docApprove.getZZ_FinalWorkflowStateValue(), true) && docApprove.getZZ_FinalWorkflowStateValue().equals(docApprove.getZZ_DocStatus())) {
			doLogic();
		}
		
		sentNotify(queueNotifis, get_TrxName());
		return null;
	}
	
	
	
	protected void doLogic() {
		// overrider on child to implement logic when reach to FinalWorkflowState (approve/complete)
	}
	
	public static final int FROM_EMAIL_USER_ID = MSysConfig.getIntValue("FROM_EMAIL_USER_ID",1000011);
	
	protected List<Map<NotificationFields, Object>> queueNotifis = new ArrayList<>();
	
	public enum NotificationFields {
	    MAIL_TO_USER_ID,
	    TABLE_ID,
	    RECORD_ID,
	    MAIL_TEMPLATE;
	}
	
	public static void queueNotifyForRole(List<Map<NotificationFields, Object>> queueNotifis, int mailRoleId, int tableID, int recordID, MMailText mailTemplate) {
		Arrays.asList(MUserRoles.getOfRole(Env.getCtx(), mailRoleId)).forEach(role -> {
			if (role.isActive()) {
				AbstractDocApproveProcess.queueNotify(queueNotifis, role.getAD_User_ID(), tableID, recordID, mailTemplate);
			}
		});
	}
	
	public static void queueNotify(List<Map<NotificationFields, Object>> queueNotifis, int mailToUserId, int tableID, int recordID, MMailText mailTemplate) {
		Map<NotificationFields, Object> sentNotifyInfo = new EnumMap<>(NotificationFields.class);
		sentNotifyInfo.put(NotificationFields.MAIL_TO_USER_ID, mailToUserId);
		sentNotifyInfo.put(NotificationFields.TABLE_ID, tableID);
		sentNotifyInfo.put(NotificationFields.RECORD_ID, recordID);
		sentNotifyInfo.put(NotificationFields.MAIL_TEMPLATE, mailTemplate);
		queueNotifis.add(sentNotifyInfo);
	}
	
	public static void sentNotify(List<Map<NotificationFields, Object>> queueNotifis, String trxName) {
		queueNotifis.forEach(sentNotifyInfo -> {
			int mailToUserId = (int) sentNotifyInfo.get(NotificationFields.MAIL_TO_USER_ID);
			int tableID = (int) sentNotifyInfo.get(NotificationFields.TABLE_ID);
			int recordID = (int) sentNotifyInfo.get(NotificationFields.RECORD_ID);
			MMailText mailTemplate = (MMailText) sentNotifyInfo.get(NotificationFields.MAIL_TEMPLATE);
			
			MUser mUser = new MUser(Env.getCtx(), mailToUserId, null);
			mailTemplate.setUser(mUser);
			String msgHeader = mailTemplate.getMailHeader();
			String msgBody = mailTemplate.getMailText();
			
			if (X_AD_User.NOTIFICATIONTYPE_Notice.equals(mUser.getNotificationType()) ||
					X_AD_User.NOTIFICATIONTYPE_EMailPlusNotice.equals(mUser.getNotificationType())	) {
				
				MNote note = new MNote(Env.getCtx(), 0, mUser.getAD_User_ID(),
						tableID, recordID, 
						msgHeader, msgBody, trxName);
				
				note.saveEx();
			}
			
			if (X_AD_User.NOTIFICATIONTYPE_EMail.equals(mUser.getNotificationType()) ||
					X_AD_User.NOTIFICATIONTYPE_EMailPlusNotice.equals(mUser.getNotificationType())) {
				
				MClient client = MClient.get(Env.getCtx());
				
				MUser from = MUser.get(Env.getCtx(), FROM_EMAIL_USER_ID);
				
				String extraForEmail = mailTemplate.getMailText2();
				if (StringUtils.isNotBlank(extraForEmail)){
					extraForEmail = extraForEmail.replace("@documentLink@", AEnv.getZoomUrlTableUU(mailTemplate.getPO()));
					msgBody = msgBody + System.lineSeparator() + extraForEmail;
				}
				
				if (!client.sendEMail(from, mUser, msgHeader, msgBody, null, mailTemplate.isHtml())) {
					log.fine("Problem Sending Email.  Please contact Support");
				}

			}
		});
	}
}
