package com.junitddt.library.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WriteExcel extends ExcelUtility {

	public WriteExcel(String path) {
		super(path);
		try {
			fos = new FileOutputStream(new File(path));
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		}
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
	
	public void saveAndCloseExcel() {
		try {
			workbook.write(fos);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
