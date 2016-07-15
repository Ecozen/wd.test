/**
 * 
 */
package topdeep.autotest.biz.executer.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import topdeep.autotest.entity.db.AtTestTask;
import topdeep.autotest.entity.execute.TaskExecuterInfo;
import topdeep.autotest.entity.execute.TestTaskExecute;

import common.util.StringUtils;

/**
 * @author niexin
 *
 */
public class TestTaskThreadExecute implements Runnable {

	private static Log logger = LogFactory.getLog(TestTaskThreadExecute.class);

	private String taskId;

	private AtTestTask task;

	private TestTaskExecute execute;

	private TaskExecuterInfo executerInfo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			if (execute != null) {
				if (task != null) {
					execute.execute(task, executerInfo);
				} else if (!StringUtils.isNullOrEmpty(taskId)) {
					execute.execute(taskId, executerInfo);
				} else {
					logger.warn("no task or taskId, can't execute testTask!");
				}
			} else {
				logger.warn("no execute, can't execute testTask!");
			}
		} catch (Throwable tr) {
			logger.error("execute testtask error: " + tr.getMessage(), tr);
		}
	}

	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the task
	 */
	public AtTestTask getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(AtTestTask task) {
		this.task = task;
	}

	/**
	 * @return the execute
	 */
	public TestTaskExecute getExecute() {
		return execute;
	}

	/**
	 * @param execute the execute to set
	 */
	public void setExecute(TestTaskExecute execute) {
		this.execute = execute;
	}

	/**
	 * @return the executerInfo
	 */
	public TaskExecuterInfo getExecuterInfo() {
		return executerInfo;
	}

	/**
	 * @param executerInfo the executerInfo to set
	 */
	public void setExecuterInfo(TaskExecuterInfo executerInfo) {
		this.executerInfo = executerInfo;
	}

}
