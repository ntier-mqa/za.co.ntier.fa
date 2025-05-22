package za.co.ntier.fa.callout;

import org.apache.commons.lang3.StringUtils;

public class ImportFixAssetConvert {
	public String convertUseLife(String useLife) {
		if (StringUtils.isNotBlank(useLife)) {
			String [] useLifes = useLife.split("-");
			int months = Integer.parseInt(useLifes[0]) + Integer.parseInt(useLifes[1]);
			return Integer.toString(months);
		}
		
		return "0";
	}
}
