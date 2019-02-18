package com.junitddt.library.excel;

public interface Excel {

	String getCellValue(final String sheet, final int row, final int column);

	void writeData(final String sheet, final int row, final int column, final String value);

	int getTotalRows(final String sheet);

	int getTotalColumns(final String sheet);

	int getRowIndex(final String sheet, final String identifier);

	int getColumnIndex(final String sheet, final String identifier);

	void teardown();

}
