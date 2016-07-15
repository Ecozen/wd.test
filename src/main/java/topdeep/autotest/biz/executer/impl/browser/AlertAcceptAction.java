/**
 * 
 */
package topdeep.autotest.biz.executer.impl.browser;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import topdeep.autotest.biz.executer.impl.BaseExecute;
import topdeep.autotest.entity.constant.EnumType.ObjType;
import topdeep.autotest.entity.constant.EnumType.TestContextDataKey;
import topdeep.autotest.entity.constant.EnumType.TestResult;
import topdeep.autotest.entity.db.AtObjParams;
import topdeep.autotest.entity.db.AtUserCaseAction;
import topdeep.autotest.entity.execute.ObjRegisterAttribute;
import topdeep.autotest.entity.execute.UserCaseActionExecute;
import topdeep.autotest.entity.execute.UserCaseExecute;

/**
 * @author niexin
 *
 */
@Service("Vk0-eT3ToysB39DVKeypg..I")
@ObjRegisterAttribute(BeanId = "Vk0-eT3ToysB39DVKeypg..I")
public class AlertAcceptAction extends BaseExecute implements UserCaseActionExecute {

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getId()
	 */
	public String getId() {
		return "eMHIK7WT1Cnskq0OHwQ9Q..O";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getName()
	 */
	public String getName() {
		return "接受弹出对话框";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.BaseExecute#getObjType()
	 */
	public String getObjType() {
		return ObjType.UserCaseActionExecute.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.UserCaseActionExecute#getActionDesc(java.util.List)
	 */
	public String getActionDesc(List<AtObjParams> paramList) {
		return "接受弹出对话框";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see topdeep.autotest.entity.execute.UserCaseActionExecute#execute(topdeep.autotest.entity.execute.UserCaseExecute,
	 * topdeep.autotest.entity.db.AtUserCaseAction, java.util.List, java.util.Map, org.apache.commons.logging.Log)
	 */
	public TestResult execute(UserCaseExecute userCaseExecute, AtUserCaseAction userCaseAction, List<AtObjParams> paramList, Map<String, Object> data,
			Log taskLog) throws Exception {
		// Map<String, AtObjParams> paramMap = convertParamListToMap(paramList);
		// BrowserUserCaseExecute execute = (BrowserUserCaseExecute) userCaseExecute;
		if (data.containsKey(TestContextDataKey.Driver.getValue()) && data.containsKey(TestContextDataKey.Alert.getValue())) {
			WebDriver wd = (WebDriver) data.get(TestContextDataKey.Driver.getValue());
			Alert alert = (Alert) data.get(TestContextDataKey.Alert.getValue());
			taskLog.debug("alter receive ");
			if (alert != null) {
				alert.accept();
				return TestResult.Success;
			} else {
				return TestResult.Fail;
			}
		} else {
			taskLog.error(getName() + ":" + userCaseAction.getId() + ":缺少参数");
		}
		return TestResult.Fail;
	}

}
