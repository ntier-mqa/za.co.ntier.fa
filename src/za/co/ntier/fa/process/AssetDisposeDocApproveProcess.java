package za.co.ntier.fa.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAssetDisposed;
import org.compiere.model.X_A_Asset_Disposed;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.wf.MWorkflow;

import za.co.ntier.fa.model.MZZAssetDisposal;
import za.co.ntier.fa.process.api.AbstractDocApproveProcess;

@org.adempiere.base.annotation.Process(name="za.co.ntier.fa.process.AssetDisposeDocApproveProcess")
public class AssetDisposeDocApproveProcess extends AbstractDocApproveProcess<MZZAssetDisposal> {
	
	@Override
	protected void initDocApproveObj() {
		docApprove = poObj;
	}
	
	@Override
	protected void doLogic(){
		disposeAsset();
	}
	
	private void disposeAsset() {
		MAssetDisposed assetDisposed = new MAssetDisposed(getCtx(), 0, get_TrxName());
		assetDisposed.setA_Asset_ID(poObj.getA_Asset_ID());
		assetDisposed.setA_Disposed_Method(X_A_Asset_Disposed.A_DISPOSED_METHOD_Trade);
		assetDisposed.setDateDoc(poObj.getDateAcct());
		assetDisposed.saveEx();
		poObj.setA_Asset_Disposed_ID(assetDisposed.getA_Asset_Disposed_ID());
		poObj.saveEx();
		ProcessInfo pi = MWorkflow.runDocumentActionWorkflow(assetDisposed, DocAction.ACTION_Complete);
		if (pi.isError()) {
			throw new AdempiereException(pi.getSummary()); 
		}
	}
}
