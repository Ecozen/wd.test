/**
 * 
 */
package topdeep.autotest.entity.execute;

import java.util.List;

import topdeep.autotest.entity.data.AtObjParams;

/**
 * @author niexin
 *
 */
public interface BaseExecute {
	String getId();

	String getName();

	String getObjType();

	List<AtObjParams> getInitParamList();

	int getBeanVersion();
}
