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
import topdeep.autotest.entity.data.AtUserCaseAction;

public class ExcelReader {
	List<HeaderType> headers = new ArrayList<HeaderType>();
	
	public Map<HeaderType,String> getAction(String sheetName,Workbook wb,int rownum){
		Sheet sheet = wb.getSheet(sheetName);
		Map<HeaderType,String> action = new HashMap<HeaderType, String>();
		
		if(rownum ==0){
			headers = getHead(sheet, rownum);
		}else if(rownum>0){
			Row r = sheet.getRow(rownum );
			for (Cell cell : r) {
				int col = 0;
				action.put(headers.get(col),getSheetCellValue(cell) );
				col++;
			}
		}
		
		return action;
	}
	
	public AtUserCaseAction getAction(Map<HeaderType,String> action ){
		
		AtUserCaseAction userCase = new AtUserCaseAction();
		userCase.setActionExecuteId(action.get(HeaderType.ACTION_EXCUTE_ID));
		userCase.setActionDesc(action.get(HeaderType.ACTION_DESC));
		userCase.setActionType(ActionType.valueOf(action.get(HeaderType.ACTION_TYPE)));
		userCase.setLocateType(LocateType.valueOf(action.get(HeaderType.LOCATE_TYPE)));
		userCase.setLocateParam(action.get(HeaderType.LOCATE_PARAM));
		userCase.setInputValue(action.get(HeaderType.INPUT_VALUE));
		userCase.setCheckType(CheckType.valueOf(action.get(HeaderType.CHECK_TYPE)));
		userCase.setCheckValue(action.get(HeaderType.CHECK_VALUE));
		userCase.setTime(action.get(HeaderType.TIME));
		userCase.setScreenShotFlag(ScreenShotFlag.valueOf(action.get(HeaderType.SCREEN_SHOT_FLAG)));
		
		return userCase;
	}
	
	public List<HeaderType> getHead(Sheet sheet,int rownum){
		Row head = sheet.getRow(rownum ); 
		for (Cell cell : head) {
			headers.add(getHeaderValue(cell));
		}
		return headers;
	}
	
	
	private List<String> getRowValue(Sheet sheet ,int rownum){
		List<String> rowValue = new ArrayList<String>();
		Row row = sheet.getRow(rownum ); 
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
