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

public class CommonPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By footer = By.xpath("//footer//div[@class='container']//a");
	private By logo = By.xpath("//div[@id='logo']//img");
	
	private static final Logger log = LogManager.getLogger(CommonPage.class);
	
	public CommonPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	@Step("Getting the logo")
	public boolean getLogo() {
		boolean flag = eleUtil.doIsElementDisplayed(logo);
		if(flag) {
			//System.out.println("Logo is displayed");
			log.info("Logo is displayed");
		}
		else {
			//System.out.println("Logo is not displayed");
			log.error("Logo is not displayed");
		}
		return flag;
	}
	
	@Step("Getting the list of available footers")
	public List<String> getFooters()  {
		List<WebElement> footers = eleUtil.waitForElementsVisible(footer, AppConstants.DEFAULT_TIMEOUT);
		List<String> footersList = new ArrayList<String>();
		
		for (WebElement e : footers) {
			String text = e.getText();
			footersList.add(text);
		}
		//System.out.println("All available footers list: " + footersList);
		log.info("All available footers list: " + footersList);
		return footersList;
	}
	
	@Step("Checking if footer {0} is available in the list of footers")
	public boolean isFooterAvailable(String footersList) {
		return getFooters().contains(footersList);
	}
}
