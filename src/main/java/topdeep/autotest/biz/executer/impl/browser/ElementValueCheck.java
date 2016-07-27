/**
 * 
 */
package topdeep.autotest.biz.executer.impl.browser;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;

import topdeep.autotest.entity.constant.EnumType.CheckType;
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
public class ElementValueCheck implements UserCaseActionExecute {

	public static String PARAM_TARGET_PARAM = "targetParam";
	public static String PARAM_ELEMENT_LOCATE_TYPE = "eleLocType";
	public static String PARAM_CHECK_TYPE = "checkType";
	public static String PARAM_CHECK_VALUE = "checkValue";

	public String getId() {
		return "5t6L5aqTNK40Lk2RVAxQA..q";
	}

	public String getName() {
		return "元素值检查";
	}

	public String getObjType() {
		return ObjType.UserCaseActionExecute.getValue();
	}
	@Override
	public TestResult execute(UserCaseExecute userCaseExecute, AtUserCaseAction userCaseAction, List<AtObjParams> paramList, Map<String, Object> data
			) throws Exception {
		BrowserUserCaseExecute execute = (BrowserUserCaseExecute) userCaseExecute;


		if (data.containsKey(TestContextDataKey.Driver.getValue())) {
			WebDriver wd = (WebDriver) data.get(TestContextDataKey.Driver.getValue());
			LocateType locateType = userCaseAction.getLocateType();
			String targetParam = userCaseAction.getLocateParam();
			CheckType checkType = userCaseAction.getCheckType();
			String checkValue = userCaseAction.getInputValue();
			String value = execute.getElementValue(wd, locateType, targetParam);
//			taskLog.debug("check element value: " + locateType + "," + targetParam + "," + checkType + "," + checkValue);
			if (checkStringValue(value, checkType, checkValue)) {
				return TestResult.Success;
			}
		}
		return TestResult.Fail;
	}

	private boolean checkStringValue(String title, CheckType checkType, String checkValue) {
		if (title == null || checkValue == null || checkType == null) {
			return false;
		}
		if (checkType.equals(CheckType.Equale)) {
			return title.equals(checkValue);
		}else if (checkType.equals(CheckType.Like)){
			return title.indexOf(checkValue)>=0;
		}else if (checkType.equals(CheckType.Regex)){
			return(Pattern.matches(title, checkValue));
		}
		return false;
	}
	
	public int getBeanVersion() {
		return 2;
	}

	@Override
	public String getActionDesc(List<AtObjParams> paramList) {
		// TODO Auto-generated method stub
		return null;
	}

}
