/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package za.co.ntier.fa.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for ZZ_Asset_Disposal
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_ZZ_Asset_Disposal 
{

    /** TableName=ZZ_Asset_Disposal */
    public static final String Table_Name = "ZZ_Asset_Disposal";

    /** AD_Table_ID=1000011 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Tenant.
	  * Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within tenant
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within tenant
	  */
	public int getAD_Org_ID();

    /** Column name A_Asset_Disposed_ID */
    public static final String COLUMNNAME_A_Asset_Disposed_ID = "A_Asset_Disposed_ID";

	/** Set Asset Disposed	  */
	public void setA_Asset_Disposed_ID (int A_Asset_Disposed_ID);

	/** Get Asset Disposed	  */
	public int getA_Asset_Disposed_ID();

	public org.compiere.model.I_A_Asset_Disposed getA_Asset_Disposed() throws RuntimeException;

    /** Column name A_Asset_ID */
    public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

	/** Set Asset.
	  * Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID);

	/** Get Asset.
	  * Asset used internally or by customers
	  */
	public int getA_Asset_ID();

	public org.compiere.model.I_A_Asset getA_Asset() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DateAcct */
    public static final String COLUMNNAME_DateAcct = "DateAcct";

	/** Set Account Date.
	  * Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct);

	/** Get Account Date.
	  * Accounting Date
	  */
	public Timestamp getDateAcct();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name Line_Manager_ID */
    public static final String COLUMNNAME_Line_Manager_ID = "Line_Manager_ID";

	/** Set Line Manager	  */
	public void setLine_Manager_ID (int Line_Manager_ID);

	/** Get Line Manager	  */
	public int getLine_Manager_ID();

	public org.compiere.model.I_AD_User getLine_Manager() throws RuntimeException;

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();

    /** Column name ZZ_AllowLineManageApproved */
    public static final String COLUMNNAME_ZZ_AllowLineManageApproved = "ZZ_AllowLineManageApproved";

	/** Set Allow Line Manage Approved.
	  * Choose to allow line manage join to approved workfllow
	  */
	public void setZZ_AllowLineManageApproved (boolean ZZ_AllowLineManageApproved);

	/** Get Allow Line Manage Approved.
	  * Choose to allow line manage join to approved workfllow
	  */
	public boolean isZZ_AllowLineManageApproved();

    /** Column name ZZ_AllowSnrAdminFinanceApproved */
    public static final String COLUMNNAME_ZZ_AllowSnrAdminFinanceApproved = "ZZ_AllowSnrAdminFinanceApproved";

	/** Set Allow Snr Admin Finance Approved.
	  * Choose to allow Snr Admin Finance join to approved workfllow
	  */
	public void setZZ_AllowSnrAdminFinanceApproved (boolean ZZ_AllowSnrAdminFinanceApproved);

	/** Get Allow Snr Admin Finance Approved.
	  * Choose to allow Snr Admin Finance join to approved workfllow
	  */
	public boolean isZZ_AllowSnrAdminFinanceApproved();

    /** Column name ZZ_Asset_Disposal_ID */
    public static final String COLUMNNAME_ZZ_Asset_Disposal_ID = "ZZ_Asset_Disposal_ID";

	/** Set Asset Disposal	  */
	public void setZZ_Asset_Disposal_ID (int ZZ_Asset_Disposal_ID);

	/** Get Asset Disposal	  */
	public int getZZ_Asset_Disposal_ID();

    /** Column name ZZ_Asset_Disposal_UU */
    public static final String COLUMNNAME_ZZ_Asset_Disposal_UU = "ZZ_Asset_Disposal_UU";

	/** Set ZZ_Asset_Disposal_UU	  */
	public void setZZ_Asset_Disposal_UU (String ZZ_Asset_Disposal_UU);

	/** Get ZZ_Asset_Disposal_UU	  */
	public String getZZ_Asset_Disposal_UU();

    /** Column name ZZ_Date_Approved */
    public static final String COLUMNNAME_ZZ_Date_Approved = "ZZ_Date_Approved";

	/** Set Date Approved	  */
	public void setZZ_Date_Approved (Timestamp ZZ_Date_Approved);

	/** Get Date Approved	  */
	public Timestamp getZZ_Date_Approved();

    /** Column name ZZ_Date_Completed */
    public static final String COLUMNNAME_ZZ_Date_Completed = "ZZ_Date_Completed";

	/** Set Date Completed	  */
	public void setZZ_Date_Completed (Timestamp ZZ_Date_Completed);

	/** Get Date Completed	  */
	public Timestamp getZZ_Date_Completed();

    /** Column name ZZ_Date_LM_Approved */
    public static final String COLUMNNAME_ZZ_Date_LM_Approved = "ZZ_Date_LM_Approved";

	/** Set Date LM Approved	  */
	public void setZZ_Date_LM_Approved (Timestamp ZZ_Date_LM_Approved);

	/** Get Date LM Approved	  */
	public Timestamp getZZ_Date_LM_Approved();

    /** Column name ZZ_Date_Not_Approved_by_LM */
    public static final String COLUMNNAME_ZZ_Date_Not_Approved_by_LM = "ZZ_Date_Not_Approved_by_LM";

	/** Set Date Not Approved by LM	  */
	public void setZZ_Date_Not_Approved_by_LM (Timestamp ZZ_Date_Not_Approved_by_LM);

	/** Get Date Not Approved by LM	  */
	public Timestamp getZZ_Date_Not_Approved_by_LM();

    /** Column name ZZ_Date_Not_Approved_by_Snr_Adm_Fin */
    public static final String COLUMNNAME_ZZ_Date_Not_Approved_by_Snr_Adm_Fin = "ZZ_Date_Not_Approved_by_Snr_Adm_Fin";

	/** Set Date Not Approved by Snr Admin Finance	  */
	public void setZZ_Date_Not_Approved_by_Snr_Adm_Fin (Timestamp ZZ_Date_Not_Approved_by_Snr_Adm_Fin);

	/** Get Date Not Approved by Snr Admin Finance	  */
	public Timestamp getZZ_Date_Not_Approved_by_Snr_Adm_Fin();

    /** Column name ZZ_Date_Submitted */
    public static final String COLUMNNAME_ZZ_Date_Submitted = "ZZ_Date_Submitted";

	/** Set Date Submitted	  */
	public void setZZ_Date_Submitted (Timestamp ZZ_Date_Submitted);

	/** Get Date Submitted	  */
	public Timestamp getZZ_Date_Submitted();

    /** Column name ZZ_DocAction */
    public static final String COLUMNNAME_ZZ_DocAction = "ZZ_DocAction";

	/** Set Document Action	  */
	public void setZZ_DocAction (String ZZ_DocAction);

	/** Get Document Action	  */
	public String getZZ_DocAction();

    /** Column name ZZ_DocStatus */
    public static final String COLUMNNAME_ZZ_DocStatus = "ZZ_DocStatus";

	/** Set Document Status	  */
	public void setZZ_DocStatus (String ZZ_DocStatus);

	/** Get Document Status	  */
	public String getZZ_DocStatus();

    /** Column name ZZ_FinalWorkflowStateValue */
    public static final String COLUMNNAME_ZZ_FinalWorkflowStateValue = "ZZ_FinalWorkflowStateValue";

	/** Set Final Workflow State Value.
	  * Value set to ZZ_DocStatus when reach to end of approve workflow
	  */
	public void setZZ_FinalWorkflowStateValue (String ZZ_FinalWorkflowStateValue);

	/** Get Final Workflow State Value.
	  * Value set to ZZ_DocStatus when reach to end of approve workflow
	  */
	public String getZZ_FinalWorkflowStateValue();

    /** Column name ZZ_Snr_Admin_Fin_ID */
    public static final String COLUMNNAME_ZZ_Snr_Admin_Fin_ID = "ZZ_Snr_Admin_Fin_ID";

	/** Set Snr Admin Finance User	  */
	public void setZZ_Snr_Admin_Fin_ID (int ZZ_Snr_Admin_Fin_ID);

	/** Get Snr Admin Finance User	  */
	public int getZZ_Snr_Admin_Fin_ID();

	public org.compiere.model.I_AD_User getZZ_Snr_Admin_Fin() throws RuntimeException;
}
