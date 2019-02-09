package com.junitddt.library;

import com.junitddt.base.TestBase;

public class AppSpecificFunctions extends TestBase {

	public static void doLogin(String username, String password) {

		if (isLoggedIn) {
			doLogout();
		}

		try {
			getObject("link_signin_xpath").click();
			getObject("input_username_xpath").sendKeys(username);
			getObject("input_password_xpath").sendKeys(password);
			getObject("button_submit_xpath").click();

			if (getObject("link_signoff_xpath").isDisplayed()) {
				isLoggedIn = true;
			}

			isLoggedIn = true;
		} catch (Throwable e) {
			System.out.println("error signing in.. ");
		}
	}

	public static void doLogout() {
		if (getObject("link_signoff_xpath").isDisplayed()) {
			getObject("link_signoff_xpath").click();
			isLoggedIn = false;
		}
	}
}
