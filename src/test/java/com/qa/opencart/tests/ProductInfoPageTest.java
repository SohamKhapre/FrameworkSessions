package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.basetest.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic003: Desgin the product info page for open cart application")
@Story("User Story003: Design the product info page features")
@Owner("Soham Khapre")

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
	
	@Description("Verify that correct header is displayed for the searched product {1}")
	@Severity(SeverityLevel.CRITICAL)
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
	
	@Description("Verify that expected product iamge count {1} is displayed for the product {2}")
	@Test (dataProvider = "testDataForImageCountTest")
	@Severity(SeverityLevel.BLOCKER)
	public void getProductImageCountTest(String searchKey, String selectProductName, int expectedImageCount) {
		searchResult = homePage.searchForProductOnHomePage(searchKey);
		productInfo = searchResult.selectProduct(selectProductName);
		int actualImageCount = productInfo.getProductImageCount();
		Assert.assertEquals(actualImageCount, expectedImageCount);
	}
	
	@Description("Verify that correct product meta data and price details are displayed")
	@Severity(SeverityLevel.BLOCKER)
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
 
	@Description("Verify that correct product cart details details are displayed")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void productAddToCartTest() {
		searchResult = homePage.searchForProductOnHomePage("MacBook Air");
		productInfo = searchResult.selectProduct("MacBook Air");
		shoppingCart = productInfo.productAddToCart("MacBook Air", "2");
		Map<String, String> productCartDetails = shoppingCart.shoppingCartFullProductDetails("2");
		productCartDetails.forEach((k,v) -> System.out.println(k + ":" + v));
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertTrue(productCartDetails.get("Product Name").contains("MacBook Air"));
		//softAssert.assertEquals(productCartDetails.get("Product Name"), "MacBook Air");
		softAssert.assertEquals(productCartDetails.get("Total"), "$2,404.00");
		softAssert.assertEquals(productCartDetails.get("Model"), "Product 17");
		softAssert.assertEquals(productCartDetails.get("VAT (20%)"), "$400.00");
		softAssert.assertEquals(productCartDetails.get("Eco Tax (-2.00)"), "$4.00");
		softAssert.assertEquals(productCartDetails.get("Unit Price"), "$1,202.00");
		softAssert.assertEquals(productCartDetails.get("Sub-Total"), "$2,000.00");
		
		softAssert.assertAll();
	}
}
