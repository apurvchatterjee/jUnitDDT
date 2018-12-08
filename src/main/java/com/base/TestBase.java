package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class TestBase {

	public static WebDriver dr = null;
	public static EventFiringWebDriver driver = null;
	public static final String PROJPATH = System.getProperty("user.dir");

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
				fin = new FileInputStream(PROJPATH + "src/main/resources/config.properties");
				CONFIG.load(fin);

				fin = new FileInputStream(PROJPATH + "src/main/resources/or.properties");
				OR.load(fin);

				fin = new FileInputStream(PROJPATH + "src/main/resources/log4j.properties");
				LOGGER.load(fin);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// initialize web driver now
			if (CONFIG.getProperty("browser").equalsIgnoreCase("IE")) {
				System.setProperty(CONFIG.getProperty("ie_key"), CONFIG.getProperty("ie_driverpath"));
				dr = new InternetExplorerDriver();
			} else if (CONFIG.getProperty("browser").equalsIgnoreCase("FIREFOX")) {
				System.setProperty(CONFIG.getProperty("ff_key"), CONFIG.getProperty("ff_driverpath"));
				dr = new FirefoxDriver();
			} else if (CONFIG.getProperty("browser").equalsIgnoreCase("CHROME")) {
				System.setProperty(CONFIG.getProperty("gc_key"), CONFIG.getProperty("gc_driverpath"));
				dr = new ChromeDriver();
			}
			driver = new EventFiringWebDriver(dr);
		}
	}

	public static void main(String[] args) {
		doInitialize();
		System.out.println(CONFIG.getProperty("testsite"));
	}
}
