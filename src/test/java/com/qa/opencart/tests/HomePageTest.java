package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.basetest.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppErrorConstants;

public class HomePageTest extends BaseTest {
	
	@BeforeClass
	public void homePageSetUp() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void getHomePageTitleTest() {
		String actualHomePageTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actualHomePageTitle, AppConstants.HOME_PAGE_TITLE, AppErrorConstants.TITLE_NOT_FOUND_ERROR);
	}
	
	@Test
	public void getHomePageURL() {
		String actualURL = homePage.getHomePageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.HOME_PAGE_URL_FRACTION), AppErrorConstants.URL_NOT_FOUND_ERROR);
	}
	
	@Test
	public void logoutButtonExistTest() {
		boolean link = homePage.logoutButtonExist();
		Assert.assertTrue(link, AppErrorConstants.ELEMENT_NOT_FOUND_ERROR);
	}
	
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
 	
	@Test (dataProvider = "getTestData")
	public void searchForProductOnHomePageTest(String productName, int resultCount) {
		searchResult = homePage.searchForProductOnHomePage(productName);
		Assert.assertEquals(searchResult.getProductResultCount(), resultCount);
	}

}
