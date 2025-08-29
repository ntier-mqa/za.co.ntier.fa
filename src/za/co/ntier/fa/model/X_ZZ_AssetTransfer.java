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
/** Generated Model - DO NOT CHANGE */
package za.co.ntier.fa.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for ZZ_AssetTransfer
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="ZZ_AssetTransfer")
public class X_ZZ_AssetTransfer extends PO implements I_ZZ_AssetTransfer, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250610L;

    /** Standard Constructor */
    public X_ZZ_AssetTransfer (Properties ctx, int ZZ_AssetTransfer_ID, String trxName)
    {
      super (ctx, ZZ_AssetTransfer_ID, trxName);
      /** if (ZZ_AssetTransfer_ID == 0)
        {
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setZZ_AssetTransfer_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_ZZ_AssetTransfer (Properties ctx, int ZZ_AssetTransfer_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, ZZ_AssetTransfer_ID, trxName, virtualColumns);
      /** if (ZZ_AssetTransfer_ID == 0)
        {
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setZZ_AssetTransfer_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_ZZ_AssetTransfer (Properties ctx, String ZZ_AssetTransfer_UU, String trxName)
    {
      super (ctx, ZZ_AssetTransfer_UU, trxName);
      /** if (ZZ_AssetTransfer_UU == null)
        {
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setZZ_AssetTransfer_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_ZZ_AssetTransfer (Properties ctx, String ZZ_AssetTransfer_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, ZZ_AssetTransfer_UU, trxName, virtualColumns);
      /** if (ZZ_AssetTransfer_UU == null)
        {
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setZZ_AssetTransfer_ID (0);
        } */
    }

    /** Load Constructor */
    public X_ZZ_AssetTransfer (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_ZZ_AssetTransfer[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
	{
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_ID)
			.getPO(getAD_User_ID(), get_TrxName());
	}

	/** Set User/Contact.
		@param AD_User_ID User within the system - Internal or Business Partner Contact
	*/
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			set_Value (COLUMNNAME_AD_User_ID, null);
		else
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_A_Asset getA_Asset() throws RuntimeException
	{
		return (org.compiere.model.I_A_Asset)MTable.get(getCtx(), org.compiere.model.I_A_Asset.Table_ID)
			.getPO(getA_Asset_ID(), get_TrxName());
	}

	/** Set Asset.
		@param A_Asset_ID Asset used internally or by customers
	*/
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID < 1)
			set_Value (COLUMNNAME_A_Asset_ID, null);
		else
			set_Value (COLUMNNAME_A_Asset_ID, Integer.valueOf(A_Asset_ID));
	}

	/** Get Asset.
		@return Asset used internally or by customers
	  */
	public int getA_Asset_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Account Date.
		@param DateAcct Accounting Date
	*/
	public void setDateAcct (Timestamp DateAcct)
	{
		set_Value (COLUMNNAME_DateAcct, DateAcct);
	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct()
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
	}

	/** Set Description.
		@param Description Optional short description of the record
	*/
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription()
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Document No.
		@param DocumentNo Document sequence number of the document
	*/
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo()
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	public org.compiere.model.I_AD_User getLine_Manager() throws RuntimeException
	{
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_ID)
			.getPO(getLine_Manager_ID(), get_TrxName());
	}

	/** Set Line Manager.
		@param Line_Manager_ID Line Manager
	*/
	public void setLine_Manager_ID (int Line_Manager_ID)
	{
		if (Line_Manager_ID < 1)
			set_Value (COLUMNNAME_Line_Manager_ID, null);
		else
			set_Value (COLUMNNAME_Line_Manager_ID, Integer.valueOf(Line_Manager_ID));
	}

	/** Get Line Manager.
		@return Line Manager	  */
	public int getLine_Manager_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line_Manager_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Search Key.
		@param Value Search key for the record in the format required - must be unique
	*/
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue()
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

	public I_ZZAssetLocation getZZAssetLocationTo() throws RuntimeException
	{
		return (I_ZZAssetLocation)MTable.get(getCtx(), I_ZZAssetLocation.Table_ID)
			.getPO(getZZAssetLocationTo_ID(), get_TrxName());
	}

	/** Set Asset Location To.
		@param ZZAssetLocationTo_ID Asset Location To
	*/
	public void setZZAssetLocationTo_ID (int ZZAssetLocationTo_ID)
	{
		if (ZZAssetLocationTo_ID < 1)
			set_Value (COLUMNNAME_ZZAssetLocationTo_ID, null);
		else
			set_Value (COLUMNNAME_ZZAssetLocationTo_ID, Integer.valueOf(ZZAssetLocationTo_ID));
	}

	/** Get Asset Location To.
		@return Asset Location To	  */
	public int getZZAssetLocationTo_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ZZAssetLocationTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_ZZAssetLocation getZZAssetLocation() throws RuntimeException
	{
		return (I_ZZAssetLocation)MTable.get(getCtx(), I_ZZAssetLocation.Table_ID)
			.getPO(getZZAssetLocation_ID(), get_TrxName());
	}

	/** Set Asset Location.
		@param ZZAssetLocation_ID Asset Location
	*/
	public void setZZAssetLocation_ID (int ZZAssetLocation_ID)
	{
		if (ZZAssetLocation_ID < 1)
			set_Value (COLUMNNAME_ZZAssetLocation_ID, null);
		else
			set_Value (COLUMNNAME_ZZAssetLocation_ID, Integer.valueOf(ZZAssetLocation_ID));
	}

	/** Get Asset Location.
		@return Asset Location	  */
	public int getZZAssetLocation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ZZAssetLocation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_ZZAssetRegion getZZAssetRegionTo() throws RuntimeException
	{
		return (I_ZZAssetRegion)MTable.get(getCtx(), I_ZZAssetRegion.Table_ID)
			.getPO(getZZAssetRegionTo_ID(), get_TrxName());
	}

	/** Set Asset Region To.
		@param ZZAssetRegionTo_ID Asset Region To
	*/
	public void setZZAssetRegionTo_ID (int ZZAssetRegionTo_ID)
	{
		if (ZZAssetRegionTo_ID < 1)
			set_Value (COLUMNNAME_ZZAssetRegionTo_ID, null);
		else
			set_Value (COLUMNNAME_ZZAssetRegionTo_ID, Integer.valueOf(ZZAssetRegionTo_ID));
	}

	/** Get Asset Region To.
		@return Asset Region To	  */
	public int getZZAssetRegionTo_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ZZAssetRegionTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_ZZAssetRegion getZZAssetRegion() throws RuntimeException
	{
		return (I_ZZAssetRegion)MTable.get(getCtx(), I_ZZAssetRegion.Table_ID)
			.getPO(getZZAssetRegion_ID(), get_TrxName());
	}

	/** Set Asset Region.
		@param ZZAssetRegion_ID Asset Region
	*/
	public void setZZAssetRegion_ID (int ZZAssetRegion_ID)
	{
		if (ZZAssetRegion_ID < 1)
			set_Value (COLUMNNAME_ZZAssetRegion_ID, null);
		else
			set_Value (COLUMNNAME_ZZAssetRegion_ID, Integer.valueOf(ZZAssetRegion_ID));
	}

	/** Get Asset Region.
		@return Asset Region	  */
	public int getZZAssetRegion_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ZZAssetRegion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_ZZAssetVerifyBatch getZZAssetVerifyBatch() throws RuntimeException
	{
		return (I_ZZAssetVerifyBatch)MTable.get(getCtx(), I_ZZAssetVerifyBatch.Table_ID)
			.getPO(getZZAssetVerifyBatch_ID(), get_TrxName());
	}

	/** Set Asset Verify Batch.
		@param ZZAssetVerifyBatch_ID Asset Verify Batch
	*/
	public void setZZAssetVerifyBatch_ID (int ZZAssetVerifyBatch_ID)
	{
		if (ZZAssetVerifyBatch_ID < 1)
			set_ValueNoCheck (COLUMNNAME_ZZAssetVerifyBatch_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_ZZAssetVerifyBatch_ID, Integer.valueOf(ZZAssetVerifyBatch_ID));
	}

	/** Get Asset Verify Batch.
		@return Asset Verify Batch	  */
	public int getZZAssetVerifyBatch_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ZZAssetVerifyBatch_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_User getZZ_AD_UserTo() throws RuntimeException
	{
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_ID)
			.getPO(getZZ_AD_UserTo_ID(), get_TrxName());
	}

	/** Set User/Contact To.
		@param ZZ_AD_UserTo_ID User within the system - Internal or Business Partner Contact
	*/
	public void setZZ_AD_UserTo_ID (int ZZ_AD_UserTo_ID)
	{
		if (ZZ_AD_UserTo_ID < 1)
			set_Value (COLUMNNAME_ZZ_AD_UserTo_ID, null);
		else
			set_Value (COLUMNNAME_ZZ_AD_UserTo_ID, Integer.valueOf(ZZ_AD_UserTo_ID));
	}

	/** Get User/Contact To.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getZZ_AD_UserTo_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ZZ_AD_UserTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Asset Transfer.
		@param ZZ_AssetTransfer_ID Asset Transfer
	*/
	public void setZZ_AssetTransfer_ID (int ZZ_AssetTransfer_ID)
	{
		if (ZZ_AssetTransfer_ID < 1)
			set_ValueNoCheck (COLUMNNAME_ZZ_AssetTransfer_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_ZZ_AssetTransfer_ID, Integer.valueOf(ZZ_AssetTransfer_ID));
	}

	/** Get Asset Transfer.
		@return Asset Transfer	  */
	public int getZZ_AssetTransfer_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ZZ_AssetTransfer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Asset Transfer UU.
		@param ZZ_AssetTransfer_UU Asset Transfer UU
	*/
	public void setZZ_AssetTransfer_UU (String ZZ_AssetTransfer_UU)
	{
		set_Value (COLUMNNAME_ZZ_AssetTransfer_UU, ZZ_AssetTransfer_UU);
	}

	/** Get Asset Transfer UU.
		@return Asset Transfer UU	  */
	public String getZZ_AssetTransfer_UU()
	{
		return (String)get_Value(COLUMNNAME_ZZ_AssetTransfer_UU);
	}

	/** Set Date Approved.
		@param ZZ_Date_Approved Date Approved
	*/
	public void setZZ_Date_Approved (Timestamp ZZ_Date_Approved)
	{
		set_Value (COLUMNNAME_ZZ_Date_Approved, ZZ_Date_Approved);
	}

	/** Get Date Approved.
		@return Date Approved	  */
	public Timestamp getZZ_Date_Approved()
	{
		return (Timestamp)get_Value(COLUMNNAME_ZZ_Date_Approved);
	}

	/** Set Date Completed.
		@param ZZ_Date_Completed Date Completed
	*/
	public void setZZ_Date_Completed (Timestamp ZZ_Date_Completed)
	{
		set_Value (COLUMNNAME_ZZ_Date_Completed, ZZ_Date_Completed);
	}

	/** Get Date Completed.
		@return Date Completed	  */
	public Timestamp getZZ_Date_Completed()
	{
		return (Timestamp)get_Value(COLUMNNAME_ZZ_Date_Completed);
	}

	/** Set Date LM Approved.
		@param ZZ_Date_LM_Approved Date LM Approved
	*/
	public void setZZ_Date_LM_Approved (Timestamp ZZ_Date_LM_Approved)
	{
		set_Value (COLUMNNAME_ZZ_Date_LM_Approved, ZZ_Date_LM_Approved);
	}

	/** Get Date LM Approved.
		@return Date LM Approved	  */
	public Timestamp getZZ_Date_LM_Approved()
	{
		return (Timestamp)get_Value(COLUMNNAME_ZZ_Date_LM_Approved);
	}

	/** Set Date Not Approved by LM.
		@param ZZ_Date_Not_Approved_by_LM Date Not Approved by LM
	*/
	public void setZZ_Date_Not_Approved_by_LM (Timestamp ZZ_Date_Not_Approved_by_LM)
	{
		set_Value (COLUMNNAME_ZZ_Date_Not_Approved_by_LM, ZZ_Date_Not_Approved_by_LM);
	}

	/** Get Date Not Approved by LM.
		@return Date Not Approved by LM	  */
	public Timestamp getZZ_Date_Not_Approved_by_LM()
	{
		return (Timestamp)get_Value(COLUMNNAME_ZZ_Date_Not_Approved_by_LM);
	}

	/** Set Date Not Approved by Snr Admin Finance.
		@param ZZ_Date_Not_Approved_by_Snr_Adm_Fin Date Not Approved by Snr Admin Finance
	*/
	public void setZZ_Date_Not_Approved_by_Snr_Adm_Fin (Timestamp ZZ_Date_Not_Approved_by_Snr_Adm_Fin)
	{
		set_Value (COLUMNNAME_ZZ_Date_Not_Approved_by_Snr_Adm_Fin, ZZ_Date_Not_Approved_by_Snr_Adm_Fin);
	}

	/** Get Date Not Approved by Snr Admin Finance.
		@return Date Not Approved by Snr Admin Finance	  */
	public Timestamp getZZ_Date_Not_Approved_by_Snr_Adm_Fin()
	{
		return (Timestamp)get_Value(COLUMNNAME_ZZ_Date_Not_Approved_by_Snr_Adm_Fin);
	}

	/** Set Date Submitted.
		@param ZZ_Date_Submitted Date Submitted
	*/
	public void setZZ_Date_Submitted (Timestamp ZZ_Date_Submitted)
	{
		set_Value (COLUMNNAME_ZZ_Date_Submitted, ZZ_Date_Submitted);
	}

	/** Get Date Submitted.
		@return Date Submitted	  */
	public Timestamp getZZ_Date_Submitted()
	{
		return (Timestamp)get_Value(COLUMNNAME_ZZ_Date_Submitted);
	}

	/** Approve/Do Not Approve = AP */
	public static final String ZZ_DOCACTION_ApproveDoNotApprove = "AP";
	/** Complete = CO */
	public static final String ZZ_DOCACTION_Complete = "CO";
	/** Final Approval/Do not Approve = FA */
	public static final String ZZ_DOCACTION_FinalApprovalDoNotApprove = "FA";
	/** Submit to Manager Finance Consumables = SC */
	public static final String ZZ_DOCACTION_SubmitToManagerFinanceConsumables = "SC";
	/** Submit to Line Manager = SU */
	public static final String ZZ_DOCACTION_SubmitToLineManager = "SU";
	/** Set Document Action.
		@param ZZ_DocAction Document Action
	*/
	public void setZZ_DocAction (String ZZ_DocAction)
	{

		set_Value (COLUMNNAME_ZZ_DocAction, ZZ_DocAction);
	}

	/** Get Document Action.
		@return Document Action	  */
	public String getZZ_DocAction()
	{
		return (String)get_Value(COLUMNNAME_ZZ_DocAction);
	}

	/** Approved By Manager Finance Consumables = AC */
	public static final String ZZ_DOCSTATUS_ApprovedByManagerFinanceConsumables = "AC";
	/** Approved = AP */
	public static final String ZZ_DOCSTATUS_Approved = "AP";
	/** Completed = CO */
	public static final String ZZ_DOCSTATUS_Completed = "CO";
	/** Draft = DR */
	public static final String ZZ_DOCSTATUS_Draft = "DR";
	/** In Progress = IP */
	public static final String ZZ_DOCSTATUS_InProgress = "IP";
	/** Not Approved By Manager Finance Consumables = NC */
	public static final String ZZ_DOCSTATUS_NotApprovedByManagerFinanceConsumables = "NC";
	/** Not Approved by LM = NL */
	public static final String ZZ_DOCSTATUS_NotApprovedByLM = "NL";
	/** Not Approved by Snr Admin Finance = NS */
	public static final String ZZ_DOCSTATUS_NotApprovedBySnrAdminFinance = "NS";
	/** Submitted to Manager Finance Consumables = SC */
	public static final String ZZ_DOCSTATUS_SubmittedToManagerFinanceConsumables = "SC";
	/** Submitted = SU */
	public static final String ZZ_DOCSTATUS_Submitted = "SU";
	/** Set Document Status.
		@param ZZ_DocStatus Document Status
	*/
	public void setZZ_DocStatus (String ZZ_DocStatus)
	{

		set_Value (COLUMNNAME_ZZ_DocStatus, ZZ_DocStatus);
	}

	/** Get Document Status.
		@return Document Status	  */
	public String getZZ_DocStatus()
	{
		return (String)get_Value(COLUMNNAME_ZZ_DocStatus);
	}

	public org.compiere.model.I_AD_User getZZ_Snr_Admin_Fin() throws RuntimeException
	{
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_ID)
			.getPO(getZZ_Snr_Admin_Fin_ID(), get_TrxName());
	}

	/** Set Snr Admin Finance User.
		@param ZZ_Snr_Admin_Fin_ID Snr Admin Finance User
	*/
	public void setZZ_Snr_Admin_Fin_ID (int ZZ_Snr_Admin_Fin_ID)
	{
		if (ZZ_Snr_Admin_Fin_ID < 1)
			set_Value (COLUMNNAME_ZZ_Snr_Admin_Fin_ID, null);
		else
			set_Value (COLUMNNAME_ZZ_Snr_Admin_Fin_ID, Integer.valueOf(ZZ_Snr_Admin_Fin_ID));
	}

	/** Get Snr Admin Finance User.
		@return Snr Admin Finance User	  */
	public int getZZ_Snr_Admin_Fin_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ZZ_Snr_Admin_Fin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}