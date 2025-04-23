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
package za.co.ntier.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for ZZConfigDocApproveFlow
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_ZZConfigDocApproveFlow 
{

    /** TableName=ZZConfigDocApproveFlow */
    public static final String Table_Name = "ZZConfigDocApproveFlow";

    /** AD_Table_ID=1000019 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException;

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

    /** Column name ZZConfigDocApproveFlow_ID */
    public static final String COLUMNNAME_ZZConfigDocApproveFlow_ID = "ZZConfigDocApproveFlow_ID";

	/** Set Config Document Approve Flow.
	  * Configuration approve flow for a document
	  */
	public void setZZConfigDocApproveFlow_ID (int ZZConfigDocApproveFlow_ID);

	/** Get Config Document Approve Flow.
	  * Configuration approve flow for a document
	  */
	public int getZZConfigDocApproveFlow_ID();

    /** Column name ZZConfigDocApproveFlow_UU */
    public static final String COLUMNNAME_ZZConfigDocApproveFlow_UU = "ZZConfigDocApproveFlow_UU";

	/** Set ZZConfigDocApproveFlow_UU	  */
	public void setZZConfigDocApproveFlow_UU (String ZZConfigDocApproveFlow_UU);

	/** Get ZZConfigDocApproveFlow_UU	  */
	public String getZZConfigDocApproveFlow_UU();

    /** Column name ZZMailLineApproved_ID */
    public static final String COLUMNNAME_ZZMailLineApproved_ID = "ZZMailLineApproved_ID";

	/** Set Mail Template Line Manage Approved.
	  * Email template sent to submitor who do submit document to inform him document is approved by line manage
	  */
	public void setZZMailLineApproved_ID (int ZZMailLineApproved_ID);

	/** Get Mail Template Line Manage Approved.
	  * Email template sent to submitor who do submit document to inform him document is approved by line manage
	  */
	public int getZZMailLineApproved_ID();

	public org.compiere.model.I_R_MailText getZZMailLineApproved() throws RuntimeException;

    /** Column name ZZMailLineReject_ID */
    public static final String COLUMNNAME_ZZMailLineReject_ID = "ZZMailLineReject_ID";

	/** Set Mail Template Line Manage Reject.
	  * Email template sent to submitor who do submit document to inform him document is reject by line manage
	  */
	public void setZZMailLineReject_ID (int ZZMailLineReject_ID);

	/** Get Mail Template Line Manage Reject.
	  * Email template sent to submitor who do submit document to inform him document is reject by line manage
	  */
	public int getZZMailLineReject_ID();

	public org.compiere.model.I_R_MailText getZZMailLineReject() throws RuntimeException;

    /** Column name ZZMailRequestLine_ID */
    public static final String COLUMNNAME_ZZMailRequestLine_ID = "ZZMailRequestLine_ID";

	/** Set Mail Template Request Line Manage Approve.
	  * Email template sent to line manage to notify him has document need him review and approve
	  */
	public void setZZMailRequestLine_ID (int ZZMailRequestLine_ID);

	/** Get Mail Template Request Line Manage Approve.
	  * Email template sent to line manage to notify him has document need him review and approve
	  */
	public int getZZMailRequestLine_ID();

	public org.compiere.model.I_R_MailText getZZMailRequestLine() throws RuntimeException;

    /** Column name ZZMailRequestSnr_ID */
    public static final String COLUMNNAME_ZZMailRequestSnr_ID = "ZZMailRequestSnr_ID";

	/** Set Mail Template Request Snr Admin Finance Manage Approve.
	  * Email template sent to Snr Admin Finance to notify him has document need him review and approve, this email can sent when line manage do approve or user submit up to workflow
	  */
	public void setZZMailRequestSnr_ID (int ZZMailRequestSnr_ID);

	/** Get Mail Template Request Snr Admin Finance Manage Approve.
	  * Email template sent to Snr Admin Finance to notify him has document need him review and approve, this email can sent when line manage do approve or user submit up to workflow
	  */
	public int getZZMailRequestSnr_ID();

	public org.compiere.model.I_R_MailText getZZMailRequestSnr() throws RuntimeException;

    /** Column name ZZMailSnrApproved_ID */
    public static final String COLUMNNAME_ZZMailSnrApproved_ID = "ZZMailSnrApproved_ID";

	/** Set Mail Template Snr Admin Finance Manage Approved.
	  * Email template sent to Submitor when Snr Admin Finance Approved document
	  */
	public void setZZMailSnrApproved_ID (int ZZMailSnrApproved_ID);

	/** Get Mail Template Snr Admin Finance Manage Approved.
	  * Email template sent to Submitor when Snr Admin Finance Approved document
	  */
	public int getZZMailSnrApproved_ID();

	public org.compiere.model.I_R_MailText getZZMailSnrApproved() throws RuntimeException;

    /** Column name ZZMailSnrRejectLine_ID */
    public static final String COLUMNNAME_ZZMailSnrRejectLine_ID = "ZZMailSnrRejectLine_ID";

	/** Set Mail Template Snr Admin Finance Manage Reject To Line Manage.
	  * Email template sent to line manage when Snr Admin Finance reject document
	  */
	public void setZZMailSnrRejectLine_ID (int ZZMailSnrRejectLine_ID);

	/** Get Mail Template Snr Admin Finance Manage Reject To Line Manage.
	  * Email template sent to line manage when Snr Admin Finance reject document
	  */
	public int getZZMailSnrRejectLine_ID();

	public org.compiere.model.I_R_MailText getZZMailSnrRejectLine() throws RuntimeException;

    /** Column name ZZMailSnrRejectSubmitor_ID */
    public static final String COLUMNNAME_ZZMailSnrRejectSubmitor_ID = "ZZMailSnrRejectSubmitor_ID";

	/** Set Mail Template Snr Admin Finance Manage Reject To Submitor.
	  * Email template sent to Submitor when Snr Admin Finance reject document
	  */
	public void setZZMailSnrRejectSubmitor_ID (int ZZMailSnrRejectSubmitor_ID);

	/** Get Mail Template Snr Admin Finance Manage Reject To Submitor.
	  * Email template sent to Submitor when Snr Admin Finance reject document
	  */
	public int getZZMailSnrRejectSubmitor_ID();

	public org.compiere.model.I_R_MailText getZZMailSnrRejectSubmitor() throws RuntimeException;

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
}
