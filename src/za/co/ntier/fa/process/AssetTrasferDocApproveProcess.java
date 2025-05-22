package za.co.ntier.fa.process;

import org.adempiere.model.POWrapper;
import org.compiere.model.MAsset;

import za.co.ntier.fa.model.I_A_Asset;
import za.co.ntier.fa.model.MZZAssetTransfer;
import za.co.ntier.process.api.AbstractDocApproveProcess;

@org.adempiere.base.annotation.Process(name="za.co.ntier.fa.process.AssetTrasferDocApproveProcess")
public class AssetTrasferDocApproveProcess extends AbstractDocApproveProcess<MZZAssetTransfer> {

	@Override
	protected void initDocApproveObj() {
		docApprove = poObj;
	}
	
	@Override
	protected void doLogic(){
		transferAsset();
	}

	private void transferAsset() {
		MAsset asset = new MAsset(getCtx(), poObj.getA_Asset_ID(), get_TrxName());
		I_A_Asset assetWrapper = POWrapper.create(asset, I_A_Asset.class);
		assetWrapper.setZZAssetLocation_ID(poObj.getZZAssetLocationTo_ID());
		assetWrapper.setZZAssetRegion_ID(poObj.getZZAssetRegionTo_ID());
		assetWrapper.setAD_User_ID(poObj.getZZ_AD_UserTo_ID());
		asset.saveEx();
	}

}
