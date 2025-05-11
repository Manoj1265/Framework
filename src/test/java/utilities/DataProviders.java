package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	ExcelUtility excelUtility;
	
	@DataProvider(name="LoginData")
	public String[][] getLoginData() throws IOException {
		excelUtility = new ExcelUtility(".\\testData\\LoginPositiveNegative.xlsx", "Login");
		int rowCount = excelUtility.getRowCount();
		int columnCount = excelUtility.getColumnCount();

		String [][] data = new String[rowCount][columnCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				data[i-1][j] = excelUtility.getCellData("Login", i, j);
			}
		}
		return data;
	}
}
