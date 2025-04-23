package za.co.ntier.fa.process.api;

import java.sql.Timestamp;
import java.util.Arrays;

import org.adempiere.base.annotation.Parameter;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.POWrapper;
import org.adempiere.webui.apps.AEnv;
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
	
	@SuppressWarnings("unchecked")
	@Override
	protected void prepare() {
		MTable table = MTable.get(getCtx(), getTable_ID());
		poObj = (T)table.getPO(getRecord_ID(), get_TrxName());
		initDocApproveObj();
		now = new Timestamp(System.currentTimeMillis());
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
		
		Arrays.asList(MUserRoles.getOfRole(getCtx(), IDocApprove.SNR_ADMIN_FIN_ROLE_ID)).forEach(role -> {
			if (role.isActive()) {
				AbstractDocApproveProcess.sendNotification(role.getAD_User_ID(), getTable_ID(), getRecord_ID(), docApprove.getZZMailRequestSnr(), get_TrxName());
			}
		});
		
		
	}
	
	protected void doSubmitDocForLineManage(){
		if (docApprove.getLine_Manager_ID() <= 0)
			throw new AdempiereException(Msg.getMsg(getCtx(), "ZZ_ApproveChooseLineManager"));
		
		docApprove.setZZ_DocStatus(IDocApprove.ZZ_DOCSTATUS_Submitted);
		docApprove.setZZ_DocAction(IDocApprove.ZZ_DOCACTION_ApproveDoNotApprove);
		docApprove.setZZ_Date_Submitted(now);
		
		AbstractDocApproveProcess.sendNotification(
				docApprove.getLine_Manager_ID(), getTable_ID(), getRecord_ID(), docApprove.getZZMailRequestLine(), get_TrxName());
	}
	
	protected void doLineManageApprove() {
		if("Y".equals(pApproveRejLM)){
			doSubmitDocForSnrAdminFinanceManage(false);
		}else{
			docApprove.setZZ_DocStatus(IDocApprove.ZZ_DOCSTATUS_NotApprovedByLM);
			docApprove.setZZ_Date_Not_Approved_by_LM(now);
			AbstractDocApproveProcess.sendNotification(docApprove.getCreatedBy(), getTable_ID(), getRecord_ID(), docApprove.getZZMailLineReject(), get_TrxName());
		}
	}
	
	protected void doSnrAdminFinanceApprove() {
		if (pApproveRejSAF.equals("Y")) {
			docApprove.setZZ_DocStatus(IDocApprove.ZZ_DOCSTATUS_Approved);
			docApprove.setZZ_Date_Approved(now);
			docApprove.setZZ_Snr_Admin_Fin_ID(Env.getAD_User_ID(getCtx()));
			AbstractDocApproveProcess.sendNotification(
					docApprove.getCreatedBy(), getTable_ID(), getRecord_ID(), docApprove.getZZMailSnrApproved(), get_TrxName());
		} else {
			docApprove.setZZ_DocStatus(IDocApprove.ZZ_DOCSTATUS_NotApprovedBySnrAdminFinance);
			docApprove.setZZ_Date_Not_Approved_by_Snr_Adm_Fin(now);
			if (docApprove.isZZ_AllowLineManageApproved())
				AbstractDocApproveProcess.sendNotification(docApprove.getCreatedBy(), getTable_ID(), getRecord_ID(), docApprove.getZZMailSnrRejectLine(), get_TrxName());
			
			AbstractDocApproveProcess.sendNotification(docApprove.getLine_Manager_ID(), getTable_ID(), getRecord_ID(), docApprove.getZZMailSnrRejectSubmitor(), get_TrxName());
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
	
	@Override
	protected String doIt() throws Exception {
		validateData();
		String currentDocAction = docApprove.getZZ_DocAction();
		String currentDocStatus = docApprove.getZZ_DocStatus();
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
			throw new AdempiereException(Msg.getMsg(getCtx(), "ZZ_WrongWorkflowState", 
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
		return null;
	}
	
	
	
	protected void doLogic() {
		// overrider on child to implement logic when reach to FinalWorkflowState (approve/complete)
	}
	
	public static final int FROM_EMAIL_USER_ID = MSysConfig.getIntValue("FROM_EMAIL_USER_ID",1000011);
	public static void sendNotification(int mailToUserId, int tableID, int recordID, MMailText mailTemplate, String trxName) {
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
			msgBody = msgBody.replace("@documentLink@", AEnv.getZoomUrlTableUU(mailTemplate.getPO()));
			if (!client.sendEMail(from, mUser, msgHeader, msgBody, null, mailTemplate.isHtml())) {
				log.fine("Problem Sending Email.  Please contact Support");
			}

		}
	}
}
