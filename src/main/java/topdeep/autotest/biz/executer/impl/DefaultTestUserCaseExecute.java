/**
 * 
 */
package topdeep.autotest.biz.executer.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;

import topdeep.autotest.entity.constant.EnumType.ObjType;
import topdeep.autotest.entity.constant.EnumType.TestResult;
import topdeep.autotest.entity.constant.EnumType.TestState;
import topdeep.autotest.entity.db.AtRegisterObject;
import topdeep.autotest.entity.db.AtTestContext;
import topdeep.autotest.entity.db.AtTestDataGroup;
import topdeep.autotest.entity.db.AtTestResult;
import topdeep.autotest.entity.db.AtTestResultUserCase;
import topdeep.autotest.entity.db.AtTestUserCase;
import topdeep.autotest.entity.db.AtTestUserCaseData;
import topdeep.autotest.entity.db.AtTestUserCaseDataExample;
import topdeep.autotest.entity.db.AtUserCase;
import topdeep.autotest.entity.execute.ObjRegisterAttribute;
import topdeep.autotest.entity.execute.TestUserCaseExecute;
import topdeep.autotest.entity.execute.UserCaseExecute;
import topdeep.common.util.ApplicationContextHelper;
import topdeep.common.util.UUIDGenerator;

/**
 * @author niexin
 *
 */
@Service("SU_OUYoQyiOBWMYQaaM5A..v")
@ObjRegisterAttribute(BeanId = "SU_OUYoQyiOBWMYQaaM5A..v")
public class DefaultTestUserCaseExecute extends BaseExecute implements TestUserCaseExecute {

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getId()
	 */
	public String getId() {
		return "vC082ZwRgaK58Dy7Kn62A..D";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getName()
	 */
	public String getName() {
		return "默认测试用例执行器";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getObjType()
	 */
	public String getObjType() {
		return ObjType.TestUserCaseExecute.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.TestUserCaseExecute#execute(topdeep.autotest.entity.db.AtTestUserCase)
	 */
	public TestResult execute(AtTestUserCase testUserCase, AtTestContext testContext, AtTestResult testResult, Log taskLog) throws Exception {
		AtTestUserCaseDataExample condition = new AtTestUserCaseDataExample();
		condition.or().andTestUserCaseIdEqualTo(testUserCase.getId()).andAuditStateEqualTo("1");
		condition.setOrderByClause("ORDER_FIELD asc");
		List<AtTestUserCaseData> testUserCaseDataList = userCaseManageBiz.atTestUserCaseDataSelectObjects(condition);
		AtUserCase userCase = userCaseManageBiz.atUserCaseSelectByPrimaryKey(testUserCase.getUserCaseId());
		AtRegisterObject userCaseObj = userCaseManageBiz.atRegisterObjectSelectByPrimaryKey(userCase.getUserCaseExecuteId());
		UserCaseExecute userCaseExecute = (UserCaseExecute) ApplicationContextHelper.getBean(userCaseObj.getObjBeanId());
		boolean hasSuccess = false;
		boolean hasFail = false;
		int testDataIndex = 0;
		Random random = new Random();
		for (int i = 0; i < testUserCase.getPlanTestCount(); i++) {
			AtTestResultUserCase testResultUserCase = new AtTestResultUserCase();
			testResultUserCase.setId(UUIDGenerator.getUUID24());
			testResultUserCase.setSuperAdminId(testResult.getSuperAdminId());
			testResultUserCase.setRowVersion(0);
			testResultUserCase.setTestNo(i + 1);
			testResultUserCase.setTestResult(TestResult.NoTest.getValue());
			testResultUserCase.setTestState(TestState.Testing.getValue());
			testResultUserCase.setTestUserCaseId(testUserCase.getId());
			testResultUserCase.setTestResultId(testResult.getId());
			testResultUserCase.setUserCaseId(userCase.getId());
			testResultUserCase.setBeginTime(new Date());
			testResultUserCase.setDeleted("0");
			userCaseManageBiz.atTestResultUserCaseInsert(testResultUserCase);
			Map<String, Object> data = new HashMap<String, Object>();
			userCaseExecute.beforeExecute(userCase, testContext, data, taskLog);
			AtTestDataGroup testDataGroup = null;
			if (testUserCaseDataList.size() > 0) {
				int useTestDataIndex = 0;
				if ("0".equals(testUserCase.getReadDateFlag())) {
					useTestDataIndex = testDataIndex++;
					if (testDataIndex >= testUserCaseDataList.size()) {
						testDataIndex = 0;
					}
				} else {
					useTestDataIndex = random.nextInt(testUserCaseDataList.size() - 1);
				}
				testDataGroup = userCaseManageBiz.atTestDataGroupSelectByPrimaryKey(testUserCaseDataList.get(useTestDataIndex).getTestDataGroupId());
			}
			try {
				TestResult result = userCaseExecute.execute(userCase, testContext, testResult, testResultUserCase, testDataGroup, data, taskLog);
				if (result == TestResult.Success) {
					hasSuccess = true;
				} else if (result == TestResult.Fail) {
					hasFail = true;
				}
				testResultUserCase.setTestResult(result.getValue());
			} catch (Exception ex) {
				hasFail = true;
			}
			userCaseExecute.afterExecute(userCase, testContext, data, taskLog);
			testResultUserCase.setTestState(TestState.Finished.getValue());
			testResultUserCase.setEndTime(new Date());
			userCaseManageBiz.atTestResultUserCaseUpdate(testResultUserCase);
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
