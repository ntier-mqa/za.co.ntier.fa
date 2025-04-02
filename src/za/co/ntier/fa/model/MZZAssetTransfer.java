package za.co.ntier.fa.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Env;

import za.co.ntier.fa.process.IDocApprove;

public class MZZAssetTransfer extends X_ZZ_AssetTransfer implements IDocApprove {

	private static final long serialVersionUID = -7395188662340168851L;

	public MZZAssetTransfer() {
		super(Env.getCtx(), 0, null);
	}

	public MZZAssetTransfer(Properties ctx, int assetTransferID, String trxName) {
		super(ctx, assetTransferID, trxName);
	}

	public MZZAssetTransfer(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
}
