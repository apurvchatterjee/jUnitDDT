package com.junitddt.testcases;

import org.junit.Before;
import org.junit.Test;

import com.junitddt.base.TestBase;
import com.junitddt.library.AppSpecificFunctions;

public class LoginTest extends TestBase {

	@Before
	public void setUp() {
		doInitialize();
	}

	@Test
	public void testValidLogin() {
		AppSpecificFunctions.doLogin("tutorial", "tutorial");
	}

}
