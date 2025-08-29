package za.co.ntier.fa.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.annotation.Callout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import za.co.ntier.fa.model.I_ZZ_AssetRevaluation;

//@Callout(tableName = I_ZZ_AssetTransfer.Table_Name, columnName = I_ZZ_AssetTransfer.COLUMNNAME_A_Asset_ID)
//@Callout(tableName = I_ZZ_AssetRevaluation.Table_Name, columnName = I_ZZ_AssetRevaluation.COLUMNNAME_A_Asset_ID)
@Callout(tableName = I_ZZ_AssetRevaluation.Table_Name, columnName = I_ZZ_AssetRevaluation.COLUMNNAME_UseLifeYears)
@Callout(tableName = I_ZZ_AssetRevaluation.Table_Name, columnName = I_ZZ_AssetRevaluation.COLUMNNAME_UseLifeMonths)
public class MQAFACallout implements IColumnCallout {

	@Override
	public String start(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		if (I_ZZ_AssetRevaluation.Table_Name.equals(mTab.getTableName())
				&& (I_ZZ_AssetRevaluation.COLUMNNAME_UseLifeMonths.equals(mField.getColumnName())
						|| I_ZZ_AssetRevaluation.COLUMNNAME_UseLifeYears.equals(mField.getColumnName()))) {
			doCalculatorUseLife(ctx, windowNo, mTab, mField, value, oldValue);
		}

		return null;
	}

	private static final BigDecimal TWELVE = BigDecimal.valueOf(12.0);
	
	void doCalculatorUseLife(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		if ("3f820615-130b-48a4-8f33-cc65a2f690ba".equals(mTab.getAD_Tab_UU())) {// main tab on window Asset Revaluation
			BigDecimal uselife = null;

			if (value == null) {
				mTab.setValue(I_ZZ_AssetRevaluation.COLUMNNAME_UseLifeMonths, null);
				mTab.setValue(I_ZZ_AssetRevaluation.COLUMNNAME_UseLifeYears, null);
				return;
			}

			if (I_ZZ_AssetRevaluation.COLUMNNAME_UseLifeYears.equals(mField.getColumnName()))
			{
				uselife =  new BigDecimal(value.toString()).multiply(TWELVE);
				mTab.setValue(I_ZZ_AssetRevaluation.COLUMNNAME_UseLifeMonths, uselife);
			} else if (I_ZZ_AssetRevaluation.COLUMNNAME_UseLifeMonths.equals(mField.getColumnName()))
			{
				uselife =  new BigDecimal(value.toString()).setScale(12).divide(TWELVE, RoundingMode.HALF_UP);
				mTab.setValue(I_ZZ_AssetRevaluation.COLUMNNAME_UseLifeYears, uselife);
			}
		}

	}
}
