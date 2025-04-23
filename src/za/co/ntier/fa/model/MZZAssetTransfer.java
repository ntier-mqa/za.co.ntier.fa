package za.co.ntier.fa.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Env;

import za.co.ntier.fa.process.api.IDocApprove;
import za.co.ntier.model.MZZConfigDocApproveFlow;
import za.co.ntier.model.X_ZZConfigDocApproveFlow;

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
	

	X_ZZConfigDocApproveFlow configDocApproveFlow = MZZConfigDocApproveFlow.getByDocTableID(get_Table_ID());
	@Override
	public boolean isZZ_AllowLineManageApproved() {
		return configDocApproveFlow.isZZ_AllowLineManageApproved();
	}

	@Override
	public boolean isZZ_AllowSnrAdminFinanceApproved() {
		return configDocApproveFlow.isZZ_AllowSnrAdminFinanceApproved();
	}

	@Override
	public String getZZ_FinalWorkflowStateValue() {
		return configDocApproveFlow.getZZ_FinalWorkflowStateValue();
	}
}
