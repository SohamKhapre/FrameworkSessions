package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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

@Epic("Epic002: Desgin the home page for open cart application")
@Story("User Story002: Desgin different features for home page")
@Owner("Soham Khapre")
public class HomePageTest extends BaseTest {
	
	@BeforeClass
	public void homePageSetUp() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Description("Verify that correct home page tile is fetched")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void getHomePageTitleTest() {
		String actualHomePageTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actualHomePageTitle, AppConstants.HOME_PAGE_TITLE, AppErrorConstants.TITLE_NOT_FOUND_ERROR);
	}
	
	@Description("Verify that corretc home page URL is fethced")
	@Severity(SeverityLevel.TRIVIAL)
	@Test
	public void getHomePageURL() {
		String actualURL = homePage.getHomePageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.HOME_PAGE_URL_FRACTION), AppErrorConstants.URL_NOT_FOUND_ERROR);
	}
	
	@Description("Verify that log out button is displayed on the home page")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void logoutButtonExistTest() {
		boolean link = homePage.logoutButtonExist();
		Assert.assertTrue(link, AppErrorConstants.ELEMENT_NOT_FOUND_ERROR);
	}
	
	@Description("Getting the list of headers")
	@Test
	public void getHomePageHeadersTest() {
		List<String> actualHeaders = homePage.getHomePageHeaders();
	}
	
	@DataProvider
	public Object[][] getTestData() {
		return new Object[][] {
			{"mac", 4},
			{"apple", 1},
			{"samsung", 2},
			{"motorola", 0}
		};
	}
 	
	@Description("Verify that product {0} is searched and expected result count {1} is returned for seached product {0}")
	@Severity(SeverityLevel.CRITICAL)
	@Test (dataProvider = "getTestData")
	public void searchForProductOnHomePageTest(String productName, int resultCount) {
		searchResult = homePage.searchForProductOnHomePage(productName);
		Assert.assertEquals(searchResult.getProductResultCount(), resultCount);
	}

}
