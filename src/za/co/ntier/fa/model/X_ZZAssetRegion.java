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

/** Generated Model for ZZAssetRegion
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="ZZAssetRegion")
public class X_ZZAssetRegion extends PO implements I_ZZAssetRegion, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250422L;

    /** Standard Constructor */
    public X_ZZAssetRegion (Properties ctx, int ZZAssetRegion_ID, String trxName)
    {
      super (ctx, ZZAssetRegion_ID, trxName);
      /** if (ZZAssetRegion_ID == 0)
        {
			setName (null);
			setZZAssetRegion_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_ZZAssetRegion (Properties ctx, int ZZAssetRegion_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, ZZAssetRegion_ID, trxName, virtualColumns);
      /** if (ZZAssetRegion_ID == 0)
        {
			setName (null);
			setZZAssetRegion_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_ZZAssetRegion (Properties ctx, String ZZAssetRegion_UU, String trxName)
    {
      super (ctx, ZZAssetRegion_UU, trxName);
      /** if (ZZAssetRegion_UU == null)
        {
			setName (null);
			setZZAssetRegion_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_ZZAssetRegion (Properties ctx, String ZZAssetRegion_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, ZZAssetRegion_UU, trxName, virtualColumns);
      /** if (ZZAssetRegion_UU == null)
        {
			setName (null);
			setZZAssetRegion_ID (0);
        } */
    }

    /** Load Constructor */
    public X_ZZAssetRegion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_ZZAssetRegion[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
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

	/** Set Name.
		@param Name Alphanumeric identifier of the entity
	*/
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName()
	{
		return (String)get_Value(COLUMNNAME_Name);
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

	/** Set Asset Region.
		@param ZZAssetRegion_ID Asset Region
	*/
	public void setZZAssetRegion_ID (int ZZAssetRegion_ID)
	{
		if (ZZAssetRegion_ID < 1)
			set_ValueNoCheck (COLUMNNAME_ZZAssetRegion_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_ZZAssetRegion_ID, Integer.valueOf(ZZAssetRegion_ID));
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

	/** Set ZZAssetRegion_UU.
		@param ZZAssetRegion_UU ZZAssetRegion_UU
	*/
	public void setZZAssetRegion_UU (String ZZAssetRegion_UU)
	{
		set_Value (COLUMNNAME_ZZAssetRegion_UU, ZZAssetRegion_UU);
	}

	/** Get ZZAssetRegion_UU.
		@return ZZAssetRegion_UU	  */
	public String getZZAssetRegion_UU()
	{
		return (String)get_Value(COLUMNNAME_ZZAssetRegion_UU);
	}
}