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

/** Generated Model for ZZAssetVerifyBatch
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="ZZAssetVerifyBatch")
public class X_ZZAssetVerifyBatch extends PO implements I_ZZAssetVerifyBatch, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250610L;

    /** Standard Constructor */
    public X_ZZAssetVerifyBatch (Properties ctx, int ZZAssetVerifyBatch_ID, String trxName)
    {
      super (ctx, ZZAssetVerifyBatch_ID, trxName);
      /** if (ZZAssetVerifyBatch_ID == 0)
        {
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setZZAssetVerifyBatch_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_ZZAssetVerifyBatch (Properties ctx, int ZZAssetVerifyBatch_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, ZZAssetVerifyBatch_ID, trxName, virtualColumns);
      /** if (ZZAssetVerifyBatch_ID == 0)
        {
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setZZAssetVerifyBatch_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_ZZAssetVerifyBatch (Properties ctx, String ZZAssetVerifyBatch_UU, String trxName)
    {
      super (ctx, ZZAssetVerifyBatch_UU, trxName);
      /** if (ZZAssetVerifyBatch_UU == null)
        {
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setZZAssetVerifyBatch_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_ZZAssetVerifyBatch (Properties ctx, String ZZAssetVerifyBatch_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, ZZAssetVerifyBatch_UU, trxName, virtualColumns);
      /** if (ZZAssetVerifyBatch_UU == null)
        {
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setZZAssetVerifyBatch_ID (0);
        } */
    }

    /** Load Constructor */
    public X_ZZAssetVerifyBatch (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_ZZAssetVerifyBatch[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set ZZAssetVerifyBatch_UU.
		@param ZZAssetVerifyBatch_UU ZZAssetVerifyBatch_UU
	*/
	public void setZZAssetVerifyBatch_UU (String ZZAssetVerifyBatch_UU)
	{
		set_Value (COLUMNNAME_ZZAssetVerifyBatch_UU, ZZAssetVerifyBatch_UU);
	}

	/** Get ZZAssetVerifyBatch_UU.
		@return ZZAssetVerifyBatch_UU	  */
	public String getZZAssetVerifyBatch_UU()
	{
		return (String)get_Value(COLUMNNAME_ZZAssetVerifyBatch_UU);
	}
}