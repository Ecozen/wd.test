/**
 * 
 */
package topdeep.autotest.biz.executer.impl.browser;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import topdeep.autotest.entity.constant.EnumType.LocateType;
import topdeep.autotest.entity.constant.EnumType.TestContextDataKey;
import topdeep.autotest.entity.constant.EnumType.TestResult;
import topdeep.autotest.entity.data.AtObjParams;
import topdeep.autotest.entity.data.AtUserCaseAction;
import topdeep.autotest.entity.execute.UserCaseActionExecute;
import topdeep.autotest.entity.execute.UserCaseExecute;

/**
 * @author niexin
 *
 */
public class ElementClickAction  implements UserCaseActionExecute {

	public static String PARAM_TARGET_PARAM = "targetParam";
	public static String PARAM_ELEMENT_LOCATE_TYPE = "eleLocType";
	
	public String getActionDesc(List<topdeep.autotest.entity.data.AtObjParams> paramList) {
		
		return null;
	}
	public TestResult execute(UserCaseExecute userCaseExecute,AtUserCaseAction userCaseAction,List<AtObjParams> paramList, Map<String, Object> data)
			throws Exception {
		BrowserUserCaseExecute execute = (BrowserUserCaseExecute) userCaseExecute;
		WebDriver wd = (WebDriver) data.get(TestContextDataKey.Driver.getValue());
		LocateType locateType = userCaseAction.getLocateType();
		String targetParam =userCaseAction.getLocateParam();
		
		if (execute.elementClick(wd, locateType, targetParam)) {
			return TestResult.Success;
		}
		return TestResult.Fail;
	}

}
