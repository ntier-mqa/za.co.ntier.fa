package za.co.ntier.fa.modeleventdelegate;

import org.adempiere.base.annotation.EventTopicDelegate;
import org.adempiere.base.annotation.ModelEventTopic;
import org.adempiere.base.event.annotations.ModelEventDelegate;
import org.adempiere.base.event.annotations.po.BeforeChange;
import org.adempiere.base.event.annotations.po.BeforeNew;
import org.compiere.model.MAsset;
import org.compiere.util.Env;
import org.osgi.service.event.Event;

import za.co.ntier.fa.model.I_A_Asset;
import za.co.ntier.fa.model.I_ZZ_AssetTransfer;
import za.co.ntier.fa.model.MZZAssetTransfer;

@EventTopicDelegate
@ModelEventTopic(modelClass = MZZAssetTransfer.class)
public class ModelAssetTransferHandle extends ModelEventDelegate<MZZAssetTransfer>{
	
	public ModelAssetTransferHandle(MZZAssetTransfer po, Event event) {
		super(po, event);
	}

	@BeforeNew
	@BeforeChange
	public void beforeNewMDepreciationExp(){
		if (getModel().is_ValueChanged(I_ZZ_AssetTransfer.COLUMNNAME_A_Asset_ID)) {
			setLocationFollowAsset(getModel().getA_Asset_ID());
		}
	}
	
	void setLocationFollowAsset(int assetId) {
		int locatorId = 0;
		int regionId  = 0;
		int userId = 0;
		if(assetId > 0) {
			MAsset asset = MAsset.get(Env.getCtx(), assetId, null);
			locatorId = asset.get_ValueAsInt(I_A_Asset.COLUMNNAME_ZZAssetLocation_ID);
			regionId = asset.get_ValueAsInt(I_A_Asset.COLUMNNAME_ZZAssetRegion_ID);
			userId = asset.get_ValueAsInt(I_ZZ_AssetTransfer.COLUMNNAME_AD_User_ID);
		}
		getModel().setZZAssetLocation_ID(locatorId);
		getModel().setZZAssetRegion_ID(regionId);
		getModel().setAD_User_ID(userId);
	}
}
