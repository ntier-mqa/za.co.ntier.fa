package za.co.ntier.fa.osgi.component;

import org.adempiere.webui.apps.IProcessParameterListener;
import org.adempiere.webui.apps.ProcessParameterPanel;
import org.adempiere.webui.editor.WEditor;
import org.osgi.service.component.annotations.Component;


@Component(
		 property= {"service.ranking:Integer=2", 
				 "ProcessClass=91ffcb73-897c-4898-9d9f-e498ccef2a77",
				 "ColumnName=*"},
		 service = IProcessParameterListener.class
		 )
public class MqaProcessParameterListener implements IProcessParameterListener{

	@Override
	public void onChange(ProcessParameterPanel parameterPanel, String columnName, WEditor editor) {
		// TODO Auto-generated method stub
		
	}

}
