package za.co.ntier.fa.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_AD_User;
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

	@Override
	public void setZZ_Date_MFC_Not_Approved(Timestamp ZZ_Date_MFC_Not_Approved) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setZZ_Date_SDL_Approved(Timestamp ZZ_Date_SDL_Approved) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Timestamp getZZ_Date_SDL_Approved() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setZZ_Date_SDL_Not_Approved(Timestamp ZZ_Date_SDL_Not_Approved) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Timestamp getZZ_Date_SDL_Not_Approved() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setZZ_SDL_Fin_Mgr_ID(int ZZ_SDL_Fin_Mgr_ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getZZ_SDL_Fin_Mgr_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public I_AD_User getZZ_SDL_Fin_Mgr() throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}
}
