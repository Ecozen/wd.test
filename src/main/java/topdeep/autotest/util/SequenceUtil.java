/**
 * 
 */
package topdeep.autotest.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author niexin
 *
 */
public class SequenceUtil {

	private static Map<String, Integer> sequMap = new HashMap<String, Integer>();

	public static void resetSequence(String sequName) {
		if (sequMap.containsKey(sequName)) {
			sequMap.remove(sequName);
		}
	}

	public synchronized static int getNextSequence(String sequName) {
		int ret = 0;
		if (sequMap.containsKey(sequName)) {
			ret = sequMap.get(sequName);
		}
		ret++;
		sequMap.put(sequName, ret);
		return ret;
	}
}
