package za.co.ntier.fa.process;

import java.sql.Timestamp;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAssetAcct;
import org.compiere.model.MAssetDisposed;
import org.compiere.model.MDepreciationExp;
import org.compiere.model.MDepreciationWorkfile;
import org.compiere.model.MDocType;
import org.compiere.model.MPeriod;
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
	
	@Override
	protected void validateData() {
		MPeriod.testPeriodOpen(getCtx(), poObj.getDateAcct(), MDocType.DOCBASETYPE_GLDocument, poObj.getAD_Org_ID());
		
		if (!isDisposedProrated()) {
			MDepreciationWorkfile assetwk = MDepreciationWorkfile.get(getCtx(), poObj.getA_Asset_ID(), X_A_Asset_Disposed.POSTINGTYPE_Actual, get_TrxName());
			if (assetwk != null && assetwk.isDepreciated(poObj.getDateAcct())){
				throw new AdempiereException("@AssetAlreadyDepreciated@");
			}
			
			MDepreciationExp.checkExistsNotProcessedEntries(getCtx(), poObj.getA_Asset_ID(), poObj.getDateAcct(), 
					X_A_Asset_Disposed.POSTINGTYPE_Actual, get_TrxName());
		}
	}
	
	private boolean isDisposedProrated() {
		MDepreciationWorkfile assetwk = MDepreciationWorkfile.get(getCtx(), poObj.getA_Asset_ID(), X_A_Asset_Disposed.POSTINGTYPE_Actual, get_TrxName());
		if (assetwk != null) {
			try {
				MAssetAcct assetAcct = MAssetAcct.forA_Asset_ID(getCtx(), assetwk.getC_AcctSchema_ID(), poObj.getA_Asset_ID(), X_A_Asset_Disposed.POSTINGTYPE_Actual, poObj.getDateAcct(), get_TrxName());
				if (assetAcct != null) {
					return assetAcct.isZZDisposedProrated();
				}
			} catch (Exception e) {
				// If acct not found or error, fallback to false
			}
		}
		return false;
	}
	
	private void disposeAsset() {
		MAssetDisposed assetDisposed = new MAssetDisposed(getCtx(), 0, get_TrxName());
		assetDisposed.setDateDoc(now);
		assetDisposed.setDateAcct(poObj.getDateAcct());
		assetDisposed.setA_Asset_ID(poObj.getA_Asset_ID());
		if (isDisposedProrated()) {
			assetDisposed.setA_Disposed_Method(X_A_Asset_Disposed.A_DISPOSED_METHOD_Prorated);
			Timestamp disposedDate = poObj.getA_Disposed_Date();
			if (disposedDate == null) {
				disposedDate = poObj.getDateAcct();
			}
			assetDisposed.setA_Disposed_Date(disposedDate);
		} else {
			assetDisposed.setA_Disposed_Method(X_A_Asset_Disposed.A_DISPOSED_METHOD_Trade);
			assetDisposed.setA_Disposed_Date(poObj.getDateAcct());
		}
		assetDisposed.saveEx();
		poObj.setA_Asset_Disposed_ID(assetDisposed.getA_Asset_Disposed_ID());
		poObj.saveEx();
		ProcessInfo pi = MWorkflow.runDocumentActionWorkflow(assetDisposed, DocAction.ACTION_Complete);
		if (pi.isError()) {
			throw new AdempiereException(pi.getSummary()); 
		}
	}
}
