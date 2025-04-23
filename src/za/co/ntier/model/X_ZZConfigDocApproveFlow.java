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
package za.co.ntier.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for ZZConfigDocApproveFlow
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="ZZConfigDocApproveFlow")
public class X_ZZConfigDocApproveFlow extends PO implements I_ZZConfigDocApproveFlow, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250418L;

    /** Standard Constructor */
    public X_ZZConfigDocApproveFlow (Properties ctx, int ZZConfigDocApproveFlow_ID, String trxName)
    {
      super (ctx, ZZConfigDocApproveFlow_ID, trxName);
      /** if (ZZConfigDocApproveFlow_ID == 0)
        {
			setZZConfigDocApproveFlow_ID (0);
			setZZ_AllowLineManageApproved (false);
// N
			setZZ_AllowSnrAdminFinanceApproved (true);
// Y
        } */
    }

    /** Standard Constructor */
    public X_ZZConfigDocApproveFlow (Properties ctx, int ZZConfigDocApproveFlow_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, ZZConfigDocApproveFlow_ID, trxName, virtualColumns);
      /** if (ZZConfigDocApproveFlow_ID == 0)
        {
			setZZConfigDocApproveFlow_ID (0);
			setZZ_AllowLineManageApproved (false);
// N
			setZZ_AllowSnrAdminFinanceApproved (true);
// Y
        } */
    }

    /** Standard Constructor */
    public X_ZZConfigDocApproveFlow (Properties ctx, String ZZConfigDocApproveFlow_UU, String trxName)
    {
      super (ctx, ZZConfigDocApproveFlow_UU, trxName);
      /** if (ZZConfigDocApproveFlow_UU == null)
        {
			setZZConfigDocApproveFlow_ID (0);
			setZZ_AllowLineManageApproved (false);
// N
			setZZ_AllowSnrAdminFinanceApproved (true);
// Y
        } */
    }

    /** Standard Constructor */
    public X_ZZConfigDocApproveFlow (Properties ctx, String ZZConfigDocApproveFlow_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, ZZConfigDocApproveFlow_UU, trxName, virtualColumns);
      /** if (ZZConfigDocApproveFlow_UU == null)
        {
			setZZConfigDocApproveFlow_ID (0);
			setZZ_AllowLineManageApproved (false);
// N
			setZZ_AllowSnrAdminFinanceApproved (true);
// Y
        } */
    }

    /** Load Constructor */
    public X_ZZConfigDocApproveFlow (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client
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
      StringBuilder sb = new StringBuilder ("X_ZZConfigDocApproveFlow[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException
	{
		return (org.compiere.model.I_AD_Table)MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_ID)
			.getPO(getAD_Table_ID(), get_TrxName());
	}

	/** Set Table.
		@param AD_Table_ID Database Table information
	*/
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1)
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
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

	/** Set Config Document Approve Flow.
		@param ZZConfigDocApproveFlow_ID Configuration approve flow for a document
	*/
	public void setZZConfigDocApproveFlow_ID (int ZZConfigDocApproveFlow_ID)
	{
		if (ZZConfigDocApproveFlow_ID < 1)
			set_ValueNoCheck (COLUMNNAME_ZZConfigDocApproveFlow_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_ZZConfigDocApproveFlow_ID, Integer.valueOf(ZZConfigDocApproveFlow_ID));
	}

	/** Get Config Document Approve Flow.
		@return Configuration approve flow for a document
	  */
	public int getZZConfigDocApproveFlow_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ZZConfigDocApproveFlow_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ZZConfigDocApproveFlow_UU.
		@param ZZConfigDocApproveFlow_UU ZZConfigDocApproveFlow_UU
	*/
	public void setZZConfigDocApproveFlow_UU (String ZZConfigDocApproveFlow_UU)
	{
		set_Value (COLUMNNAME_ZZConfigDocApproveFlow_UU, ZZConfigDocApproveFlow_UU);
	}

	/** Get ZZConfigDocApproveFlow_UU.
		@return ZZConfigDocApproveFlow_UU	  */
	public String getZZConfigDocApproveFlow_UU()
	{
		return (String)get_Value(COLUMNNAME_ZZConfigDocApproveFlow_UU);
	}

	public org.compiere.model.I_R_MailText getZZMailLineApproved() throws RuntimeException
	{
		return (org.compiere.model.I_R_MailText)MTable.get(getCtx(), org.compiere.model.I_R_MailText.Table_ID)
			.getPO(getZZMailLineApproved_ID(), get_TrxName());
	}

	/** Set Mail Template Line Manage Approved.
		@param ZZMailLineApproved_ID Email template sent to submitor who do submit document to inform him document is approved by line manage
	*/
	public void setZZMailLineApproved_ID (int ZZMailLineApproved_ID)
	{
		if (ZZMailLineApproved_ID < 1)
			set_Value (COLUMNNAME_ZZMailLineApproved_ID, null);
		else
			set_Value (COLUMNNAME_ZZMailLineApproved_ID, Integer.valueOf(ZZMailLineApproved_ID));
	}

	/** Get Mail Template Line Manage Approved.
		@return Email template sent to submitor who do submit document to inform him document is approved by line manage
	  */
	public int getZZMailLineApproved_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ZZMailLineApproved_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_R_MailText getZZMailLineReject() throws RuntimeException
	{
		return (org.compiere.model.I_R_MailText)MTable.get(getCtx(), org.compiere.model.I_R_MailText.Table_ID)
			.getPO(getZZMailLineReject_ID(), get_TrxName());
	}

	/** Set Mail Template Line Manage Reject.
		@param ZZMailLineReject_ID Email template sent to submitor who do submit document to inform him document is reject by line manage
	*/
	public void setZZMailLineReject_ID (int ZZMailLineReject_ID)
	{
		if (ZZMailLineReject_ID < 1)
			set_Value (COLUMNNAME_ZZMailLineReject_ID, null);
		else
			set_Value (COLUMNNAME_ZZMailLineReject_ID, Integer.valueOf(ZZMailLineReject_ID));
	}

	/** Get Mail Template Line Manage Reject.
		@return Email template sent to submitor who do submit document to inform him document is reject by line manage
	  */
	public int getZZMailLineReject_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ZZMailLineReject_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_R_MailText getZZMailRequestLine() throws RuntimeException
	{
		return (org.compiere.model.I_R_MailText)MTable.get(getCtx(), org.compiere.model.I_R_MailText.Table_ID)
			.getPO(getZZMailRequestLine_ID(), get_TrxName());
	}

	/** Set Mail Template Request Line Manage Approve.
		@param ZZMailRequestLine_ID Email template sent to line manage to notify him has document need him review and approve
	*/
	public void setZZMailRequestLine_ID (int ZZMailRequestLine_ID)
	{
		if (ZZMailRequestLine_ID < 1)
			set_Value (COLUMNNAME_ZZMailRequestLine_ID, null);
		else
			set_Value (COLUMNNAME_ZZMailRequestLine_ID, Integer.valueOf(ZZMailRequestLine_ID));
	}

	/** Get Mail Template Request Line Manage Approve.
		@return Email template sent to line manage to notify him has document need him review and approve
	  */
	public int getZZMailRequestLine_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ZZMailRequestLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_R_MailText getZZMailRequestSnr() throws RuntimeException
	{
		return (org.compiere.model.I_R_MailText)MTable.get(getCtx(), org.compiere.model.I_R_MailText.Table_ID)
			.getPO(getZZMailRequestSnr_ID(), get_TrxName());
	}

	/** Set Mail Template Request Snr Admin Finance Manage Approve.
		@param ZZMailRequestSnr_ID Email template sent to Snr Admin Finance to notify him has document need him review and approve, this email can sent when line manage do approve or user submit up to workflow
	*/
	public void setZZMailRequestSnr_ID (int ZZMailRequestSnr_ID)
	{
		if (ZZMailRequestSnr_ID < 1)
			set_Value (COLUMNNAME_ZZMailRequestSnr_ID, null);
		else
			set_Value (COLUMNNAME_ZZMailRequestSnr_ID, Integer.valueOf(ZZMailRequestSnr_ID));
	}

	/** Get Mail Template Request Snr Admin Finance Manage Approve.
		@return Email template sent to Snr Admin Finance to notify him has document need him review and approve, this email can sent when line manage do approve or user submit up to workflow
	  */
	public int getZZMailRequestSnr_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ZZMailRequestSnr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_R_MailText getZZMailSnrApproved() throws RuntimeException
	{
		return (org.compiere.model.I_R_MailText)MTable.get(getCtx(), org.compiere.model.I_R_MailText.Table_ID)
			.getPO(getZZMailSnrApproved_ID(), get_TrxName());
	}

	/** Set Mail Template Snr Admin Finance Manage Approved.
		@param ZZMailSnrApproved_ID Email template sent to Submitor when Snr Admin Finance Approved document
	*/
	public void setZZMailSnrApproved_ID (int ZZMailSnrApproved_ID)
	{
		if (ZZMailSnrApproved_ID < 1)
			set_Value (COLUMNNAME_ZZMailSnrApproved_ID, null);
		else
			set_Value (COLUMNNAME_ZZMailSnrApproved_ID, Integer.valueOf(ZZMailSnrApproved_ID));
	}

	/** Get Mail Template Snr Admin Finance Manage Approved.
		@return Email template sent to Submitor when Snr Admin Finance Approved document
	  */
	public int getZZMailSnrApproved_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ZZMailSnrApproved_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_R_MailText getZZMailSnrRejectLine() throws RuntimeException
	{
		return (org.compiere.model.I_R_MailText)MTable.get(getCtx(), org.compiere.model.I_R_MailText.Table_ID)
			.getPO(getZZMailSnrRejectLine_ID(), get_TrxName());
	}

	/** Set Mail Template Snr Admin Finance Manage Reject To Line Manage.
		@param ZZMailSnrRejectLine_ID Email template sent to line manage when Snr Admin Finance reject document
	*/
	public void setZZMailSnrRejectLine_ID (int ZZMailSnrRejectLine_ID)
	{
		if (ZZMailSnrRejectLine_ID < 1)
			set_Value (COLUMNNAME_ZZMailSnrRejectLine_ID, null);
		else
			set_Value (COLUMNNAME_ZZMailSnrRejectLine_ID, Integer.valueOf(ZZMailSnrRejectLine_ID));
	}

	/** Get Mail Template Snr Admin Finance Manage Reject To Line Manage.
		@return Email template sent to line manage when Snr Admin Finance reject document
	  */
	public int getZZMailSnrRejectLine_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ZZMailSnrRejectLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_R_MailText getZZMailSnrRejectSubmitor() throws RuntimeException
	{
		return (org.compiere.model.I_R_MailText)MTable.get(getCtx(), org.compiere.model.I_R_MailText.Table_ID)
			.getPO(getZZMailSnrRejectSubmitor_ID(), get_TrxName());
	}

	/** Set Mail Template Snr Admin Finance Manage Reject To Submitor.
		@param ZZMailSnrRejectSubmitor_ID Email template sent to Submitor when Snr Admin Finance reject document
	*/
	public void setZZMailSnrRejectSubmitor_ID (int ZZMailSnrRejectSubmitor_ID)
	{
		if (ZZMailSnrRejectSubmitor_ID < 1)
			set_Value (COLUMNNAME_ZZMailSnrRejectSubmitor_ID, null);
		else
			set_Value (COLUMNNAME_ZZMailSnrRejectSubmitor_ID, Integer.valueOf(ZZMailSnrRejectSubmitor_ID));
	}

	/** Get Mail Template Snr Admin Finance Manage Reject To Submitor.
		@return Email template sent to Submitor when Snr Admin Finance reject document
	  */
	public int getZZMailSnrRejectSubmitor_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ZZMailSnrRejectSubmitor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Allow Line Manage Approved.
		@param ZZ_AllowLineManageApproved Choose to allow line manage join to approved workfllow
	*/
	public void setZZ_AllowLineManageApproved (boolean ZZ_AllowLineManageApproved)
	{
		set_Value (COLUMNNAME_ZZ_AllowLineManageApproved, Boolean.valueOf(ZZ_AllowLineManageApproved));
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
		set_Value (COLUMNNAME_ZZ_AllowSnrAdminFinanceApproved, Boolean.valueOf(ZZ_AllowSnrAdminFinanceApproved));
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
}