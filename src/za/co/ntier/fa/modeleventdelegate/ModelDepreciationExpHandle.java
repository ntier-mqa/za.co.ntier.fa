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
import za.co.ntier.fa.model.I_A_Depreciation_Exp;

@EventTopicDelegate
@ModelEventTopic(modelClass = MDepreciationExp.class)
public class ModelDepreciationExpHandle extends ModelEventDelegate<MDepreciationExp>{
	public ModelDepreciationExpHandle(MDepreciationExp po, Event event) {
        super(po, event);
     }
	
	@BeforeNew
	public void beforeNewMDepreciationExp(){
		if (MqaDepreciationMethod.saveDailyDepreciateRate.get() == null)
			throw new AdempiereException(Msg.getMsg(Env.getCtx(), "ZZ_AssetRevaluationDailyRateNotFound"));
		
		getModel().set_ValueOfColumn(I_A_Depreciation_Exp.COLUMNNAME_ZZ_DailyRate, MqaDepreciationMethod.saveDailyDepreciateRate.get());
		getModel().set_ValueOfColumn(I_A_Depreciation_Exp.COLUMNNAME_ZZMonthlyRate, MqaDepreciationMethod.saveMonthlyDepreciateRate.get());
	}
}
