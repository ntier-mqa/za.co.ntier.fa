package za.co.ntier.fa.model;

import java.sql.ResultSet;
import java.util.Properties;

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
}
