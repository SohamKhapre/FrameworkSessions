package com.qa.opencart.homepage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.searchpage.SearchResultPage;
import com.qa.opencart.util.ElementUtil;

public class HomePage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutButton = By.linkText("Logout");
	private By headersList = By.cssSelector("div#content h2");
	private By searchKey = By.name("search");
	private By searchIcon = By.xpath("//div[@id='search']//button"); 
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getHomePageTitle () {
		String title = eleUtil.waitForTitleIs(AppConstants.HOME_PAGE_TITLE, AppConstants.DEFAULT_TIMEOUT);
		System.out.println("Home page title is: " + title);
		System.out.println("---------------");
		return title;
	}
	
	public String getHomePageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.HOME_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIMEOUT);
		System.out.println("Home page URL: " + url);
		System.out.println("---------------");
		return url;
	}
	
	public boolean logoutButtonExist() {
		boolean link = eleUtil.doIsElementDisplayed(logoutButton);
		System.out.println("Logout button displayed (true/false): " + link);
		System.out.println("---------------");
		return link;
	}
	
	public List<String> getHomePageHeaders () {
		List<WebElement> headers = eleUtil.waitForElementsVisible(headersList, AppConstants.SHORT_TIMEOUT);
		List<String> headerList = new ArrayList<String>();
		for (WebElement e : headers) {
			String text = e.getText();
			headerList.add(text);
		}
		System.out.println("List of home page headers: " + headerList);
		System.out.println("---------------");
		return headerList;
	}
	
	public SearchResultPage searchForProductOnHomePage (String productName) {
		System.out.println("Seach for the product: " + productName);
		System.out.println("---------------");
		WebElement element = eleUtil.waitForElementVisible(searchKey, AppConstants.DEFAULT_TIMEOUT);
		eleUtil.doSendKeys(element, productName);
		eleUtil.doClick(searchIcon);
		return new SearchResultPage(driver);
	}

}
