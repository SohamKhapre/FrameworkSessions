package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		System.out.println("Enter browser name: " + browserName);
		System.out.println("---------------");

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Please pass the correct borwser name " + browserName);
			throw new FrameworkException ("****<--invalid browser name passed-->***");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		return driver;
	}
	
	public Properties initProp() {
		
		prop = new Properties();
		
		try {
			FileInputStream ip = new FileInputStream(AppConstants.CONFIG_PROP_FILE);
			prop.load(ip);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	
	
}
