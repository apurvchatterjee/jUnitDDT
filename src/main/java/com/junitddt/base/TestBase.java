package com.junitddt.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class TestBase {

	public static WebDriver dr = null;
	public static EventFiringWebDriver driver = null;

	public static final String PROJPATH = System.getProperty("user.dir");
	private static final String PROPPATH = "src/main/resources/properties/";
	public static boolean isLoggedIn = false;

	public static Properties CONFIG = null;
	public static Properties OR = null;
	public static Properties LOGGER = null;

	private static FileInputStream fin;

	public static void doInitialize() {

		if (driver == null) {
			// initialize CONFIG property files
			CONFIG = new Properties();
			OR = new Properties();
			LOGGER = new Properties();

			try {
				fin = new FileInputStream(PROJPATH + PROPPATH + "config.properties");
				CONFIG.load(fin);

				fin = new FileInputStream(PROJPATH + PROPPATH + "or.properties");
				OR.load(fin);

				fin = new FileInputStream(PROJPATH + PROPPATH + "log4j.properties");
				LOGGER.load(fin);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// initialize web driver now
			if (CONFIG.getProperty("browser").equalsIgnoreCase("IE")) {
				System.setProperty(CONFIG.getProperty("ie_key"), PROJPATH + CONFIG.getProperty("ie_driverpath"));
				dr = new InternetExplorerDriver();
			} else if (CONFIG.getProperty("browser").equalsIgnoreCase("FIREFOX")) {
				System.setProperty(CONFIG.getProperty("ff_key"), PROJPATH + CONFIG.getProperty("ff_driverpath"));
				dr = new FirefoxDriver();
			} else if (CONFIG.getProperty("browser").equalsIgnoreCase("CHROME")) {
				System.setProperty(CONFIG.getProperty("gc_key"), PROJPATH + CONFIG.getProperty("gc_driverpath"));
				dr = new ChromeDriver();
			}
			driver = new EventFiringWebDriver(dr);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	public static WebElement getObject(String xpathkey) {
		try {
			return driver.findElement(By.xpath(OR.getProperty(xpathkey)));
		} catch (Throwable t) {
			System.out.println("unable to locate element: " + xpathkey);
			return null;
		}
	}
}
