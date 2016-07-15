package topdeep.autotest.entity.data;

public class AtUserCaseAction {

    private String id;
  
    private String superAdminId;

    private String userCaseId;

    private Integer orderField;

    private String actionExecuteId;
   
    private String actionMemo;
    
    private String actionName;

    private String actionDesc;
   
    private String locateTpye;
    
    private String locateParam;
    
    private String inputValue;
    
    private String checkType;
    
    private String checkValue;
    
    private String screenShotFlag;

    private String time;

		public AtUserCaseAction( String actionExecuteId,String actionName, String actionDesc, String locateTpye,String locateParam,
	    		String inputValue,String checkType,String checkValue, String screenShotFlag,String time) {
	        this.actionExecuteId = actionExecuteId;
	        this.actionName = actionName;
	        this.actionDesc = actionDesc;
	        this.screenShotFlag = screenShotFlag;
	        this.locateTpye =locateTpye;
	        this.locateParam=locateParam;
	        this.inputValue=inputValue;
	        this.checkType=checkType;
	        this.checkValue=checkValue;
	        this.time=time;
	    }
		

	    public AtUserCaseAction() {
	        super();
	    }

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id == null ? null : id.trim();
	    }

	    public String getSuperAdminId() {
	        return superAdminId;
	    }

	    public void setSuperAdminId(String superAdminId) {
	        this.superAdminId = superAdminId == null ? null : superAdminId.trim();
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

	    public String getActionName() {
	        return actionName;
	    }

	    public void setActionName(String actionName) {
	        this.actionName = actionName == null ? null : actionName.trim();
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

	
	    public String getScreenShotFlag() {
	        return screenShotFlag;
	    }

	    public void setScreenShotFlag(String screenShotFlag) {
	        this.screenShotFlag = screenShotFlag == null ? null : screenShotFlag.trim();
	    }


		public String getLocateTpye() {
			return locateTpye;
		}


		public void setLocateTpye(String locateTpye) {
			this.locateTpye = locateTpye;
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


		public String getCheckType() {
			return checkType;
		}


		public void setCheckType(String checkType) {
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
