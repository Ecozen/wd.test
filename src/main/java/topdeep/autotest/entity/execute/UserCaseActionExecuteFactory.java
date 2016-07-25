package topdeep.autotest.entity.execute;

import topdeep.autotest.biz.executer.impl.browser.ElementClickAction;
import topdeep.autotest.biz.executer.impl.browser.ElementInputAction;
import topdeep.autotest.biz.executer.impl.browser.ElementValueCheck;
import topdeep.autotest.biz.executer.impl.browser.ElementWaitAction;
import topdeep.autotest.biz.executer.impl.browser.HttpGetAction;
import topdeep.autotest.biz.executer.impl.browser.PageTitleCheck;
import topdeep.autotest.biz.executer.impl.browser.TimeWaitAction;
import topdeep.autotest.entity.constant.EnumType.ActionType;

public class UserCaseActionExecuteFactory {

	public static UserCaseActionExecute getUserCaseActionExcuter(ActionType actionType){
		if (ActionType.CLICK.equals(actionType)) {
			return new ElementClickAction();
		}else if(ActionType.INPUT_TEXT.equals(actionType)){
			return new ElementInputAction();
		}else if(ActionType.CHECK_VALUE.equals(actionType)){
			return new ElementValueCheck();
		}else if(ActionType.WAIT_ELEMENT.equals(actionType)){
			return new ElementWaitAction();
		}else if(ActionType.HTTP_GET.equals(actionType)){
			return new HttpGetAction();
		}else if(ActionType.WAIT_TIME.equals(actionType)){
			return new TimeWaitAction();
		}else if(ActionType.CHECK_TITLE.equals(actionType)){
			return new PageTitleCheck();
		}else{
			return null;
		}
	}
}
