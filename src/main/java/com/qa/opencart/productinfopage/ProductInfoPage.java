package com.qa.opencart.productinfopage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productDetails;

	private By productHeader = By.cssSelector("div#content h1");
	private By images = By.xpath("//div//ul[@class='thumbnails']/li");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productMetaPrice = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeader() {
		String header = eleUtil.waitForElementVisible(productHeader, AppConstants.DEFAULT_TIMEOUT).getText();
		System.out.println("Product header is: " + header);
		System.out.println("---------------");
		return header;
	}

	public int getProductImageCount() {
		int imageCount = eleUtil.waitForElementsVisible(images, AppConstants.DEFAULT_TIMEOUT).size();
		System.out.println("Product image count: " + imageCount);
		System.out.println("---------------");
		return imageCount;
	}

	public Map<String, String> getFullProductDetails() {
		productDetails = new HashMap<String, String>();
		productDetails.put("Header", getProductHeader());
		productDetails.put("ImageCount", Integer.toString(getProductImageCount()));
		getProductMetaDataDetails();
		getProductMetaDataPriceDetails();
		System.out.println(productDetails);
		System.out.println("---------------");
		return productDetails;
	}

	/**
	 * Method to read the following data details -
	 * a. Brand: Apple
	 * b. Product Code: Product 18
	 * c. Reward Points: 800
	 * d. Availability: In Stock
	 */

	public void getProductMetaDataDetails() {
		List<WebElement> details = eleUtil.waitForElementsVisible(productMetaData, AppConstants.SHORT_TIMEOUT);
		for (WebElement e : details) {
			String metaText = e.getText();
			String metaDetails[] = metaText.split(":");
			String metaKey = metaDetails[0].trim();
			String metaValue = metaDetails[1].trim();
			productDetails.put(metaKey, metaValue);
		}
	}

	/**
	 * Method to read the following price details -
	 * a. $2,000.00
	 * b. Ex Tax: $2,000.00
	 */

	public void getProductMetaDataPriceDetails() {
		List<WebElement> price = eleUtil.waitForElementsVisible(productMetaPrice, AppConstants.SHORT_TIMEOUT);
		String productPrice = price.get(0).getText().trim();
		String metaDetails[] = price.get(1).getText().split(":");
		String metaKey = metaDetails[0].trim();
		String metaValue = metaDetails[1].trim();
		productDetails.put("Price", productPrice);
		productDetails.put(metaKey, metaValue);
	}

}
