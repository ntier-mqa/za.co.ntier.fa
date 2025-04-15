package za.co.ntier.fa.process;

import java.sql.Timestamp;

import org.adempiere.base.annotation.Parameter;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.POWrapper;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

import za.ntier.utils.Notifications;

@org.adempiere.base.annotation.Process(name="za.co.ntier.fa.process.BaseDocApproveProcess")
public class BaseDocApproveProcess<T extends PO> extends SvrProcess{
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
			
		Notifications.sendNotificationToRole(
				IDocApprove.SNR_ADMIN_FIN_ROLE_ID,
				getRecord_ID(),
				docApprove.getSubject(),
				docApprove.getMessage(),
				docApprove.getMessageID(),
				getCtx(),
				get_TrxName(),
				getTable_ID());
	}
	
	protected void doSubmitDocForLineManage(){
		if (docApprove.getLine_Manager_ID() <= 0)
			throw new AdempiereException(Msg.getMsg(getCtx(), "ZZ_ApproveChooseLineManager"));
		
		if (!docApprove.isZZ_ID_Copy_Uploaded())
			throw new AdempiereException(Msg.getMsg(getCtx(), "ZZ_ApproveUploadCopyID"));
		
		docApprove.setZZ_DocStatus(IDocApprove.ZZ_DOCSTATUS_Submitted);
		docApprove.setZZ_DocAction(IDocApprove.ZZ_DOCACTION_ApproveDoNotApprove);
		docApprove.setZZ_Date_Submitted(now);
		
		Notifications.sendNotification(
				docApprove.getLine_Manager_ID(),
				getRecord_ID(),
				docApprove.getSubject(),
				docApprove.getMessage(),
				docApprove.getMessageID(),
				getCtx(),
				get_TrxName(),
				getTable_ID());
	}
	
	protected void doLineManageApprove() {
		if("Y".equals(pApproveRejLM)){
			doSubmitDocForSnrAdminFinanceManage(false);
		}else{
			docApprove.setZZ_DocStatus(IDocApprove.ZZ_DOCSTATUS_NotApprovedByLM);
			docApprove.setZZ_Date_Not_Approved_by_LM(now);
			Notifications.sendNotification(
					docApprove.getCreatedBy(),
					getRecord_ID(),
					docApprove.getSubject(),
					docApprove.getMessage(),
					docApprove.getMessageID(),
					getCtx(),
					get_TrxName(),
					getTable_ID());
		}
	}
	
	protected void doSnrAdminFinanceApprove() {
		if (pApproveRejSAF.equals("Y")) {
			docApprove.setZZ_DocStatus(IDocApprove.ZZ_DOCSTATUS_Approved);
			docApprove.setZZ_Date_Approved(now);
			docApprove.setZZ_Snr_Admin_Fin_ID(Env.getAD_User_ID(getCtx()));
			Notifications.sendNotification(
					docApprove.getCreatedBy(),
					getRecord_ID(),
					docApprove.getSubject(),
					docApprove.getMessage(),
					docApprove.getMessageID(),
					getCtx(),
					get_TrxName(),
					getTable_ID());
		} else {
			docApprove.setZZ_DocStatus(IDocApprove.ZZ_DOCSTATUS_NotApprovedBySnrAdminFinance);
			docApprove.setZZ_Date_Not_Approved_by_Snr_Adm_Fin(now);
			Notifications.sendNotification(
					docApprove.getCreatedBy(),
					getRecord_ID(),
					docApprove.getSubject(),
					docApprove.getMessage(),
					docApprove.getMessageID(),
					getCtx(),
					get_TrxName(),
					getTable_ID());
			Notifications.sendNotification(
					docApprove.getLine_Manager_ID(),
					getRecord_ID(),
					docApprove.getSubject(),
					docApprove.getMessage(),
					docApprove.getMessageID(),
					getCtx(),
					get_TrxName(),
					getTable_ID());
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
}
