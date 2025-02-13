package com.qa.opencart.basetest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.homepage.HomePage;
import com.qa.opencart.loginpage.LoginPage;
import com.qa.opencart.productinfopage.ProductInfoPage;
import com.qa.opencart.searchpage.SearchResultPage;

public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	
	protected Properties prop;
	
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected SearchResultPage searchResult;
	protected ProductInfoPage productInfo;

	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
