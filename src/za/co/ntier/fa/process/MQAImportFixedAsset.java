package za.co.ntier.fa.process;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MAsset;
import org.compiere.model.MAssetAcct;
import org.compiere.model.MAssetAddition;
import org.compiere.model.MAssetGroupAcct;
import org.compiere.model.MDepreciationWorkfile;
import org.compiere.model.MIFixedAsset;
import org.compiere.model.MProcessPara;
import org.compiere.model.POResultSet;
import org.compiere.model.Query;
import org.compiere.model.X_I_FixedAsset;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

import za.co.ntier.fa.model.I_I_FixedAsset;

@org.adempiere.base.annotation.Process(name = "org.idempiere.fa.process.ImportFixedAsset")
public class MQAImportFixedAsset extends SvrProcess{
	/**	Client to be imported to		*/
	private int				p_AD_Client_ID = 0;
	/**	Organization to be imported to	*/
	private int				p_AD_Org_ID = 0;

	/** Account Date					*/
	private Timestamp		p_DateAcct = null;

	/** Validate Only - only validate import data */
	private boolean			p_IsValidateOnly = false;
	
	/**	Delete old Imported				*/
	private boolean			p_DeleteOldImported = true;
	
	String sqlCheck;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("DateAcct"))
				p_DateAcct = ((Timestamp)para[i].getParameter());
			else if (name.equals("DeleteOldImported"))
				p_DeleteOldImported = "Y".equals(para[i].getParameter());
			else if (name.equals("IsValidateOnly"))
				p_IsValidateOnly = "Y".equals(para[i].getParameter());
			else
				MProcessPara.validateUnknownParameter(getProcessInfo().getAD_Process_ID(), para[i]);
		}
		
		if(p_AD_Client_ID==0)
			p_AD_Client_ID = Env.getAD_Client_ID(getCtx());
		
		sqlCheck = " AND AD_Client_ID=" + p_AD_Client_ID;
	}	//	prepare

	String createLocalTrx(String functionName) {
		Trx trx = Trx.get(Trx.createTrxName("MQAImportFixedAsset." + functionName), true);
		trx.setDisplayName(getClass().getName()+ "_" + functionName);
		return trx.getTrxName();
	}
	
	StringBuilder sql = null;
	protected void fillData() throws SQLException {
		String trxName = createLocalTrx("fillData");
		
		int no = 0;

		//	****	Prepare	****

		//	Delete Old Imported
		if (p_DeleteOldImported)
		{
			sql = new StringBuilder ("DELETE FROM "+X_I_FixedAsset.Table_Name
				  + " WHERE I_IsImported='Y'").append (sqlCheck);
			no = DB.executeUpdateEx(sql.toString(), trxName);
			if (log.isLoggable(Level.FINE)) log.fine("Delete Old Imported =" + no);
		}
		
		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+ " "
			  + "SET AD_Client_ID = COALESCE (AD_Client_ID,").append (p_AD_Client_ID).append ("),"
			  + " AD_Org_ID = COALESCE (AD_Org_ID,").append (p_AD_Org_ID).append ("),"
			  + " IsActive = COALESCE (IsActive, 'Y'),"
			  + " Created = COALESCE (Created, getDate()),"
			  + " CreatedBy = COALESCE (CreatedBy, 0),"
			  + " Updated = COALESCE (Updated, getDate()),"
			  + " UpdatedBy = COALESCE (UpdatedBy, 0),"
			  + " I_ErrorMsg = ' ',"
			  + " I_IsImported = 'N' "
			  + "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString(), trxName);
		if (log.isLoggable(Level.INFO)) log.info ("Reset=" + no);
		
		// Check if Org is Null or 0
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Org, '"
			+ "WHERE (AD_Org_ID IS NULL OR AD_Org_ID=0"
			+ " OR EXISTS (SELECT * FROM AD_Org oo WHERE ifa.AD_Org_ID=oo.AD_Org_ID AND (oo.IsSummary='Y' OR oo.IsActive='N')))"
			+ " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		if (no != 0)
			log.warning ("Invalid Org=" + no);
		
		// Check if Name is Null
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Name Is Mandatory, '"
			+ "WHERE Name IS NULL AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		if (no != 0)
			log.warning ("Invalid Name=" + no);
		
		// Asset Group From Value
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			  + "SET A_Asset_Group_ID=(SELECT MAX(A_Asset_Group_ID) FROM A_Asset_Group t"
			  + " WHERE ifa.A_Asset_Group_Value=t.Name AND ifa.AD_Client_ID=t.AD_Client_ID) "
			  + "WHERE A_Asset_Group_ID IS NULL AND A_Asset_Group_Value IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		if (log.isLoggable(Level.FINE)) log.fine("Set Asset Group from Value=" + no);
		
		// Check if Asset Group Have Asset Group Acct Record
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Asset Group Doesnt Have Asset Group Acct Record, ' "
			+ "WHERE A_Asset_Group_ID IS NOT NULL AND A_Asset_Group_ID>0 " //@win change to AND from OR
			+ "AND NOT EXISTS (SELECT 1 FROM A_Asset_Group_Acct aga WHERE ifa.A_Asset_Group_ID=aga.A_Asset_Group_ID) " //@win change to AND from OR
			+ "AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		if (no != 0)
			log.warning ("Invalid Asset Group=" + no);
		
		// Asset Type From Value
		/* commented by @win
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			  + "SET A_Asset_Type_ID=(SELECT MAX(A_Asset_Type_ID) FROM A_Asset_Type t"
			  + " WHERE ifa.A_Asset_Type_Value=t.Value AND ifa.AD_Client_ID=t.AD_Client_ID) "
			  + "WHERE A_Asset_Type_ID IS NULL AND A_Asset_Type_Value IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Asset Type from Value=" + no);
		*/
		
		// BP From Value
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			  + "SET C_BPartnerSR_ID=(SELECT MAX(C_BPartner_ID) FROM C_BPartner t"
			  + " WHERE ifa.BPartner_Value=t.Value AND ifa.AD_Client_ID=t.AD_Client_ID) "
			  + "WHERE C_BPartnerSR_ID IS NULL AND BPartner_Value IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		if (log.isLoggable(Level.FINE)) log.fine("Set BP from Value=" + no);
		
		// City From Value
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			  + "SET C_City_ID=(SELECT MAX(C_City_ID) FROM C_City t"
			  + " WHERE ifa.C_City_Value=t.Name AND ifa.AD_Client_ID=t.AD_Client_ID) "
			  + "WHERE C_City_ID IS NULL AND C_City_Value IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		if (log.isLoggable(Level.FINE)) log.fine("Set City from Value=" + no);
		
		// Product 
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			  + "SET M_Product_ID=(SELECT MAX(M_Product_ID) FROM M_Product t"
			  + " WHERE ifa.ProductValue=t.Value AND ifa.AD_Client_ID=t.AD_Client_ID) "
			  + "WHERE M_Product_ID IS NULL AND ProductValue IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		if (log.isLoggable(Level.FINE)) log.fine("Set Product from Value=" + no);
		
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			  + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Product, ' "
			  + "WHERE M_Product_ID IS NULL AND ProductValue IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		if (no != 0)
			log.warning ("Invalid Product=" + no);
		
		// Check if Product using Product Category has A Asset Category Set
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Product Using Product Category Without Asset Group Defined, ' "
			+ "WHERE EXISTS (SELECT 1 FROM M_Product p "
			+ "JOIN M_Product_Category pc ON p.M_Product_Category_ID=pc.M_Product_Category_ID "
			+ "WHERE ifa.M_Product_ID=p.M_Product_ID "
			+ "AND (pc.A_Asset_Group_ID=0 OR pc.A_Asset_Group_ID IS NULL)) "
			+ "AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		if (no != 0)
			log.warning ("Invalid Product Category=" + no);
				
		// Locator From Value
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			  + "SET M_Locator_ID=(SELECT MAX(M_Locator_ID) FROM M_Product t"
			  + " WHERE ifa.LocatorValue=t.Value AND ifa.AD_Client_ID=t.AD_Client_ID) "
			  + "WHERE M_Locator_ID IS NULL AND LocatorValue IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		if (log.isLoggable(Level.FINE)) log.fine("Set Locator from Value=" + no);
		
		//	Currency From ISO Code
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa ")
			.append("SET C_Currency_ID=(SELECT c.C_Currency_ID FROM C_Currency c")
			.append(" WHERE c.ISO_Code=ifa.ISO_Code AND c.AD_Client_ID IN (0,ifa.AD_Client_ID)) ")
			.append("WHERE C_Currency_ID IS NULL AND ISO_Code IS NOT NULL")
			.append(" AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		if (log.isLoggable(Level.FINE)) log.fine("Set Currency from ISO=" + no);
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa ")
			.append("SET C_Currency_ID=(SELECT a.C_Currency_ID FROM C_AcctSchema a")
			.append(" WHERE a.C_AcctSchema_ID=ifa.C_AcctSchema_ID AND a.AD_Client_ID=ifa.AD_Client_ID)")
			.append("WHERE C_Currency_ID IS NULL AND ISO_Code IS NULL")
			.append(" AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		if (log.isLoggable(Level.FINE)) log.fine("Set Default Currency=" + no);
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa ")
			.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Currency, '")
			.append("WHERE (C_Currency_ID IS NULL OR C_Currency_ID=0)")
			.append(" AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		if (no != 0)
			log.warning ("Invalid Currency=" + no);
			
		//	AcctSchema From Name
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa ")
			.append("SET C_AcctSchema_ID=(SELECT a.C_AcctSchema_ID FROM C_AcctSchema a")
			.append(" WHERE ifa.AcctSchemaName=a.Name AND ifa.AD_Client_ID=a.AD_Client_ID) ")
			.append("WHERE C_AcctSchema_ID IS NULL AND AcctSchemaName IS NOT NULL")
			.append(" AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		if (log.isLoggable(Level.FINE)) log.fine("Set AcctSchema from Name=" + no);
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa ")
			.append("SET C_AcctSchema_ID=(SELECT c.C_AcctSchema1_ID FROM AD_ClientInfo c WHERE c.AD_Client_ID=ifa.AD_Client_ID) ")
			.append("WHERE C_AcctSchema_ID IS NULL AND AcctSchemaName IS NULL")
			.append(" AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		if (log.isLoggable(Level.FINE)) log.fine("Set AcctSchema from Client=" + no);
		//	Error AcctSchema
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa ")
			.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid AcctSchema, '")
			.append("WHERE (C_AcctSchema_ID IS NULL OR C_AcctSchema_ID=0")
			.append(" OR NOT EXISTS (SELECT * FROM C_AcctSchema a WHERE ifa.AD_Client_ID=a.AD_Client_ID))")
			.append(" AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		if (no != 0)
			log.warning ("Invalid AcctSchema=" + no);
		
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa ")
			.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=AssetServiceDate Is Mandatory, '")
			.append("WHERE AssetServiceDate IS NULL AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		if (no != 0)
			log.warning ("No AssetServiceDate=" + no);
		
		sql = new StringBuilder ("UPDATE "+MIFixedAsset.Table_Name+" ifa ")
			.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=AssetDepreciationDate Is Mandatory, '")
			.append("WHERE AssetDepreciationDate IS NULL AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), trxName);
		if (no != 0)
			log.warning ("No AssetDepreciationDate=" + no);
		
		closeTrx(trxName, false);
	}
	
	void closeTrx(String trxName, boolean slient) throws SQLException {
		Trx tran = Trx.get(trxName, false);
		try {
			tran.commit(true);
		} catch (SQLException e) {
			if (!slient) {
				throw e;
			}
		}finally {
			tran.close();
		}	
	}
	
	
	/**
	 *  Perform process.
	 *  @return Message
	 *  @throws Exception
	 */
	@Override
	protected String doIt() throws java.lang.Exception
	{
		fillData();
		
		if (p_IsValidateOnly)
			return "Data Was Validated";

		int noInsert = 0;
		int noUpdate = 0;
		
		String whereClause = "NVL(I_IsImported,'N')='N'"+sqlCheck;
		POResultSet<X_I_FixedAsset> rs = new Query(getCtx(), X_I_FixedAsset.Table_Name, whereClause, null)
				.setOrderBy(X_I_FixedAsset.COLUMNNAME_I_FixedAsset_ID)
				.scroll();
		String trxBatchName = null;
		Trx trxBatch = null;
		Trx trxMain = Trx.get(get_TrxName(), false);
		try
		{
			long timmer = 10 * 60 * 1000; // 10 minutes
			int batchSize = 0;
			int rowsProcessed = 0;
			
			while (rs.hasNext()) {
				if (batchSize > 100) {
					closeTrx(trxBatchName, false);
					batchSize = 0;
					trxBatch = null;
					trxBatchName = null;
				}
				
				if (batchSize == 0) {
					trxBatchName = createLocalTrx("import fix Asset:" + rowsProcessed);
					trxBatch = Trx.get(trxBatchName, false);
					trxBatch.setTimeout(20*60);
				}
				
				long startProcessTime = System.currentTimeMillis();
				
				X_I_FixedAsset xfa = rs.next();
				MIFixedAsset ifa = new MIFixedAsset(getCtx(), xfa.getI_FixedAsset_ID(), trxBatchName);
				
				if (ifa.getA_Accumulated_Depr().compareTo(BigDecimal.ZERO) < 0 ||
						(ifa.getA_Asset_Cost().compareTo(BigDecimal.ZERO) == 0 && "Active".equalsIgnoreCase(ifa.get_ValueAsString(I_I_FixedAsset.COLUMNNAME_A_Asset_Status)))) {
					ifa.setI_ErrorMsg("Error: cost = " + ifa.getA_Asset_Cost() + " - Accumulated = " + ifa.getA_Accumulated_Depr());
					ifa.saveEx();
					continue;
				}
				
				try
				{
					rowsProcessed++;
					batchSize++;
					
					if(ifa.getA_Asset_Cost().compareTo(BigDecimal.ZERO) == 0 && "Retired".equalsIgnoreCase(ifa.get_ValueAsString(I_I_FixedAsset.COLUMNNAME_A_Asset_Status))){
						MAsset asset = new MAsset(ifa);
						asset.setA_Asset_Status(MAsset.A_ASSET_STATUS_Retired);
						asset.saveEx();
						
					
						ifa.setA_Asset_ID(asset.getA_Asset_ID());
						ifa.setI_IsImported(true);
						ifa.setI_ErrorMsg(null);
						ifa.setProcessed(true);
						ifa.saveEx();
						noInsert++;
						continue;
					}
					
					boolean isUpdate = ifa.getA_Asset_ID() > 0;
					if (ifa.getA_Asset_ID() == 0)
					{
						//	check for existing active asset by name and inventory no
						sql = new StringBuilder("SELECT A_Asset_ID FROM A_Asset WHERE IsActive='Y' AND Name=? AND InventoryNo=?");
						int A_Asset_ID = DB.getSQLValue(trxBatchName, sql.toString(), ifa.getName(), ifa.getInventoryNo());
						if (A_Asset_ID > 0)
						{
							isUpdate = true;
							ifa.setA_Asset_ID(A_Asset_ID);
							ifa.saveEx();
						}
					}
					
					if (ifa.getDateAcct() == null && p_DateAcct != null)
						ifa.setDateAcct(p_DateAcct);
							
					MAssetAddition assetAdd = MAssetAddition.createAsset(ifa);
					if (assetAdd == null)
					{
						ifa.setI_ErrorMsg("Failed Create Assets");
						ifa.saveEx();
						assetAdd = null;
						continue;
					}
					
					ifa.setA_Asset_ID(assetAdd.getA_Asset_ID());
					
					if (isUpdate)
					{
						MAsset asset = MAsset.get(getCtx(), ifa.getA_Asset_ID(), trxBatchName);
						for (MAssetGroupAcct assetgrpacct :  MAssetGroupAcct.forA_Asset_Group_ID(getCtx(), asset.getA_Asset_Group_ID(), null, trxBatchName))
						{			
							if (assetgrpacct.getAD_Org_ID() == 0 || assetgrpacct.getAD_Org_ID() == asset.getAD_Org_ID()) 
							{
								if (assetgrpacct.getC_AcctSchema_ID() != ifa.getC_AcctSchema_ID())
									continue;
								
								// Asset Accounting
								MAssetAcct assetacct = new MAssetAcct(asset, assetgrpacct);
								assetacct.setAD_Org_ID(asset.getAD_Org_ID()); //added by @win
								assetacct.saveEx();
								
								// Asset Depreciation Workfile
								MDepreciationWorkfile assetworkFile = new MDepreciationWorkfile(asset, assetacct.getPostingType(), assetgrpacct);
								assetworkFile.setAD_Org_ID(asset.getAD_Org_ID()); //added by @win
								assetworkFile.setUseLifeYears(assetgrpacct.getUseLifeYears());
								assetworkFile.setUseLifeMonths(assetgrpacct.getUseLifeMonths());
								assetworkFile.setUseLifeYears_F(assetgrpacct.getUseLifeYears_F());
								assetworkFile.setUseLifeMonths_F(assetgrpacct.getUseLifeMonths_F());
								assetworkFile.saveEx();
							}
						}
					}
					
					for (MDepreciationWorkfile assetworkFile : MDepreciationWorkfile.forA_Asset_ID(getCtx(), ifa.getA_Asset_ID(), trxBatchName))
					{
						if (assetworkFile.getC_AcctSchema_ID() != ifa.getC_AcctSchema_ID())
							continue;
						
						if (ifa.getAssetDepreciationDate() != null)
							assetworkFile.setAssetDepreciationDate(ifa.getAssetDepreciationDate());
						
						assetworkFile.setDateAcct(assetAdd.getDateAcct());
						
						BigDecimal assetCost = ifa.getA_Asset_Cost();
						if (assetCost == null)
							assetCost = Env.ZERO;
						assetworkFile.setA_Asset_Cost(assetCost);
						
						BigDecimal accumulatedDepr = ifa.getA_Accumulated_Depr();
						if (accumulatedDepr == null)
							accumulatedDepr = Env.ZERO;
						assetworkFile.setA_Accumulated_Depr(accumulatedDepr);
						
						BigDecimal accumulatedDeprF = ifa.getA_Accumulated_Depr_F();
						if (accumulatedDeprF == null)
							accumulatedDeprF = Env.ZERO;
						assetworkFile.setA_Accumulated_Depr_F(accumulatedDeprF);
						
						BigDecimal salvageValue = ifa.getA_Salvage_Value();
						if (salvageValue == null)
							salvageValue = Env.ZERO;
						assetworkFile.setA_Salvage_Value(salvageValue);
						
						assetworkFile.setUseLifeMonths(ifa.getUseLifeMonths());
						assetworkFile.setUseLifeMonths_F(ifa.getUseLifeMonths_F());
						
						int currentPeriod = ifa.getA_Current_Period();
						if (currentPeriod == 0)
						{
							int useLifeMonths = ifa.getUseLifeMonths();
							if (useLifeMonths > 0)
								currentPeriod = (int) (accumulatedDepr.doubleValue() / (assetCost.doubleValue() / useLifeMonths));
						}
						assetworkFile.setA_Current_Period(currentPeriod > 0 ? currentPeriod : 1);
						
						assetworkFile.saveEx();
					}
					
					//	Process Asset Addition Based on Document Action
					if (!assetAdd.processIt(ifa.getDocAction()))
					{
						ifa.setI_ErrorMsg(assetAdd.getProcessMsg());
						ifa.saveEx();
						assetAdd = null;
						continue;
					}
					assetAdd.saveEx();
					
					ifa.setI_IsImported(true);
					ifa.setI_ErrorMsg(null);
					ifa.setProcessed(true);
					ifa.saveEx();
					
					if (isUpdate)
						noUpdate++;
					else
						noInsert++;
					
					long endProcessTime = System.currentTimeMillis();
					log.warning(String.format("Processed rows %d in %.2f s", noInsert, (endProcessTime - startProcessTime)/1000f));
				
					if (trxBatch.getTimeout() > 0 && trxBatch.getStartTime().getTime() > 0) {
						long trxBatchSpendTime = System.currentTimeMillis() - trxBatch.getStartTime().getTime();
						
						if (trxBatchSpendTime > (trxBatch.getTimeout() * 1000 - timmer)) {
							trxBatch.setTimeout(trxBatch.getTimeout() + 10 * 60); // extend timeout by 10 minutes
							log.warning(String.format("Transaction timeout extended by 10 minutes: record:%d trxName:%s ", rowsProcessed, trxBatchName));
						}
					}
					
					if (trxMain.getTimeout() > 0 && trxMain.getStartTime().getTime() > 0) {
						long trxMainSpendTime = System.currentTimeMillis() - trxMain.getStartTime().getTime();
						
						if (trxMainSpendTime > (trxMain.getTimeout() * 1000 - timmer)) {
							trxMain.setTimeout(trxMain.getTimeout() + 10 * 60); // extend timeout by 10 minutes
							log.warning(String.format("Transaction timeout extended by 10 minutes: record:%d trxName:%s ", rowsProcessed, get_TrxName()));
						}
					}
				}
				catch (Exception e) {
					ifa.setI_ErrorMsg(e.getLocalizedMessage());
					ifa.saveEx();
				}
			}
		}
		finally
		{
			if (trxBatch != null && trxBatch.isActive()) {
				closeTrx(trxBatchName, false);
				trxBatch = null;
				trxBatchName = null;
			}
			
			DB.close(rs); rs = null;
			//
			sql = new StringBuilder ("UPDATE "+X_I_FixedAsset.Table_Name)
				.append(" SET I_IsImported='N', Updated=SysDate ")
				.append("WHERE I_IsImported<>'Y'").append(sqlCheck);
			int no = DB.executeUpdate(sql.toString(), get_TrxName());
			addLog (0, null, new BigDecimal (no), "@Errors@");
			addLog (0, null, new BigDecimal (noInsert), "@A_Asset_ID@: @Inserted@");
			addLog (0, null, new BigDecimal (noUpdate), "@A_Asset_ID@: @Updated@");
		}
		
		return "";
	}	//	doIt
}
