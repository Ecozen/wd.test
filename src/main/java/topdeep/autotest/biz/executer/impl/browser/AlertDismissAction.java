/**
 * 
 */
package topdeep.autotest.biz.executer.impl.browser;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;

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
public class AlertDismissAction implements UserCaseActionExecute {

	@Override
	public TestResult execute(UserCaseExecute userCaseExecute, AtUserCaseAction userCaseAction, List<AtObjParams> paramList, Map<String, Object> data
		) throws Exception {
		// Map<String, AtObjParams> paramMap = convertParamListToMap(paramList);
		// BrowserUserCaseExecute execute = (BrowserUserCaseExecute) userCaseExecute;
		if (data.containsKey(TestContextDataKey.Driver.getValue()) && data.containsKey(TestContextDataKey.Alert.getValue())) {
			// WebDriver wd = (WebDriver) data.get(TestContextDataKey.Driver.getValue());
			Alert alert = (Alert) data.get(TestContextDataKey.Alert.getValue());
//			taskLog.debug("alter dismiss ");
			if (alert != null) {
				alert.dismiss();
				return TestResult.Success;
			} else {
				return TestResult.Fail;
			}
		} else {
//			taskLog.error(getName() + ":" + userCaseAction.getId() + ":缺少参数");
		}
		return TestResult.Fail;
	}

	@Override
	public String getActionDesc(List<AtObjParams> paramList) {
		// TODO Auto-generated method stub
		return null;
	}

}
