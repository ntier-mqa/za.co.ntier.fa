package za.co.ntier.fa.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_AD_User;
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

	@Override
	public void setZZ_Date_Account_Created(Timestamp ZZ_Date_Account_Created) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Timestamp getZZ_Date_Account_Created() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setZZ_Date_IT_Manager_Approved(Timestamp ZZ_Date_IT_Manager_Approved) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Timestamp getZZ_Date_IT_Manager_Approved() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setZZ_Date_IT_Manager_Rejected(Timestamp ZZ_Date_IT_Manager_Rejected) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Timestamp getZZ_Date_IT_Manager_Rejected() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setZZ_Date_Manager_Approved(Timestamp ZZ_Date_Manager_Approved) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Timestamp getZZ_Date_Manager_Approved() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setZZ_Date_Manager_Rejected(Timestamp ZZ_Date_Manager_Rejected) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Timestamp getZZ_Date_Manager_Rejected() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setZZ_IT_Admin_ID(int ZZ_IT_Admin_ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getZZ_IT_Admin_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public I_AD_User getZZ_IT_Admin() throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setZZ_IT_Manager_ID(int ZZ_IT_Manager_ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getZZ_IT_Manager_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public I_AD_User getZZ_IT_Manager() throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setZZ_Manager_ID(int ZZ_Manager_ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getZZ_Manager_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setZZ_Recommender_ID(int ZZ_Recommender_ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getZZ_Recommender_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setZZ_Exec_Approver_ID(int ZZ_Exec_Approver_ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getZZ_Exec_Approver_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setStartDate(Timestamp StartDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Timestamp getStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEndDate(Timestamp EndDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Timestamp getEndDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setZZ_Date_Recommended(Timestamp ZZ_Date_Recommended) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Timestamp getZZ_Date_Recommended() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setZZ_Date_Not_Approved(Timestamp ZZ_Date_Not_Approved) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Timestamp getZZ_Date_Not_Approved() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setZZ_Date_Not_Recommended(Timestamp ZZ_Date_Not_Recommended) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Timestamp getZZ_Date_Not_Recommended() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setZZ_Snr_Mgr_LP_ID(int ZZ_Snr_Mgr_LP_ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getZZ_Snr_Mgr_LP_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setZZ_Submitter_ID(int ZZ_Submitter_ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getZZ_Submitter_ID() {
		// TODO Auto-generated method stub
		return 0;
	}
}
