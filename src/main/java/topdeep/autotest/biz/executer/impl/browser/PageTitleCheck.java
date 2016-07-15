/**
 * 
 */
package topdeep.autotest.biz.executer.impl.browser;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import topdeep.autotest.biz.executer.impl.BaseExecute;
import topdeep.autotest.entity.constant.EnumType.CheckType;
import topdeep.autotest.entity.constant.EnumType.ObjType;
import topdeep.autotest.entity.constant.EnumType.ParamType;
import topdeep.autotest.entity.constant.EnumType.TestContextDataKey;
import topdeep.autotest.entity.constant.EnumType.TestResult;
import topdeep.autotest.entity.db.AtObjParams;
import topdeep.autotest.entity.db.AtUserCaseAction;
import topdeep.autotest.entity.execute.ObjRegisterAttribute;
import topdeep.autotest.entity.execute.UserCaseActionExecute;
import topdeep.autotest.entity.execute.UserCaseExecute;
import topdeep.common.util.EnumTypeUtil;

/**
 * @author niexin
 *
 */
@Service("kRN58jURkmc733xpPZRqA..l")
@ObjRegisterAttribute(BeanId = "kRN58jURkmc733xpPZRqA..l")
public class PageTitleCheck extends BaseExecute implements UserCaseActionExecute {

	public static String PARAM_CHECK_TYPE = "checkType";
	public static String PARAM_CHECK_VALUE = "checkValue";

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getId()
	 */
	public String getId() {
		return "Fe5caAMRK2-YcHto-h0zw..c";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getName()
	 */
	public String getName() {
		return "页面标题检查";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getObjType()
	 */
	public String getObjType() {
		return ObjType.UserCaseActionExecute.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.UserCaseActionExecute#getActionDesc(java.util.List)
	 */
	public String getActionDesc(List<AtObjParams> paramList) {
		Map<String, AtObjParams> paramMap = convertParamListToMap(paramList);
		String ret = getName() + ":";
		if (paramMap.containsKey(PARAM_CHECK_TYPE)) {
			String checkType = paramMap.get(PARAM_CHECK_TYPE).getParamValue();
			CheckType ct = EnumTypeUtil.valueOf(checkType, CheckType.class);
			if (ct != null) {
				ret += "检查类型-" + ct.getName();
			}
		}
		String targetParam = "";
		if (paramMap.containsKey(PARAM_CHECK_VALUE)) {
			targetParam = paramMap.get(PARAM_CHECK_VALUE).getParamValue();
			if (targetParam == null) {
				targetParam = "";
			}
			ret += "检查值是-" + targetParam;
		}
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
		Map<String, AtObjParams> paramMap = convertParamListToMap(paramList);
		BrowserUserCaseExecute execute = (BrowserUserCaseExecute) userCaseExecute;
		if (paramMap.containsKey(PARAM_CHECK_TYPE) && paramMap.containsKey(PARAM_CHECK_VALUE) && data.containsKey(TestContextDataKey.Driver.getValue())) {
			WebDriver wd = (WebDriver) data.get(TestContextDataKey.Driver.getValue());
			String checkType = paramMap.get(PARAM_CHECK_TYPE).getParamValue();
			String checkValue = paramMap.get(PARAM_CHECK_VALUE).getParamValue();
			String title = execute.getPageTitle(wd);
			taskLog.debug("check pageTitle: " + checkType + "," + checkValue);
			if (checkStringValue(title, checkType, checkValue)) {
				return TestResult.Success;
			}
		}
		return TestResult.Fail;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.biz.executer.impl.BaseExecute#getParamList()
	 */
	@Override
	public List<AtObjParams> getInitParamList() {
		List<AtObjParams> paramList = super.getInitParamList();
		paramList.add(createNewParam(getId(), ObjType.UserCaseActionExecute.getValue(), getId(), PARAM_CHECK_TYPE, "检查类型", "检查标题的方式",
				ParamType.CheckType.getValue(), "", CheckType.Equale.getValue(), null, "1", "1", 1));
		paramList.add(createNewParam(getId(), ObjType.UserCaseActionExecute.getValue(), getId(), PARAM_CHECK_VALUE, "检查值", "用来检查的值",
				ParamType.String.getValue(), "", null, null, "1", "1", 2));
		return paramList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getBeanVersion()
	 */
	public int getBeanVersion() {
		return 2;
	}

}
