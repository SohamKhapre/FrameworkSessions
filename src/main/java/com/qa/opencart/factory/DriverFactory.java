package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.exception.FrameworkException;

import io.qameta.allure.Step;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public static String highlight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	private static final Logger log = LogManager.getLogger(DriverFactory.class);
	
	@Step("Initialize the browser")
	public WebDriver initDriver(Properties prop) {
		
		//String browserName = System.property("browser");
		// mvn clean install -Denvironment="qa" -Dbrowser="chrome"
		
		String browserName = prop.getProperty("browser");
		//System.out.println("Enter browser name: " + browserName);
		log.info("Enter browser name: " + browserName);
		//System.out.println("---------------");
		log.info("---------------");
		highlight = prop.getProperty("highlight");

		optionsManager = new OptionsManager(prop);

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			// driver = new SafariDriver();
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			// driver = new EdgeDriver(optionsManager.getEdgeOptions());
			break;
		default:
			//System.out.println("Please pass the correct borwser name " + browserName);
			log.error("Please pass the correct borwser name " + browserName);
			throw new FrameworkException("****<--invalid browser name passed-->***");
		}

		getTLDriver().manage().deleteAllCookies();
		getTLDriver().manage().window().maximize();
		getTLDriver().get(prop.getProperty("url"));
		return getTLDriver();
	}

	public static WebDriver getTLDriver() {
		return tlDriver.get();
	}

	@Step("Initialize the environment property")
	public Properties initProp() {

		String envName = System.getProperty("environment");
		//System.out.println("Running the suite on: " + envName);
		log.info("Running the suite on: " + envName);

		FileInputStream ip = null;
		prop = new Properties();

		try {
			if (envName == null) {
				//System.out.println("No env name passed. Hence, running the suite on default QA environment");
				log.warn("No env name passed. Hence, running the suite on default QA environment");
				ip = new FileInputStream(AppConstants.QA_CONFIG_PROP_FILE);
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream(AppConstants.QA_CONFIG_PROP_FILE);
					break;
				case "dev":
					ip = new FileInputStream(AppConstants.DEV_CONFIG_PROP_FILE);
					break;
				case "stage":
					ip = new FileInputStream(AppConstants.STAGE_CONFIG_PROP_FILE);
					break;
				case "prod":
					ip = new FileInputStream(AppConstants.PROD_CONFIG_PROP_FILE);
					break;
				case "uat":
					ip = new FileInputStream(AppConstants.UAT_CONFIG_PROP_FILE);
					break;
				default:
					throw new FrameworkException("Invalid environment name is passed.... " + envName + " Please pass the correct env name");
				}
			}
			prop.load(ip);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return prop;
	}

}
