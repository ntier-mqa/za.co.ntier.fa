package za.co.ntier.fa.process;

import org.adempiere.model.POWrapper;
import org.compiere.model.MAsset;

import za.co.ntier.fa.model.I_A_Asset;
import za.co.ntier.fa.model.MZZAssetTransfer;

@org.adempiere.base.annotation.Process(name="za.co.ntier.fa.process.AssetTrasferDocApproveProcess")
public class AssetTrasferDocApproveProcess extends BaseDocApproveProcess<MZZAssetTransfer> {

	@Override
	protected void initDocApproveObj() {
		docApprove = poObj;
	}
	
	@Override
	protected String doIt() throws Exception {
		String resultMsg = null;
		resultMsg = super.doIt();
		if (resultMsg == null && IDocApprove.ZZ_DOCSTATUS_Approved.equals(docApprove.getZZ_DocStatus())) {
			transferAsset();
			return null;
		} else {
			return resultMsg;
		}
	}

	private void transferAsset() {
		MAsset asset = new MAsset(getCtx(), poObj.getA_Asset_ID(), get_TrxName());
		I_A_Asset assetWrapper = POWrapper.create(asset, I_A_Asset.class);
		assetWrapper.setM_Locator_ID(poObj.getM_LocatorTo_ID());
		assetWrapper.setM_Warehouse_ID(poObj.getM_WarehouseTo_ID());
		assetWrapper.setAD_User_ID(poObj.getZZ_AD_UserTo_ID());
		asset.saveEx();
	}

}
