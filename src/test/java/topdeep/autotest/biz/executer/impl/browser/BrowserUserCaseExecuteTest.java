package topdeep.autotest.biz.executer.impl.browser;

import org.testng.annotations.Test;

import topdeep.autotest.entity.constant.EnumType.BrowserType;
import topdeep.autotest.entity.constant.EnumType.TestContextDataKey;
import topdeep.autotest.entity.data.AtTestContext;
import topdeep.autotest.entity.data.AtTestResult;
import topdeep.autotest.entity.data.AtTestResultUserCase;
import topdeep.autotest.entity.data.AtUserCase;
import topdeep.autotest.entity.data.util.ExcelReader;
import topdeep.autotest.util.impl.testng.TestBase;

import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BrowserUserCaseExecuteTest extends TestBase{

BrowserUserCaseExecute executer = new BrowserUserCaseExecute();
String filePath = "";
Workbook wb = null;
Sheet actionSheet = null; 
AtTestContext context = new AtTestContext();
Map<String, Object> data = new HashMap<String, Object>();
ExcelReader reader = new ExcelReader();
AtUserCase userCase;


  @BeforeClass
  @Parameters({"sheetName","workBook","serviceUrl"})
  public void beforeTest(String sheetName,String path,String serviceUrl) throws Exception {
      filePath = path;
      wb=getWorkBook(filePath,wb);
	  context.setPlatform();
	  context.setBrowser(BrowserType.Firefox);
	  context.setProtocol("http");
	  context.setHost("192.168.0.162");
	  context.setPort("4444");
	  context.setServiceUrl(serviceUrl);
	  executer.beforeExecute( context, data);
	  userCase = reader.getUserCase(sheetName, wb,sheetName);
  }
  @AfterClass
  public void afterTest() {
	  executer.afterExecute(context, data);
	  
  }
  
  @Test
  public void executeTest() throws Exception {
	  AtTestResult testResult = null;
	  AtTestResultUserCase testResultUserCase = null;
	  
	  executer.execute(userCase, context, testResult, testResultUserCase, data);
  }
  
  public Workbook getWorkBook(String filePath,Workbook wb){
      try {
        	if (filePath.endsWith("xlsx")){
        		wb = new XSSFWorkbook(new FileInputStream(filePath));
        		return wb;
  			}else if(filePath.endsWith("xls")){
	  			wb = new HSSFWorkbook(new FileInputStream(filePath));
	  			return wb;
  			}
        } catch (FileNotFoundException e){
            e.printStackTrace();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
      return wb;
  }

  public WebDriver getDriver() {
  	String key = TestContextDataKey.Driver.getValue();
  	WebDriver driver = null;
  	if (data.containsKey(key)) {
  		driver = (WebDriver) data.get(key);
  	}
  	return driver;
  }

}
