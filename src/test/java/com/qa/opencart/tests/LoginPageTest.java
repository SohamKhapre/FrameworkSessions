package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.basetest.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppErrorConstants;

public class LoginPageTest extends BaseTest{
	
	@Test
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getAccountPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE, AppErrorConstants.TITLE_NOT_FOUND_ERROR);
	}
	
	@Test (priority = Integer.MAX_VALUE)
	public void doLoginTest() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		String actualLoginPageTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actualLoginPageTitle, AppConstants.HOME_PAGE_TITLE, AppErrorConstants.TITLE_NOT_FOUND_ERROR);
	}
	
	@Test
	public void getLoginPageURLTest() {
		String actualURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION), AppErrorConstants.URL_NOT_FOUND_ERROR);
	}
	
	@Test
	public void forgotPasswordLinkExistTest() {
		boolean forgotPwdLink = loginPage.forgotPasswordLinkExist();
		Assert.assertTrue(forgotPwdLink, AppErrorConstants.ELEMENT_NOT_FOUND_ERROR);
	}

}
