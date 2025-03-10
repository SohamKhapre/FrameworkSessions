package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

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
	
	private static final Logger log = LogManager.getLogger(LoginPage.class);

	@Step("Fetching the login page tile")
	public String getAccountPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_TIMEOUT);
		//System.out.println("Login page title: " + title);
		log.info("Login page title: " + title);
		//System.out.println("---------------");
		log.info("---------------");
		return title;
	}
	
	@Step("Login with credentials: username {0} and password {1}")
	public HomePage doLogin(String email, String password) {
		//System.out.println("App creds are: " + email + " | " + password);
		log.info("App creds are: " + email + " | " + password);
		//System.out.println("---------------");
		log.info("---------------");
		eleUtil.waitForElementVisible(emailID, AppConstants.DEFAULT_TIMEOUT).sendKeys(email);
		eleUtil.doSendKeys(pwd, password);
		eleUtil.clickElementWhenReady(loginButton, AppConstants.DEFAULT_TIMEOUT);
		String loginPageTitle = eleUtil.waitForTitleIs(AppConstants.HOME_PAGE_TITLE, AppConstants.DEFAULT_TIMEOUT);
		//System.out.println("Home page title: " + loginPageTitle);
		log.info("Home page title: " + loginPageTitle);
		
		return new HomePage(driver);
	}
	
	@Step("Fetching the login page URl")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIMEOUT);
		//System.out.println("Login page URL: " + url);
		log.info("Login page URL: " + url);
		//System.out.println("---------------");
		log.info("---------------");
		return url;
	}
	
	@Step("Cheking if forgot password link is displayed")
	public boolean forgotPasswordLinkExist() {
		boolean link = eleUtil.doIsElementDisplayed(forgotPwdLink);
		//System.out.println("Forgot passwrod link exist (ture/false): " + link);
		log.info("Forgot passwrod link exist (ture/false): " + link);
		//System.out.println("---------------");
		log.info("---------------");
		return link;
	}
}
