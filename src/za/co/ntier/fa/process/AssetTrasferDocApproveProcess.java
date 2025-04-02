package za.co.ntier.fa.process;

import org.compiere.model.MAsset;

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
		asset.setM_Locator_ID(poObj.getM_LocatorTo_ID());
		asset.saveEx();
	}

}
