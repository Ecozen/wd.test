package topdeep.autotest.entity.data.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Sheet;

import topdeep.autotest.entity.data.AtTestCase;
import topdeep.autotest.entity.data.AtUserCaseAction;

public class ExcelDataProvider {

	DataReader myInputData;
	AtTestCase testCase = new AtTestCase();
	
	public ExcelDataProvider(Sheet sheet, Boolean has_headers, Boolean has_key_column, Integer key_column) {

		myInputData = new DataReader(sheet, has_headers, has_key_column, key_column);		
	}

	public AtTestCase getTestCase(){
		
		return testCase;
	}

	public AtTestCase getdata(){
        Map<String, RecordHandler> sortmap = new TreeMap<String,RecordHandler>(new Comparator<String>(){

			public int compare(String key1, String key2) {
				return key1.compareTo(key2);
			}
        });
        
        sortmap.putAll(myInputData.get_map());
		
        for(Map.Entry<String, RecordHandler> entry : sortmap.entrySet()){
        	entry.getKey();
        	RecordHandler userAction = entry.getValue();
            for (int i = 0; i < userAction.size(); i++) {
                String actionExecuteId = userAction.get("序号");
                
                String actionDesc = userAction.get("步骤说明");
                
                String actionName = userAction.get("动作类型");
               
                String locateTpye = userAction.get("定位方式");
                
                String locateParam = userAction.get("定位参数");
                
                String inputValue = userAction.get("输入值");
                
                String checkType = userAction.get("检查类型");
                
                String checkValue = userAction.get("检查值");
                
                String time = userAction.get("时间");
                
                String screenShotFlag = userAction.get("截图标志");
                
                AtUserCaseAction action =new AtUserCaseAction(actionExecuteId, actionName, actionDesc, locateTpye, locateParam, inputValue, checkType, checkValue, screenShotFlag, time);
                
                testCase.addActions(action);
			}
        }
        return testCase;
	}
}
