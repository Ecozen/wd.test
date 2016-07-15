/**
 * 
 */
package topdeep.autotest.biz.executer.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import topdeep.autotest.entity.constant.EnumType.ObjType;
import topdeep.autotest.entity.constant.EnumType.TestResult;
import topdeep.autotest.entity.db.AtTestCase;
import topdeep.autotest.entity.db.AtTestContext;
import topdeep.autotest.entity.db.AtTestResult;
import topdeep.autotest.entity.db.AtTestUserCase;
import topdeep.autotest.entity.db.AtTestUserCaseExample;
import topdeep.autotest.entity.execute.ObjRegisterAttribute;
import topdeep.autotest.entity.execute.TestCaseExecute;

/**
 * @author niexin
 *
 */
@Service("nnCNN4pS7W_Xjy1GEXR1w..k")
@ObjRegisterAttribute(BeanId = "nnCNN4pS7W_Xjy1GEXR1w..k")
public class DefaultTestCaseExecute extends BaseExecute implements TestCaseExecute {

	@Autowired
	private DefaultTestUserCaseExecute testUserCaseExecute;

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getId()
	 */
	public String getId() {
		return "Tb2ViL0T42JfJV4wHPHIw..9";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getName()
	 */
	public String getName() {
		return "默认测试用例执行";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getObjType()
	 */
	public String getObjType() {
		return ObjType.TestCaseExecute.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.TestCaseExecute#execute(topdeep.autotest.entity.db.AtTestCase)
	 */
	public TestResult execute(AtTestCase testCase, AtTestContext testContext, AtTestResult testResult, Log taskLog) throws Exception {
		AtTestUserCaseExample condition = new AtTestUserCaseExample();
		condition.or().andTestIdEqualTo(testCase.getId()).andAuditStateEqualTo("1").andDeletedEqualTo("0");
		condition.setOrderByClause("ORDER_FIELD asc");
		List<AtTestUserCase> testUserCaseList = userCaseManageBiz.atTestUserCaseSelectObjects(condition);
		boolean hasSuccess = false;
		boolean hasFail = false;
		for (AtTestUserCase item : testUserCaseList) {
			TestResult result = testUserCaseExecute.execute(item, testContext, testResult, taskLog);
			if (result == TestResult.Success) {
				hasSuccess = true;
			} else if (result == TestResult.Fail) {
				hasFail = true;
			} else if (result == TestResult.PartialSuccess) {
				hasSuccess = true;
				hasFail = true;
			}
		}
		if (hasSuccess && !hasFail) {
			return TestResult.Success;
		} else if (hasSuccess && hasFail) {
			return TestResult.PartialSuccess;
		} else if (hasFail) {
			return TestResult.Fail;
		} else {
			return TestResult.NoTest;
		}
	}

}
