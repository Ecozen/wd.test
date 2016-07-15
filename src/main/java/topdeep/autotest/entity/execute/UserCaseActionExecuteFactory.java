package topdeep.autotest.entity.execute;

import topdeep.autotest.biz.executer.impl.browser.ElementClickAction;
import topdeep.autotest.biz.executer.impl.browser.ElementInputAction;
import topdeep.autotest.biz.executer.impl.browser.ElementValueCheck;
import topdeep.autotest.biz.executer.impl.browser.ElementWaitAction;
import topdeep.autotest.biz.executer.impl.browser.HttpGetAction;
import topdeep.autotest.biz.executer.impl.browser.PageTitleCheck;
import topdeep.autotest.biz.executer.impl.browser.TimeWaitAction;
import topdeep.autotest.entity.constant.ActionType;

public class UserCaseActionExecuteFactory {

	public static UserCaseActionExecute getUserCaseActionExcuter(String actionName){
		if (ActionType.CLICK.equals(actionName)) {
			return new ElementClickAction();
		}else if(ActionType.INPUT_TEXT.equals(actionName)){
			return new ElementInputAction();
		}else if(ActionType.CHECK_VALUE.equals(actionName)){
			return new ElementValueCheck();
		}else if(ActionType.WAIT_ELEMENT.equals(actionName)){
			return new ElementWaitAction();
		}else if(ActionType.HTTP_GET.equals(actionName)){
			return new HttpGetAction();
		}else if(ActionType.WAIT_TIME.equals(actionName)){
			return new TimeWaitAction();
		}else if(ActionType.CHECK_TITLE.equals(actionName)){
			return new PageTitleCheck();
		}else{
			return null;
		}
	}
}
