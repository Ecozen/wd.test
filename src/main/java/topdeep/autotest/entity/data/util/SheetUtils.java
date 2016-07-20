package topdeep.autotest.entity.data.util;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SheetUtils {

	public static void removeSheetByName(Workbook wb, String sheetName) {
		Sheet sheet = wb.getSheet(sheetName);
		wb.removeSheetAt(wb.getSheetIndex(sheet));
	}
	
	public static void addSheetByName(Workbook wb,String sheetName){
		wb.createSheet(sheetName);
	}
}
