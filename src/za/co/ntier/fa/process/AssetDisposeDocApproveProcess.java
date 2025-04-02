package za.co.ntier.fa.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAssetDisposed;
import org.compiere.model.X_A_Asset_Disposed;
import org.compiere.process.DocAction;

import za.co.ntier.fa.model.MZZAssetDisposal;

@org.adempiere.base.annotation.Process(name="za.co.ntier.fa.process.AssetDisposeDocApproveProcess")
public class AssetDisposeDocApproveProcess extends BaseDocApproveProcess<MZZAssetDisposal> {
	
	@Override
	protected void initDocApproveObj() {
		docApprove = poObj;
	}
	
	@Override
	protected String doIt() throws Exception {
		String resultMsg = null;
		resultMsg = super.doIt();
		if (resultMsg == null && IDocApprove.ZZ_DOCSTATUS_Completed.equals(docApprove.getZZ_DocStatus())) {
			disposeAsset();
			return null;
		}else {
			return resultMsg;
		}
	}
	
	private void disposeAsset() {
		MAssetDisposed assetDisposed = new MAssetDisposed(getCtx(), 0, get_TrxName());
		assetDisposed.setA_Asset_ID(poObj.getA_Asset_ID());
		assetDisposed.setA_Disposed_Method(X_A_Asset_Disposed.A_DISPOSED_METHOD_Trade);
		assetDisposed.setDateDoc(poObj.getDateAcct());
		assetDisposed.saveEx();
		poObj.setA_Asset_Disposed_ID(assetDisposed.getA_Asset_Disposed_ID());
		poObj.saveEx();
		boolean success = assetDisposed.processIt(DocAction.ACTION_Complete);
		if (!success) {
			throw new AdempiereException(assetDisposed.getProcessMsg()); 
		}
	}
}
