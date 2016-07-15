/**
 * 
 */
package topdeep.autotest.entity.execute;

import org.apache.commons.logging.Log;

import topdeep.autotest.entity.constant.EnumType.TestResult;
import topdeep.autotest.entity.data.AtTestContext;
import topdeep.autotest.entity.data.AtTestResult;
import topdeep.autotest.entity.data.AtTestUserCase;

/**
 * @author niexin
 *
 */
public interface TestUserCaseExecute extends BaseExecute {
	TestResult execute(AtTestUserCase testUserCase, AtTestContext testContext, AtTestResult testResult, Log taskLog) throws Exception;
}
