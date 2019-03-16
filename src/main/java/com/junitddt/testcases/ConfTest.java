package com.junitddt.testcases;

import com.junitddt.library.AppSpecificFunctions;

public class ConfTest extends AppSpecificFunctions {

	public static void main(String[] args) {

		initializeConfig();
		System.out.println(config.getString("username"));

	}

}
