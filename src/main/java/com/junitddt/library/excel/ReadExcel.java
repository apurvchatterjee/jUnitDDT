package com.junitddt.library.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	private Workbook workbook;

	public ReadExcel() {
		workbook = null;
	}

	public ReadExcel(final String path) {
		try {
			if (path.endsWith("xlsx"))
				this.workbook = new XSSFWorkbook(new FileInputStream(new File(path)));
			else if (path.endsWith("xls"))
				this.workbook = new HSSFWorkbook(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Object getCellValue(final String sheet, final int row, final int column) {
		Cell cell = this.workbook.getSheet(sheet).getRow(row).getCell(column);
		if (cell.getCellType() == CellType.NUMERIC)
			return cell.getNumericCellValue();
		else
			return cell.getStringCellValue();
	}

	private int getTotalRows(final String sheet) {
		return this.workbook.getSheet(sheet).getLastRowNum();
	}

	private int getTotalColumns(final String sheet) {
		return this.workbook.getSheet(sheet).getRow(0).getLastCellNum();
	}

	private int getRowIndex(final String sheet, final String identifier) {
		int row = 0;
		for (row = 0; row < getTotalRows(sheet); row++) {
			if (String.valueOf(getCellValue(sheet, row, 0)).equalsIgnoreCase(identifier))
				break;
		}
		return row;
	}

	public void closeData() {
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<Object, Object> getData(String sheet, String identifier) {
		Map<Object, Object> map = new HashMap<>();
		// TODO: Implement the strategy to store data in HashMap
		return map;
	}

}
