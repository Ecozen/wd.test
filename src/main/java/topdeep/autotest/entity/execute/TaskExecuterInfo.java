/**
 * 
 */
package topdeep.autotest.entity.execute;

/**
 * @author niexin
 *
 */
public class TaskExecuterInfo {

	private String superUserid;

	private String executerId;

	private String executerName;

	private String testProjectId;

	/**
	 * @return the executerId
	 */
	public String getExecuterId() {
		return executerId;
	}

	/**
	 * @param executerId the executerId to set
	 */
	public void setExecuterId(String executerId) {
		this.executerId = executerId;
	}

	/**
	 * @return the executerName
	 */
	public String getExecuterName() {
		return executerName;
	}

	/**
	 * @param executerName the executerName to set
	 */
	public void setExecuterName(String executerName) {
		this.executerName = executerName;
	}

	/**
	 * @return the superUserid
	 */
	public String getSuperUserid() {
		return superUserid;
	}

	/**
	 * @param superUserid the superUserid to set
	 */
	public void setSuperUserid(String superUserid) {
		this.superUserid = superUserid;
	}

	/**
	 * @return the testProjectId
	 */
	public String getTestProjectId() {
		return testProjectId;
	}

	/**
	 * @param testProjectId the testProjectId to set
	 */
	public void setTestProjectId(String testProjectId) {
		this.testProjectId = testProjectId;
	}
}
