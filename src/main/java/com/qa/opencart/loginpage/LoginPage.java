package com.qa.opencart.loginpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.homepage.HomePage;
import com.qa.opencart.util.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By emailID = By.id("input-email");
	private By pwd = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");

	public String getAccountPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_TIMEOUT);
		System.out.println("Login page title: " + title);
		System.out.println("---------------");
		return title;
	}
	
	public HomePage doLogin(String email, String password) {
		System.out.println("App creds are: " + email + " | " + password);
		System.out.println("---------------");
		eleUtil.waitForElementVisible(emailID, AppConstants.DEFAULT_TIMEOUT).sendKeys(email);
		eleUtil.doSendKeys(pwd, password);
		eleUtil.clickElementWhenReady(loginButton, AppConstants.DEFAULT_TIMEOUT);
		String loginPageTitle = eleUtil.waitForTitleIs(AppConstants.HOME_PAGE_TITLE, AppConstants.DEFAULT_TIMEOUT);
		System.out.println("Home page title: " + loginPageTitle);
		
		return new HomePage(driver);
	}
	
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIMEOUT);
		System.out.println("Login page URL: " + url);
		System.out.println("---------------");
		return url;
	}
	
	public boolean forgotPasswordLinkExist() {
		boolean link = eleUtil.doIsElementDisplayed(forgotPwdLink);
		System.out.println("Forgot passwrod link exist (ture/false): " + link);
		System.out.println("---------------");
		return link;
	}
}
