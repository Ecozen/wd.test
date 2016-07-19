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

public class ElementWaitAction implements UserCaseActionExecute {

	public static String PARAM_TARGET_PARAM = "targetParam";
	public static String PARAM_ELEMENT_LOCATE_TYPE = "eleLocType";
	public static String PARAM_TIME_OUT = "timeOut";

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getId()
	 */
	public String getId() {
		return "YPcWuH3RxqWIwTYgLEuzA..Z";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getName()
	 */
	public String getName() {
		return "等待元素";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getObjType()
	 */
	public String getObjType() {
		return ObjType.UserCaseActionExecute.getValue();
	}

	public String getActionDesc(List<AtObjParams> paramList) {
		Map<String, AtObjParams> paramMap ;
		String ret = getName() + ":";
		String targetParam = "";
		ret += ",目标-" + targetParam;
		String timeOut = "";
		ret += ",超时" + timeOut + "毫秒";
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.UserCaseActionExecute#execute(topdeep.autotest.entity.execute.UserCaseExecute,
	 * topdeep.autotest.entity.db.AtUserCaseAction, java.util.List, java.util.Map)
	 */
	public TestResult execute(UserCaseExecute userCaseExecute, AtUserCaseAction userCaseAction, List<AtObjParams> paramList, Map<String, Object> data,
			Log taskLog) throws Exception {
		Map<String, AtObjParams> paramMap = null ;
		BrowserUserCaseExecute execute = (BrowserUserCaseExecute) userCaseExecute;
		TestResult result = TestResult.Success;
		if (!paramMap.containsKey(PARAM_ELEMENT_LOCATE_TYPE)) {
			result = TestResult.Fail;
		}
		if (result == TestResult.Success) {
			if (!paramMap.containsKey(PARAM_TARGET_PARAM)) {
				result = TestResult.Fail;
			}
		}
		if (result == TestResult.Success) {
			if (!paramMap.containsKey(PARAM_TIME_OUT)) {
				result = TestResult.Fail;
			}
		}
		if (data.containsKey(TestContextDataKey.Driver.getValue())) {
			WebDriver wd = (WebDriver) data.get(TestContextDataKey.Driver.getValue());
			String locateType = paramMap.get(PARAM_ELEMENT_LOCATE_TYPE).getParamValue();
			String targetParam = paramMap.get(PARAM_TARGET_PARAM).getParamValue();
			int timeout = Integer.parseInt(paramMap.get(PARAM_TIME_OUT).getParamValue()) / 1000;
			taskLog.debug("wait element: " + locateType + "," + targetParam + "," + timeout);
			if (execute.waitElement(wd, locateType, targetParam, timeout)) {
				return TestResult.Success;
			}
		}
		return TestResult.Fail;
	}

	public List<AtObjParams> getInitParamList() {
		List<AtObjParams> paramList = null ;
		return paramList;
	}

	public int getBeanVersion() {
		return 4;
	}
}
