package za.co.ntier.fa.osgi.component;

import org.adempiere.base.AnnotationBasedColumnCalloutFactory;
import org.adempiere.base.IColumnCalloutFactory;
import org.osgi.service.component.annotations.Component;

import za.co.ntier.fa.callout.MQAFACallout;

@Component(immediate = true, service = IColumnCalloutFactory.class, property = {"service.ranking:Integer=1"})
public class MqaFAAnnotationColumnCalloutFactory extends AnnotationBasedColumnCalloutFactory {

	@Override
	protected String[] getPackages() {
		return new String[] { MQAFACallout.class.getPackageName() };
	}

}
