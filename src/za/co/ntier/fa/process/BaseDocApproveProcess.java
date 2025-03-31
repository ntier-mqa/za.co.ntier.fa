package za.co.ntier.fa.process;

import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.base.annotation.Parameter;
import org.adempiere.model.POWrapper;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import za.ntier.utils.Notifications;

@org.adempiere.base.annotation.Process(name="za.co.ntier.fa.process.BaseDocApproveProcess")
public class BaseDocApproveProcess extends SvrProcess{
	@Parameter(name="ZZ_Approve_Rej_LM")
	protected String pApproveRejLM;
	@Parameter(name="ZZ_Approve_Rej_SAF")
	protected String pApproveRejSAF;
	
	@Override
	protected void prepare() {
		
	}
	
	@Override
	protected String doIt() throws Exception {
		MTable table = MTable.get(getCtx(), getTable_ID());
		PO po = table.getPO(getRecord_ID(), get_TrxName());
		//PO po = new GenericPO(table.getTableName(), getCtx(),getRecord_ID());
		IDocApprove docApprove = POWrapper.create(po, IDocApprove.class);
		if (docApprove.getZZ_DocAction().equals(IDocApprove.ZZ_DOCACTION_SubmitToLineManager)) {
			if (docApprove.getLine_Manager_ID() <= 0) {
				return Msg.getMsg(getCtx(), "ZZ_ApproveChooseLineManager");
			}
			if (!docApprove.isZZ_ID_Copy_Uploaded()) {
				return Msg.getMsg(getCtx(), "ZZ_ApproveUploadCopyID");
			}
			docApprove.setZZ_DocStatus(IDocApprove.ZZ_DOCSTATUS_Submitted);
			docApprove.setZZ_DocAction(IDocApprove.ZZ_DOCACTION_ApproveDoNotApprove);
			docApprove.setZZ_Date_Submitted(new Timestamp(System.currentTimeMillis()));
			
			Notifications.sendNotification(
					docApprove.getLine_Manager_ID(),
					getRecord_ID(),
					docApprove.getSubject(),
					docApprove.getMessage(),
					docApprove.getMessageID(),
					getCtx(),
					get_TrxName(),
					getTable_ID());
		} else if (docApprove.getZZ_DocAction().equals(IDocApprove.ZZ_DOCACTION_ApproveDoNotApprove) && 
				docApprove.getZZ_DocStatus().equals(IDocApprove.ZZ_DOCSTATUS_Submitted)) {
			if (pApproveRejLM.equals("Y")) {
				docApprove.setZZ_DocStatus(IDocApprove.ZZ_DOCSTATUS_InProgress);
				docApprove.setZZ_DocAction(IDocApprove.ZZ_DOCACTION_FinalApprovalDoNotApprove);
				docApprove.setZZ_Date_LM_Approved(new Timestamp(System.currentTimeMillis()));
				Notifications.sendNotificationToRole(
						IDocApprove.SNR_ADMIN_FIN_ROLE_ID,
						getRecord_ID(),
						docApprove.getSubject(),
						docApprove.getMessage(),
						docApprove.getMessageID(),
						getCtx(),
						get_TrxName(),
						getTable_ID());
			} else {
				docApprove.setZZ_DocStatus(IDocApprove.ZZ_DOCSTATUS_NotApprovedByLM);
				docApprove.setZZ_Date_Not_Approved_by_LM(new Timestamp(System.currentTimeMillis()));
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
		} else if (docApprove.getZZ_DocAction().equals(IDocApprove.ZZ_DOCACTION_FinalApprovalDoNotApprove) && 
				docApprove.getZZ_DocStatus().equals(IDocApprove.ZZ_DOCSTATUS_InProgress)) {
			if (pApproveRejSAF.equals("Y")) {
				docApprove.setZZ_DocStatus(IDocApprove.ZZ_DOCSTATUS_Approved);
				docApprove.setZZ_DocAction(IDocApprove.ZZ_DOCACTION_Complete);
				docApprove.setZZ_Date_Approved(new Timestamp(System.currentTimeMillis()));
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
				docApprove.setZZ_Date_Not_Approved_by_Snr_Adm_Fin(new Timestamp(System.currentTimeMillis()));
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
		} else if (docApprove.getZZ_DocAction().equals(IDocApprove.ZZ_DOCACTION_Complete) && 
				docApprove.getZZ_DocStatus().equals(IDocApprove.ZZ_DOCSTATUS_Approved)) {
			if (!docApprove.isZZ_AOR_Uploaded()) {
				return Msg.getMsg(getCtx(), "ZZ_ApproveUploadAOR");
			}
			docApprove.setZZ_DocStatus(IDocApprove.ZZ_DOCSTATUS_Completed);
			docApprove.setZZ_Date_Completed(new Timestamp(System.currentTimeMillis()));
		}
		if (!po.save()) {
			if (log.isLoggable(Level.FINE)) log.fine("Problem Saving.  Please contact Support");
			return Msg.getMsg(getCtx(), "ZZ_PO_SaveProblem");
		}

		return null;
	}
}
