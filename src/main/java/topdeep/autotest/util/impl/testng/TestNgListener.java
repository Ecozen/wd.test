package topdeep.autotest.util.impl.testng;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.log4testng.Logger;

import topdeep.autotest.entity.constant.EnumType.TestContextDataKey;

public class TestNgListener extends TestListenerAdapter {
	
	private static Logger logger = Logger.getLogger(TestNgListener.class);
	public static final String CONFIG = "config.properties";

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		logger.info(tr.getName() + " Success");
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		logger.info(tr.getName() + " Failure");
		takeScreenShot(tr);
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		logger.info(tr.getName() + " Skipped");
        takeScreenShot(tr);
	}

	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		super.onTestStart(result);
		logger.info(result.getName() + " Start");
	}
	
	private void takeScreenShot(ITestResult tr) {
		Map<String, Object> data = null;
        WebDriver driver = (WebDriver)data.get(TestContextDataKey.Driver.getValue());
        
	}

}
