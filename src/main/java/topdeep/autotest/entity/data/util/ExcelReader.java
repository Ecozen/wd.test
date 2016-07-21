package topdeep.autotest.entity.data.util;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelReader {
	
	public void read(String sheetName,Workbook wb){
		Sheet sheet = wb.getSheet(sheetName);
		
	}
}
