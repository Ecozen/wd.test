/**
 * 
 */
package topdeep.autotest.biz.common;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import topdeep.autotest.entity.db.AtObjParamsExample;

import common.cache2.CacheException;

/**
 * @author niexin
 *
 */
@Service("executeManageBiz")
public class ExecuteManageBiz extends BaseBiz {

	@Transactional
	public int registerObjectDeleteByPrimaryKey(String AId) throws CacheException {
		int ret = super.atRegisterObjectDeleteByPrimaryKey(AId);
		AtObjParamsExample condition = new AtObjParamsExample();
		condition.or().andIdEqualTo(AId);
		atObjParamsDeleteByCondition(condition);
		return ret;
	}

}
