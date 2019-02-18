package com.junitddt.library.excel;

import java.util.HashMap;
import java.util.Map;

public class WriteExcel extends ExcelUtility {

	public WriteExcel(String path) {
		super(path);
	}

	public void writeRow(final String sheet, final String identifier, final HashMap<String, String> map) {
		int loc = getRowIndex(sheet, identifier);
		if (loc != -999) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				int column = getColumnIndex(sheet, entry.getKey());
				if (column != -999) {
					writeData(sheet, loc, column, entry.getValue());
				}
			}
		}
	}
}
