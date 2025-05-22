package za.co.ntier.fa.modeleventdelegate;

import org.adempiere.base.annotation.EventTopicDelegate;
import org.adempiere.base.annotation.ModelEventTopic;
import org.adempiere.base.event.annotations.ModelEventDelegate;
import org.adempiere.base.event.annotations.po.BeforeNew;
import org.apache.commons.lang3.StringUtils;
import org.compiere.model.X_I_FixedAsset;
import org.osgi.service.event.Event;

import za.co.ntier.fa.model.I_I_FixedAsset;

@EventTopicDelegate
@ModelEventTopic(modelClass = X_I_FixedAsset.class)
public class IFixAssetHandle extends ModelEventDelegate<X_I_FixedAsset> {

	public IFixAssetHandle(X_I_FixedAsset po, Event event) {
		super(po, event);
	}

	@BeforeNew
	public void beforeNewFixAssetHandle(){
		X_I_FixedAsset iFixAsset = getModel();
		String useLife = iFixAsset.get_ValueAsString(I_I_FixedAsset.COLUMNNAME_ZZLifeUseInfo);
		if (StringUtils.isNotBlank(useLife)) {
			String[] useLifes = useLife.split("-");
			int months = Integer.parseInt(useLifes[0]) * 12 + Integer.parseInt(useLifes[1]);
			iFixAsset.setUseLifeMonths(months);
		}
	}

}
