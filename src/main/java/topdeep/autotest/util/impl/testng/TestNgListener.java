package topdeep.autotest.util.impl.testng;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.log4testng.Logger;

import topdeep.autotest.biz.executer.impl.browser.BrowserUserCaseExecuteTest;

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
        try {
        	BrowserUserCaseExecuteTest tb = (BrowserUserCaseExecuteTest) tr.getInstance();
            WebDriver driver = tb.getDriver();
            ScreenShot shot = new ScreenShot(driver);
            shot.takeScreenshot();
            logger.info(driver.getTitle());
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {         
            e.printStackTrace();
        }
	}

}
