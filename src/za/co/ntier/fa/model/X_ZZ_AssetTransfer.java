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
	private static final long serialVersionUID = 20250407L;

    /** Standard Constructor */
    public X_ZZ_AssetTransfer (Properties ctx, int ZZ_AssetTransfer_ID, String trxName)
    {
      super (ctx, ZZ_AssetTransfer_ID, trxName);
      /** if (ZZ_AssetTransfer_ID == 0)
        {
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setZZ_AllowLineManageApproved (true);
// Y
			setZZ_AllowSnrAdminFinanceApproved (false);
// N
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
			setZZ_AllowLineManageApproved (true);
// Y
			setZZ_AllowSnrAdminFinanceApproved (false);
// N
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
			setZZ_AllowLineManageApproved (true);
// Y
			setZZ_AllowSnrAdminFinanceApproved (false);
// N
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
			setZZ_AllowLineManageApproved (true);
// Y
			setZZ_AllowSnrAdminFinanceApproved (false);
// N
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

	public org.compiere.model.I_M_Locator getM_LocatorTo() throws RuntimeException
	{
		return (org.compiere.model.I_M_Locator)MTable.get(getCtx(), org.compiere.model.I_M_Locator.Table_ID)
			.getPO(getM_LocatorTo_ID(), get_TrxName());
	}

	/** Set Locator To.
		@param M_LocatorTo_ID Location inventory is moved to
	*/
	public void setM_LocatorTo_ID (int M_LocatorTo_ID)
	{
		if (M_LocatorTo_ID < 1)
			set_Value (COLUMNNAME_M_LocatorTo_ID, null);
		else
			set_Value (COLUMNNAME_M_LocatorTo_ID, Integer.valueOf(M_LocatorTo_ID));
	}

	/** Get Locator To.
		@return Location inventory is moved to
	  */
	public int getM_LocatorTo_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_LocatorTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Locator getM_Locator() throws RuntimeException
	{
		return (I_M_Locator)MTable.get(getCtx(), I_M_Locator.Table_ID)
			.getPO(getM_Locator_ID(), get_TrxName());
	}

	/** Set Locator.
		@param M_Locator_ID Warehouse Locator
	*/
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1)
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Warehouse getM_WarehouseTo() throws RuntimeException
	{
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_ID)
			.getPO(getM_WarehouseTo_ID(), get_TrxName());
	}

	/** Set Warehouse To.
		@param M_WarehouseTo_ID To Storage Warehouse and Service Point
	*/
	public void setM_WarehouseTo_ID (int M_WarehouseTo_ID)
	{
		if (M_WarehouseTo_ID < 1)
			set_Value (COLUMNNAME_M_WarehouseTo_ID, null);
		else
			set_Value (COLUMNNAME_M_WarehouseTo_ID, Integer.valueOf(M_WarehouseTo_ID));
	}

	/** Get Warehouse To.
		@return To Storage Warehouse and Service Point
	  */
	public int getM_WarehouseTo_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_WarehouseTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException
	{
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_ID)
			.getPO(getM_Warehouse_ID(), get_TrxName());
	}

	/** Set Warehouse.
		@param M_Warehouse_ID Storage Warehouse and Service Point
	*/
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1)
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
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

	/** Set Allow Line Manage Approved.
		@param ZZ_AllowLineManageApproved Choose to allow line manage join to approved workfllow
	*/
	public void setZZ_AllowLineManageApproved (boolean ZZ_AllowLineManageApproved)
	{
		set_ValueNoCheck (COLUMNNAME_ZZ_AllowLineManageApproved, Boolean.valueOf(ZZ_AllowLineManageApproved));
	}

	/** Get Allow Line Manage Approved.
		@return Choose to allow line manage join to approved workfllow
	  */
	public boolean isZZ_AllowLineManageApproved()
	{
		Object oo = get_Value(COLUMNNAME_ZZ_AllowLineManageApproved);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allow Snr Admin Finance Approved.
		@param ZZ_AllowSnrAdminFinanceApproved Choose to allow Snr Admin Finance join to approved workfllow
	*/
	public void setZZ_AllowSnrAdminFinanceApproved (boolean ZZ_AllowSnrAdminFinanceApproved)
	{
		set_ValueNoCheck (COLUMNNAME_ZZ_AllowSnrAdminFinanceApproved, Boolean.valueOf(ZZ_AllowSnrAdminFinanceApproved));
	}

	/** Get Allow Snr Admin Finance Approved.
		@return Choose to allow Snr Admin Finance join to approved workfllow
	  */
	public boolean isZZ_AllowSnrAdminFinanceApproved()
	{
		Object oo = get_Value(COLUMNNAME_ZZ_AllowSnrAdminFinanceApproved);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
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

	/** Approved = AP */
	public static final String ZZ_FINALWORKFLOWSTATEVALUE_Approved = "AP";
	/** Completed = CO */
	public static final String ZZ_FINALWORKFLOWSTATEVALUE_Completed = "CO";
	/** Draft = DR */
	public static final String ZZ_FINALWORKFLOWSTATEVALUE_Draft = "DR";
	/** In Progress = IP */
	public static final String ZZ_FINALWORKFLOWSTATEVALUE_InProgress = "IP";
	/** Not Approved by LM = NL */
	public static final String ZZ_FINALWORKFLOWSTATEVALUE_NotApprovedByLM = "NL";
	/** Not Approved by Snr Admin Finance = NS */
	public static final String ZZ_FINALWORKFLOWSTATEVALUE_NotApprovedBySnrAdminFinance = "NS";
	/** Submitted = SU */
	public static final String ZZ_FINALWORKFLOWSTATEVALUE_Submitted = "SU";
	/** Set Final Workflow State Value.
		@param ZZ_FinalWorkflowStateValue Value set to ZZ_DocStatus when reach to end of approve workflow
	*/
	public void setZZ_FinalWorkflowStateValue (String ZZ_FinalWorkflowStateValue)
	{

		set_Value (COLUMNNAME_ZZ_FinalWorkflowStateValue, ZZ_FinalWorkflowStateValue);
	}

	/** Get Final Workflow State Value.
		@return Value set to ZZ_DocStatus when reach to end of approve workflow
	  */
	public String getZZ_FinalWorkflowStateValue()
	{
		return (String)get_Value(COLUMNNAME_ZZ_FinalWorkflowStateValue);
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