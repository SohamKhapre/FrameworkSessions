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

public class ShoppingCartPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By enterQuantity = By.cssSelector("div.input-group.btn-block > input");
	private By submitButton = By.cssSelector("span.input-group-btn button[type='submit']");
	//private By productName = By.linkText("MacBook Air");
	private By productName = By.xpath("(//td//following-sibling::td)[14]");
	private By model = By.xpath("(//td//following-sibling::td)[15]");
	private By unitPrice = By.xpath("(//td//following-sibling::td)[17]"); 
	private By total = By.xpath("(//td//following-sibling::td)[18]");
	private By details = By.xpath("//div[@class='col-sm-4 col-sm-offset-8']//tr");
	private Map<String, String> cartDetails;
	
	private static final Logger log = LogManager.getLogger(ShoppingCartPage.class);
	
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	@Step("Getting the cart details for the product")
	public Map<String, String> shoppingCartFullProductDetails(String updatedQuantity) {
		sendQuantity(updatedQuantity);
		cartDetails = new HashMap<String, String>();
		cartDetails.put("Product Name", eleUtil.waitForElementVisible(productName, AppConstants.DEFAULT_TIMEOUT).getText());
		cartDetails.put("Model", eleUtil.waitForElementVisible(model, AppConstants.DEFAULT_TIMEOUT).getText());
		cartDetails.put("Unit Price", eleUtil.waitForElementVisible(unitPrice, AppConstants.DEFAULT_TIMEOUT).getText());
		cartDetails.put("Total", eleUtil.waitForElementVisible(total, AppConstants.DEFAULT_TIMEOUT).getText());
		shoppingCart();
		return cartDetails;
	}
	
	public void shoppingCart() {
		List<WebElement> productDetails = eleUtil.waitForElementsVisible(details, AppConstants.SHORT_TIMEOUT);
		for (WebElement e : productDetails) {
			String metaData = e.getText();
			String data[] = metaData.split(":");
			String metaKey = data[0].trim();
			String metaValue = data[1].trim();
			cartDetails.put(metaKey, metaValue);
		}
	}
	
	@Step("Entering the updated quantity {0} on the cart page")
	public void sendQuantity(String updatedQuantity) {
		WebElement element = eleUtil.waitForElementVisible(enterQuantity, AppConstants.SHORT_TIMEOUT);
		eleUtil.doSendKeys(element, updatedQuantity);
		eleUtil.doClick(submitButton);
	}

}
