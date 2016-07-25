package topdeep.autotest.entity.data;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import topdeep.autotest.entity.data.util.ExcelReader;

public class AtUserCase {
	private String userCaseId;
	private List<AtUserCaseAction> userCase;
	
	public AtUserCase(AtUserCaseAction action){
		if (this.userCaseId.equals(action.getUserCaseId())) {
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
	
	public List<AtUserCaseAction> createUserCase(String sheetName, Workbook wb, int rownum){
		ExcelReader reader = new ExcelReader();
		reader.getAction(sheetName, wb, rownum);
		
		
		return null;
	}
}
