/**
 * 
 */
package topdeep.autotest.biz;

import java.util.Date;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import topdeep.autotest.biz.common.ExecuteManageBiz;
import topdeep.autotest.entity.db.AtObjParams;
import topdeep.autotest.entity.db.AtRegisterObject;
import topdeep.autotest.entity.execute.BaseExecute;
import topdeep.autotest.entity.execute.ObjRegisterAttribute;
import topdeep.autotest.entity.execute.TestCaseExecute;
import topdeep.autotest.entity.execute.TestContextExecute;
import topdeep.autotest.entity.execute.TestUserCaseExecute;
import topdeep.autotest.entity.execute.UserCaseActionExecute;
import topdeep.autotest.entity.execute.UserCaseExecute;
import topdeep.common.util.ApplicationContextHelper;
import topdeep.common.util.UUIDGenerator;

import common.cache2.CacheException;
import common.util.ResolverUtil;

/**
 * @author niexin
 *
 */
@Service("executeManager")
public class ExecuteManager {

	private Log logger = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("executeManageBiz")
	private ExecuteManageBiz executeManageBiz;

	/**
	 * 
	 */
	public ExecuteManager() {

	}

	@Transactional()
	public void initExecute() throws Exception {
		initObjRegister(TestCaseExecute.class);
		initObjRegister(TestUserCaseExecute.class);
		initObjRegister(UserCaseExecute.class);
		initObjRegister(UserCaseActionExecute.class);
		initObjRegister(TestContextExecute.class);
	}

	@Transactional()
	public <T extends BaseExecute> void initObjRegister(Class<T> type) throws CacheException {
		ResolverUtil<T> resolver = new ResolverUtil<T>();
		resolver.findImplementations(type, "topdeep.autotest");
		Set<Class<? extends T>> handleSet = resolver.getClasses();
		for (Class<? extends T> item : handleSet) {
			ObjRegisterAttribute attr = item.getAnnotation(ObjRegisterAttribute.class);
			if (attr != null) {
				T registerObj = (T) ApplicationContextHelper.getBean(attr.BeanId());
				if (registerObj != null) {
					AtRegisterObject regObj = executeManageBiz.atRegisterObjectSelectByPrimaryKey(registerObj.getId());
					if (regObj != null && regObj.getObjVersionNumber() < registerObj.getBeanVersion()) {
						executeManageBiz.registerObjectDeleteByPrimaryKey(registerObj.getId());
						regObj = null;
					}
					if (regObj == null) {
						regObj = new AtRegisterObject();
						regObj.setCreateTime(new Date());
						regObj.setId(registerObj.getId());
						regObj.setModifyTime(new Date());
						regObj.setObjBeanId(attr.BeanId());
						regObj.setObjName(registerObj.getName());
						regObj.setObjType(registerObj.getObjType());
						regObj.setObjVersionNumber(registerObj.getBeanVersion());
						regObj.setRowVersion(0);
						executeManageBiz.atRegisterObjectInsert(regObj);

						for (AtObjParams item1 : registerObj.getInitParamList()) {
							item1.setSuperAdminId(UUIDGenerator.PARENT_ID);
							executeManageBiz.atObjParamsInsert(item1);
						}
					}
				}
			}
		}

	}

}
