package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.basetest.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoPageSetUp() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] testDataForHeadersTest() {
		return new Object[][] {
			{"macbook", "MacBook"},
			{"macbook", "MacBook Air"},
			{"macbook", "MacBook Pro"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"samsung", "Samsung Galaxy Tab 10.1"},
		};
	}
	
	@Test (dataProvider = "testDataForHeadersTest")
	public void getProductHeaderTest(String searchKey, String selectProductName) {
		searchResult = homePage.searchForProductOnHomePage(searchKey);
		productInfo = searchResult.selectProduct(selectProductName);
		String actualProductHeader = productInfo.getProductHeader();
		Assert.assertEquals(actualProductHeader, selectProductName);
	}
	
	@DataProvider
	public Object[][] testDataForImageCountTest() {
		return new Object[][] {
			{"macbook", "MacBook", 5},
			{"macbook", "MacBook Air", 4},
			{"macbook", "MacBook Pro", 4},
			{"samsung", "Samsung SyncMaster 941BW", 1},
			{"samsung", "Samsung Galaxy Tab 10.1", 7},
		};
	}
	
	@Test (dataProvider = "testDataForImageCountTest")
	public void getProductImageCountTest(String searchKey, String selectProductName, int expectedImageCount) {
		searchResult = homePage.searchForProductOnHomePage(searchKey);
		productInfo = searchResult.selectProduct(selectProductName);
		int actualImageCount = productInfo.getProductImageCount();
		Assert.assertEquals(actualImageCount, expectedImageCount);
	}
	
	@Test
	public void getFullProductDetailsTest() {
		searchResult = homePage.searchForProductOnHomePage("macbook");
		productInfo = searchResult.selectProduct("MacBook Air");
		
		Map<String, String> productDetails = productInfo.getFullProductDetails();
		productDetails.forEach((k,v)  -> System.out.println(k + ":" + v));
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(productDetails.get("Header"), "MacBook Air");
		softAssert.assertEquals(productDetails.get("ImageCount"), "4");
		softAssert.assertEquals(productDetails.get("Brand"), "Apple");
		softAssert.assertEquals(productDetails.get("Product Code"), "Product 17");
		softAssert.assertEquals(productDetails.get("Reward Points"), "700");
		softAssert.assertEquals(productDetails.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(productDetails.get("Price"), "$1,202.00");
		softAssert.assertEquals(productDetails.get("Ex Tax"), "$1,000.00");
		
		softAssert.assertAll();
	}
 
}
