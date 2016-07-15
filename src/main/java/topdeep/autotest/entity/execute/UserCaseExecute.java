/**
 * 
 */
package topdeep.autotest.entity.execute;

import java.util.Map;

import org.apache.commons.logging.Log;

import topdeep.autotest.entity.constant.EnumType.TestResult;
import topdeep.autotest.entity.data.AtTestCase;
import topdeep.autotest.entity.data.AtTestContext;
import topdeep.autotest.entity.data.AtTestDataGroup;
import topdeep.autotest.entity.data.AtTestResult;
import topdeep.autotest.entity.data.AtTestResultUserCase;
import topdeep.autotest.entity.data.AtUserCase;


/**
 * @author niexin
 *
 */
public interface UserCaseExecute extends BaseExecute {
	void beforeExecute(AtTestCase userCase, AtTestContext context, Map<String, Object> data, Log taskLog) throws Exception;

	TestResult execute(AtTestCase userCase, AtTestContext context, AtTestResult testResult, AtTestResultUserCase testResultUserCase,
			AtTestDataGroup testDataGroup, Map<String, Object> data, Log taskLog) throws Exception;

	void afterExecute(AtTestCase userCase, AtTestContext context, Map<String, Object> data, Log taskLog);
}
