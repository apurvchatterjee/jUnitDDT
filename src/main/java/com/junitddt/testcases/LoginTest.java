package com.junitddt.testcases;



import com.junitddt.base.TestBase;
import com.junitddt.library.AppSpecificFunctions;
import org.junit.Before;
import org.junit.Test;

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
