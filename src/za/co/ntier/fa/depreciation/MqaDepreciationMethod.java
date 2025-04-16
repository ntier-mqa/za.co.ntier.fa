package za.co.ntier.fa.depreciation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAssetChange;
import org.compiere.model.MDepreciationExp;
import org.compiere.model.MDepreciationWorkfile;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.idempiere.fa.service.api.DepreciationDTO;
import org.idempiere.fa.service.api.IDepreciationMethod;

import za.co.ntier.fa.model.I_A_Depreciation_Exp;
import za.co.ntier.fa.model.MZZAssetChange;

public class MqaDepreciationMethod implements IDepreciationMethod {
	// save to use at event handle to save to MDepreciationExp
	public static final ThreadLocal<BigDecimal> saveDailyDepreciateRate = new ThreadLocal<>();
	public static final ThreadLocal<BigDecimal> saveMonthlyDepreciateRate = new ThreadLocal<>();
	
	public static final BigDecimal monthsInYear = BigDecimal.valueOf(12);
	public static final BigDecimal daysInYear = BigDecimal.valueOf(365);
	public static final String COLUMN_NAME_ZZ_DAILY_RATE = "ZZ_DailyRate";
	protected BigDecimal averageDaysInMonth;
	protected MDepreciationExp lastDepreciationExp;
	protected MDepreciationWorkfile assetwk = null;

	long periodsCount = 0;
	/**
	 * day start of use life (is "in Service date")
	 * to calculator days, detect number of period
	 */
	LocalDate dayStartOfLife;
	/*
	 * day end of use life
	 * to calculator total days
	 */
	LocalDate dayEndOfLife;
	/*
	 * latest time has change, it separate period to 2 part and need to use old and new rate to calculator depreciation
	 * can be null in case no change from latest depreciated time
	 */
	LocalDate changeDate;
	/*
	 * list of asset change on time not yet depreciated (max 2 item, one for amount other for use life)
	 */
	List<MAssetChange> activeAssetChanges;
	/*
	 * get from latest depreciation, can be null
	 * in case change happen on first period, first range use rate calculator from old value (get from first asset change)
	 */
	BigDecimal dailyDepreciateRate = null;
	BigDecimal monthlyDepreciateRate = null;
	/*
	 * calculated daily rate for current build
	 */
	BigDecimal newDailyDepreciateRate = null;
	BigDecimal newMonthlyDepreciateRate = null;

	/**
	 * to accumulated depreciation so can get remain amount each time calculator
	 */
	BigDecimal accumulatedDep = null;

	/**
	 * get latest depreciation that do depreciated
	 * can be null in case not yet run post depreciation on this asset
	 * @param assetID
	 * @param postingType
	 * @param acctSchemaID
	 * @return
	 */
	private MDepreciationExp getLastDepreciatedExp(int assetID, String postingType, int acctSchemaID, String trxName) {
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

		return new Query(Env.getCtx(), I_A_Depreciation_Exp.Table_Name, whereClause, trxName)
									.setParameters(assetID, postingType, true, true, acctSchemaID)
									.setOrderBy(I_A_Depreciation_Exp.COLUMNNAME_A_Period + " DESC"
												+ "," + I_A_Depreciation_Exp.COLUMNNAME_DateAcct + " DESC")
									.first();
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
		if(assetwk == null) {
			assetwk = new MDepreciationWorkfile(Env.getCtx(), depreciationDTO.depreciationId, depreciationDTO.trxName);
		}

		// period start calculator
		if (depreciationDTO.period == assetwk.getA_Current_Period()) {
			init(depreciationDTO);
		}

		// (1) bring all remain cost to end period
		if (depreciationDTO.period == periodsCount) {
			return assetwk.getRemainingCost(accumulatedDep);
		}

		//(2) start and end date of period calculation
		LocalDate dayStartOfPeriod = null;
		LocalDate dayEndOfPeriod = null;

		// day 1 of calculation period date, at first period it's start date
		if (depreciationDTO.period == 1) {
			dayStartOfPeriod = dayStartOfLife;
		} else {
			dayStartOfPeriod = dayStartOfLife.plusMonths(depreciationDTO.period - 1).with(TemporalAdjusters.firstDayOfMonth());
		}

		// always at day end of month because case last period already done above
		dayEndOfPeriod = dayStartOfPeriod.with(TemporalAdjusters.lastDayOfMonth());

		//(2) days use previous daily rate and days use new daily rate
		BigDecimal daysUseOldRate = BigDecimal.ZERO;
		BigDecimal daysUseNewRate = BigDecimal.ZERO;
		boolean isFullMonths = false;
		// hasn't change or calculator for period before change date so have date for old rate only
		if (changeDate == null || dayEndOfPeriod.isBefore(changeDate)) {
			if (dayStartOfPeriod.getDayOfMonth() == 1) { // full month
				daysUseOldRate = averageDaysInMonth;
				isFullMonths = true;
			} else {
				daysUseOldRate = BigDecimal.valueOf(ChronoUnit.DAYS.between(dayStartOfPeriod, dayEndOfPeriod) + 1);
            }
		// calculator for period after change date so have date for new rate only
		}else if (dayStartOfPeriod.isAfter(changeDate) || changeDate.isEqual(dayStartOfPeriod)){
			if (dayStartOfPeriod.getDayOfMonth() == 1) { // full month, end partial month already done at (1)
				daysUseNewRate = averageDaysInMonth;
				isFullMonths = true;
			} else {
				daysUseNewRate = BigDecimal.valueOf(ChronoUnit.DAYS.between(dayStartOfPeriod, dayEndOfPeriod) + 1);
            }
		}else if ((dayStartOfPeriod.isBefore(changeDate) && dayEndOfPeriod.isAfter(changeDate)) ||
				changeDate.isEqual(dayEndOfPeriod)){// new change split current period
			daysUseOldRate = BigDecimal.valueOf(ChronoUnit.DAYS.between(dayStartOfPeriod, changeDate));
			daysUseNewRate = BigDecimal.valueOf(ChronoUnit.DAYS.between(changeDate, dayEndOfPeriod) + 1);
		}else {
			// not happen, in case something change make this case happen then throw exception
			throw new AdempiereException(Msg.getMsg(Env.getCtx(), "ZZ_AssetRevaluationChangeDateOutOfHandle"));
		}
		
		// (3) amount use old and new rate
		// can be zero when prevDailyRateDays is zero
		BigDecimal amountUseOldRate = daysUseOldRate.compareTo(BigDecimal.ZERO) > 0 && isFullMonths ? monthlyDepreciateRate : daysUseOldRate.multiply(dailyDepreciateRate);
		
		BigDecimal fullAmountPeriod = amountUseOldRate;
		
		// reach to new rate calculator it
		if (newDailyDepreciateRate == null && daysUseNewRate.compareTo(BigDecimal.ZERO) > 0) {// new change split current period
			BigDecimal remainAssetCost = assetwk.getRemainingCost(accumulatedDep.add(amountUseOldRate));//prevDailyRateAmount = zero when change date at end of prev month

    		BigDecimal remainDays = BigDecimal.valueOf(ChronoUnit.DAYS.between(changeDate, dayEndOfLife) + 1);

    		newDailyDepreciateRate = remainAssetCost.divide(remainDays, depreciationDTO.scale, RoundingMode.HALF_DOWN);
    		
    		BigDecimal monthsRemmain = null;
    		
    		if (changeDate.getDayOfMonth() == 1 && dayStartOfLife.getDayOfMonth() == 1) {
    			// plus 1 to end month because ChronoUnit.MONTHS.between exclude end day
    			monthsRemmain = BigDecimal.valueOf(ChronoUnit.MONTHS.between(dayStartOfLife, dayEndOfLife.plusDays(1)));
    		}else {
    			monthsRemmain = remainDays.multiply(monthsInYear).divide(daysInYear, depreciationDTO.scale, RoundingMode.HALF_DOWN);
    		}
    		
    		newMonthlyDepreciateRate = remainAssetCost.divide(monthsRemmain, depreciationDTO.scale, RoundingMode.HALF_DOWN);
		}
		
		// amount use new rate
		if (daysUseNewRate.compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal amountUseNewRate = isFullMonths ? newMonthlyDepreciateRate : daysUseNewRate.multiply(newDailyDepreciateRate);
			fullAmountPeriod = fullAmountPeriod.add(amountUseNewRate);
		}
		
		// save current rate
		if (daysUseNewRate.compareTo(BigDecimal.ZERO) > 0) {
			// save on ThreadLocal for re-get at ModelDepreciationExpHandle to save to MDepreciationExp
			saveDailyDepreciateRate.set(newDailyDepreciateRate);
			saveMonthlyDepreciateRate.set(newMonthlyDepreciateRate);
		}else {
			saveDailyDepreciateRate.set(dailyDepreciateRate);
			saveMonthlyDepreciateRate.set(monthlyDepreciateRate);
		}
		
		// accumulated
		accumulatedDep = accumulatedDep.add(fullAmountPeriod);

		return fullAmountPeriod;
	}

	/**
	 * init at period start calculator to avoid repeat calculation
	 * @param depreciationDTO
	 * @return
	 */
	protected void init(DepreciationDTO depreciationDTO) {
		// get accumulated from MDepreciationWorkfile as init value
		accumulatedDep = assetwk.getA_Accumulated_Depr(assetwk.isFiscal());

		dayStartOfLife = depreciationDTO.inServiceDate.toLocalDateTime().toLocalDate();

		// (1) start date not yet depreciated
		LocalDate startMonthNotDepreciated = null;
		lastDepreciationExp = getLastDepreciatedExp(assetwk.getA_Asset_ID(), assetwk.getPostingType(), assetwk.getC_AcctSchema_ID(), depreciationDTO.trxName);

		if (lastDepreciationExp == null) {
			// in case not depreciate then use account date of MDepreciationWorkfile
			startMonthNotDepreciated = assetwk.getDateAcct().toLocalDateTime().toLocalDate();
		}else {
			// next month of last depreciation (it can get from MDepreciationWorkfile like above)
			startMonthNotDepreciated = lastDepreciationExp.getDateAcct().toLocalDateTime().toLocalDate().plusMonths(1);
		}

		//(2) query change info from latest depreciated time
		Timestamp startTimeNotDepreciated = Timestamp.valueOf(startMonthNotDepreciated.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay());

		// list order by newest to older
		activeAssetChanges = MZZAssetChange.getAssetChangesForRevaluation(assetwk.getA_Asset_ID(), assetwk.getPostingType(),
				assetwk.getC_AcctSchema_ID(), startTimeNotDepreciated, depreciationDTO.trxName);

		if (!activeAssetChanges.isEmpty()) {
			changeDate = activeAssetChanges.get(0).getDateAcct().toLocalDateTime().toLocalDate();
		}

		// (3) average number of days in month
		averageDaysInMonth = daysInYear.divide(monthsInYear, depreciationDTO.scale, RoundingMode.HALF_DOWN);

		// (4) end date of use life
		long monthsLife = depreciationDTO.useFullLife.longValueExact();
		if (dayStartOfLife.getDayOfMonth() == 1) {
			dayEndOfLife = dayStartOfLife.plusMonths(monthsLife - 1).with(TemporalAdjusters.lastDayOfMonth());
		} else {
			dayEndOfLife = dayStartOfLife.plusMonths(monthsLife).minusDays(1);//start day is 12/4/2025 with use life 2 then end day is 11/6/2025 
		}

		//(5) previous/current daily rate
		if (lastDepreciationExp != null) {
			dailyDepreciateRate = (BigDecimal)lastDepreciationExp.get_Value(I_A_Depreciation_Exp.COLUMNNAME_ZZ_DailyRate);
			monthlyDepreciateRate = (BigDecimal)lastDepreciationExp.get_Value(I_A_Depreciation_Exp.COLUMNNAME_ZZMonthlyRate);
			
			if (dailyDepreciateRate == null || monthlyDepreciateRate == null) {// depreciate is calculator not use this plugin
				throw new AdempiereException(Msg.getMsg(Env.getCtx(), "ZZ_AssetRevaluationDailyRateNotFound"));
			}
		}

		// is not depreciated so no saved rate
		// need to get original asset cost and  original use life from first MAssetChange to calculate previous daily rate
		if(changeDate != null && dailyDepreciateRate == null) {
    		MAssetChange firstChange = activeAssetChanges.get(activeAssetChanges.size() - 1);
    		BigDecimal originalAssetCost = assetwk.getActualCost(firstChange.getAssetValueAmt());
    		
    		long useLifeDays = ChronoUnit.DAYS.between(dayStartOfLife, dayEndOfLife) + 1;
			dailyDepreciateRate = originalAssetCost.divide(BigDecimal.valueOf(useLifeDays), depreciationDTO.scale, RoundingMode.HALF_DOWN);
			int useLifeMonths = firstChange.getUseLifeMonths();
			monthlyDepreciateRate = originalAssetCost.divide(BigDecimal.valueOf(useLifeMonths), depreciationDTO.scale, RoundingMode.HALF_DOWN);
        }
		
		if(changeDate == null && dailyDepreciateRate == null){
			// not yes depreciated and nothing change, just use value from assetwk to calculate and use for whole periods
			BigDecimal originalAssetCost = assetwk.getActualCost();

			long useLifeDays = ChronoUnit.DAYS.between(dayStartOfLife, dayEndOfLife) + 1;
			dailyDepreciateRate = originalAssetCost.divide(BigDecimal.valueOf(useLifeDays), depreciationDTO.scale, RoundingMode.HALF_DOWN);
			int useLifeMonths = assetwk.getUseLifeMonths();
			monthlyDepreciateRate = originalAssetCost.divide(BigDecimal.valueOf(useLifeMonths), depreciationDTO.scale, RoundingMode.HALF_DOWN);

		}

		if (dailyDepreciateRate == null) {
			throw new AdempiereException("something wrong this point dailyDepreciateRateForOldAssetValue need to non-null");
		}		
	}

	/**
	 * MQA use pro-rata on first period so need to plus one period in case not start by first day of month
	 */
	@Override
	public long getCountPeriod(DepreciationDTO depreciationDTO) {
		long useFullLife = depreciationDTO.useFullLife.longValueExact();

		if (useFullLife == 0) {
			periodsCount = 0;
		}else {
			periodsCount = depreciationDTO.inServiceDate.toLocalDateTime().toLocalDate().getDayOfMonth() == 1 ?
							useFullLife : useFullLife + 1;
		}

		return periodsCount;
	}

	@Override
	public boolean isPeriodAdjustment() {
		return false;
	}
}
