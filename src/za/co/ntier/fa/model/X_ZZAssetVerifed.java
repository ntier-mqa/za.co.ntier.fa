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

/** Generated Model for ZZAssetVerifed
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="ZZAssetVerifed")
public class X_ZZAssetVerifed extends PO implements I_ZZAssetVerifed, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250610L;

    /** Standard Constructor */
    public X_ZZAssetVerifed (Properties ctx, int ZZAssetVerifed_ID, String trxName)
    {
      super (ctx, ZZAssetVerifed_ID, trxName);
      /** if (ZZAssetVerifed_ID == 0)
        {
			setZZAssetVerifed_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_ZZAssetVerifed (Properties ctx, int ZZAssetVerifed_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, ZZAssetVerifed_ID, trxName, virtualColumns);
      /** if (ZZAssetVerifed_ID == 0)
        {
			setZZAssetVerifed_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_ZZAssetVerifed (Properties ctx, String ZZAssetVerifed_UU, String trxName)
    {
      super (ctx, ZZAssetVerifed_UU, trxName);
      /** if (ZZAssetVerifed_UU == null)
        {
			setZZAssetVerifed_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_ZZAssetVerifed (Properties ctx, String ZZAssetVerifed_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, ZZAssetVerifed_UU, trxName, virtualColumns);
      /** if (ZZAssetVerifed_UU == null)
        {
			setZZAssetVerifed_ID (0);
        } */
    }

    /** Load Constructor */
    public X_ZZAssetVerifed (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_ZZAssetVerifed[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Asset Verifed.
		@param ZZAssetVerifed_ID Asset Verifed
	*/
	public void setZZAssetVerifed_ID (int ZZAssetVerifed_ID)
	{
		if (ZZAssetVerifed_ID < 1)
			set_ValueNoCheck (COLUMNNAME_ZZAssetVerifed_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_ZZAssetVerifed_ID, Integer.valueOf(ZZAssetVerifed_ID));
	}

	/** Get Asset Verifed.
		@return Asset Verifed	  */
	public int getZZAssetVerifed_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ZZAssetVerifed_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ZZAssetVerifed_UU.
		@param ZZAssetVerifed_UU ZZAssetVerifed_UU
	*/
	public void setZZAssetVerifed_UU (String ZZAssetVerifed_UU)
	{
		set_Value (COLUMNNAME_ZZAssetVerifed_UU, ZZAssetVerifed_UU);
	}

	/** Get ZZAssetVerifed_UU.
		@return ZZAssetVerifed_UU	  */
	public String getZZAssetVerifed_UU()
	{
		return (String)get_Value(COLUMNNAME_ZZAssetVerifed_UU);
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
}