package za.co.ntier.fa.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Env;

import za.co.ntier.fa.process.api.IDocApprove;

public class MZZAssetDisposal extends X_ZZ_Asset_Disposal implements IDocApprove{

	private static final long serialVersionUID = -4624637432370615359L;

	public MZZAssetDisposal() {
		super(Env.getCtx(), 0, null);
	}

	public MZZAssetDisposal(Properties ctx, int assetDisposalID, String trxName) {
		super(ctx, assetDisposalID, trxName);
	}

	public MZZAssetDisposal(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
}
