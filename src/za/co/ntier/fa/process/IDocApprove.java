package za.co.ntier.fa.process;

import java.sql.Timestamp;

public interface IDocApprove {
	/** Approve/Do Not Approve = AP */
	public static final String ZZ_DOCACTION_ApproveDoNotApprove = "AP";
	/** Complete = CO */
	public static final String ZZ_DOCACTION_Complete = "CO";
	/** Final Approval/Do not Approve = FA */
	public static final String ZZ_DOCACTION_FinalApprovalDoNotApprove = "FA";
	/** Submit to Line Manager = SU */
	public static final String ZZ_DOCACTION_SubmitToLineManager = "SU";
	
	/** Approved = AP */
	public static final String ZZ_DOCSTATUS_Approved = "AP";
	/** Completed = CO */
	public static final String ZZ_DOCSTATUS_Completed = "CO";
	/** Draft = DR */
	public static final String ZZ_DOCSTATUS_Draft = "DR";
	/** In Progress = IP */
	public static final String ZZ_DOCSTATUS_InProgress = "IP";
	/** Not Approved by LM = NL */
	public static final String ZZ_DOCSTATUS_NotApprovedByLM = "NL";
	/** Not Approved by Snr Admin Finance = NS */
	public static final String ZZ_DOCSTATUS_NotApprovedBySnrAdminFinance = "NS";
	/** Submitted = SU */
	public static final String ZZ_DOCSTATUS_Submitted = "SU";
	
	public static final String DOCACTION_Approve = "AP";
	
	public final static int SNR_ADMIN_FIN_ROLE_ID = 1000003;
	
	public String getZZ_DocAction();
	public void setZZ_DocAction(String docAction);
	
	public void setZZ_DocStatus(String docStatus);
	public String getZZ_DocStatus();
	
	public Timestamp getZZ_Date_Submitted();
	public void setZZ_Date_Submitted(Timestamp submittDate);
	public void setZZ_Date_LM_Approved(Timestamp approveDate);
	public void setZZ_Date_Approved (Timestamp approveDate);
	public void setZZ_Date_Not_Approved_by_LM(Timestamp notApproveDate);
	public void setZZ_Date_Completed (Timestamp completeDate);
	public String getDocumentNo();
	public int getLine_Manager_ID();
	public void setZZ_Date_Not_Approved_by_Snr_Adm_Fin (Timestamp ZZ_Date_Not_Approved_by_Snr_Adm_Fin);
	public default boolean isZZ_ID_Copy_Uploaded() {
		return true;
	}
	public default boolean isZZ_AOR_Uploaded() {
		return true;
	}
	public void setZZ_Snr_Admin_Fin_ID(int ZZ_Snr_Admin_Fin_ID);
	public default String getSubject() {
		return "default subject";
	}
	
	public default String getMessage() {
		return "default message";
	}
	
	public default int getMessageID() {
		return 1000009;//TODO change to default message id
	}
	public int getCreatedBy();
}
