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
@Service("wsaf04JT2GzLZhT7k5hyw..J")
@ObjRegisterAttribute(BeanId = "wsaf04JT2GzLZhT7k5hyw..J")
public class ElementValueCheck extends BaseExecute implements UserCaseActionExecute {

	public static String PARAM_TARGET_PARAM = "targetParam";
	public static String PARAM_ELEMENT_LOCATE_TYPE = "eleLocType";
	public static String PARAM_CHECK_TYPE = "checkType";
	public static String PARAM_CHECK_VALUE = "checkValue";

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getId()
	 */
	public String getId() {
		return "5t6L5aqTNK40Lk2RVAxQA..q";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getName()
	 */
	public String getName() {
		return "元素值检查";
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
		if (paramMap.containsKey(PARAM_ELEMENT_LOCATE_TYPE)) {
			String locType = paramMap.get(PARAM_ELEMENT_LOCATE_TYPE).getParamValue();
			if (locType == null) {
				locType = "";
			}
			ret += "定位方式-" + locType;
		}
		String targetParam = "";
		if (paramMap.containsKey(PARAM_TARGET_PARAM)) {
			targetParam = paramMap.get(PARAM_TARGET_PARAM).getParamValue();
			if (targetParam == null) {
				targetParam = "";
			}
		}
		ret += ",目标-" + targetParam;
		if (paramMap.containsKey(PARAM_CHECK_TYPE)) {
			String checkType = paramMap.get(PARAM_CHECK_TYPE).getParamValue();
			CheckType ct = EnumTypeUtil.valueOf(checkType, CheckType.class);
			if (ct != null) {
				ret += "检查类型-" + ct.getName();
			}
		}
		targetParam = "";
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
		TestResult result = TestResult.Success;
		if (!paramMap.containsKey(PARAM_ELEMENT_LOCATE_TYPE)) {
			result = TestResult.Fail;
		}
		if (result == TestResult.Success) {
			if (!paramMap.containsKey(PARAM_TARGET_PARAM)) {
				result = TestResult.Fail;
			}
		}
		if (!paramMap.containsKey(PARAM_CHECK_TYPE)) {
			result = TestResult.Fail;
		}
		if (result == TestResult.Success) {
			if (!paramMap.containsKey(PARAM_CHECK_VALUE)) {
				result = TestResult.Fail;
			}
		}

		if (data.containsKey(TestContextDataKey.Driver.getValue())) {
			WebDriver wd = (WebDriver) data.get(TestContextDataKey.Driver.getValue());
			String locateType = paramMap.get(PARAM_ELEMENT_LOCATE_TYPE).getParamValue();
			String targetParam = paramMap.get(PARAM_TARGET_PARAM).getParamValue();
			String checkType = paramMap.get(PARAM_CHECK_TYPE).getParamValue();
			String checkValue = paramMap.get(PARAM_CHECK_VALUE).getParamValue();
			String value = execute.getElementValue(wd, locateType, targetParam);
			taskLog.debug("check element value: " + locateType + "," + targetParam + "," + checkType + "," + checkValue);
			if (checkStringValue(value, checkType, checkValue)) {
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
		paramList.add(createNewParam(getId(), ObjType.UserCaseActionExecute.getValue(), getId(), PARAM_ELEMENT_LOCATE_TYPE, "目标定位方式", "需要点击的元素定位方式",
				ParamType.EleLocType.getValue(), "", "id", "", "1", "1", 1));
		paramList.add(createNewParam(getId(), ObjType.UserCaseActionExecute.getValue(), getId(), PARAM_TARGET_PARAM, "目标定位参数", "根据定位方式不同而不同",
				ParamType.String.getValue(), "", null, null, "1", "1", 2));
		paramList.add(createNewParam(getId(), ObjType.UserCaseActionExecute.getValue(), getId(), PARAM_CHECK_TYPE, "检查类型", "检查标题的方式",
				ParamType.CheckType.getValue(), "", CheckType.Equale.getValue(), null, "1", "1", 3));
		paramList.add(createNewParam(getId(), ObjType.UserCaseActionExecute.getValue(), getId(), PARAM_CHECK_VALUE, "检查值", "用来检查的值",
				ParamType.String.getValue(), "", null, null, "1", "1", 4));
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
