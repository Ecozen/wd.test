package topdeep.autotest.entity.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import topdeep.autotest.entity.data.util.ExcelReader;

public class AtUserCase {
	private String userCaseId;
	private List<AtUserCaseAction> userCase = new ArrayList<AtUserCaseAction>();
	
	public AtUserCase(){
		
	}
	
	public void add(AtUserCaseAction action){
		if (this.userCaseId.equals(action.getUserCaseId())) {
			userCase.add(action);
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
	
	public List<AtUserCaseAction> createUserCase(String sheetName, Workbook wb){
		ExcelReader reader = new ExcelReader();
		Sheet sheet = wb.getSheet(sheetName);
		int rownum = sheet.getLastRowNum();
		for (int i = 0; i < rownum; i++) {
			AtUserCaseAction action =reader.getAction(sheetName, wb, i,userCaseId);
			add(action);
		}
		return userCase;
	}
}
