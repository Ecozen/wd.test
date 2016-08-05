/**
 * 
 */
package topdeep.autotest.biz.executer.impl.browser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import topdeep.autotest.entity.constant.EnumType.BrowserType;
import topdeep.autotest.entity.constant.EnumType.LocateType;
import topdeep.autotest.entity.constant.EnumType.TestContextDataKey;
import topdeep.autotest.entity.constant.EnumType.TestContextParamKey;
import topdeep.autotest.entity.constant.EnumType.TestResult;
import topdeep.autotest.entity.data.AtObjParams;
import topdeep.autotest.entity.data.AtUserCase;
import topdeep.autotest.entity.data.AtTestContext;
import topdeep.autotest.entity.data.AtTestData;
import topdeep.autotest.entity.data.AtTestDataGroup;
import topdeep.autotest.entity.data.AtTestResult;
import topdeep.autotest.entity.data.AtTestResultUserCase;
import topdeep.autotest.entity.data.AtUserCaseAction;
import topdeep.autotest.entity.execute.UserCaseActionExecute;
import topdeep.autotest.entity.execute.UserCaseActionExecuteFactory;
import topdeep.autotest.entity.execute.UserCaseExecute;


public class BrowserUserCaseExecute implements UserCaseExecute {

	/**
	 * 查出测试用例所关联的所有用例和用例相关联的测试数据，然后逐个用例的执行所有的测试动作
	 */
	public TestResult execute(AtUserCase userCase, AtTestContext context, AtTestResult testResult, AtTestResultUserCase testResultUserCase,
			 Map<String, Object> data) throws Exception {
		List<AtTestData> dataList = null;
		
		dataList = new ArrayList<AtTestData>();
			
		Map<String, String> testDataMap = new HashMap<String, String>();
		List<AtObjParams> paramList = null;

		for (AtTestData item : dataList) {
			if (!testDataMap.containsKey(item.getDataKey())) {
				testDataMap.put(item.getDataKey(), item.getDataValue());
			}
		}
		
		List<AtUserCaseAction> actionList = userCase.getUserCase();
		WebDriver webDriver = (WebDriver) data.get(TestContextDataKey.Driver.getValue());
		
		int actionNo = 1;
		TestResult userCaseResult = TestResult.Success;
		
		for (AtUserCaseAction item : actionList) {
			UserCaseActionExecute actionExecute = UserCaseActionExecuteFactory.getUserCaseActionExcuter(item.getActionType());
			TestResult result = actionExecute.execute(this, item, paramList,data);
			
//			if (result != TestResult.Success) {
//				userCaseResult = result;
//			}
//			if (userCaseResult != TestResult.Success) {
//				break;
//			}
			actionNo++;
		}

		return userCaseResult;
	}

	public void beforeExecute(AtTestContext context, Map<String, Object> data) throws Exception {
		DesiredCapabilities capabilities = null;
		if (context.getBrowser().equals(BrowserType.IE)) {
			capabilities = DesiredCapabilities.internetExplorer();
		}else if(context.getBrowser().equals(BrowserType.Chrome)){
			capabilities = DesiredCapabilities.chrome();
		}else{
			capabilities = DesiredCapabilities.firefox();
		}
		String url = context.getProtocol()+"://"+context.getHost()+":"+context.getPort()+"/wd/hub";
		String serviceUrl =context.getServiceUrl();
		URL hostUrl = new URL(url) ;
		data.put(TestContextParamKey.ServeUrl.getValue(), serviceUrl);
		WebDriver wd = new RemoteWebDriver(hostUrl , capabilities);
		data.put(TestContextDataKey.Driver.getValue(), wd);
		
	}

	public void afterExecute(AtTestContext context, Map<String, Object> data) {
		String key = TestContextDataKey.Driver.getValue();
		if (data.containsKey(key)) {
			WebDriver wd = (WebDriver) data.get(key);
			wd.quit();
		}
	}

	public void httpGet(WebDriver webDriver, String url) {
		webDriver.get(url);
	}

	/**
	 * 
	 * @param webDriver
	 * @param targetId 目标htmlId
	 * @param timeout 以秒为单位
	 */
	public boolean waitElement(WebDriver webDriver, final LocateType locateType, final String targetParam, int timeout) {
		WebElement element = (new WebDriverWait(webDriver, timeout)).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				WebElement e = getWebElement(d, locateType, targetParam);
				if (e.isDisplayed()) {
					return e;
				} else {
					return null;
				}
			}
		});
		return element != null;
	}

	private WebElement getWebElement(WebDriver webDriver, LocateType locateType, String targetParam) {
		String type = locateType.getName();
		if ("id".equals(type)) {
			return getWebElementById(webDriver, targetParam);
		} else if ("class".equals(type)) {
			return getWebElementByClass(webDriver, targetParam);
		} else if ("cssSelector".equals(type)) {
			return getWebElementByCssSelector(webDriver, targetParam);
		} else if("linkText".equals(type)){
			return getWebElementByLinkText(webDriver, targetParam);
		} else if("name".equals(type)){
			return getWebElementByName(webDriver, targetParam);
		} else if("PartiaLinkText".equals(type)){
			return getWebElementByPartialLinkText(webDriver, targetParam);
		} else if("tagName".equals(type)){
			return getWebElementByTagName(webDriver, targetParam);
		} else if("xpath".equals(type)){
			return getWebElementByXpathExpression(webDriver, targetParam);
		} 
		return null;
	}

	public boolean setInputElementValue(WebDriver webDriver, LocateType locateType, String targetParam, String inputContent) {
		WebElement input = getWebElement(webDriver, locateType, targetParam);
		if (input != null) {
			input.sendKeys(inputContent);
			return true;
		}
		return false;
	}
	
	public String getInputElementText(WebDriver webDriver,LocateType locateType,String targetParam){
		WebElement input = getWebElement(webDriver, locateType, targetParam);
		if(input !=null){
			return input.getText();
		}
		return "null";
	}
	
	public void runJavaScrpit(WebDriver webDriver,String script,Object... args){
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript(script, args);
	}
	
	public WebDriver switchWindows(WebDriver webDriver,String windowName){
		return webDriver.switchTo().window(windowName);
	}
	
	public WebDriver switchFrame(WebDriver webDriver,String frameName){
		return webDriver.switchTo().frame(frameName);
	}
	
	public WebDriver swithcTop(WebDriver webDriver){
		return webDriver.switchTo().defaultContent();
	}
	
	public void waitImplicitly(WebDriver webDriver,short seconds){
		webDriver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
	
	public void waitPage(WebDriver webDriver,short seconds){
		webDriver.manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
	}
	
	public void waitScript(WebDriver webDriver,short seconds){
		webDriver.manage().timeouts().setScriptTimeout(seconds, TimeUnit.SECONDS);
	}

	public boolean elementClick(WebDriver webDriver, LocateType locateType, String targetParam) {
		WebElement element = getWebElement(webDriver, locateType, targetParam);
		if (element != null) {
			element.click();
			return true;
		}
		return false;
	}

	protected WebElement getWebElementById(WebDriver driver, String elementId) {
		WebElement element = driver.findElement(By.id(elementId));
		if (element == null) {
			// logger.warn("can't find element: "+elementId);
		}
		return element;
	}

	protected WebElement getWebElementByClass(WebDriver driver, String elementClass) {
		WebElement element = driver.findElement(By.className(elementClass));
		if (element == null) {
			// logger.warn("can't find element: "+elementId);
		}
		return element;
	}

	protected WebElement getWebElementByCssSelector(WebDriver driver, String cssSelector) {
		WebElement element = driver.findElement(By.cssSelector(cssSelector));
		if (element == null) {
			// logger.warn("can't find element: "+elementId);
		}
		return element;
	}
	
	protected WebElement getWebElementByLinkText(WebDriver driver,String linkText){
		WebElement element = driver.findElement(By.linkText(linkText));
		if(element == null){
			// logger.warn("can't find element: "+elementId);
		}
		return element;
	}
	
	protected WebElement getWebElementByName(WebDriver driver,String name){
		WebElement element =  driver.findElement(By.name(name));
		if(element == null){
			// logger.warn("can't find element: "+elementId);
		}
		return element;
	}
	
	protected WebElement getWebElementByPartialLinkText(WebDriver driver,String partialLinkText){
		WebElement element = driver.findElement(By.partialLinkText(partialLinkText));
		if(element == null){
			// logger.warn("can't find element: "+elementId);
		}
		return element;
	}
	
	protected WebElement getWebElementByTagName(WebDriver driver,String tagName){
		WebElement element = driver.findElement(By.tagName(tagName));
		if(element == null){
			// logger.warn("can't find element: "+elementId);
		}
		return element;
	}
	
	protected WebElement getWebElementByXpathExpression(WebDriver driver,String xpathExpression){
		WebElement element = driver.findElement(By.xpath(xpathExpression));
		if(element == null){
			// logger.warn("can't find element: "+elementId);
		}
		return element;
	}

	/**
	 * 文件保存路径 保存位置/testTaskId/testResultId/testResultUserCaseId/actionName_xxx.jpg 截屏顺序编码
	 * 
	 * @param webDriver
	 * @param savePath
	 */
	public void screenShot(WebDriver webDriver, String savePath) {

		if (!savePath.startsWith("/")) {
			String filepath =System.getProperty("user.home");
			if (!filepath.endsWith("/")) {
				filepath += "/";
			}
			savePath = filepath + savePath;
		}
		String dir = savePath.substring(0, savePath.lastIndexOf("/"));
		File fdir = new File(dir);
		if (!fdir.exists()) {
			fdir.mkdirs();
		}
		File source_file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE); // 关键代码，执行屏幕截图，默认会把截图保存到temp目录
		source_file.renameTo(new File(savePath));

	}

	public Alert alertSwitchTo(WebDriver webDriver) {
		Alert alert = webDriver.switchTo().alert();
		return alert;
	}

	public String getAlertText(Alert alert) {
		return alert.getText();
	}

	public boolean alterAccept(Alert alert) {
		alert.accept();
		return true;
	}

	public boolean alertDismiss(Alert alert) {
		alert.dismiss();
		return true;
	}

	public String getPageTitle(WebDriver webDriver) {
		return webDriver.getTitle();
	}

	public String getElementValue(WebDriver webDriver, LocateType locateType, String targetParam) {
		WebElement input = getWebElement(webDriver, locateType, targetParam);
		if (input != null) {
			return input.getText();
		}
		return null;
	}
	

	
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getObjType() {
		// TODO Auto-generated method stub
		return null;
	}


	public List<AtObjParams> getInitParamList() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getBeanVersion() {
		// TODO Auto-generated method stub
		return 0;
	}



}
