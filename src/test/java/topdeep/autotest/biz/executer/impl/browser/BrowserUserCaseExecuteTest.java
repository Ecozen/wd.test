package topdeep.autotest.biz.executer.impl.browser;

import org.testng.annotations.Test;

import topdeep.autotest.entity.data.AtTestCase;
import topdeep.autotest.entity.data.AtTestContext;
import topdeep.autotest.entity.data.AtTestDataGroup;
import topdeep.autotest.entity.data.AtTestResult;
import topdeep.autotest.entity.data.AtTestResultUserCase;
import topdeep.autotest.entity.data.util.ExcelDataProvider;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
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
  @BeforeTest
  @Parameters({"url","workBook","version"})
  public void beforeTest(String url,String path,String version) throws MalformedURLException {
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
	  context.setVersion(version);
	  context.createUrl(url);
	  context.setPlatform();
  }
  @AfterTest
  public void afterTest() {
	  
  }


  @Test
  public void executeTest() throws Exception {
	AtTestCase userCase = new ExcelDataProvider(actionSheet , true, true, 0).getdata();
	
	AtTestResult testResult = null;
	AtTestResultUserCase testResultUserCase = null;
	AtTestDataGroup testDataGroup = null;
	Log taskLog = null;

	executer.beforeExecute(userCase, context, data, taskLog);
	executer.execute(userCase, context, testResult, testResultUserCase, testDataGroup, data, taskLog);
	executer.afterExecute(userCase, context, data, taskLog);
  }
}
