package za.co.ntier.fa.modeleventdelegate;

import org.adempiere.base.annotation.EventTopicDelegate;
import org.adempiere.base.annotation.ModelEventTopic;
import org.adempiere.base.event.annotations.ModelEventDelegate;
import org.adempiere.base.event.annotations.po.BeforeNew;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDepreciationExp;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.osgi.service.event.Event;

import za.co.ntier.fa.depreciation.MqaDepreciationMethod;

@EventTopicDelegate
@ModelEventTopic(modelClass = MDepreciationExp.class)
public class ModelDepreciationExpHandle extends ModelEventDelegate<MDepreciationExp>{
	public ModelDepreciationExpHandle(MDepreciationExp po, Event event) {
        super(po, event);
     }
	
	@BeforeNew
	public void beforeNewMDepreciationExp(){
		if (MqaDepreciationMethod.activePeriodDailyDepreciateRate.get() == null)
			throw new AdempiereException(Msg.getMsg(Env.getCtx(), "ZZ_AssetRevaluationDailyRateNotFound"));
		getModel().set_ValueOfColumn(MqaDepreciationMethod.COLUMN_NAME_ZZ_DAILY_RATE, MqaDepreciationMethod.activePeriodDailyDepreciateRate.get());
	}
}
