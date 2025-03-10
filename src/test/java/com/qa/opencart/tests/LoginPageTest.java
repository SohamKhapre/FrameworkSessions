package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.basetest.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppErrorConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic001: Design the login page features for open cart application")
@Story("User Story001: Design different features ofr login page")
@Owner("Soham Khapre")
public class LoginPageTest extends BaseTest{

	@Description("Verify that correct login page title is fetched")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getAccountPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE, AppErrorConstants.TITLE_NOT_FOUND_ERROR);
	}
	
	@Description("Check the login functionality")
	@Severity(SeverityLevel.BLOCKER)
	@Test (priority = Integer.MAX_VALUE)
	public void doLoginTest() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		String actualLoginPageTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actualLoginPageTitle, AppConstants.HOME_PAGE_TITLE, AppErrorConstants.TITLE_NOT_FOUND_ERROR);
	}
	
	@Description("Verify that correct login page URL is fetched")
	@Severity(SeverityLevel.TRIVIAL)
	@Test
	public void getLoginPageURLTest() {
		String actualURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION), AppErrorConstants.URL_NOT_FOUND_ERROR);
	}
	
	@Description("Verify that fowgot password link is displayed")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void forgotPasswordLinkExistTest() {
		boolean forgotPwdLink = loginPage.forgotPasswordLinkExist();
		Assert.assertTrue(forgotPwdLink, AppErrorConstants.ELEMENT_NOT_FOUND_ERROR);
	}
	
	@Description("Verify that logo is displayed on the lop left corner of the login page")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void getLogoTest() {
		Assert.assertTrue(commonPage.getLogo(), AppErrorConstants.LOGO_NOT_FOUND_ERROR);
	}
	
	@DataProvider
	public Object[][] getFooterTestData() {
		return new Object[][] {
			{"Delivery Information"},
			{"Site Map"},
			{"Brands"},
			{"Newsletter"},
			{"OpenCart"},
		};
	}
	
	@Description("Verify that esential footers are visible on the login page")
	@Severity(SeverityLevel.MINOR)
	@Test (dataProvider = "getFooterTestData")
	public void isFooterAvailableTest(String footerName) {
		Assert.assertTrue(commonPage.isFooterAvailable(footerName),AppErrorConstants.FOOTER_NOT_FOUND_ERROR);
	}

}
