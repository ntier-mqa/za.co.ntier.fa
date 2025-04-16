package za.co.ntier.fa.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAsset;
import org.compiere.model.MAssetChange;
import org.compiere.model.MDepreciationWorkfile;
import org.compiere.model.X_A_Depreciation_Workfile;
import org.compiere.util.Msg;

import za.co.ntier.fa.model.I_ZZ_AssetRevaluation;
import za.co.ntier.fa.model.MZZAssetChange;
import za.co.ntier.fa.model.MZZAssetRevaluation;

@org.adempiere.base.annotation.Process(name="za.co.ntier.fa.process.AssetTrasferDocApproveProcess")
public class AssetRevaluationDocApproveProcess extends BaseDocApproveProcess<MZZAssetRevaluation> {
	@Override
	protected void initDocApproveObj() {
		docApprove = poObj;
	}

	MDepreciationWorkfile dwf;
	MAsset asset;

	@Override
	protected void validateData() {
		int assetId = poObj.getA_Asset_ID();
		asset = MAsset.get(getCtx(), assetId, null);
		if(assetId == 0) {
			throw new AdempiereException(Msg.getMsg(getCtx(), "ZZ_AssetRevaluationAssetNotFound"));
		}

		int acctSchemaId = poObj.getC_AcctSchema_ID();
		dwf = MDepreciationWorkfile.get(getCtx(), assetId, X_A_Depreciation_Workfile.POSTINGTYPE_Actual, null, acctSchemaId);

		if (dwf == null) {
			throw new AdempiereException(Msg.getMsg(getCtx(), "ZZ_AssetRevaluationBalancesNotFound"));
		}

		if (poObj.get_Value(I_ZZ_AssetRevaluation.COLUMNNAME_A_Asset_Cost) == null &&
				poObj.get_Value(I_ZZ_AssetRevaluation.COLUMNNAME_UseLifeYears) == null) {
			throw new AdempiereException(Msg.getMsg(getCtx(), "ZZ_AssetRevaluationNothingChange"));
		}
	}

	@Override
	protected void doLogic(){
		revaluationAsset();
	}

	protected void saveAssetBeforeChange(MAssetChange change, MDepreciationWorkfile dwf) {
		change.setA_Period_Start(dwf.getA_Current_Period());
		change.setUseLifeMonths(dwf.getUseLifeMonths());
		change.setAssetValueAmt(dwf.getA_Asset_Cost());
	}

	protected void saveAssetAfterChange(MAssetChange change, MDepreciationWorkfile dwf) {

	}

	private void revaluationAsset() {
		boolean isCostChange = poObj.get_Value(I_ZZ_AssetRevaluation.COLUMNNAME_A_Asset_Cost) != null;
		MAssetChange change = MZZAssetChange.createAssetChangeForRevaluation(getCtx(), get_TrxName(), isCostChange,
				poObj.getA_Asset_ID(), dwf.getPostingType(), poObj.getC_AcctSchema_ID(),
				dwf.getA_Accumulated_Depr(false), poObj.getDateAcct());

		saveAssetBeforeChange(change, dwf);
		if (isCostChange) {//ZZ_AssetRevaluationNothingChange validate make sure one thing need to change, when both change then priority is cost
			dwf.setA_Asset_Cost(poObj.getA_Asset_Cost());
		}else {
			dwf.setUseLifeYears(poObj.getUseLifeYears());
			dwf.setUseLifeMonths(poObj.getUseLifeMonths());
			dwf.setA_Life_Period(poObj.getUseLifeMonths());
			
			dwf.setUseLifeYears_F(poObj.getUseLifeYears());
			dwf.setUseLifeMonths_F(poObj.getUseLifeMonths());
			dwf.setA_Life_Period_F(poObj.getUseLifeMonths());
		}

		dwf.saveEx(get_TrxName());
		saveAssetAfterChange(change, dwf);

		change.saveEx();

		dwf.buildDepreciation();
	}
}
