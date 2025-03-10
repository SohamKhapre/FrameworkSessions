package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productDetails;

	private By productHeader = By.cssSelector("div#content h1");
	private By images = By.xpath("//div//ul[@class='thumbnails']/li");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productMetaPrice = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By quantity = By.xpath("//input[@name='quantity']");
	private By addToCartButton = By.id("button-cart");
	private By successMsg = By.cssSelector("div.alert.alert-success.alert-dismissible");
	private By goToCart = By.linkText("shopping cart");
	
	private static final Logger log = LogManager.getLogger(ProductInfoPage.class);

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	@Step("Getting the product header name")
	public String getProductHeader() {
		String header = eleUtil.waitForElementVisible(productHeader, AppConstants.DEFAULT_TIMEOUT).getText();
		//System.out.println("Product header is: " + header);
		log.info("Product header is: " + header);
		//System.out.println("---------------");
		log.info("---------------");
		return header;
	}

	@Step("Getting the product image count")
	public int getProductImageCount() {
		int imageCount = eleUtil.waitForElementsVisible(images, AppConstants.DEFAULT_TIMEOUT).size();
		//System.out.println("Product image count: " + imageCount);
		log.info("Product image count: " + imageCount);
		//System.out.println("---------------");
		log.info("---------------");
		return imageCount;
	}

	public Map<String, String> getFullProductDetails() {
		productDetails = new HashMap<String, String>();
		productDetails.put("Header", getProductHeader());
		productDetails.put("ImageCount", Integer.toString(getProductImageCount()));
		getProductMetaDataDetails();
		getProductMetaDataPriceDetails();
		//System.out.println(productDetails);
		log.info(productDetails);
		//System.out.println("---------------");
		log.info("---------------");
		return productDetails;
	}

	/**
	 * Method to read the following data details -
	 * a. Brand: Apple
	 * b. Product Code: Product 18
	 * c. Reward Points: 800
	 * d. Availability: In Stock
	 */

	@Step("Getting the product meta data details")
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

	@Step("Getting the product price details")
	public void getProductMetaDataPriceDetails() {
		List<WebElement> price = eleUtil.waitForElementsVisible(productMetaPrice, AppConstants.SHORT_TIMEOUT);
		String productPrice = price.get(0).getText().trim();
		String metaDetails[] = price.get(1).getText().split(":");
		String metaKey = metaDetails[0].trim();
		String metaValue = metaDetails[1].trim();
		productDetails.put("Price", productPrice);
		productDetails.put(metaKey, metaValue);
	}
	
	@Step("Adding the product {0} with quantity {1} to the cart")
	public ShoppingCartPage productAddToCart(String productName, String productQuantity) {
		//System.out.println("Enter quantity: " + productQuantity);
		log.info("Enter quantity: " + productQuantity);
		eleUtil.doSendKeys(quantity, productQuantity);
		eleUtil.clickElementWhenReady(addToCartButton, AppConstants.DEFAULT_TIMEOUT);
		String msg = eleUtil.waitForElementVisible(successMsg, AppConstants.DEFAULT_TIMEOUT).getText();
		//System.out.println(productName + " to cart--> " + msg);
		log.info(productName + " to cart--> " + msg);
		eleUtil.doClick(goToCart);
		return new ShoppingCartPage(driver);
	}

}
