package com.tieto.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static Object[][] getSheetIntoObject(String fileDetail,String sheetName) throws IOException {
		FileInputStream file = new FileInputStream(fileDetail);
		XSSFWorkbook book = new XSSFWorkbook(file);
		XSSFSheet sheet = book.getSheet(sheetName);
		
		int rowCount=sheet.getPhysicalNumberOfRows();
		int cellcount=sheet.getRow(0).getPhysicalNumberOfCells();
		
		DataFormatter format = new DataFormatter();
		Object[][] data=new Object[rowCount-1][cellcount];
		
		for (int r = 1; r < rowCount; r++) {
			XSSFRow row = sheet.getRow(r);
			for (int c = 0; c < cellcount; c++) {
				XSSFCell cell = row.getCell(c);
				data[r-1][c]=format.formatCellValue(cell);
				//System.out.println(data[r-1][c]);
			}
		}
		book.close();
		file.close();
		return data;
	}

}
