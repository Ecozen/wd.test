/**
 * 
 */
package topdeep.autotest.biz.executer.impl.browser;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;

import topdeep.autotest.entity.constant.EnumType.CheckType;
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
public class PageTitleCheck  implements UserCaseActionExecute {

	public static String PARAM_CHECK_TYPE = "checkType";
	public static String PARAM_CHECK_VALUE = "checkValue";

	public String getId() {
		return "Fe5caAMRK2-YcHto-h0zw..c";
	}

	public String getName() {
		return "页面标题检查";
	}

	public String getObjType() {
		return ObjType.UserCaseActionExecute.getValue();
	}

	public String getActionDesc(List<AtObjParams> paramList) {
		Map<String, AtObjParams> paramMap ;
		String ret = getName() + ":";
		String targetParam = "";
		return ret;
	}

	@Override
	public TestResult execute(UserCaseExecute userCaseExecute, AtUserCaseAction userCaseAction, List<AtObjParams> paramList, Map<String, Object> data
			) throws Exception {
		Map<String, AtObjParams> paramMap = null ;
		BrowserUserCaseExecute execute = (BrowserUserCaseExecute) userCaseExecute;
		if (data.containsKey(TestContextDataKey.Driver.getValue())) {
			WebDriver wd = (WebDriver) data.get(TestContextDataKey.Driver.getValue());
			CheckType checkType = userCaseAction.getCheckType();
			String checkValue = userCaseAction.getCheckValue();
			String title = execute.getPageTitle(wd);
//			taskLog.debug("check pageTitle: " + checkType + "," + checkValue);
			if (checkStringValue(title, checkType, checkValue)) {
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

	public List<AtObjParams> getInitParamList() {
		// TODO Auto-generated method stub
		return null;
	}

}
