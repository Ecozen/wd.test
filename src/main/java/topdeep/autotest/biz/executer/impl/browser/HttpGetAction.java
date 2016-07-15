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
public class HttpGetAction extends BaseExecute implements UserCaseActionExecute {

	public static String PARAM_TARGET_URL = "targetUrl";

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getId()
	 */
	public String getId() {
		return "S1C0q0_SPqOWKXQ0v1y-Q..u";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getName()
	 */
	public String getName() {
		return "打开网页";
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
		String ret = getName();
		if (paramMap.containsKey(PARAM_TARGET_URL)) {
			ret += ": " + paramMap.get(PARAM_TARGET_URL).getParamValue();
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.UserCaseActionExecute#execute(topdeep.autotest.entity.execute.UserCaseExecute,
	 * topdeep.autotest.entity.db.AtUserCaseAction, java.util.List)
	 */
	public TestResult execute(UserCaseExecute userCaseExecute, AtUserCaseAction userCaseAction, List<AtObjParams> paramList, Map<String, Object> data,
			Log taskLog) throws Exception {
		Map<String, AtObjParams> paramMap = convertParamListToMap(paramList);
		BrowserUserCaseExecute execute = (BrowserUserCaseExecute) userCaseExecute;
		if (paramMap.containsKey(PARAM_TARGET_URL) && data.containsKey(TestContextDataKey.Driver.getValue())) {
			WebDriver wd = (WebDriver) data.get(TestContextDataKey.Driver.getValue());
			String url = (String) paramMap.get(PARAM_TARGET_URL).getParamValue();
			taskLog.debug("open url: " + url);
			execute.httpGet(wd, url);
			return TestResult.Success;
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
		paramList.add(createNewParam(getId(), ObjType.UserCaseActionExecute.getValue(), getId(), PARAM_TARGET_URL, "目标地址", "需要打开的URL完整地址",
				ParamType.String.getValue(), "", null, null, "1", "1", 1));
		return paramList;
	}


	
}
