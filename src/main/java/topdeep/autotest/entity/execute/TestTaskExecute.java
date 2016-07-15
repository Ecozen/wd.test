/**
 * 
 */
package topdeep.autotest.entity.execute;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import topdeep.autotest.entity.db.AtTestTask;

/**
 * @author niexin
 *
 */
public interface TestTaskExecute extends BaseExecute {
	void execute(String testTaskId, TaskExecuterInfo executerInfo) throws Exception;

	void execute(AtTestTask testTask, TaskExecuterInfo executerInfo) throws Exception;
	
}
