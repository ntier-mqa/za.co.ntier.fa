package za.co.ntier.fa.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.annotation.Callout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.I_AD_User;
import org.compiere.model.I_M_Locator;
import org.compiere.model.I_M_Warehouse;
import org.compiere.model.MAsset;

import za.co.ntier.fa.model.I_ZZ_AssetTransfer;

@Callout(tableName = I_ZZ_AssetTransfer.Table_Name, columnName = I_ZZ_AssetTransfer.COLUMNNAME_A_Asset_ID)
@Callout(tableName = I_M_Locator.Table_Name, columnName = I_M_Locator.COLUMNNAME_Value)
public class MQAFACallout implements IColumnCallout {

	@Override
	public String start(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		if (I_ZZ_AssetTransfer.Table_Name.equals(mTab.getTableName())
				&& I_ZZ_AssetTransfer.COLUMNNAME_A_Asset_ID.equals(mField.getColumnName())) {
			setLocationFollowAsset(ctx, windowNo, mTab, mField, value, oldValue);
		}else if (I_M_Locator.Table_Name.equals(mTab.getTableName())
				&& I_M_Locator.COLUMNNAME_Value.equals(mField.getColumnName())) {
			setXFollowValueForAssetLocator(ctx, windowNo, mTab, mField, value, oldValue);
		}

		return null;
	}

	void setLocationFollowAsset(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		int locatorId = 0;
		int warehouseId = 0;
		int userId = 0;
		if(value != null) {
			MAsset asset = MAsset.get(ctx, (Integer) value, null);
			locatorId = asset.getM_Locator_ID();
			warehouseId = asset.get_ValueAsInt(I_ZZ_AssetTransfer.COLUMNNAME_M_Warehouse_ID);
			userId = asset.get_ValueAsInt(I_ZZ_AssetTransfer.COLUMNNAME_AD_User_ID);
		}
		mTab.setValue(I_ZZ_AssetTransfer.COLUMNNAME_M_Locator_ID, locatorId);
		mTab.setValue(I_ZZ_AssetTransfer.COLUMNNAME_M_Warehouse_ID, warehouseId);
		mTab.setValue(I_ZZ_AssetTransfer.COLUMNNAME_AD_User_ID, userId);
	}
	
	void setXFollowValueForAssetLocator(Properties ctx, int windowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		if ("0911a818-70da-4503-a950-312f587aa012".equals(mTab.getAD_Tab_UU())) {
			mTab.setValue(I_M_Locator.COLUMNNAME_X, mField.getValue());
		}
	}
}
