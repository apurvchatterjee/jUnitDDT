package com.junitddt.library.excel;

import java.util.HashMap;
import java.util.Map;

public class ReadExcel extends ExcelUtility {

	public ReadExcel(final String path) {
		super(path);
	}

	public Map<String, String> getData(String sheet, String identifier) {
		Map<String, String> map = new HashMap<>();
		int loc = getRowIndex(sheet, identifier);
		if (loc != -999) {
			for (int col = 0; col <= getTotalColumns(sheet); col++) {
				map.put(getCellValue(sheet, 0, col), getCellValue(sheet, loc, col));
			}
		}
		return map;
	}

}
