/**
 * 
 */
package topdeep.autotest.biz.executer.impl.browser;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;

import topdeep.autotest.biz.executer.impl.BaseExecute;
import topdeep.autotest.entity.constant.EnumType.CheckType;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.UserCaseActionExecute#execute(topdeep.autotest.entity.execute.UserCaseExecute,
	 * topdeep.autotest.entity.db.AtUserCaseAction, java.util.List, java.util.Map, org.apache.commons.logging.Log)
	 */
	public TestResult execute(UserCaseExecute userCaseExecute, AtUserCaseAction userCaseAction, List<AtObjParams> paramList, Map<String, Object> data,
			Log taskLog) throws Exception {
		Map<String, AtObjParams> paramMap = null ;
		BrowserUserCaseExecute execute = (BrowserUserCaseExecute) userCaseExecute;
		if (paramMap.containsKey(PARAM_CHECK_TYPE) && paramMap.containsKey(PARAM_CHECK_VALUE) && data.containsKey(TestContextDataKey.Driver.getValue())) {
			WebDriver wd = (WebDriver) data.get(TestContextDataKey.Driver.getValue());
			String checkType = userCaseAction.getCheckType();
			String checkValue = userCaseAction.getCheckValue();
			String title = execute.getPageTitle(wd);
			taskLog.debug("check pageTitle: " + checkType + "," + checkValue);
			if (checkStringValue(title, checkType, checkValue)) {
				return TestResult.Success;
			}
		}
		return TestResult.Fail;
	}


	private boolean checkStringValue(String title, String checkType, String checkValue) {
		if (title == null || checkValue == null || checkType == null) {
			return false;
		}
		if (checkType.equals("Equale")) {
			return title.equals(checkValue);
		}else if (checkType.equals("Like")){
			return title.indexOf(checkValue)>=0;
		}else if (checkType.equals("Regex")){
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
