package com.vtiger.crm.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	Workbook workbook;
	public void openExcel() throws IOException {

        FileInputStream f = new FileInputStream("./src/test/resources/Test_Data/VtigerTestData.xlsx");

        workbook = WorkbookFactory.create(f);
    }
	
	// Get data from Excel
    public String getData(String sheetName, int rowNum, int cellNum) {

        Sheet sheet = workbook.getSheet(sheetName);

        Row row = sheet.getRow(rowNum);

        Cell cell = row.getCell(cellNum);
        DataFormatter df=new DataFormatter();
        return df.formatCellValue(cell);
    }
    
   
    
//    get multiple data for integration
    public Object[][] getMultipleData(String sheetName) throws IOException {

        FileInputStream file = new FileInputStream("./src/test/resources/Test_Data/VtigerTestData.xlsx");

        Workbook wb = WorkbookFactory.create(file);
        Sheet sh = wb.getSheet(sheetName);

        int rowCount = sh.getLastRowNum();
        int colCount = sh.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][colCount];

        DataFormatter df = new DataFormatter();

        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] =df.formatCellValue(sh.getRow(i).getCell(j));
            }
        }

        wb.close();
        file.close();

        return data;
    }
}
