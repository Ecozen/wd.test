package topdeep.autotest.entity.data.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class ExcelDataProviderTest {
	String filePath = "";
	Workbook wb = null;
	Sheet actionSheet = null;
	
	@BeforeTest
    @Parameters("workBook")
    public void setup(String path) {
        filePath = path;
     
        try {
        	if (filePath.endsWith("xlsx")) {
        		wb = new XSSFWorkbook(new FileInputStream(filePath));
			}else if(filePath.endsWith("xls")){
				wb = new HSSFWorkbook(new FileInputStream(filePath));
			}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        actionSheet = wb.getSheet("Lionfund_openAccount_action");
        
    }

	
	
  @Test
  public void getdataTest() {
	  ExcelDataProvider reader = new ExcelDataProvider(actionSheet, true, true, 0);
	  reader.getdata();
	  System.out.println("sucess--------");
  }
}
