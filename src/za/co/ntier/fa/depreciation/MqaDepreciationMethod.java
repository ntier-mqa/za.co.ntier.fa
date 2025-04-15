package za.co.ntier.fa.depreciation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_A_Depreciation_Exp;
import org.compiere.model.MAssetChange;
import org.compiere.model.MDepreciationExp;
import org.compiere.model.MDepreciationWorkfile;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.idempiere.fa.service.api.DepreciationDTO;
import org.idempiere.fa.service.api.IDepreciationMethod;

import za.co.ntier.fa.model.MZZAssetChange;

public class MqaDepreciationMethod implements IDepreciationMethod {
	// save to use at event handle to save to MDepreciationExp
	public static final ThreadLocal<BigDecimal> activePeriodDailyDepreciateRate = new ThreadLocal<>();
	public static final BigDecimal monthsInYear = BigDecimal.valueOf(12);
	public static final BigDecimal daysInYear = BigDecimal.valueOf(365);
	public static final String COLUMN_NAME_ZZ_DAILY_RATE = "ZZ_DailyRate";
	protected BigDecimal averageDaysInMonth;
	protected MDepreciationExp lastDepreciationExp;
	protected MDepreciationWorkfile assetwk = null;

	long periodsCount = 0;
	/**
	 * day start of use life (is in Service date)
	 */
	LocalDate dayStartOfLife;
	/*
	 * day end of use life
	 */
	LocalDate dayEndOfLife;
	/*
	 * fist day of current account date (value on MDepreciationWorkfile)
	 */
	LocalDate fisrtDayAcctDateWkf;
	/*
	 * next month of account date (at start of month)
	 */
	LocalDate fisrtDayNextMonthAcctDateWkf;
	/*
	 * latest change asset, can be null
	 */
	LocalDate lastDateEffectChange;
	/*
	 * list of asset change history (max 2 item)
	 */
	List<MAssetChange> effectChanges;
	/*
	 * get from latest depreciation, can be null
	 * in case change happen on first period, first range use rate calculator from old value (get from first asset change)
	 */
	BigDecimal dailyDepreciateRateForOldAssetValue = null;
	/*
	 * calculated daily rate for current build
	 */
	BigDecimal dailyDepreciateRateForNewAssetValue = null;

	/**
	 * Accumulated depreciate to current calculator
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
	private MDepreciationExp getLastDepreciatedExp(int assetID, String postingType, int acctSchemaID) {
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

		// remaining cost util this period
		BigDecimal amountDepreciation = assetwk.getRemainingCost(accumulatedDep);

		// (1) bring all remain cost to end period
		if (depreciationDTO.period == periodsCount) {
			return amountDepreciation;
		}

		//(2) start and end date of period calculation
		LocalDate startPeriodDate = null;
		LocalDate endPeriodDate = null;

		// day 1 of calculation period date, at first period it's start date
		if (depreciationDTO.period == 1) {
			startPeriodDate = dayStartOfLife;
		} else {
			startPeriodDate = dayStartOfLife.plusMonths(depreciationDTO.period - 1).withDayOfMonth(1);
		}

		// always at day end of month because case last period already done above
		endPeriodDate = startPeriodDate.withDayOfMonth(startPeriodDate.lengthOfMonth());

		//(2) days use previous daily rate and days use new daily rate
		BigDecimal prevDailyRateDays = BigDecimal.ZERO;
		BigDecimal currentDailyRateDays = BigDecimal.ZERO;

		// change date at between current calculate period
		if (lastDateEffectChange == null) {
			if (startPeriodDate.getDayOfMonth() == 1) { // full month
				currentDailyRateDays = averageDaysInMonth;
			} else {
            	currentDailyRateDays = BigDecimal.valueOf(ChronoUnit.DAYS.between(startPeriodDate, endPeriodDate) + 1);
            }
		}else if (lastDateEffectChange.isBefore(startPeriodDate) || lastDateEffectChange.isEqual(startPeriodDate)){
			if (startPeriodDate.getDayOfMonth() == 1) { // full month
				currentDailyRateDays = averageDaysInMonth;
			} else {
            	// not happen
            }
		}else if (lastDateEffectChange.isAfter(endPeriodDate)){
			if (startPeriodDate.getDayOfMonth() == 1) { // full month
				prevDailyRateDays = averageDaysInMonth;
			} else {
            	prevDailyRateDays = BigDecimal.valueOf(ChronoUnit.DAYS.between(startPeriodDate, endPeriodDate) + 1);
            }
		}else if ((lastDateEffectChange.isAfter(startPeriodDate) && lastDateEffectChange.isBefore(endPeriodDate)) ||
				lastDateEffectChange.isEqual(endPeriodDate)){// new change split current period
			prevDailyRateDays = BigDecimal.valueOf(ChronoUnit.DAYS.between(startPeriodDate, lastDateEffectChange));
			currentDailyRateDays = BigDecimal.valueOf(ChronoUnit.DAYS.between(lastDateEffectChange, endPeriodDate) + 1);
		}else {
			// not happen, in case something change make this case happen then throw exception
			throw new AdempiereException(Msg.getMsg(Env.getCtx(), "ZZ_AssetRevaluationChangeDateOutOfHandle"));
		}
		
		// can be zero when prevDailyRateDays is zero
		BigDecimal prevDailyRateAmount = prevDailyRateDays.multiply(dailyDepreciateRateForOldAssetValue);

		BigDecimal currentDailyRateAmount = BigDecimal.ZERO;
		
		if (dailyDepreciateRateForNewAssetValue == null && currentDailyRateDays.compareTo(BigDecimal.ZERO) > 0) {// new change split current period
			BigDecimal remainAssetCost = assetwk.getRemainingCost(accumulatedDep.add(prevDailyRateAmount));//prevDailyRateAmount = zero when change date at end of prev month

    		BigDecimal remainDays = BigDecimal.valueOf(ChronoUnit.DAYS.between(lastDateEffectChange, dayEndOfLife) + 1);

    		dailyDepreciateRateForNewAssetValue = remainAssetCost.divide(remainDays, depreciationDTO.scale, RoundingMode.HALF_DOWN);
		}
		
		if (currentDailyRateDays.compareTo(BigDecimal.ZERO) > 0)
			currentDailyRateAmount = currentDailyRateDays.multiply(dailyDepreciateRateForNewAssetValue);
		
		// save on ThreadLocal for re-get at ModelDepreciationExpHandle to save to MDepreciationExp
		if (dailyDepreciateRateForNewAssetValue != null)
			activePeriodDailyDepreciateRate.set(dailyDepreciateRateForNewAssetValue);
		else
			activePeriodDailyDepreciateRate.set(dailyDepreciateRateForOldAssetValue);
		
		BigDecimal result = prevDailyRateAmount.add(currentDailyRateAmount);
		accumulatedDep = accumulatedDep.add(result);

		return result;
	}

	/**
	 * init at period start calculator to avoid repeat calculation
	 * @param depreciationDTO
	 * @return
	 */
	protected void init(DepreciationDTO depreciationDTO) {
		// init accumulated from wkf
		accumulatedDep = assetwk.getA_Accumulated_Depr(assetwk.isFiscal());
		fisrtDayAcctDateWkf = assetwk.getDateAcct().toLocalDateTime().toLocalDate().withDayOfMonth(1);
		fisrtDayNextMonthAcctDateWkf = fisrtDayAcctDateWkf.plusMonths(1).withDayOfMonth(1);
		dayStartOfLife = depreciationDTO.inServiceDate.toLocalDateTime().toLocalDate();

		// (1) start date not yet depreciated
		LocalDate startMonthNotDepreciated = null;
		lastDepreciationExp = getLastDepreciatedExp(assetwk.getA_Asset_ID(), assetwk.getPostingType(), assetwk.getC_AcctSchema_ID());

		if (lastDepreciationExp == null) {
			startMonthNotDepreciated = assetwk.getDateAcct().toLocalDateTime().toLocalDate();
		}else {
			startMonthNotDepreciated = lastDepreciationExp.getDateAcct().toLocalDateTime().toLocalDate().plusMonths(1);
		}

		//(2) query change info from latest depreciated time
		Timestamp startTimeNotDepreciated = Timestamp.valueOf(startMonthNotDepreciated.withDayOfMonth(1).atStartOfDay());

		effectChanges = MZZAssetChange.getAssetChangesForRevaluation(assetwk.getA_Asset_ID(), assetwk.getPostingType(),
				assetwk.getC_AcctSchema_ID(), startTimeNotDepreciated, depreciationDTO.trxName);

		if (!effectChanges.isEmpty()) {
			lastDateEffectChange = effectChanges.get(0).getDateAcct().toLocalDateTime().toLocalDate();
		}

		// (3) average number of days in month
		averageDaysInMonth = daysInYear.divide(monthsInYear, depreciationDTO.scale, RoundingMode.HALF_DOWN);

		// (4) end date of use life
		LocalDate estEndDate = dayStartOfLife.plusMonths(depreciationDTO.useFullLife.longValueExact());
		if (dayStartOfLife.getDayOfMonth() == 1) {
			dayEndOfLife = estEndDate.withDayOfMonth(estEndDate.lengthOfMonth());
		} else {
			dayEndOfLife = dayStartOfLife.plusMonths(depreciationDTO.useFullLife.longValueExact() + 1);
		}

		//(5) previous daily rate
		if (lastDepreciationExp != null) {
			dailyDepreciateRateForOldAssetValue = (BigDecimal)lastDepreciationExp.get_Value(COLUMN_NAME_ZZ_DAILY_RATE);
			if (dailyDepreciateRateForOldAssetValue == null) {
				throw new AdempiereException(Msg.getMsg(Env.getCtx(), "ZZ_AssetRevaluationDailyRateNotFound"));
			}
		}

		// not yet do any posting for depreciation so no previous daily rate.
		// need to get original asset cost and  original use life from first MAssetChange to calculate previous daily rate
		if(lastDateEffectChange != null && dailyDepreciateRateForOldAssetValue == null) {
    		MAssetChange firstChange = effectChanges.get(effectChanges.size() - 1);
    		BigDecimal originalAssetCost = assetwk.getActualCost(firstChange.getAssetValueAmt());

    		BigDecimal totalDays = BigDecimal.valueOf(firstChange.getUseLifeMonths()).multiply(daysInYear).divide(monthsInYear, depreciationDTO.scale, RoundingMode.HALF_DOWN);
			dailyDepreciateRateForOldAssetValue = originalAssetCost.divide(totalDays, depreciationDTO.scale, RoundingMode.HALF_DOWN);

        }
		
		if(lastDateEffectChange == null && dailyDepreciateRateForOldAssetValue == null){
			// not yes depreciated and nothing change, just use value from assetwk to calculate and use for whole periods
			BigDecimal originalAssetCost = assetwk.getActualCost();

    		BigDecimal totalDays = BigDecimal.valueOf(assetwk.getUseLifeMonths()).multiply(daysInYear).divide(monthsInYear, depreciationDTO.scale, RoundingMode.HALF_DOWN);
    		
    		dailyDepreciateRateForOldAssetValue = originalAssetCost.divide(totalDays, depreciationDTO.scale, RoundingMode.HALF_DOWN);
		}

		if (dailyDepreciateRateForOldAssetValue == null) {
			throw new AdempiereException("something wrong this point dailyDepreciateRateForOldAssetValue need to non-null");
		}
		// (6) current daily rate
		if(lastDateEffectChange == null) {
			// has no asset change so keep use previous daily rate
			dailyDepreciateRateForNewAssetValue = dailyDepreciateRateForOldAssetValue;
		}else {
			// util time when prev days before change time is complete calculator and accumulated to accumulatedDep we has enough info to calculator dailyDepreciateRateForNewAssetValue 
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
