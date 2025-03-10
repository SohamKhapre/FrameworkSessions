package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By resultCount = By.cssSelector("div.product-thumb");
	
	private static final Logger log = LogManager.getLogger(SearchResultPage.class);
	
	public SearchResultPage (WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	@Step("Getting the count for the searched product")
	public int getProductResultCount () {
		int count = eleUtil.waitForElementsVisible(resultCount, AppConstants.DEFAULT_TIMEOUT).size();
		//System.out.println("Number of results for the searched product: " + count);
		log.info("Number of results for the searched product: " + count);
		//System.out.println("---------------");
		log.info("---------------");
		return count;
	}
	
	@Step("Selecting the product {0}")
	public ProductInfoPage selectProduct(String productName) {
		//System.out.println("Select the product: " + productName);
		log.info("Select the product: " + productName);
		//System.out.println("---------------");
		log.info("---------------");
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}

}
