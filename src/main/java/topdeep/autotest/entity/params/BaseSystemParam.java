/**
 * 
 */
package topdeep.autotest.entity.params;

/**
 * @author niexin
 *
 */
public class BaseSystemParam {
	/**
	 * 参数分类
	 */
	private String paramCategory;

	public BaseSystemParam(){
		paramCategory = getClass().getName();
	}
	
	/**
	 * @return the paramCategory
	 */
	public String getParamCategory() {
		return paramCategory;
	}

	/**
	 * @param paramCategory the paramCategory to set
	 */
	public void setParamCategory(String paramCategory) {
		this.paramCategory = paramCategory;
	}
}
