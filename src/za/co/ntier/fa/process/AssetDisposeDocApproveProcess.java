package za.co.ntier.fa.process;

import java.sql.Timestamp;
import org.adempiere.util.ProcessUtil;
import org.compiere.model.I_A_Asset;
import org.compiere.model.I_A_Asset_Disposed;
import org.compiere.model.MAssetDisposed;
import org.compiere.model.MColumn;
import org.compiere.model.PO;
import org.compiere.model.X_A_Asset_Disposed;
import org.compiere.process.DocAction;
import org.compiere.util.Env;

@org.adempiere.base.annotation.Process(name="za.co.ntier.fa.process.AssetDisposeDocApproveProcess")
public class AssetDisposeDocApproveProcess extends BaseDocApproveProcess<PO> {
	
	@Override
	protected String doIt() throws Exception {
		String resultMsg = null;
		resultMsg = super.doIt();
		if (resultMsg == null && IDocApprove.ZZ_DOCSTATUS_Completed.equals(docApprove.getZZ_DocStatus())) {
			resultMsg = disposeAsset();
		}
		return resultMsg;
	}
	
	private String disposeAsset() {
		MAssetDisposed assetDisposed = new MAssetDisposed(getCtx(), 0, get_TrxName());
		assetDisposed.setA_Asset_ID(poObj.get_ValueAsInt(I_A_Asset.COLUMNNAME_A_Asset_ID));
		assetDisposed.setA_Disposed_Method(X_A_Asset_Disposed.A_DISPOSED_METHOD_Trade);
		assetDisposed.setDateDoc((Timestamp)poObj.get_Value(I_A_Asset_Disposed.COLUMNNAME_DateAcct));
		assetDisposed.saveEx();
		assetDisposed.processIt(DocAction.ACTION_Complete);

		return null;
	}
}
