/**
 * 
 */
package topdeep.autotest.biz.common;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import topdeep.autotest.dao.common.BaseDao;
import topdeep.autotest.entity.db.BusinessLogicManage;

import common.cache2.Cache;

/**
 * @author niexin
 *
 */
public class BaseBiz extends topdeep.autotest.entity.db.BaseBiz {

	protected Log logger = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("baseDao")
	private BaseDao baseDao;

	@Autowired
	protected WebRuntime webUserRuntimeBiz;

	@Override
	public BusinessLogicManage getDao() {
		// TODO Auto-generated method stub
		return baseDao;
	}

	public Long getNextSequValue(String sequName) {
		return baseDao.getNextSequValue(sequName);
	}

	public void checkDatabase() {
		baseDao.checkDatabase();
	}

	public <T> T selectSingleObject(List<T> tlist) {
		if (tlist != null && tlist.size() > 0) {
			return tlist.get(0);
		}
		return null;
	}

	public Object queryScalar(String sql) {
		return baseDao.queryScalar(sql);
	}

	@Override
	protected Boolean needCache(String tableName) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see au.com.jasu.entity.db.BaseBiz#getCache()
	 */
	@Override
	public Cache getCache() {
		// return (common.cache2.Cache) ApplicationContextProvider.getApplicationContext().getBean("cache");
		return null;
	}

	/**
	 * @return the baseDao
	 */
	public BaseDao getBaseDao() {
		return baseDao;
	}

	/**
	 * @param baseDao the baseDao to set
	 */
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
