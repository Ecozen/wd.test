/**
 * 
 */
package topdeep.autotest.biz.executer.impl.browser;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;

import topdeep.autotest.biz.executer.impl.BaseExecute;
import topdeep.autotest.entity.constant.EnumType.ObjType;
import topdeep.autotest.entity.constant.EnumType.ParamType;
import topdeep.autotest.entity.constant.EnumType.TestContextDataKey;
import topdeep.autotest.entity.constant.EnumType.TestResult;
import topdeep.autotest.entity.data.AtObjParams;
import topdeep.autotest.entity.data.AtUserCaseAction;
import topdeep.autotest.entity.execute.ObjRegisterAttribute;
import topdeep.autotest.entity.execute.UserCaseActionExecute;
import topdeep.autotest.entity.execute.UserCaseExecute;

/**
 * @author niexin
 *
 */
public class HttpGetAction  implements UserCaseActionExecute {

	public static String PARAM_TARGET_URL = "targetUrl";


	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getObjType()
	 */
	public String getObjType() {
		return ObjType.UserCaseActionExecute.getValue();
	}

	public String getActionDesc(List<AtObjParams> paramList) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.UserCaseActionExecute#execute(topdeep.autotest.entity.execute.UserCaseExecute,
	 * topdeep.autotest.entity.db.AtUserCaseAction, java.util.List)
	 */
	public TestResult execute(UserCaseExecute userCaseExecute, AtUserCaseAction userCaseAction, List<AtObjParams> paramList, Map<String, Object> data,
			Log taskLog) throws Exception {
		Map<String, AtObjParams> paramMap ;
		BrowserUserCaseExecute execute = (BrowserUserCaseExecute) userCaseExecute;
		if (paramMap.containsKey(PARAM_TARGET_URL) && data.containsKey(TestContextDataKey.Driver.getValue())) {
			WebDriver wd = (WebDriver) data.get(TestContextDataKey.Driver.getValue());
			String url = ;
			taskLog.debug("open url: " + url);
			execute.httpGet(wd, url);
			return TestResult.Success;
		}
		return TestResult.Fail;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AtObjParams> getInitParamList() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getBeanVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
