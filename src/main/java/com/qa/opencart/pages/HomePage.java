package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class HomePage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutButton = By.linkText("Logout");
	private By headersList = By.cssSelector("div#content h2");
	private By searchKey = By.name("search");
	private By searchIcon = By.xpath("//div[@id='search']//button"); 
	
	private static final Logger log = LogManager.getLogger(HomePage.class);
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	@Step("Fetching the home page title")
	public String getHomePageTitle () {
		String title = eleUtil.waitForTitleIs(AppConstants.HOME_PAGE_TITLE, AppConstants.DEFAULT_TIMEOUT);
		//System.out.println("Home page title is: " + title);
		log.info("Home page title is: " + title);
		//System.out.println("---------------");
		log.info("---------------");
		return title;
	}
	
	@Step("Fetching the home pge URL")
	public String getHomePageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.HOME_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIMEOUT);
		//System.out.println("Home page URL: " + url);
		log.info("Home page URL: " + url);
		//System.out.println("---------------");
		log.info("---------------");
		return url;
	}
	
	@Step("Checking if logout button is displayed on the home page")
	public boolean logoutButtonExist() {
		boolean link = eleUtil.doIsElementDisplayed(logoutButton);
		//System.out.println("Logout button displayed (true/false): " + link);
		log.info("Logout button displayed (true/false): " + link);
		//System.out.println("---------------");
		log.info("---------------");
		return link;
	}
	
	@Step("Fetching the home page headers list")
	public List<String> getHomePageHeaders () {
		List<WebElement> headers = eleUtil.waitForElementsVisible(headersList, AppConstants.SHORT_TIMEOUT);
		List<String> headerList = new ArrayList<String>();
		for (WebElement e : headers) {
			String text = e.getText();
			headerList.add(text);
		}
		System.out.println("List of home page headers: " + headerList);
		//System.out.println("---------------");
		log.info("---------------");
		return headerList;
	}
	
	@Step("Searching for the product {0} on the home page")
	public SearchResultPage searchForProductOnHomePage (String productName) {
		//System.out.println("Seach for the product: " + productName);
		log.info("Seach for the product: " + productName);
		//System.out.println("---------------");
		log.info("---------------");
		WebElement element = eleUtil.waitForElementVisible(searchKey, AppConstants.DEFAULT_TIMEOUT);
		eleUtil.doSendKeys(element, productName);
		eleUtil.doClick(searchIcon);
		return new SearchResultPage(driver);
	}

}
