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
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for I_ZZAssetVerify
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="I_ZZAssetVerify")
public class X_I_ZZAssetVerify extends PO implements I_I_ZZAssetVerify, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250610L;

    /** Standard Constructor */
    public X_I_ZZAssetVerify (Properties ctx, int I_ZZAssetVerify_ID, String trxName)
    {
      super (ctx, I_ZZAssetVerify_ID, trxName);
      /** if (I_ZZAssetVerify_ID == 0)
        {
			setI_IsImported (false);
// N
			setI_ZZAssetVerify_ID (0);
			setProcessed (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_I_ZZAssetVerify (Properties ctx, int I_ZZAssetVerify_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, I_ZZAssetVerify_ID, trxName, virtualColumns);
      /** if (I_ZZAssetVerify_ID == 0)
        {
			setI_IsImported (false);
// N
			setI_ZZAssetVerify_ID (0);
			setProcessed (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_I_ZZAssetVerify (Properties ctx, String I_ZZAssetVerify_UU, String trxName)
    {
      super (ctx, I_ZZAssetVerify_UU, trxName);
      /** if (I_ZZAssetVerify_UU == null)
        {
			setI_IsImported (false);
// N
			setI_ZZAssetVerify_ID (0);
			setProcessed (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_I_ZZAssetVerify (Properties ctx, String I_ZZAssetVerify_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, I_ZZAssetVerify_UU, trxName, virtualColumns);
      /** if (I_ZZAssetVerify_UU == null)
        {
			setI_IsImported (false);
// N
			setI_ZZAssetVerify_ID (0);
			setProcessed (false);
// N
        } */
    }

    /** Load Constructor */
    public X_I_ZZAssetVerify (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_I_ZZAssetVerify[")
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

	/** Set Import Error Message.
		@param I_ErrorMsg Messages generated from import process
	*/
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg()
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Imported.
		@param I_IsImported Has this import been processed
	*/
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported()
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Import Asset Verify.
		@param I_ZZAssetVerify_ID Import Asset Verify
	*/
	public void setI_ZZAssetVerify_ID (int I_ZZAssetVerify_ID)
	{
		if (I_ZZAssetVerify_ID < 1)
			set_ValueNoCheck (COLUMNNAME_I_ZZAssetVerify_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_I_ZZAssetVerify_ID, Integer.valueOf(I_ZZAssetVerify_ID));
	}

	/** Get Import Asset Verify.
		@return Import Asset Verify	  */
	public int getI_ZZAssetVerify_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_ZZAssetVerify_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set I_ZZAssetVerify_UU.
		@param I_ZZAssetVerify_UU I_ZZAssetVerify_UU
	*/
	public void setI_ZZAssetVerify_UU (String I_ZZAssetVerify_UU)
	{
		set_Value (COLUMNNAME_I_ZZAssetVerify_UU, I_ZZAssetVerify_UU);
	}

	/** Get I_ZZAssetVerify_UU.
		@return I_ZZAssetVerify_UU	  */
	public String getI_ZZAssetVerify_UU()
	{
		return (String)get_Value(COLUMNNAME_I_ZZAssetVerify_UU);
	}

	/** Set Processed.
		@param Processed The document has been processed
	*/
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed()
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now
	*/
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing()
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
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

	/** Misplaced = Misplaced */
	public static final String ZZVERIFIEDSTATUS_Misplaced = "Misplaced";
	/** Missing = Missing */
	public static final String ZZVERIFIEDSTATUS_Missing = "Missing";
	/** Verified = Verified */
	public static final String ZZVERIFIEDSTATUS_Verified = "Verified";
	/** Set Verified Status.
		@param ZZVerifiedStatus Verified Status
	*/
	public void setZZVerifiedStatus (String ZZVerifiedStatus)
	{

		set_Value (COLUMNNAME_ZZVerifiedStatus, ZZVerifiedStatus);
	}

	/** Get Verified Status.
		@return Verified Status	  */
	public String getZZVerifiedStatus()
	{
		return (String)get_Value(COLUMNNAME_ZZVerifiedStatus);
	}
}