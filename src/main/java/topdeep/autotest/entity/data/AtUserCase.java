package topdeep.autotest.entity.data;

import java.util.List;

public class AtUserCase {
	private String userCaseId;
	private List<AtUserCaseAction> userCase;
	
	public AtUserCase(String userCaseId,AtUserCaseAction action){
		if (userCaseId.equals(action.getUserCaseId())) {
			userCase.add(Integer.parseInt(action.getActionExecuteId()),action);
		}
	}

	public String getUserCaseId() {
		return userCaseId;
	}

	public void setUserCaseId(String userCaseId) {
		this.userCaseId = userCaseId;
	}

	public List<AtUserCaseAction> getUserCase() {
		return userCase;
	}

	public void setUserCase(List<AtUserCaseAction> userCase) {
		this.userCase = userCase;
	}
	
}
