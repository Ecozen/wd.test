/**
 * 
 */
package topdeep.autotest.biz.executer.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import topdeep.autotest.entity.constant.EnumType.ObjType;
import topdeep.autotest.entity.constant.EnumType.TestResult;
import topdeep.autotest.entity.constant.EnumType.TestState;
import topdeep.autotest.entity.db.AtTestCase;
import topdeep.autotest.entity.db.AtTestContext;
import topdeep.autotest.entity.db.AtTestResult;
import topdeep.autotest.entity.db.AtTestTask;
import topdeep.autotest.entity.execute.ObjRegisterAttribute;
import topdeep.autotest.entity.execute.TaskExecuterInfo;
import topdeep.autotest.entity.execute.TestTaskExecute;
import topdeep.autotest.util.CustomLogger;
import topdeep.common.util.UUIDGenerator;

/**
 * @author niexin
 *
 */
@Service("ZS6A6_XTvSzn1PGVxzH5w..8")
@ObjRegisterAttribute(BeanId = "ZS6A6_XTvSzn1PGVxzH5w..8")
public class DefaultTestTaskExecute extends BaseExecute implements TestTaskExecute {

	@Autowired
	private DefaultTestCaseExecute testCaseExecute;

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getId()
	 */
	public String getId() {
		return "TGxQLxTR-mSEHCc2wYWUQ..u";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getName()
	 */
	public String getName() {
		return "测试任务执行器";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getObjType()
	 */
	public String getObjType() {
		return ObjType.TestTaskExecute.getValue();
	}

	public void execute(String testTaskId, TaskExecuterInfo executerInfo) throws Exception {
		AtTestTask testTask = userCaseManageBiz.atTestTaskSelectByPrimaryKey(testTaskId);
		if (testTask != null) {
			execute(testTask, executerInfo);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.TestTaskExecute#execute(topdeep.autotest.entity.db.AtTestTask)
	 */
	public void execute(AtTestTask testTask, TaskExecuterInfo executerInfo) throws Exception {
		AtTestCase testCase = userCaseManageBiz.atTestCaseSelectByPrimaryKey(testTask.getTestCaseId());
		AtTestContext testContext = userCaseManageBiz.atTestContextSelectByPrimaryKey(testTask.getTestContextId());
		int testResultNo = 0;
		for (int i = 0; i < testTask.getTestCount(); i++) {
			AtTestResult testResult = new AtTestResult();
			testResult.setBeginTime(new Date());
			testResult.setId(UUIDGenerator.getUUID24());
			testResult.setSuperAdminId(executerInfo.getSuperUserid());
			testResult.setTestProjectId(executerInfo.getTestProjectId());
			testResult.setRowVersion(0);
			testResult.setTestNo(i + 1);
			testResult.setTestResult(TestResult.NoTest.getValue());
			testResult.setTestState(TestState.Testing.getValue());
			testResult.setTestTaskId(testTask.getId());
			testResult.setDeleted("0");
			testResult.setTestResultNo(testResultNo);
			userCaseManageBiz.atTestResultInsert(testResult);

			String logPath = applicationParam.getTestResultPath();
			if (!logPath.endsWith("/")) {
				logPath += "/";
			}
			// 文件保存路径 保存位置/testTaskId/testResultId/testResultUserCaseId/actionName_xxx.jpg 截屏顺序编码
			logPath += testResult.getTestTaskId() + "/" + testResult.getId() + "/log.log";

			Logger taskLogger = Logger.getLogger(testResult.getId());
			Log taskLog = new CustomLogger(taskLogger);
			taskLogger.setLevel(Level.DEBUG);
			Layout layout = new PatternLayout("%d{hh:mm:ss} %-5p %m --- [%t][%c]%n");
			Appender appender = new FileAppender(layout, logPath);
			taskLogger.addAppender(appender);
			taskLog.debug("test start!");
			TestResult result = testCaseExecute.execute(testCase, testContext, testResult, taskLog);
			testResult.setEndTime(new Date());
			testResult.setTestState(TestState.Finished.getValue());
			testResult.setTestResult(result.getValue());
			userCaseManageBiz.atTestResultUpdate(testResult);
			testTask.setLastTestResult(result.getValue());
			userCaseManageBiz.atTestTaskUpdate(testTask);
			taskLog.debug("test end!");
			taskLogger.removeAllAppenders();
		}

	}
}
