package topdeep.autotest.biz.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import topdeep.autotest.biz.common.BaseBiz;
import topdeep.autotest.dao.project.ProjectDao;
import topdeep.autotest.entity.base.PageImpl;
import topdeep.autotest.entity.constant.EnumType.ObjType;
import topdeep.autotest.entity.db.AtObjParams;
import topdeep.autotest.entity.db.AtObjParamsExample;
import topdeep.autotest.entity.db.AtObjParamsKey;
import topdeep.autotest.entity.db.AtProject;
import topdeep.autotest.entity.db.AtRegisterObject;
import topdeep.autotest.entity.db.AtTestContext;
import topdeep.autotest.entity.db.AtTestData;
import topdeep.autotest.entity.db.AtTestDataKey;
import topdeep.autotest.entity.db.AtUserCase;
import topdeep.autotest.entity.db.AtUserCaseAction;
import topdeep.autotest.entity.db.AtUserCaseActionExample;
import topdeep.autotest.entity.execute.UserCaseActionExecute;
import topdeep.common.entity.BizException;
import topdeep.common.util.ApplicationContextHelper;
import topdeep.common.util.UUIDGenerator;

import common.cache2.CacheException;
import common.util.StringUtils;

@Service("projectBiz")
public class ProjectBiz extends BaseBiz {

	@Autowired
	@Qualifier("projectDao")
	private ProjectDao projectDao;

	public void changeAtProjectAuditState(String auditId, String ids, int changeType) throws CacheException {
		String changeState = "";
		if (changeType == 0) {
			changeState = "1";
		} else {
			changeState = "2";
		}
		String[] IdsArray = ids.split(",");
		for (String id : IdsArray) {
			if (!StringUtils.isNullOrEmpty(id)) {
				AtProject atProject = atProjectSelectByPrimaryKey(id);
				atProject.setAuditTime(new Date());
				atProject.setAuditId(auditId);
				atProject.setAuditState(changeState);
				atProjectUpdate(atProject);
			}
		}
	}

	@Transactional
	public void changeAtTestDataAuditState(String ids, String keys, int changeType) throws CacheException {
		String changeState = "";
		if (changeType == 0) {
			changeState = "1";
		} else {
			changeState = "2";
		}
		String[] IdsArray = ids.split(",");
		String[] keysArray = keys.split(",");
		for (int i = 0; i < IdsArray.length; i++) {
			AtTestDataKey atTestDataKey = new AtTestDataKey();
			atTestDataKey.setTestDataGroupId(IdsArray[i]);
			atTestDataKey.setDataKey(keysArray[i]);
			AtTestData atTestData = atTestDataSelectByPrimaryKey(atTestDataKey);
			atTestData.setAuditTime(new Date());
			atTestData.setAuditId(webUserRuntimeBiz.getLoginUserId());
			atTestData.setAuditName(webUserRuntimeBiz.getLoginUserName());
			atTestData.setAuditState(changeState);
			atTestDataUpdate(atTestData);
		}
	}

	@Transactional
	public int testContextSave(AtTestContext testContext, boolean newObj, String testContextExecuteId) throws Exception {
		int ret = 0;
		if (newObj) {
			ret = atTestContextInsert(testContext);
		} else {
			ret = atTestContextUpdate(testContext);
		}
		if (newObj && ret > 0) {
			AtObjParamsExample condition = new AtObjParamsExample();
			condition.or().andIdEqualTo(testContextExecuteId);
			condition.setOrderByClause("PARAM_ORDER asc, PARAM_CODE asc");
			List<AtObjParams> referParamList = atObjParamsSelectObjects(condition);
			for (AtObjParams item : referParamList) {
				AtObjParams obj = new AtObjParams();
				obj.setAuditState("1");
				obj.setSuperAdminId(webUserRuntimeBiz.getLoginSuperUserId());
				obj.setCreateId(webUserRuntimeBiz.getLoginUserId());
				obj.setCreateName(webUserRuntimeBiz.getLoginUserName());
				obj.setCreateTime(new Date());
				obj.setId(testContext.getId());
				obj.setModifyId(webUserRuntimeBiz.getLoginUserId());
				obj.setModifyName(webUserRuntimeBiz.getLoginUserName());
				obj.setModifyTime(new Date());
				obj.setObjType(ObjType.TestContext.getValue());
				obj.setOwnerId(testContext.getId());
				obj.setParamCode(item.getParamCode());
				obj.setParamDesc(item.getParamDesc());
				obj.setParamName(item.getParamName());
				obj.setParamRequired(item.getParamRequired());
				obj.setParamType(item.getParamType());
				obj.setParamTypeData(item.getParamTypeData());
				obj.setParamValue(item.getParamValue());
				obj.setParamValueDesc(item.getParamValueDesc());
				obj.setRowVersion(0);
				obj.setUserModify(item.getUserModify());
				obj.setParamOrder(item.getParamOrder());
				atObjParamsInsert(obj);
			}
		}
		return ret;
	}

	@Transactional
	public int atUserCaseActionInsert(AtUserCaseAction atUserCaseAction) throws CacheException {
		int ret = super.atUserCaseActionInsert(atUserCaseAction);
		if (ret > 0) {
			AtObjParamsExample condition = new AtObjParamsExample();
			condition.or().andIdEqualTo(atUserCaseAction.getActionExecuteId());
			condition.setOrderByClause("PARAM_ORDER asc, PARAM_CODE asc");
			List<AtObjParams> referParamList = atObjParamsSelectObjects(condition);
			List<AtObjParams> paramList = new ArrayList<AtObjParams>();
			for (AtObjParams item : referParamList) {
				AtObjParams obj = new AtObjParams();
				obj.setSuperAdminId(webUserRuntimeBiz.getLoginSuperUserId());
				obj.setAuditState("1");
				obj.setCreateId(webUserRuntimeBiz.getLoginUserId());
				obj.setCreateName(webUserRuntimeBiz.getLoginUserName());
				obj.setCreateTime(new Date());
				obj.setId(atUserCaseAction.getId());
				obj.setModifyId(webUserRuntimeBiz.getLoginUserId());
				obj.setModifyName(webUserRuntimeBiz.getLoginUserName());
				obj.setModifyTime(new Date());
				obj.setObjType(ObjType.UserCaseAction.getValue());
				obj.setOwnerId(atUserCaseAction.getUserCaseId());
				obj.setParamCode(item.getParamCode());
				obj.setParamDesc(item.getParamDesc());
				obj.setParamName(item.getParamName());
				obj.setParamRequired(item.getParamRequired());
				obj.setParamType(item.getParamType());
				obj.setParamTypeData(item.getParamTypeData());
				obj.setParamValue(item.getParamValue());
				obj.setParamValueDesc(item.getParamValueDesc());
				obj.setRowVersion(0);
				obj.setUserModify(item.getUserModify());
				obj.setParamOrder(item.getParamOrder());
				atObjParamsInsert(obj);
				paramList.add(obj);
			}

			AtRegisterObject regObj = atRegisterObjectSelectByPrimaryKey(atUserCaseAction.getActionExecuteId());
			UserCaseActionExecute execute = (UserCaseActionExecute) ApplicationContextHelper.getBean(regObj.getObjBeanId());
			atUserCaseAction.setActionDesc(execute.getActionDesc(paramList));
			atUserCaseActionUpdate(atUserCaseAction);
		}
		return ret;
	}

	/**
	 * 更新一个对象AtUserCaseAction到数据库中 <param name="atUserCaseAction">需要更新的AtUserCaseAction</param>
	 */
	@Transactional
	public int atUserCaseActionUpdate(AtUserCaseAction atUserCaseAction, boolean rebuildParams) throws CacheException {
		int ret = super.atUserCaseActionUpdate(atUserCaseAction);
		if (rebuildParams && ret > 0) {
			AtObjParamsExample condition = new AtObjParamsExample();
			condition.or().andIdEqualTo(atUserCaseAction.getId());
			atObjParamsDeleteByCondition(condition);
			condition.clear();
			condition.or().andIdEqualTo(atUserCaseAction.getActionExecuteId());
			condition.setOrderByClause("PARAM_ORDER asc, PARAM_CODE asc");
			List<AtObjParams> referParamList = atObjParamsSelectObjects(condition);
			List<AtObjParams> paramList = new ArrayList<AtObjParams>();
			for (AtObjParams item : referParamList) {
				AtObjParams obj = new AtObjParams();
				obj.setSuperAdminId(webUserRuntimeBiz.getLoginSuperUserId());
				obj.setAuditState("1");
				obj.setCreateId(webUserRuntimeBiz.getLoginUserId());
				obj.setCreateName(webUserRuntimeBiz.getLoginUserName());
				obj.setCreateTime(new Date());
				obj.setId(atUserCaseAction.getId());
				obj.setModifyId(webUserRuntimeBiz.getLoginUserId());
				obj.setModifyName(webUserRuntimeBiz.getLoginUserName());
				obj.setModifyTime(new Date());
				obj.setObjType(ObjType.UserCaseAction.getValue());
				obj.setOwnerId(atUserCaseAction.getUserCaseId());
				obj.setParamCode(item.getParamCode());
				obj.setParamDesc(item.getParamDesc());
				obj.setParamName(item.getParamName());
				obj.setParamRequired(item.getParamRequired());
				obj.setParamType(item.getParamType());
				obj.setParamTypeData(item.getParamTypeData());
				obj.setParamValue(item.getParamValue());
				obj.setParamValueDesc(item.getParamValueDesc());
				obj.setRowVersion(0);
				obj.setUserModify(item.getUserModify());
				obj.setParamOrder(item.getParamOrder());
				atObjParamsInsert(obj);
				paramList.add(obj);
			}

			AtRegisterObject regObj = atRegisterObjectSelectByPrimaryKey(atUserCaseAction.getActionExecuteId());
			UserCaseActionExecute execute = (UserCaseActionExecute) ApplicationContextHelper.getBean(regObj.getObjBeanId());
			atUserCaseAction.setActionDesc(execute.getActionDesc(paramList));
			atUserCaseActionUpdate(atUserCaseAction);
		}
		return ret;
	}

	@Transactional
	public int atTestContextDeleteByPrimaryKey(String AId) throws CacheException {
		int ret = super.atTestContextDeleteByPrimaryKey(AId);
		AtObjParamsExample condition = new AtObjParamsExample();
		condition.or().andIdEqualTo(AId);
		atObjParamsDeleteByCondition(condition);
		return ret;
	}

	@Transactional
	public void copyUserCase(String srcUserCaseId) throws CacheException, BizException {
		AtUserCase userCase = atUserCaseSelectByPrimaryKey(srcUserCaseId);// 用例
		if (userCase == null) {
			throw new BizException("找不到该用例");
		}
		String userCaseId = UUIDGenerator.getUUID24();
		userCase.setId(userCaseId);
		userCase.setCreateId(webUserRuntimeBiz.getLoginUserId());
		userCase.setCreateName(webUserRuntimeBiz.getLoginUserName());
		userCase.setCreateTime(new Date());
		userCase.setModifyTime(new Date());
		userCase.setModifyId(webUserRuntimeBiz.getLoginUserId());
		userCase.setModifyName(webUserRuntimeBiz.getLoginUserName());
		userCase.setSuperAdminId(webUserRuntimeBiz.getLoginSuperUserId());
		userCase.setTestProjectId(webUserRuntimeBiz.getSelectedTestProjectId());
		userCase.setAuditState("0");
		userCase.setDeleted("0");
		atUserCaseInsert(userCase);
		// 复制动作
		AtUserCaseActionExample condition = new AtUserCaseActionExample();
		condition.or().andUserCaseIdEqualTo(srcUserCaseId);
		condition.setOrderByClause("ORDER_FIELD asc");
		List<AtUserCaseAction> actionList = atUserCaseActionSelectObjects(condition);
		for (AtUserCaseAction item : actionList) {
			if (item.getDeleted().equals("0")) {
				copyUserCaseAction(item, userCase.getId());
			}
		}

	}

	@Transactional
	public void copyUserCaseAction(String srcUserCaseActionId, String userCaseId) throws CacheException, BizException {
		AtUserCaseAction srcUserCaseAction = atUserCaseActionSelectByPrimaryKey(srcUserCaseActionId);
		if (srcUserCaseAction == null) {
			throw new BizException("源用例动作不存在");
		}
		copyUserCaseAction(srcUserCaseAction, userCaseId);
	}

	@Transactional
	public void copyUserCaseAction(AtUserCaseAction srcUserCaseAction, String userCaseId) throws CacheException, BizException {
		AtUserCaseAction newAction = new AtUserCaseAction();
		newAction.setSuperAdminId(webUserRuntimeBiz.getLoginSuperUserId());
		newAction.setActionDesc(srcUserCaseAction.getActionDesc());
		newAction.setActionExecuteId(srcUserCaseAction.getActionExecuteId());
		newAction.setActionMemo(srcUserCaseAction.getActionMemo());
		newAction.setActionName(srcUserCaseAction.getActionType());
		newAction.setAuditState("1");
		newAction.setCreateId(webUserRuntimeBiz.getLoginUserId());
		newAction.setCreateName(webUserRuntimeBiz.getLoginUserName());
		newAction.setCreateTime(new Date());
		newAction.setId(UUIDGenerator.getUUID24());
		newAction.setModifyId(webUserRuntimeBiz.getLoginUserId());
		newAction.setModifyName(webUserRuntimeBiz.getLoginUserName());
		newAction.setModifyTime(new Date());
		newAction.setOrderField(srcUserCaseAction.getOrderField());
		newAction.setRowVersion(0);
		newAction.setScreenShotFlag(srcUserCaseAction.getScreenShotFlag());
		newAction.setUserCaseId(userCaseId);
		newAction.setDeleted(srcUserCaseAction.getDeleted());
		atUserCaseActionInsert(newAction);
		// 复制动作的参数
		copyObjParams(srcUserCaseAction.getId(), newAction.getId(), newAction.getUserCaseId());
	}

	/**
	 * 复制一个对象的参数到另一个对象，要求两个对象类型完全相同，默认目标对象不存在任何参数
	 * 
	 * @param srcId
	 * @param targetId
	 * @throws CacheException
	 */
	@Transactional
	private void copyObjParams(String srcId, String targetId, String targetOwnerId) throws CacheException {
		AtObjParamsExample condition = new AtObjParamsExample();
		condition.or().andIdEqualTo(srcId);
		List<AtObjParams> paramList = atObjParamsSelectObjects(condition);
		for (AtObjParams item : paramList) {
			AtObjParams newParam = null;
			newParam = atObjParamsSelectByPrimaryKey(new AtObjParamsKey(targetId, item.getParamCode()));
			boolean newObj = newParam == null;
			if (newObj) {
				newParam = new AtObjParams();
				newParam.setSuperAdminId(webUserRuntimeBiz.getLoginSuperUserId());
				newParam.setOwnerId(targetOwnerId);
				newParam.setObjType(item.getObjType());
				newParam.setParamCode(item.getParamCode());
				newParam.setParamType(item.getParamType());
				newParam.setParamTypeData(item.getParamTypeData());
				newParam.setParamName(item.getParamName());
				newParam.setParamDesc(item.getParamDesc());
				newParam.setUserModify(item.getUserModify());
				newParam.setParamRequired(item.getParamRequired());
				newParam.setRowVersion(0);
				newParam.setCreateTime(new Date());
				newParam.setCreateId(webUserRuntimeBiz.getLoginUserId());
				newParam.setCreateName(webUserRuntimeBiz.getLoginUserName());
				newParam.setId(targetId);
				newParam.setParamOrder(item.getParamOrder());
			}
			newParam.setParamValue(item.getParamValue());
			newParam.setParamValueDesc(item.getParamValueDesc());
			newParam.setModifyTime(new Date());
			newParam.setModifyId(webUserRuntimeBiz.getLoginUserId());
			newParam.setModifyName(webUserRuntimeBiz.getLoginUserName());
			newParam.setAuditTime(item.getAuditTime());
			newParam.setAuditId(item.getAuditId());
			newParam.setAuditName(item.getAuditName());
			newParam.setAuditState("1");
			if (newObj) {
				atObjParamsInsert(newParam);
			} else {
				atObjParamsUpdate(newParam);
			}
		}
	}

	@Override
	@Transactional
	public int atObjParamsUpdate(AtObjParams atObjParams) throws CacheException {
		int ret = super.atObjParamsUpdate(atObjParams);
		if (ret > 0 && atObjParams.getObjType().equals(ObjType.UserCaseAction.getValue())) {
			// 一个用例动作对象的参数发生变更，应当去更新它的动作描述
			AtUserCaseAction actionIns = atUserCaseActionSelectByPrimaryKey(atObjParams.getId());
			AtRegisterObject actionObj = atRegisterObjectSelectByPrimaryKey(actionIns.getActionExecuteId());
			UserCaseActionExecute actionExecute = (UserCaseActionExecute) ApplicationContextHelper.getBean(actionObj.getObjBeanId());
			AtObjParamsExample condition = new AtObjParamsExample();
			condition.or().andIdEqualTo(actionIns.getId());
			List<AtObjParams> paramList = atObjParamsSelectObjects(condition);
			String desc = actionExecute.getActionDesc(paramList);
			actionIns.setActionDesc(desc);
			atUserCaseActionUpdate(actionIns);
		}
		return ret;
	}

	@Transactional
	public int atUserCaseActionMove(String id, boolean moveUp) throws CacheException {
		int ret = 0;
		AtUserCaseActionExample condition = new AtUserCaseActionExample();
		condition.or().andIdEqualTo(id).andSuperAdminIdEqualTo(webUserRuntimeBiz.getLoginSuperUserId());
		AtUserCaseAction userCaseAction = selectSingleObject(atUserCaseActionSelectObjects(condition));
		if (userCaseAction != null) {
			condition.clear();
			topdeep.autotest.entity.db.AtUserCaseActionExample.Criteria criteria = condition.or();
			criteria.andUserCaseIdEqualTo(userCaseAction.getUserCaseId()).andSuperAdminIdEqualTo(webUserRuntimeBiz.getLoginSuperUserId());
			if (moveUp) {
				criteria.andOrderFieldLessThan(userCaseAction.getOrderField());
				condition.setOrderByClause("ORDER_FIELD desc");
			} else {
				criteria.andOrderFieldGreaterThan(userCaseAction.getOrderField());
				condition.setOrderByClause("ORDER_FIELD asc");
			}
			condition.setPage(new PageImpl(0, 1));
			AtUserCaseAction userCaseAction2 = selectSingleObject(atUserCaseActionSelectObjects(condition));
			if (userCaseAction2 != null) {
				int temp = userCaseAction.getOrderField();
				userCaseAction.setOrderField(userCaseAction2.getOrderField());
				userCaseAction2.setOrderField(temp);
				ret += atUserCaseActionUpdate(userCaseAction);
				ret += atUserCaseActionUpdate(userCaseAction2);
			}
		}
		return ret;
	}
}