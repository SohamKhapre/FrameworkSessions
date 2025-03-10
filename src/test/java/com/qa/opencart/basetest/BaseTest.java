package com.qa.opencart.basetest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.CommonPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultPage;
import com.qa.opencart.pages.ShoppingCartPage;

//@Listeners(ChainTestListener.class)

public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	
	protected Properties prop;
	
	protected CommonPage commonPage;
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected SearchResultPage searchResult;
	protected ProductInfoPage productInfo;
	protected ShoppingCartPage shoppingCart;

	@Parameters({"browser"})
	@BeforeTest(description = "Setup: Initialize the properties and driver")
	public void setUp(String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		
		if (browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		commonPage = new CommonPage(driver);
	}

	@AfterTest (description = "Close the browser")
	public void tearDown() {
		driver.close();
	}

}
