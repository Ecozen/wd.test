/**
 * 
 */
package topdeep.autotest.biz.executer.impl.browser;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

import topdeep.autotest.biz.executer.impl.BaseExecute;
import topdeep.autotest.entity.constant.EnumType.ObjType;
import topdeep.autotest.entity.constant.EnumType.ParamType;
import topdeep.autotest.entity.constant.EnumType.TestResult;
import topdeep.autotest.entity.data.AtObjParams;
import topdeep.autotest.entity.data.AtUserCaseAction;
import topdeep.autotest.entity.execute.ObjRegisterAttribute;
import topdeep.autotest.entity.execute.UserCaseActionExecute;
import topdeep.autotest.entity.execute.UserCaseExecute;

public class TimeWaitAction extends BaseExecute implements UserCaseActionExecute {

	public static String PARAM_WAIT_TIME = "waitTime";

	public String getId() {
		return "i8uBtyRQmeFyKKwPcSjrg..h";
	}

	public String getName() {
		return "等待时间";
	}

	public String getObjType() {
		return ObjType.UserCaseActionExecute.getValue();
	}

	public String getActionDesc(List<AtObjParams> paramList) {
		Map<String, AtObjParams> paramMap = convertParamListToMap(paramList);
		String ret = getName();
		
		return ret;
	}

	public TestResult execute(UserCaseExecute userCaseExecute, AtUserCaseAction userCaseAction, List<AtObjParams> paramList, Map<String, Object> data,
			Log taskLog) throws Exception {
		Map<String, AtObjParams> paramMap = convertParamListToMap(paramList);
		if (paramMap.containsKey(PARAM_WAIT_TIME)) {
			int waitTime = Integer.parseInt(userCaseAction.getInputValue());
			taskLog.debug("wait time: " + waitTime);
			Thread.sleep(waitTime);
		}
		return TestResult.Success;
	}

	@Override
	public List<AtObjParams> getInitParamList() {
		List<AtObjParams> paramList = super.getInitParamList();
		paramList.add(createNewParam(getId(), ObjType.UserCaseActionExecute.getValue(), getId(), PARAM_WAIT_TIME, "等待时间", "等待时间，毫秒单位",
				ParamType.Integer.getValue(), "2000", null, null, "1", "1", 1));
		return paramList;
	}

	public int getBeanVersion() {
		return 2;
	}

}
