package com.qa.opencart.searchpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;
import com.qa.opencart.productinfopage.ProductInfoPage;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By resultCount = By.cssSelector("div.product-thumb");
	
	public SearchResultPage (WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public int getProductResultCount () {
		int count = eleUtil.waitForElementsVisible(resultCount, AppConstants.DEFAULT_TIMEOUT).size();
		System.out.println("Number of results for the searched product: " + count);
		System.out.println("---------------");
		return count;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Select the product: " + productName);
		System.out.println("---------------");
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}

}
