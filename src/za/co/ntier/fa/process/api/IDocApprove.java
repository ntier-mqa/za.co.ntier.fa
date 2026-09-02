package za.co.ntier.fa.process.api;

import java.sql.Timestamp;

import org.compiere.model.I_R_MailText;
import org.compiere.model.MMailText;
import org.compiere.model.PO;

import za.co.ntier.model.MZZConfigDocApproveFlow;

public interface IDocApprove {
	/** Approve/Do Not Approve  = AP */
	public static final String ZZ_DOCACTION_ApproveDoNotApprove = "AP";
	/** Complete = CO */
	public static final String ZZ_DOCACTION_Complete = "CO";
	/** Final Approval/Do not Approve = FA */
	public static final String ZZ_DOCACTION_FinalApprovalDoNotApprove = "FA";
	/** Submit to Line Manager = SU */
	public static final String ZZ_DOCACTION_SubmitToLineManager = "SU";
	/** Submit to Line Manager = SU */
	public static final String ZZ_DOCACTION_SubmitToSnrAdminFinance = "SS";
	public static final String ZZ_DOCACTION_SubmitToSDLFinanceMgr = "SD";
	public static final String ZZ_DOCACTION_SubmitToRecommender = "SR";
	public static final String ZZ_DOCACTION_SubmitToSnrMgrLP = "SL";
	
	/** Submit to Manager Finance Consumables = SC */
	public static final String ZZ_DOCACTION_SubmitToManagerFinanceConsumables = "SC";
	
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
	public static final String ZZ_DOCSTATUS_NotApprovedBySDL = "ND";
	/** Not Approved by Snr Admin Finance = NS */
	public static final String ZZ_DOCSTATUS_NotApprovedBySnrAdminFinance = "NS";
	public static final String ZZ_DOCSTATUS_NotApprovedBySDLFinanceMgr = "ND";
	/** Submitted = SU */
	public static final String ZZ_DOCSTATUS_Submitted = "SU";
	
	public static final String DOCACTION_Approve = "AP";
	
	/** Approved By Manager Finance Consumables = AC */
	public static final String ZZ_DOCSTATUS_ApprovedByManagerFinanceConsumables = "AC";
	/** Not Approved By Manager Finance Consumables = NC */
	public static final String ZZ_DOCSTATUS_NotApprovedByManagerFinanceConsumables = "NC";
	/** Submitted to Manager Finance Consumables = SC */
	public static final String ZZ_DOCSTATUS_SubmittedToManagerFinanceConsumables = "SC";
	
	public static final String ZZ_DOCSTATUS_SubmittedToSDLFinanceMgr = "SD";

	/** Submitted to Snr Admin Finance = SA */
	public static final String ZZ_DOCSTATUS_SubmittedToSnrAdminFinance = "SA";
	
	public static final String ZZ_DOCSTATUS_NOT_RECOMMENDED = "NR";
	
	public static final int SNR_ADMIN_FIN_ROLE_ID = 1000003;
	
	public static final int SDL_FIN_MGR_ROLE_ID = 1000005;
	
	public static final int MGR_ADMIN_FINANCE_ROLE_ID = 1000004;
	
	public static final int IT_MGR_ROLE_ID = 1000015;
	
	public static final int IT_ADMIN_ROLE_ID = 1000016;
	

	// ACTIONS
	public static final String ZZ_DOCACTION_SubmitOpenWindow = "SU";   // reuse SU for "Submit"
	public static final String ZZ_DOCACTION_Recommend       = "RE";   // Recommender clicks "Recommend"
	public static final String ZZ_DOCACTION_ApproveExec     = "AE";   // Exec (COO/EMCS) clicks "Approve"

	// STATUSES

	public static final String ZZ_DOCSTATUS_Recommended  = "RC";  // <-- NEW
	
	public static final String ZZ_DOCSTATUS_NotApproved  = "NA";  // generic catch-all (optional)

	// OPTIONAL (role ids from your comment)
	public static final int ROLE_CO0_ID        = 1000026;
	public static final int ROLE_EMCS_ID       = 1000027;
	public static final int ROLE_SNR_MGR_SPU   = 1000021;
	public static final int ROLE_MANAGER_LP_OPS       = 1000022;  
	public static final int ROLE_MANAGER_LP_PROJECTS       = 1000032;  
	public static final int ROLE_MANAGER_SPU      = 1000029;
	public static final int ROLE_MANAGER_SRU      = 1000037;
	public static final int ROLE_MANAGER_QA      = 1000034;
	public static final int ROLE_SNR_MANAGER_LP_OPS   = 1000019; 
	public static final int ROLE_SNR_MANAGER_QA   = 1000031; 
	public static final int ROLE_SNR_MANAGER_SRU   = 1000036; 
	public static final int ROLE_SNR_MANAGER_LP_PROJECTS   = 1000030; 
	public static final int[] ROLES_MANAGER_LP      = {1000022,1000032};
	public static final int[] ROLES_SNR_MANAGER_LP      = {1000019,1000030,1000036,1000031}; // snr mgrs ops, projects , SRU , QA
	public static final int[] ORGS_SNR_MANAGER_LP       = {1000010,1000013,1000015,1000009};  // LP Ops , LP Projects, SRU , QA
	public static final String[] ZZ_DOCACTIONS          = {"SO","SP","SS","SQ"};
	
	
	public String getZZ_DocAction();
	public void setZZ_DocAction(String docAction);
	
	public void setZZ_DocStatus(String docStatus);
	public String getZZ_DocStatus();
	public String getDocumentNo();
	
	/**
	 * set time when user submits the document to approve
	 * @param submittDate
	 */
	public void setZZ_Date_Submitted(Timestamp submittDate);
	/**
	 * line manage (in case ZZ_AllowLineManageApproved = Y)
	 * @return
	 */
	public int getLine_Manager_ID();
	/**
	 * get time when user submits the document to approve
	 * @return
	 */
	public Timestamp getZZ_Date_Submitted();
	/**
	 * time when line manager approves the document (in case ZZ_AllowLineManageApproved = Y)
	 * @param approveDate
	 */
	public void setZZ_Date_LM_Approved(Timestamp approveDate);

	/**
	 * time when line manager rejects the document (in case ZZ_AllowLineManageApproved = Y)
	 * @param notApproveDate
	 */
	public void setZZ_Date_Not_Approved_by_LM(Timestamp notApproveDate);

	public int getZZ_Snr_Admin_Fin_ID();
	
	/**
	 * when SnrAdminFinance approve document, Set his id here (in case ZZ_AllowSnrAdminFinanceApproved = Y)
	 * @param ZZ_Snr_Admin_Fin_ID
	 */
	public void setZZ_Snr_Admin_Fin_ID(int ZZ_Snr_Admin_Fin_ID);
	/**
	 * set time when Snr Admin Finance approves the document (in case ZZ_AllowSnrAdminFinanceApproved = Y)
	 * @param approveDate
	 */
	public void setZZ_Date_Approved (Timestamp approveDate);
	/**
	 * set time when Snr Admin Finance rejects the document (in case ZZ_AllowSnrAdminFinance = Y)
	 * @param ZZ_Date_Not_Approved_by_Snr_Adm_Fin
	 */
	public void setZZ_Date_Not_Approved_by_Snr_Adm_Fin (Timestamp ZZ_Date_Not_Approved_by_Snr_Adm_Fin);
	
	/**
	 * set time when document is completed (up to document type, doc can be auto completed after approved or manual do by submitter)
	 * @param completeDate
	 */
	public void setZZ_Date_Completed (Timestamp completeDate);
	
	/** Set Date Manager Finance Consumables	  */
	public void setZZ_Date_MFC_Approved (Timestamp ZZ_Date_MFC_Approved);
	
	/** Get Date Manager Finance Not Approved	  */
	public Timestamp getZZ_Date_MFC_Not_Approved();
	
	/** Get Allow Mgr Finance Consumables Approval	  */
	public boolean isZZ_AllowMgrFinConsumablesApproval();
	
	/** Set Date Manager Finance Not Approved	  */
	public void setZZ_Date_MFC_Not_Approved (Timestamp ZZ_Date_MFC_Not_Approved);
	
	/** Set Date Approved By SDL Finance Manager	  */
	public void setZZ_Date_SDL_Approved (Timestamp ZZ_Date_SDL_Approved);

	/** Get Date Approved By SDL Finance Manager	  */
	public Timestamp getZZ_Date_SDL_Approved();

	/** Set Date Not Approved By the SDL Finance Mgr	  */
	public void setZZ_Date_SDL_Not_Approved (Timestamp ZZ_Date_SDL_Not_Approved);

	/** Get Date Not Approved By the SDL Finance Mgr	  */
	public Timestamp getZZ_Date_SDL_Not_Approved();

	/** Set Date Account Created By IT Admin	  */
	public void setZZ_Date_Account_Created (Timestamp ZZ_Date_Account_Created);

	/** Get Date Account Created By IT Admin	  */
	public Timestamp getZZ_Date_Account_Created();

	/** Set Date Approved by IT Manager	  */
	public void setZZ_Date_IT_Manager_Approved (Timestamp ZZ_Date_IT_Manager_Approved);

	/** Get Date Approved by IT Manager	  */
	public Timestamp getZZ_Date_IT_Manager_Approved();

	/** Set Date Rejected by IT Manager	  */
	public void setZZ_Date_IT_Manager_Rejected (Timestamp ZZ_Date_IT_Manager_Rejected);

	/** Get Date Rejected by IT Manager	  */
	public Timestamp getZZ_Date_IT_Manager_Rejected();

	/** Set Manager Approved	  */
	public void setZZ_Date_Manager_Approved (Timestamp ZZ_Date_Manager_Approved);

	/** Get Manager Approved	  */
	public Timestamp getZZ_Date_Manager_Approved();

	/** Set Date Rejected By Manager	  */
	public void setZZ_Date_Manager_Rejected (Timestamp ZZ_Date_Manager_Rejected);

	/** Get Date Rejected By Manager	  */
	public Timestamp getZZ_Date_Manager_Rejected();

	public void setZZ_IT_Admin_ID (int ZZ_IT_Admin_ID);

	/** Get IT Admin	  */
	public int getZZ_IT_Admin_ID();

	public org.compiere.model.I_AD_User getZZ_IT_Admin() throws RuntimeException;

	/** Set IT Manager 	  */
	public void setZZ_IT_Manager_ID (int ZZ_IT_Manager_ID);

	/** Get IT Manager 	  */
	public int getZZ_IT_Manager_ID();

	public org.compiere.model.I_AD_User getZZ_IT_Manager() throws RuntimeException;


	/** Set Manager	  */
	public void setZZ_Manager_ID (int ZZ_Manager_ID);

	/** Get Manager	  */
	public int getZZ_Manager_ID();
	/**
	 * get user who created/summit this document
	 * 
	 * @return
	 */
	public int getCreatedBy();
	
	public int get_Table_ID();	

	/** Set Manager Finance Consumables	  */
	public void setZZ_Mgr_Fin_Consumables_ID (int ZZ_Mgr_Fin_Consumables_ID);

	/** Get Manager Finance Consumables	  */
	public int getZZ_Mgr_Fin_Consumables_ID();

	public org.compiere.model.I_AD_User getZZ_Mgr_Fin_Consumables() throws RuntimeException;
	
	/** Set SDL Finance Mgr	  */
	public void setZZ_SDL_Fin_Mgr_ID (int ZZ_SDL_Fin_Mgr_ID);

	/** Get SDL Finance Mgr	  */
	public int getZZ_SDL_Fin_Mgr_ID();
	
	public org.compiere.model.I_AD_User getZZ_SDL_Fin_Mgr() throws RuntimeException;
	
	/** Set Recommender	  */
	public void setZZ_Recommender_ID (int ZZ_Recommender_ID);

	/** Get Recommender	  */
	public int getZZ_Recommender_ID();
	
	/** Set Exec Approver	  */
	public void setZZ_Exec_Approver_ID (int ZZ_Exec_Approver_ID);

	/** Get Exec Approver	  */
	public int getZZ_Exec_Approver_ID();

	
	/** Set Start Date.
	  * First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate);

	/** Get Start Date.
	  * First effective day (inclusive)
	  */
	public Timestamp getStartDate();

	/** Set End Date.
	  * Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate);

	/** Get End Date.
	  * Last effective date (inclusive)
	  */
	public Timestamp getEndDate();
	
	
	/** Set Date Recommended	  */
	public void setZZ_Date_Recommended (Timestamp ZZ_Date_Recommended);

	/** Get Date Recommended	  */
	public Timestamp getZZ_Date_Recommended();
	
	/** Set Date Not Approved	  */
	public void setZZ_Date_Not_Approved (Timestamp ZZ_Date_Not_Approved);

	/** Get Date Not Approved	  */
	public Timestamp getZZ_Date_Not_Approved();
	
	/** Set Date Not Recommended	  */
	public void setZZ_Date_Not_Recommended (Timestamp ZZ_Date_Not_Recommended);

	/** Get Date Not Recommended	  */
	public Timestamp getZZ_Date_Not_Recommended();
	
	
	/** Set Snr Mgr LP	  */
	public void setZZ_Snr_Mgr_LP_ID (int ZZ_Snr_Mgr_LP_ID);

	/** Get Snr Mgr LP	  */
	public int getZZ_Snr_Mgr_LP_ID();
	
	public void setAD_Org_ID (int AD_Org_ID);
	
	/** Set Submitted By	  */
	public void setZZ_Submitter_ID (int ZZ_Submitter_ID);

	/** Get Submitted By	  */
	public int getZZ_Submitter_ID();
	
	

	/** Get Unit.
	  * Organizational entity within tenant
	  */
	public int getAD_Org_ID();
	
	public default boolean isZZ_AllowLineManageApproved() {
		return MZZConfigDocApproveFlow.getByDocTableID(get_Table_ID()).isZZ_AllowLineManageApproved();
	}

	public default  boolean isZZ_AllowSnrAdminFinanceApproved() {
		return MZZConfigDocApproveFlow.getByDocTableID(get_Table_ID()).isZZ_AllowSnrAdminFinanceApproved();
	}
	
	public default String getZZ_FinalWorkflowStateValue() {
		return MZZConfigDocApproveFlow.getByDocTableID(get_Table_ID()).getZZ_FinalWorkflowStateValue();
	}
	
	public default MMailText getZZMailLineApproved() {
		return setPOForMail(MZZConfigDocApproveFlow.getByDocTableID(get_Table_ID()).getZZMailLineApproved());
	}
	
	public default MMailText getZZMailLineReject() {
		return setPOForMail(MZZConfigDocApproveFlow.getByDocTableID(get_Table_ID()).getZZMailLineReject());
	}

	public default MMailText getZZMailRequestLine() {
		return setPOForMail(MZZConfigDocApproveFlow.getByDocTableID(get_Table_ID()).getZZMailRequestLine());
	}
	
	public default MMailText getZZMailRequestSnr() {
		return setPOForMail(MZZConfigDocApproveFlow.getByDocTableID(get_Table_ID()).getZZMailRequestSnr());
	}

	public default MMailText getZZMailRequestFCM() {
		return setPOForMail(MZZConfigDocApproveFlow.getByDocTableID(get_Table_ID()).getZZMailRequestFCM());
	}
	
	public default MMailText getZZMailSnrApproved() {
		return setPOForMail(MZZConfigDocApproveFlow.getByDocTableID(get_Table_ID()).getZZMailSnrApproved());
	}
	
	public default MMailText getZZMailSnrRejectLine() {
		return setPOForMail(MZZConfigDocApproveFlow.getByDocTableID(get_Table_ID()).getZZMailSnrRejectLine());
	}
	
	public default MMailText getZZMailSnrRejectSubmitor() {
		return setPOForMail(MZZConfigDocApproveFlow.getByDocTableID(get_Table_ID()).getZZMailSnrRejectSubmitor());
	}
	
	public default MMailText setPOForMail(I_R_MailText mailTemplate) {
		MMailText mail = (MMailText)mailTemplate;
		mail.setPO((PO)this);
		return mail;
	}
}
