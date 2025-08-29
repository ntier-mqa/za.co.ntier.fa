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

/** Generated Interface for ZZ_AssetTransfer
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_ZZ_AssetTransfer 
{

    /** TableName=ZZ_AssetTransfer */
    public static final String Table_Name = "ZZ_AssetTransfer";

    /** AD_Table_ID=1000015 */
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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException;

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

    /** Column name ZZAssetLocationTo_ID */
    public static final String COLUMNNAME_ZZAssetLocationTo_ID = "ZZAssetLocationTo_ID";

	/** Set Asset Location To	  */
	public void setZZAssetLocationTo_ID (int ZZAssetLocationTo_ID);

	/** Get Asset Location To	  */
	public int getZZAssetLocationTo_ID();

	public I_ZZAssetLocation getZZAssetLocationTo() throws RuntimeException;

    /** Column name ZZAssetLocation_ID */
    public static final String COLUMNNAME_ZZAssetLocation_ID = "ZZAssetLocation_ID";

	/** Set Asset Location	  */
	public void setZZAssetLocation_ID (int ZZAssetLocation_ID);

	/** Get Asset Location	  */
	public int getZZAssetLocation_ID();

	public I_ZZAssetLocation getZZAssetLocation() throws RuntimeException;

    /** Column name ZZAssetRegionTo_ID */
    public static final String COLUMNNAME_ZZAssetRegionTo_ID = "ZZAssetRegionTo_ID";

	/** Set Asset Region To	  */
	public void setZZAssetRegionTo_ID (int ZZAssetRegionTo_ID);

	/** Get Asset Region To	  */
	public int getZZAssetRegionTo_ID();

	public I_ZZAssetRegion getZZAssetRegionTo() throws RuntimeException;

    /** Column name ZZAssetRegion_ID */
    public static final String COLUMNNAME_ZZAssetRegion_ID = "ZZAssetRegion_ID";

	/** Set Asset Region	  */
	public void setZZAssetRegion_ID (int ZZAssetRegion_ID);

	/** Get Asset Region	  */
	public int getZZAssetRegion_ID();

	public I_ZZAssetRegion getZZAssetRegion() throws RuntimeException;

    /** Column name ZZAssetVerifyBatch_ID */
    public static final String COLUMNNAME_ZZAssetVerifyBatch_ID = "ZZAssetVerifyBatch_ID";

	/** Set Asset Verify Batch	  */
	public void setZZAssetVerifyBatch_ID (int ZZAssetVerifyBatch_ID);

	/** Get Asset Verify Batch	  */
	public int getZZAssetVerifyBatch_ID();

	public I_ZZAssetVerifyBatch getZZAssetVerifyBatch() throws RuntimeException;

    /** Column name ZZ_AD_UserTo_ID */
    public static final String COLUMNNAME_ZZ_AD_UserTo_ID = "ZZ_AD_UserTo_ID";

	/** Set User/Contact To.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setZZ_AD_UserTo_ID (int ZZ_AD_UserTo_ID);

	/** Get User/Contact To.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getZZ_AD_UserTo_ID();

	public org.compiere.model.I_AD_User getZZ_AD_UserTo() throws RuntimeException;

    /** Column name ZZ_AssetTransfer_ID */
    public static final String COLUMNNAME_ZZ_AssetTransfer_ID = "ZZ_AssetTransfer_ID";

	/** Set Asset Transfer	  */
	public void setZZ_AssetTransfer_ID (int ZZ_AssetTransfer_ID);

	/** Get Asset Transfer	  */
	public int getZZ_AssetTransfer_ID();

    /** Column name ZZ_AssetTransfer_UU */
    public static final String COLUMNNAME_ZZ_AssetTransfer_UU = "ZZ_AssetTransfer_UU";

	/** Set Asset Transfer UU	  */
	public void setZZ_AssetTransfer_UU (String ZZ_AssetTransfer_UU);

	/** Get Asset Transfer UU	  */
	public String getZZ_AssetTransfer_UU();

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

    /** Column name ZZ_Snr_Admin_Fin_ID */
    public static final String COLUMNNAME_ZZ_Snr_Admin_Fin_ID = "ZZ_Snr_Admin_Fin_ID";

	/** Set Snr Admin Finance User	  */
	public void setZZ_Snr_Admin_Fin_ID (int ZZ_Snr_Admin_Fin_ID);

	/** Get Snr Admin Finance User	  */
	public int getZZ_Snr_Admin_Fin_ID();

	public org.compiere.model.I_AD_User getZZ_Snr_Admin_Fin() throws RuntimeException;
}
