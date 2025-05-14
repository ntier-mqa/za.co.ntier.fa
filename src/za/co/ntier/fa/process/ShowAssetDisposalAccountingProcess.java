package za.co.ntier.fa.process;

import org.adempiere.webui.acct.WAcctViewer;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.apps.AbstractProcessDialog;
import org.compiere.model.I_A_Asset_Disposed;
import org.compiere.model.MAssetDisposed;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

import za.co.ntier.fa.model.MZZAssetDisposal;

@org.adempiere.base.annotation.Process(name="za.co.ntier.fa.process.ShowAssetDisposalAccountingProcess")
public class ShowAssetDisposalAccountingProcess extends SvrProcess{

	@Override
	protected void prepare() {
		
		
	}

	@Override
	protected String doIt() throws Exception {
		int mqaAssetDisposalId = getRecord_ID();
		if (mqaAssetDisposalId <= 0)
			return null;
		
		MZZAssetDisposal mqaAssetDisposal = new MZZAssetDisposal(getCtx(), mqaAssetDisposalId, get_TrxName());
		int disposalId = mqaAssetDisposal.getA_Asset_Disposed_ID();
		if (disposalId <= 0)
			return null;
		
		MAssetDisposed assetDisposal = new MAssetDisposed(getCtx(), disposalId, get_TrxName());
		if (!assetDisposal.isPosted())
			return null;
		
		AEnv.executeDesktopTask(() -> {

			AbstractProcessDialog ui = (AbstractProcessDialog)getProcessInfo().getProcessUI();
			
			new WAcctViewer(Env.getContextAsInt (getCtx(), ui.getWindowNo(), "AD_Client_ID"),
					I_A_Asset_Disposed.Table_ID, assetDisposal.getA_Asset_Disposed_ID());
		});

		return null;
	}

}
