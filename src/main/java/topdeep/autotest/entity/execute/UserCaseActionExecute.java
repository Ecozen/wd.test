/**
 * 
 */
package topdeep.autotest.entity.execute;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

import topdeep.autotest.entity.constant.EnumType.TestResult;
import topdeep.autotest.entity.data.AtObjParams;
import topdeep.autotest.entity.data.AtUserCaseAction;

/**
 * @author niexin
 *
 */
public interface UserCaseActionExecute extends BaseExecute {
	/**
	 * 根据设置的参数更新动作的描述
	 * 
	 * @param paramList
	 * @return
	 */
	String getActionDesc(List<AtObjParams> paramList);

	/**
	 * 执行一个用例动作
	 * 
	 * @param userCaseExecute
	 * @param userCaseAction
	 * @param testData
	 * @param data
	 * @param taskLog
	 * @return
	 * @throws Exception
	 */
	TestResult execute(UserCaseExecute userCaseExecute, AtUserCaseAction userCaseAction, List<AtObjParams> paramList,Map<String, Object> data)
			throws Exception;
}
