package topdeep.autotest.entity.data.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import topdeep.autotest.entity.constant.EnumType.ActionType;
import topdeep.autotest.entity.constant.EnumType.CheckType;
import topdeep.autotest.entity.constant.EnumType.HeaderType;
import topdeep.autotest.entity.constant.EnumType.LocateType;
import topdeep.autotest.entity.constant.EnumType.ScreenShotFlag;
import topdeep.autotest.entity.data.AtUserCase;
import topdeep.autotest.entity.data.AtUserCaseAction;

public class ExcelReader {
	private List<HeaderType> headers = new ArrayList<HeaderType>();
	
	public AtUserCaseAction getAction(String sheetName,Workbook wb,int rownum,String userCaseId){
		Sheet sheet = wb.getSheet(sheetName);
		Map<HeaderType,String> action = new HashMap<HeaderType, String>();
		List<String> rowValue = new ArrayList<String>();
		AtUserCaseAction userCaseAction = new AtUserCaseAction();
		if(rownum>0){
			getRowValue(sheet, rownum, rowValue);
			int col =0;
			for (String item : rowValue) {
				action.put(headers.get(col), item);
				col++;
			}
			userCaseAction = convertAction(action);
			userCaseAction.setUserCaseId(userCaseId);
		}
		return userCaseAction;
	}
	
	public AtUserCase getUserCace(String sheetName,Workbook wb,String userCaseId){
		Sheet sheet = wb.getSheet(sheetName);
		int rownum = sheet.getLastRowNum();
		headers = getHead(sheet, 0);
		AtUserCase userCase = new AtUserCase();
		userCase.setUserCaseId(userCaseId);
		for (int i = 1; i <=rownum; i++) {
			AtUserCaseAction action = getAction(sheetName, wb,i,userCaseId);
			userCase.add(action);
		}
		return userCase;
	}
	
	
	
	protected AtUserCaseAction convertAction(Map<HeaderType,String> action ){
		
		AtUserCaseAction userCase = new AtUserCaseAction();
		userCase.setActionExecuteId(action.get(HeaderType.ACTION_EXCUTE_ID));
		userCase.setActionDesc(action.get(HeaderType.ACTION_DESC));
		userCase.setActionType(getActionType(action.get(HeaderType.ACTION_TYPE)));
		userCase.setLocateType(getLocateType(action.get(HeaderType.LOCATE_TYPE)));
		userCase.setLocateParam(action.get(HeaderType.LOCATE_PARAM));
		userCase.setInputValue(action.get(HeaderType.INPUT_VALUE));
		userCase.setCheckType(getCheckType(action.get(HeaderType.CHECK_TYPE)));
		userCase.setCheckValue(action.get(HeaderType.CHECK_VALUE));
		userCase.setTime(action.get(HeaderType.TIME));
		userCase.setScreenShotFlag(getScreenShotFlag(action.get(HeaderType.SCREEN_SHOT_FLAG)));
		
		return userCase;
	}
	
	public List<HeaderType> getHead(Sheet sheet,int rownum){
		Row head = sheet.getRow(rownum ); 
		for (Cell cell : head) {
			headers.add(getHeaderValue(cell));
		}
		return headers;
	}
	
	
	private List<String> getRowValue(Sheet sheet ,int rownum,List<String> rowValue){
		Row row = sheet.getRow(rownum);
		for (Cell cell : row) {
			rowValue.add(getSheetCellValue(cell));
		}
		return rowValue;
	}
	
	private String getSheetCellValue(Cell cell) {
		String value = "";
		try {
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    value = cell.getStringCellValue();
		   }catch(NullPointerException npe) {
		     return "";
		   }
		return value;
	}
	 
	private ActionType getActionType(String name){
		
		for (ActionType item : ActionType.values()) {
			if (name.equals(item.getName())) {
				return item;
			}
		}
		return null;
	}
	
	private LocateType getLocateType(String name){
		
		for (LocateType item : LocateType.values()) {
			if (name.equals(item.getName())) {
				return item;
			}
		}
		return null;
	}
	
	private CheckType getCheckType(String name){
		
		for (CheckType item : CheckType.values()) {
			if (name.equals(item.getName())) {
				return item;
			}
		}
		return null;
	}
	
	private ScreenShotFlag getScreenShotFlag(String name){
		
		for (ScreenShotFlag item : ScreenShotFlag.values()) {
			if (name.equals(item.getName())) {
				return item;
			}
		}
		return null;
	}
	
	private HeaderType getHeaderValue(Cell cell){
		String value = getSheetCellValue(cell);
		for (HeaderType header : HeaderType.values()) {
			if (value.equals(header.getName())) {
				return header;
			}
		}
		return null;
	 }
}
