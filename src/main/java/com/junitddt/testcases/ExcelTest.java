package com.junitddt.testcases;

import java.util.HashMap;

import com.junitddt.library.excel.ReadExcel;
import com.junitddt.library.excel.WriteExcel;

public class ExcelTest {

	public static void main(String[] args) {
		String path = "src/main/resources/test-data/test.xlsx";
		ReadExcel readExcel = new ReadExcel(path);
		System.out.println(readExcel.getData("Sheet1", "1"));
		readExcel.teardown();

		WriteExcel writeExcel = new WriteExcel(path);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Data4", "PK");
		map.put("Data3", "120000.00");
		map.put("Data2", "AG");
		map.put("Data1", "100000.00");

		writeExcel.writeRow("Sheet1", "1", map);
		writeExcel.saveAndCloseExcel();

	}

}
