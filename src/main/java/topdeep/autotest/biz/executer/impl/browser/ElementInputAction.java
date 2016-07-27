/**
 * 
 */
package topdeep.autotest.biz.executer.impl.browser;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import topdeep.autotest.entity.constant.EnumType.LocateType;
import topdeep.autotest.entity.constant.EnumType.ObjType;
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

	@Override
	public TestResult execute(UserCaseExecute userCaseExecute, AtUserCaseAction userCaseAction, List<AtObjParams> paramList, Map<String, Object> data
			) throws Exception {
		Map<String, AtObjParams> paramMap = null ;
		BrowserUserCaseExecute execute = (BrowserUserCaseExecute) userCaseExecute;
		TestResult result = TestResult.Success;

		if (data.containsKey(TestContextDataKey.Driver.getValue())) {
			WebDriver wd = (WebDriver) data.get(TestContextDataKey.Driver.getValue());
			LocateType locateType = userCaseAction.getLocateType();
			String targetParam = userCaseAction.getLocateParam();
			String inputContent = userCaseAction.getInputValue();
//			taskLog.debug("element input: " + locateType + "," + targetParam + "," + inputContent);
			if (execute.setInputElementValue(wd, locateType, targetParam, inputContent)) {
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
		return 3;
	}
}
