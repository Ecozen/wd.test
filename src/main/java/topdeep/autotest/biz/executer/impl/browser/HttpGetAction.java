/**
 * 
 */
package topdeep.autotest.biz.executer.impl.browser;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import topdeep.autotest.entity.constant.EnumType.ObjType;
import topdeep.autotest.entity.constant.EnumType.TestContextDataKey;
import topdeep.autotest.entity.constant.EnumType.TestContextParamKey;
import topdeep.autotest.entity.constant.EnumType.TestResult;
import topdeep.autotest.entity.data.AtObjParams;
import topdeep.autotest.entity.data.AtUserCaseAction;
import topdeep.autotest.entity.execute.UserCaseActionExecute;
import topdeep.autotest.entity.execute.UserCaseExecute;

/**
 * @author niexin
 *
 */
public class HttpGetAction implements UserCaseActionExecute {

	public static String PARAM_TARGET_URL = "targetUrl";


	public String getObjType() {
		return ObjType.UserCaseActionExecute.getValue();
	}

	public String getActionDesc(List<AtObjParams> paramList) {
		return null;
	}

	@Override
	public TestResult execute(UserCaseExecute userCaseExecute, AtUserCaseAction userCaseAction, List<AtObjParams> paramList, Map<String, Object> data
			) throws Exception {
		/*
		Map<String, AtObjParams> paramMap = new HashMap<String, AtObjParams>() ;
		for (AtObjParams item : paramList) {
			if (!paramMap.containsKey(item.getParamCode())) {
				paramMap.put(item.getParamCode(), item);
			}
		}
		*/
		BrowserUserCaseExecute execute = (BrowserUserCaseExecute) userCaseExecute;
		if (data.containsKey(TestContextDataKey.Driver.getValue())) {
			WebDriver wd = (WebDriver) data.get(TestContextDataKey.Driver.getValue());
			String host = (String) data.get(TestContextParamKey.ServeUrl.getValue());
			String url = host+userCaseAction.getInputValue();
//			taskLog.debug("open url: " + url);
			execute.httpGet(wd, url);
			return TestResult.Success;
		}
		return TestResult.Fail;
	}

}
