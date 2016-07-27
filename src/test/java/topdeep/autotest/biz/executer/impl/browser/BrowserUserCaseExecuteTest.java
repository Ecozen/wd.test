package topdeep.autotest.biz.executer.impl.browser;

import org.testng.annotations.Test;

import topdeep.autotest.entity.constant.EnumType.BrowserType;
import topdeep.autotest.entity.data.AtTestContext;
import topdeep.autotest.entity.data.AtTestResult;
import topdeep.autotest.entity.data.AtTestResultUserCase;
import topdeep.autotest.entity.data.AtUserCase;
import topdeep.autotest.entity.data.util.ExcelReader;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterTest;

public class BrowserUserCaseExecuteTest {

BrowserUserCaseExecute executer = new BrowserUserCaseExecute();
String filePath = "";
Workbook wb = null;
Sheet actionSheet = null; 
AtTestContext context = new AtTestContext();
Map<String, Object> data = new HashMap<String, Object>();
ExcelReader reader = new ExcelReader();
AtUserCase userCase;
private void getWorkBook(){
    try {
      	if (filePath.endsWith("xlsx")){
      		wb = new XSSFWorkbook(new FileInputStream(filePath));
			}else if(filePath.endsWith("xls")){
			wb = new HSSFWorkbook(new FileInputStream(filePath));
			}
      } catch (FileNotFoundException e){
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }		
}

  @BeforeTest
  @Parameters({"sheetName","workBook","serviceUrl"})
  public void beforeTest(String sheetName,String path,String serviceUrl) throws MalformedURLException {
      filePath = path;
      getWorkBook();
      
	  context.setPlatform();
	  context.setBrowser(BrowserType.Firefox);
	  context.setProtocol("http");
	  context.setHost("192.168.0.162");
	  context.setPort("4444");
	  context.setServiceUrl(serviceUrl);
	  userCase = reader.getUserCace(sheetName, wb,sheetName);
  }
  @AfterTest
  public void afterTest() {
	  
  }


  @Test
  public void executeTest() throws Exception {
	AtTestResult testResult = null;
	AtTestResultUserCase testResultUserCase = null;

	executer.beforeExecute(userCase, context, data);
	executer.execute(userCase, context, testResult, testResultUserCase, data);
	executer.afterExecute(userCase, context, data);
  }
}
