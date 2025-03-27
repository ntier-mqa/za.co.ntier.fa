package za.co.ntier.fa.osgi.component;

import org.idempiere.fa.service.api.DepreciationFactoryLookupDTO;
import org.idempiere.fa.service.api.IDepreciationMethod;
import org.idempiere.fa.service.api.IDepreciationMethodFactory;
import org.osgi.service.component.annotations.Component;

import za.co.ntier.fa.depreciation.MqaDepreciationMethod;

@Component(
		 property= {"service.ranking:Integer=2"},
		 service = IDepreciationMethodFactory.class
		 )
public class NtierDepreciationMethodFactory implements IDepreciationMethodFactory {

	@Override
	public IDepreciationMethod getDepreciationMethod(DepreciationFactoryLookupDTO factoryLookupDTO) {
		if ("SL".equals(factoryLookupDTO.depreciationType)) {
			return new MqaDepreciationMethod();
		}
		return null;
	}
}
