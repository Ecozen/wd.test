/**
 * 
 */
package topdeep.autotest.biz.executer.impl.browser;

import java.util.List;
import java.util.Map;

import topdeep.autotest.entity.constant.EnumType.TestResult;
import topdeep.autotest.entity.data.AtObjParams;
import topdeep.autotest.entity.data.AtUserCaseAction;
import topdeep.autotest.entity.execute.UserCaseActionExecute;
import topdeep.autotest.entity.execute.UserCaseExecute;

public class TimeWaitAction implements UserCaseActionExecute {

	public static String PARAM_WAIT_TIME = "waitTime";


	public String getActionDesc(List<AtObjParams> paramList) {
		return null;
	}
	
	@Override
	public TestResult execute(UserCaseExecute userCaseExecute, AtUserCaseAction userCaseAction, List<AtObjParams> paramList, Map<String, Object> data
			) throws Exception {
			int waitTime = Integer.parseInt(userCaseAction.getTime());
//			taskLog.debug("wait time: " + waitTime);
			Thread.sleep(waitTime);
		return TestResult.Success;
	}


	public int getBeanVersion() {
		return 2;
	}

}
