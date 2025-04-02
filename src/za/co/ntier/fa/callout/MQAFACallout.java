package za.co.ntier.fa.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.annotation.Callout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MAsset;

import za.co.ntier.fa.model.I_ZZ_AssetTransfer;

@Callout(tableName = I_ZZ_AssetTransfer.Table_Name, columnName = I_ZZ_AssetTransfer.COLUMNNAME_A_Asset_ID)
public class MQAFACallout implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		int locatorId = 0;
		if (value == null) {
			locatorId = 0;
		}else {
			MAsset asset = MAsset.get(ctx, (Integer) value, null);
			locatorId = asset.getM_Locator_ID();
		}
		mTab.setValue(I_ZZ_AssetTransfer.COLUMNNAME_M_Locator_ID, locatorId);

		return null;
	}

}
