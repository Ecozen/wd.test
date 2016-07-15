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
import topdeep.autotest.entity.constant.EnumType.ObjType;
import topdeep.autotest.entity.constant.EnumType.ParamType;
import topdeep.autotest.entity.constant.EnumType.TestContextDataKey;
import topdeep.autotest.entity.constant.EnumType.TestResult;
import topdeep.autotest.entity.db.AtObjParams;
import topdeep.autotest.entity.db.AtUserCaseAction;
import topdeep.autotest.entity.execute.ObjRegisterAttribute;
import topdeep.autotest.entity.execute.UserCaseActionExecute;
import topdeep.autotest.entity.execute.UserCaseExecute;

/**
 * @author niexin
 *
 */
@Service("u04oLFJTNS_KwArpjvyyw..D")
@ObjRegisterAttribute(BeanId = "u04oLFJTNS_KwArpjvyyw..D")
public class ElementInputAction extends BaseExecute implements UserCaseActionExecute {

	public static String PARAM_TARGET_PARAM = "targetParam";
	public static String PARAM_ELEMENT_LOCATE_TYPE = "eleLocType";
	public static String PARAM_INPUT_CONTENT = "inputContent";

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getId()
	 */
	public String getId() {
		return "lOa7f_JRoeW-tCSIsvlBQ..w";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getName()
	 */
	public String getName() {
		return "文本输入";
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
		String inputContent = "";
		if (paramMap.containsKey(PARAM_INPUT_CONTENT)) {
			inputContent = paramMap.get(PARAM_INPUT_CONTENT).getParamValue();
			if (inputContent == null) {
				inputContent = "";
			}
		}
		ret += ",输入: " + inputContent;
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
		paramList.add(createNewParam(getId(), ObjType.UserCaseActionExecute.getValue(), getId(), PARAM_INPUT_CONTENT, "输入内容", "需要输入的内容",
				ParamType.String.getValue(), "", null, null, "1", "1", 3));
		return paramList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getBeanVersion()
	 */
	public int getBeanVersion() {
		return 3;
	}
}
