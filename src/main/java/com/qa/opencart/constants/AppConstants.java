package com.qa.opencart.constants;

public class AppConstants {
	
	public final static String LOGIN_PAGE_TITLE = "Account Login";
	public final static String HOME_PAGE_TITLE = "My Account";
	public final static String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public final static String HOME_PAGE_URL_FRACTION = "route=account/account";
	
	public final static int DEFAULT_TIMEOUT = 5; 
	public final static int SHORT_TIMEOUT = 10; 
	public final static int MEDIUM_TIMEOUT = 15; 
	public final static int MAX_TIMEOUT = 20;
	
	public final static String QA_CONFIG_PROP_FILE = "./src/test/resources/config/qa.config.properties";
	public final static String DEV_CONFIG_PROP_FILE = "./src/test/resources/config/dev.config.properties";
	public final static String STAGE_CONFIG_PROP_FILE = "./src/test/resources/config/stage.config.properties";
	public final static String PROD_CONFIG_PROP_FILE = "./src/test/resources/config/prod.config.properties";
	public final static String UAT_CONFIG_PROP_FILE = "./src/test/resources/config/uat.config.properties";
}
