package com.async.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class ExcelUtil {

	public static void parse(InputStream inputStream) {
		
		Workbook lWorkbook = null;
		try {
			lWorkbook = WorkbookFactory.create(inputStream);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int no = lWorkbook.getNumberOfSheets();

		for (int i = 0; i < no; i++) {

			if (lWorkbook.isSheetHidden(i)) {
				// continue;
			}

			Sheet lSheet = lWorkbook.getSheetAt(i);

			String lstrSheetName = lSheet.getSheetName();
			
			Iterator<Row> lRowIterator = lSheet.iterator();
			
			while (lRowIterator.hasNext()) {
				Row lRow = (XSSFRow) lRowIterator.next();
				if (lRow.getRowNum() == 0)
					continue;

				System.out.print(getCellValue(lRow.getCell(0)) + " ");
				System.out.print(getCellValue(lRow.getCell(1)));
				System.out.println();
			}
		}

	}

	public static String getCellValue(Cell cell) {
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		}
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			Double double1 = cell.getNumericCellValue();
			if (double1 != null) {
				return double1.longValue() + "";
			}
			// return cell.getNumericCellValue()+"";
		}
		if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			FormulaEvaluator evaluator = cell.getSheet().getWorkbook()
					.getCreationHelper().createFormulaEvaluator();
			evaluator.evaluateFormulaCell(cell);
			return cell.getStringCellValue();
		}

		return "";
	}

    public static void main(String[] args) {
		String lstrExelFilePath = "/home/isdc/Desktop/Total Data.xlsx";
        FileInputStream inputStream = null;
        try {
			inputStream = new FileInputStream(new File( lstrExelFilePath ));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        parse(inputStream);
    }
}
