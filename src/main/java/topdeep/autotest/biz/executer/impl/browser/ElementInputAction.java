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
public class ElementInputAction  implements UserCaseActionExecute {

	public static String PARAM_TARGET_PARAM = "targetParam";
	public static String PARAM_ELEMENT_LOCATE_TYPE = "eleLocType";
	public static String PARAM_INPUT_CONTENT = "inputContent";

	public String getId() {
		return "lOa7f_JRoeW-tCSIsvlBQ..w";
	}

	public String getName() {
		return "文本输入";
	}

	public String getObjType() {
		return ObjType.UserCaseActionExecute.getValue();
	}

	public String getActionDesc(List<AtObjParams> paramList) {
		Map<String, AtObjParams> paramMap;
		String ret = getName() + ":";
		ret += ",输入: " ;
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
			if (!paramMap.containsKey(PARAM_INPUT_CONTENT)) {
				result = TestResult.Fail;
			}
		}
		if (data.containsKey(TestContextDataKey.Driver.getValue())) {
			WebDriver wd = (WebDriver) data.get(TestContextDataKey.Driver.getValue());
			String locateType = paramMap.get(PARAM_ELEMENT_LOCATE_TYPE).getParamValue();
			String targetParam = paramMap.get(PARAM_TARGET_PARAM).getParamValue();
			String inputContent = paramMap.get(PARAM_INPUT_CONTENT).getParamValue();
			taskLog.debug("element input: " + locateType + "," + targetParam + "," + inputContent);
			if (execute.setInputElementValue(wd, locateType, targetParam, inputContent)) {
				return TestResult.Success;
			}
			;
		}
		return TestResult.Fail;
	}

	public List<AtObjParams> getInitParamList() {
		List<AtObjParams> paramList = null ;
		return paramList;
	}

	public int getBeanVersion() {
		return 3;
	}
}
