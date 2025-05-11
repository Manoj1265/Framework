package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public String xlFilePath;
	public String sheetname;
	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public FileOutputStream fos;

	public ExcelUtility(String xlFilePath, String sheetname) {
		this.xlFilePath = xlFilePath;
		this.sheetname = sheetname;
	}

	public XSSFWorkbook getWorkbook() throws IOException {
		fis = new FileInputStream(xlFilePath);
		workbook = new XSSFWorkbook(fis);
		return workbook;
	}

	public XSSFSheet getSheet(String sheetname) throws IOException {
		fis = new FileInputStream(xlFilePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
		return sheet;
	}

	public int getRowCount() throws IOException {
		sheet = this.getSheet(this.sheetname);
		int rowCount = sheet.getLastRowNum();
		System.out.println("The Available row count is : " + rowCount);
		return rowCount;
	}

	public int getColumnCount() throws IOException {
		sheet = this.getSheet(this.sheetname);
		int colCount = sheet.getRow(0).getLastCellNum();
		System.out.println("The Available row count is : " + colCount);
		return colCount;
	}

	public String getCellData(String sheetname, int rowNum, int colNum) throws IOException {
		sheet = this.getSheet(sheetname);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);

		String cellData;
		try {
			cellData = cell.toString();
			DataFormatter dataFormate = new DataFormatter();
			cellData = dataFormate.formatCellValue(cell); // Returns the formated value of cell in String format.
		} catch (Exception e) {
			cellData = "";
		}
		return cellData;
	}

	public void setCellData(String sheetname, int rowNum, int colNum, String valueToSet) throws IOException {
		sheet = this.getSheet(sheetname);
		row = sheet.getRow(rowNum);
		cell = row.createCell(colNum);
		cell.setCellValue(valueToSet);
		fos = new FileOutputStream(this.xlFilePath);
		workbook.write(fos);
		workbook.close();
		fos.close();
		System.out.println("Test Data Added Successfully!...");
	}
}
