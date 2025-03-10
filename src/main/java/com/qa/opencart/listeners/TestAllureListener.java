package com.qa.opencart.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Attachment;


public class TestAllureListener implements ITestListener {
	
	private static final Logger log = LogManager.getLogger(TestAllureListener.class);

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	
	// Text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	// HTML attachments for Allure
	@Attachment(value = "{0}", type = "text/html")
	public static String attachHtml(String html) {
		return html;
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		//System.out.println("I am in onStart method " + iTestContext.getName());
		log.info("I am in onStart method " + iTestContext.getName());
		//iTestContext.setAttribute("WebDriver", BasePage.getDriver());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		//System.out.println("I am in onFinish method " + iTestContext.getName());
		log.info("I am in onFinish method " + iTestContext.getName());
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		//System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
		log.info("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		//System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
		log.info("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		//System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
		log.info("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
		Object testClass = iTestResult.getInstance();
		//WebDriver driver = BasePage.getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		if (DriverFactory.getTLDriver() instanceof WebDriver) {
			//System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			log.info("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveScreenshotPNG(DriverFactory.getTLDriver());
		}
		// Save a log on allure.
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");		
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		//System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
		log.info("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		//System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
		log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

}