package com.junitddt.library;

import com.junitddt.base.TestBase;

public class AppSpecificFunctions extends TestBase {

	public static void doLogin(String username, String password) {

		if (isLoggedIn) {
			return;
		}
		
		try {
			getObject("link_signin_xpath").click();
			getObject("input_username_xpath").sendKeys(username);
			getObject("input_password_xpath").sendKeys(password);
			getObject("button_submit_xpath").click();
			
			isLoggedIn = true;
		} catch (Throwable e) {
			System.out.println("error signing in.. ");
		}
	}

}
