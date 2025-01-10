package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	// DataProviders
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {

		String path = ".\\testData\\Opencart_LoginData.xlsx";

		ExcelUtility xlUtil = new ExcelUtility(path);

		int totalRow = xlUtil.getRowCount("sheet1");
		int totalCellcount =xlUtil.getCellCount("sheet1", 1);

		String logindata[][] = new String[totalRow][totalCellcount];

		for (int r = 1; r <= totalRow; r++) {
			for (int c = 0; c <totalCellcount; c++) {
				logindata[r - 1][c] = xlUtil.getCellData("sheet1", r, c);
			}
		}
		return logindata;

	}

}
