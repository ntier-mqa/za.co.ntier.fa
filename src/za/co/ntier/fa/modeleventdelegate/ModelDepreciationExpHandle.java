package za.co.ntier.fa.modeleventdelegate;

import java.sql.Timestamp;

import org.adempiere.base.annotation.EventTopicDelegate;
import org.adempiere.base.annotation.ModelEventTopic;
import org.adempiere.base.event.annotations.ModelEventDelegate;
import org.adempiere.base.event.annotations.po.BeforeChange;
import org.adempiere.base.event.annotations.po.BeforeNew;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_Period;
import org.compiere.model.MDepreciationExp;
import org.compiere.model.MPeriod;
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
		
		updatePeriod();
	}
	
	@BeforeChange
	public void beforeChangeMDepreciationExp(){
		updatePeriod();
	}
	
	void updatePeriod() {
		if (getModel().is_ValueChanged(I_A_Depreciation_Exp.COLUMNNAME_DateAcct) ||
				getModel().is_ValueChanged(I_A_Depreciation_Exp.COLUMNNAME_AD_Org_ID)) {
			
			int periodId = 0;
			if(getModel().get_Value(I_A_Depreciation_Exp.COLUMNNAME_DateAcct) != null) {
				MPeriod mPeriod = MPeriod.get(getModel().getCtx(), (Timestamp)getModel().get_Value(I_A_Depreciation_Exp.COLUMNNAME_DateAcct),
						getModel().getAD_Org_ID(), null);
				
				periodId = mPeriod.getC_Period_ID();
			}
			
			
			getModel().set_ValueOfColumn(I_C_Period.COLUMNNAME_C_Period_ID, periodId);
		}
	}
}
