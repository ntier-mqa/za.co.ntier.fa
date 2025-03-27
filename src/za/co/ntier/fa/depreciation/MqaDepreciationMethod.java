package za.co.ntier.fa.depreciation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;

import org.idempiere.fa.service.api.DepreciationDTO;
import org.idempiere.fa.service.api.IDepreciationMethod;

public class MqaDepreciationMethod implements IDepreciationMethod {
	public static final BigDecimal monthsOfYear = BigDecimal.valueOf(12);
	public static final BigDecimal datsOfYear = BigDecimal.valueOf(365);
	
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
		return 
				depreciationDTO.inServiceDate.toLocalDateTime().toLocalDate().getDayOfMonth() == 1 ?
					depreciationDTO.useFullLife.longValueExact() : 
					depreciationDTO.useFullLife.longValueExact() + 1;
	}
	
	@Override
	public boolean isPeriodAdjustment() {
		return false;
	}
}
