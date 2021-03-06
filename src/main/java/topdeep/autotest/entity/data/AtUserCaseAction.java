package topdeep.autotest.entity.data;

import topdeep.autotest.entity.constant.EnumType.ActionType;
import topdeep.autotest.entity.constant.EnumType.CheckType;
import topdeep.autotest.entity.constant.EnumType.LocateType;
import topdeep.autotest.entity.constant.EnumType.ScreenShotFlag;

public class AtUserCaseAction {


    private String userCaseId;

    private Integer orderField;

    private String actionExecuteId;
   
    private String actionMemo;
    
    private ActionType actionType;

    private String actionDesc;
   
    private LocateType locateType;
    
    private String locateParam;
    
    private String inputValue;
    
    private CheckType checkType;
    
    private String checkValue;
    
    private ScreenShotFlag flag;

    private String time;

	public AtUserCaseAction( String actionExecuteId,ActionType actionType, String actionDesc, LocateType locateTpye,String locateParam,
	    		String inputValue,CheckType checkType,String checkValue, ScreenShotFlag screenShotFlag,String time) {
	        this.actionExecuteId = actionExecuteId;
	        this.actionType = actionType;
	        this.actionDesc = actionDesc;
	        this.flag = screenShotFlag;
	        this.locateType =locateTpye;
	        this.locateParam=locateParam;
	        this.inputValue=inputValue;
	        this.checkType=checkType;
	        this.checkValue=checkValue;
	        this.time=time;
	    }
		

	    public AtUserCaseAction() {
	        super();
	    }

	    public String getUserCaseId() {
	        return userCaseId;
	    }

	    public void setUserCaseId(String userCaseId) {
	        this.userCaseId = userCaseId == null ? null : userCaseId.trim();
	    }

	    public Integer getOrderField() {
	        return orderField;
	    }

	    public void setOrderField(Integer orderField) {
	        this.orderField = orderField;
	    }

	    public String getActionExecuteId() {
	        return actionExecuteId;
	    }

	    public void setActionExecuteId(String actionExecuteId) {
	        this.actionExecuteId = actionExecuteId == null ? null : actionExecuteId.trim();
	    }

	    public ActionType getActionType() {
	        return actionType;
	    }

	    public void setActionType(ActionType actionType) {
	        this.actionType = actionType;
	    }


	    public String getActionDesc() {
	        return actionDesc;
	    }


	    public void setActionDesc(String actionDesc) {
	        this.actionDesc = actionDesc == null ? null : actionDesc.trim();
	    }


	    public String getActionMemo() {
	        return actionMemo;
	    }


	    public void setActionMemo(String actionMemo) {
	        this.actionMemo = actionMemo == null ? null : actionMemo.trim();
	    }

	
	    public ScreenShotFlag getScreenShotFlag() {
	        return flag;
	    }

	    public void setScreenShotFlag(ScreenShotFlag screenShotFlag) {
	        this.flag = screenShotFlag;
	    }


		public LocateType getLocateType() {
			return locateType;
		}


		public void setLocateType(LocateType locateType) {
			this.locateType = locateType;
		}


		public String getLocateParam() {
			return locateParam;
		}


		public void setLocateParam(String locateParam) {
			this.locateParam = locateParam;
		}


		public String getInputValue() {
			return inputValue;
		}


		public void setInputValue(String inputValue) {
			this.inputValue = inputValue;
		}


		public CheckType getCheckType() {
			return checkType;
		}


		public void setCheckType(CheckType checkType) {
			this.checkType = checkType;
		}


		public String getCheckValue() {
			return checkValue;
		}


		public void setCheckValue(String checkValue) {
			this.checkValue = checkValue;
		}


		public String getTime() {
			return time;
		}


		public void setTime(String time) {
			this.time = time == null ? null : time.trim();
		}



}
