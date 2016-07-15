/**
 * 
 */
package topdeep.autotest.entity.execute;

import org.apache.commons.logging.Log;

import topdeep.autotest.entity.constant.EnumType.TestResult;
import topdeep.autotest.entity.data.AtTestCase;
import topdeep.autotest.entity.data.AtTestContext;
import topdeep.autotest.entity.data.AtTestResult;

/**
 * @author niexin
 *
 */
public interface TestCaseExecute extends BaseExecute {
	TestResult execute(AtTestCase testCase, AtTestContext testContext, AtTestResult testResult, Log taskLog) throws Exception;
}
