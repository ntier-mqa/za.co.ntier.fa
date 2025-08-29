package za.co.ntier.fa.process;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.compiere.model.MAsset;
import org.compiere.model.MProcess;
import org.compiere.model.POResultSet;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.ServerProcessCtl;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Trx;

import za.co.ntier.fa.model.I_A_Asset;
import za.co.ntier.fa.model.I_I_ZZAssetVerify;
import za.co.ntier.fa.model.I_ZZAssetVerifyBatch;
import za.co.ntier.fa.model.I_ZZ_AssetTransfer;
import za.co.ntier.fa.model.MZZAssetDisposal;
import za.co.ntier.fa.model.MZZAssetTransfer;
import za.co.ntier.fa.model.X_I_ZZAssetVerify;
import za.co.ntier.fa.model.X_ZZAssetVerifed;
import za.co.ntier.fa.model.X_ZZAssetVerifyBatch;

@org.adempiere.base.annotation.Process(name = "za.co.ntier.fa.process.ImportAssetVerify")
public class ImportAssetVerify extends SvrProcess{

	private int				pADClientID = 0;
	
	@Override
	protected void prepare() {
		
	}

	@Override
	protected String doIt() throws Exception {
		if(pADClientID==0)
			pADClientID = Env.getAD_Client_ID(getCtx());
		
		String sqlCheck = " AND AD_Client_ID=" + pADClientID;
		String whereClause = "NVL(I_IsImported,'N')='N'" + sqlCheck;
		POResultSet<X_I_ZZAssetVerify> rs = new Query(getCtx(), I_I_ZZAssetVerify.Table_Name, whereClause, get_TrxName())
				.setOrderBy(I_I_ZZAssetVerify.COLUMNNAME_I_ZZAssetVerify_ID)
				.scroll();
		
		try{
			Timestamp dateAcct = Timestamp.valueOf(LocalDateTime.now());
			X_ZZAssetVerifyBatch assetVerifyBatch = new X_ZZAssetVerifyBatch(getCtx(), 0, get_TrxName());
			assetVerifyBatch.setDateAcct(dateAcct);
			assetVerifyBatch.saveEx();
			
			while (rs.hasNext()) {
				X_I_ZZAssetVerify xfa = rs.next();
				
				if (X_I_ZZAssetVerify.ZZVERIFIEDSTATUS_Verified.equals(xfa.getZZVerifiedStatus())) {
					X_ZZAssetVerifed assetVerifed = new X_ZZAssetVerifed(getCtx(), 0, get_TrxName());
					
					assetVerifed.setA_Asset_ID(xfa.getA_Asset_ID());
					assetVerifed.setZZAssetVerifyBatch_ID(assetVerifyBatch.getZZAssetVerifyBatch_ID());
					
					assetVerifed.saveEx();
				}else if (X_I_ZZAssetVerify.ZZVERIFIEDSTATUS_Missing.equals(xfa.getZZVerifiedStatus())) {
					MZZAssetDisposal assetDisposal = new MZZAssetDisposal(getCtx(), 0, get_TrxName());
					assetDisposal.setA_Asset_ID(xfa.getA_Asset_ID());
					assetDisposal.setZZAssetVerifyBatch_ID(assetVerifyBatch.getZZAssetVerifyBatch_ID());
					assetDisposal.setDateAcct(dateAcct);
					
					assetDisposal.saveEx();
				}else if (X_I_ZZAssetVerify.ZZVERIFIEDSTATUS_Misplaced.equals(xfa.getZZVerifiedStatus())) {
					MZZAssetTransfer assetTransfer = new MZZAssetTransfer(getCtx(), 0, get_TrxName());
					
					assetTransfer.setA_Asset_ID(xfa.getA_Asset_ID());
					assetTransfer.setZZAssetVerifyBatch_ID(assetVerifyBatch.getZZAssetVerifyBatch_ID());
					assetTransfer.setZZAssetRegionTo_ID(xfa.getZZAssetRegion_ID());
					assetTransfer.setZZAssetLocationTo_ID(xfa.getZZAssetLocation_ID());
					assetTransfer.setZZ_AD_UserTo_ID(xfa.getAD_User_ID());
					assetTransfer.setDateAcct(dateAcct);
					
					assetTransfer.saveEx();
					
					MProcess process = MProcess.get(getCtx(), "ca2d11a1-044a-4ea8-9ae5-112bfdf90219");
					ProcessInfo pi = new ProcessInfo("", process.getAD_Process_ID());
					pi.setRecord_ID(assetTransfer.getZZ_AssetTransfer_ID());
					pi.setTable_ID(I_ZZ_AssetTransfer.Table_ID);
					
					ProcessInfoParameter [] piParameters = new ProcessInfoParameter[] {
							new ProcessInfoParameter("ZZ_ByPassDocApprove", true, null, null, null),
					};
					pi.setParameter(piParameters);
					
					ServerProcessCtl processCtl = ServerProcessCtl.process(pi, Trx.get(get_TrxName(), false), false);
				}
				
				MAsset asset = new MAsset(getCtx(), xfa.getA_Asset_ID(), get_TrxName());
				asset.set_ValueOfColumn(I_A_Asset.COLUMNNAME_ZZVerifiedStatus, xfa.getZZVerifiedStatus());
				asset.set_ValueOfColumn(I_A_Asset.COLUMNNAME_ZZVerifiedDate, dateAcct);
				asset.saveEx();
			}
			
			addBufferLog(0, null, null, "please verify result here", I_ZZAssetVerifyBatch.Table_ID, assetVerifyBatch.getZZAssetVerifyBatch_ID());
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

}
