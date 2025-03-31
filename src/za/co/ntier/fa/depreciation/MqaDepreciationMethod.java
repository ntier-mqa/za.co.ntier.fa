package za.co.ntier.fa.depreciation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Optional;

import org.compiere.model.I_A_Depreciation_Exp;
import org.compiere.model.MDepreciationExp;
import org.compiere.model.MDepreciationWorkfile;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.idempiere.fa.service.api.DepreciationDTO;
import org.idempiere.fa.service.api.IDepreciationMethod;

public class MqaDepreciationMethod implements IDepreciationMethod {
	public static final BigDecimal monthsOfYear = BigDecimal.valueOf(12);
	public static final BigDecimal datsOfYear = BigDecimal.valueOf(365);
	
	protected Optional<MDepreciationExp> cacheLatestDepreciateionExp = null;
	protected Optional<MDepreciationWorkfile> depreciationWorkfile = Optional.empty();
	
	/**
	 * get latest depreciation expense from database, don't direct use this one, instead use getLatestDepreciateionExp
	 * @param assetID
	 * @param postingType
	 * @param acctSchemaID
	 * @return
	 */
	private MDepreciationExp getLatestDepreciateionExpFromDB(int assetID, String postingType, int acctSchemaID) {
		String whereClause = String.format("""
				%s = ?
				AND %s = ?
				AND %s = ? AND IsActive=?
				AND %s = ?
				""", 
				I_A_Depreciation_Exp.COLUMNNAME_A_Asset_ID,
				I_A_Depreciation_Exp.COLUMNNAME_PostingType,
				I_A_Depreciation_Exp.COLUMNNAME_Processed,
				I_A_Depreciation_Exp.COLUMNNAME_C_AcctSchema_ID);
		
		return new Query(Env.getCtx(), I_A_Depreciation_Exp.Table_Name, whereClause, null)
									.setParameters(assetID, postingType, true, true, acctSchemaID)
									.setOrderBy(I_A_Depreciation_Exp.COLUMNNAME_A_Period + " DESC"
												+ "," + I_A_Depreciation_Exp.COLUMNNAME_DateAcct + " DESC")
									.first();
	}
	
	private MDepreciationWorkfile getAssetWkFile(DepreciationDTO depreciationDTO) {
		if (depreciationWorkfile.isEmpty()) {
			depreciationWorkfile = Optional
					.of(new MDepreciationWorkfile(Env.getCtx(), depreciationDTO.depreciationId, depreciationDTO.trxName));
		}
		
		return depreciationWorkfile.orElseThrow();
	}
	/**
	 * get latest depreciation expense that processed, use cache to avoid re-query again (it also cache null in case no data found)
	 * @param assetID
	 * @param postingType
	 * @param acctSchemaID
	 * @return
	 */
	public MDepreciationExp getLatestDepreciateionExp(int assetID, String postingType, int acctSchemaID) {
		if (cacheLatestDepreciateionExp == null) {
			MDepreciationExp depexp = getLatestDepreciateionExpFromDB(assetID, postingType, acctSchemaID);
			// even null still set to cache to don't re-query again
			cacheLatestDepreciateionExp = Optional.ofNullable(depexp);
		}
		
		return cacheLatestDepreciateionExp.orElse(null);
	}
	/**
	 * logic cover bellow case
	 * 1. first period start 1st day of month (case 1)
	 * 2. first period start not 1st day of month (case 2)
	 * 3. other period (case not add more value)
	 * 4. last period (for both case 1, 2) (case not add more value) round value is more to this period 
	 */
	@Override
	public BigDecimal caclulateDepreciation(DepreciationDTO depreciationDTO) {
		BigDecimal amountDepreciation = depreciationDTO.totalAmount.subtract(depreciationDTO.salvage);
		
		LocalDate startDate = depreciationDTO.inServiceDate.toLocalDateTime().toLocalDate();
		
		MDepreciationWorkfile assetwk = getAssetWkFile(depreciationDTO);
		
		MDepreciationExp latestDepreciationExp = getLatestDepreciateionExp(assetwk.getA_Asset_ID(), assetwk.getPostingType(), assetwk.getC_AcctSchema_ID());
		
		if (depreciationDTO.period == 1 && startDate.getDayOfMonth() == 1) {
			return amountDepreciation.divide(depreciationDTO.useFullLife, depreciationDTO.scale, RoundingMode.HALF_DOWN);
		}else if (depreciationDTO.period == 1 && startDate.getDayOfMonth() != 1) {
			BigDecimal dailyDepreciate = amountDepreciation.multiply(monthsOfYear)
											.divide(depreciationDTO.useFullLife, depreciationDTO.scale, RoundingMode.HALF_DOWN)
											.divide(datsOfYear, depreciationDTO.scale, RoundingMode.HALF_DOWN);
			
			int daysInFirstMonth =  YearMonth.from(startDate).lengthOfMonth();
			int daysInFirstPeriod = daysInFirstMonth - startDate.getDayOfMonth() + 1;
			return dailyDepreciate.multiply(BigDecimal.valueOf(daysInFirstPeriod));
		}
		
		
		return amountDepreciation.divide(depreciationDTO.useFullLife, depreciationDTO.scale, RoundingMode.HALF_DOWN);
	}
	
	/**
	 * MQA use pro-rata on first period so need to plus one period in case not start by first day of month
	 */
	@Override
	public long getCountPeriod(DepreciationDTO depreciationDTO) {
		long useFullLife = depreciationDTO.useFullLife.longValueExact();

		if (useFullLife == 0L) {
			return 0;
		}else {
			return depreciationDTO.inServiceDate.toLocalDateTime().toLocalDate().getDayOfMonth() == 1 ?
							useFullLife : useFullLife + 1;
		}

	}
	
	@Override
	public boolean isPeriodAdjustment() {
		return false;
	}
}
