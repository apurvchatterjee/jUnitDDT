package com.junitddt.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class TestBase {

	public static WebDriver dr = null;
	public static EventFiringWebDriver driver = null;

	public static final String PROJPATH = System.getProperty("user.dir");
	private static final String PROPPATH = "src/main/resources/properties/";
	public static boolean isLoggedIn = false;

	public static Properties OR = null;
	public static Config config;

	public static Logger logger = LogManager.getLogger(TestBase.class.getName());

	private static FileInputStream fin;

	public static void doInitialize() {

		initializeConfig();

		if (driver == null) {
			OR = new Properties();
			try {
				logger.info("initializing object repository now");
				fin = new FileInputStream(PROJPATH + PROPPATH + "or.properties");
				OR.load(fin);
			} catch (FileNotFoundException e) {
				logger.error("\"or.properties\" not found in the path specified");
				logger.error(e.getMessage());
			} catch (IOException e) {
				logger.error("error reading \"or.properties\"");
				logger.error(e.getMessage());
			}

			// initialize web driver now
			if (config.getString("browser").equalsIgnoreCase("IE")) {
				logger.info("browser configuration set to internet explorer");
				System.setProperty(config.getString("ie_key"), PROJPATH + config.getString("ie_driverpath"));
				logger.info("initializing internet explorer now");
				dr = new InternetExplorerDriver();
			} else if (config.getString("browser").equalsIgnoreCase("FIREFOX")) {
				logger.info("browser configuration set to mozilla firefox");
				System.setProperty(config.getString("ff_key"), PROJPATH + config.getString("ff_driverpath"));
				logger.info("initializing mozilla firefox now");
				dr = new FirefoxDriver();
			} else if (config.getString("browser").equalsIgnoreCase("CHROME")) {
				logger.info("browser configuration set to google chrome");
				System.setProperty(config.getString("gc_key"), PROJPATH + config.getString("gc_driverpath"));
				logger.info("initializing google chrome now");
				dr = new ChromeDriver();
			}

			driver = new EventFiringWebDriver(dr);
			logger.info("initializing the driver object now");
			driver.manage().window().maximize();
			logger.info("maximizing browser window now");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			logger.info("default implicit wait is set to 30 seconds");

		}
	}

	public static WebElement getObject(String xpathkey) {
		try {
			logger.info("trying to locate element by locater: " + OR.getProperty(xpathkey));
			return driver.findElement(By.xpath(OR.getProperty(xpathkey)));
		} catch (Throwable t) {
			logger.error("unable to locate element with locator: " + OR.getProperty(xpathkey));
			return null;
		}
	}

	public static void initializeConfig() {
		if (config == null) {
			config = ConfigFactory.load();
		}
	}
}
