package za.co.ntier.fa.osgi.component;

import org.adempiere.base.AnnotationBasedEventManager;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = {MqaAnnotationEventManager.class})
public class MqaAnnotationEventManager extends AnnotationBasedEventManager{

	@Override
	public String[] getPackages() {
		return new String [] {"za.co.ntier.fa.modeleventdelegate"};
	}

}
