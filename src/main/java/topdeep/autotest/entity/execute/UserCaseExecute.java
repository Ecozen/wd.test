/**
 * 
 */
package topdeep.autotest.entity.execute;

import java.util.Map;

import topdeep.autotest.entity.constant.EnumType.TestResult;
import topdeep.autotest.entity.data.AtUserCase;
import topdeep.autotest.entity.data.AtTestContext;
import topdeep.autotest.entity.data.AtTestResult;
import topdeep.autotest.entity.data.AtTestResultUserCase;


/**
 * @author niexin
 *
 */
public interface UserCaseExecute extends BaseExecute {
	void beforeExecute( AtTestContext context, Map<String, Object> data) throws Exception;

	TestResult execute(AtUserCase userCase, AtTestContext context, AtTestResult testResult, AtTestResultUserCase testResultUserCase,
			Map<String, Object> data) throws Exception;

	void afterExecute( AtTestContext context, Map<String, Object> data);
}
