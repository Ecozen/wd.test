/**
 * 
 */
package topdeep.autotest.biz.executer.impl.browser;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import topdeep.autotest.biz.executer.impl.BaseExecute;
import topdeep.autotest.entity.constant.EnumType.ObjType;
import topdeep.autotest.entity.constant.EnumType.ParamType;
import topdeep.autotest.entity.constant.EnumType.TestContextDataKey;
import topdeep.autotest.entity.constant.EnumType.TestResult;
import topdeep.autotest.entity.db.AtObjParams;
import topdeep.autotest.entity.db.AtUserCaseAction;
import topdeep.autotest.entity.execute.ObjRegisterAttribute;
import topdeep.autotest.entity.execute.UserCaseActionExecute;
import topdeep.autotest.entity.execute.UserCaseExecute;

/**
 * @author niexin
 *
 */
public class ElementClickAction  implements UserCaseActionExecute {

	public static String PARAM_TARGET_PARAM = "targetParam";
	public static String PARAM_ELEMENT_LOCATE_TYPE = "eleLocType";
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getObjType() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<topdeep.autotest.entity.data.AtObjParams> getInitParamList() {
		// TODO Auto-generated method stub
		return null;
	}
	public int getBeanVersion() {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getActionDesc(List<topdeep.autotest.entity.data.AtObjParams> paramList) {
		// TODO Auto-generated method stub
		return null;
	}
	public TestResult execute(UserCaseExecute userCaseExecute,
			topdeep.autotest.entity.data.AtUserCaseAction userCaseAction,
			List<topdeep.autotest.entity.data.AtObjParams> paramList, Map<String, Object> data, Log taskLog)
			throws Exception {
		// TODO Auto-generated method stub
		BrowserUserCaseExecute execute = (BrowserUserCaseExecute) userCaseExecute;
		WebDriver wd = (WebDriver) data.get(TestContextDataKey.Driver.getValue());
		String locateType ;
		String targetParam ;
		execute.elementClick(wd, locateType, targetParam);
		
		return null;
	}



	





}
