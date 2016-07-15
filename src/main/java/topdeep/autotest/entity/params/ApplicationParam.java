/**
 * 
 */
package topdeep.autotest.entity.params;

/**
 * @author lei.yuan
 *
 */

public class ApplicationParam extends BaseSystemParam {


	private String mgServerAddress;


	private long validTime;


	private String redirectSchema;


	private String testResultPath;

	private String testResultVisitUrl;

	/**
	 * @return the mgServerAddress
	 */
	public String getMgServerAddress() {
		return mgServerAddress;
	}

	/**
	 * @param mgServerAddress the mgServerAddress to set
	 */
	public void setMgServerAddress(String mgServerAddress) {
		this.mgServerAddress = mgServerAddress;
	}

	public long getValidTime() {
		return validTime;
	}

	public void setValidTime(long validTime) {
		this.validTime = validTime;
	}

	/**
	 * @return the redirectSchema
	 */
	public String getRedirectSchema() {
		return redirectSchema;
	}

	/**
	 * @return the testResultPath
	 */
	public String getTestResultPath() {
		return testResultPath;
	}

	/**
	 * @return the testResultVisitUrl
	 */
	public String getTestResultVisitUrl() {
		return testResultVisitUrl;
	}

}
