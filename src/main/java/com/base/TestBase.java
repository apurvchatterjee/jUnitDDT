package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class TestBase {

	public static WebDriver dr = null;
	public static EventFiringWebDriver driver = null;
	private static FileInputStream fin = null;

	public static final String PROJPATH = System.getProperty("user.dir");

	public static Properties CONFIG = null;
	public static Properties OR = null;
	public static Properties logger = null;

	public static void doInitialize() {
		// initialize the property files
		CONFIG = new Properties();
		try {
			fin = new FileInputStream(PROJPATH + "/src/main/resources/properties/config.properties");
			CONFIG.load(fin);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (driver == null) {

		}
	}
}
