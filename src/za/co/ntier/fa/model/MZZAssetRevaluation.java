package za.co.ntier.fa.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_AD_User;
import org.compiere.util.Env;

import za.co.ntier.fa.process.api.IDocApprove;

public class MZZAssetRevaluation  extends X_ZZ_AssetRevaluation implements IDocApprove{

	private static final long serialVersionUID = -7284081404629735562L;

	public MZZAssetRevaluation() {
		super(Env.getCtx(), 0, null);
	}

	public MZZAssetRevaluation(Properties ctx, int assetDisposalID, String trxName) {
		super(ctx, assetDisposalID, trxName);
	}

	public MZZAssetRevaluation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	public void setZZ_Date_MFC_Approved(Timestamp ZZ_Date_MFC_Approved) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Timestamp getZZ_Date_MFC_Not_Approved() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isZZ_AllowMgrFinConsumablesApproval() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setZZ_Mgr_Fin_Consumables_ID(int ZZ_Mgr_Fin_Consumables_ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getZZ_Mgr_Fin_Consumables_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public I_AD_User getZZ_Mgr_Fin_Consumables() throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}
}
