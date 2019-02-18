package com.junitddt.library.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelUtility implements Excel {

	private Workbook workbook;
	private FileInputStream fis;
	private FileOutputStream fos;

	public ExcelUtility(String path) {
		try {
			fis = new FileInputStream(new File(path));
			fos = new FileOutputStream(new File(path));
			if (path.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(fis);
			} else if (path.endsWith("xls")) {
				workbook = new HSSFWorkbook(fis);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getCellValue(String sheet, int row, int column) {
		Cell cell = workbook.getSheet(sheet).getRow(row).getCell(column);
		if (cell.getCellType() == CellType.NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else {
			return cell.getStringCellValue();
		}
	}

	@Override
	public void writeData(String sheet, int row, int column, String value) {
		workbook.getSheet(sheet).getRow(row).createCell(column, CellType.STRING).setCellValue(value);
	}

	@Override
	public int getTotalRows(String sheet) {
		return workbook.getSheet(sheet).getLastRowNum();
	}

	@Override
	public int getTotalColumns(String sheet) {
		return workbook.getSheet(sheet).getRow(0).getLastCellNum();
	}

	@Override
	public int getRowIndex(String sheet, String identifier) {
		for (int row = 0; row < getTotalRows(sheet); row++) {
			if (getCellValue(sheet, row, 0).equalsIgnoreCase(identifier))
				return row;
		}
		return -999;
	}

	@Override
	public int getColumnIndex(String sheet, String identifier) {
		for (int column = 0; column < getTotalRows(sheet); column++) {
			if (getCellValue(sheet, 0, column).equalsIgnoreCase(identifier))
				return column;
		}
		return -999;
	}

	@Override
	public void teardown() {
		try {
			
			workbook.write(fos);
			fos.close();
			fis.close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
