package OrangeHRM;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


public class ExcelDataProvider {
	
	@org.testng.annotations.DataProvider (name = "Dynamic", parallel = true)
	public String [][] DataProvider()  throws Exception {
    File excelFile = new File("./src/test/resources/user_credentials.xlsx");
    System.out.println(excelFile.exists());
    FileInputStream fis = new FileInputStream(excelFile);
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
    XSSFSheet sheet = workbook.getSheet("sheet1");
    int noOfRows =sheet.getPhysicalNumberOfRows();
    int noOfColumns = sheet.getRow(0).getLastCellNum();
    
    
    String [][] data = new String[noOfRows-1][noOfColumns];
    for(int i =0; i<noOfRows-1; i++ ) {
    	for(int j=0; j<noOfColumns; j++) {
    		DataFormatter df = new DataFormatter();
    		data[i][j] =df.formatCellValue(sheet.getRow(i+1).getCell(j));
    		
//    		System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
    	}
    	System.out.println();
    }
    workbook.close();
    fis.close();
//    for(String [] dataArr : data) {
//    	System.out.println(Arrays.toString(dataArr));
    return data;
    }
    
    

}
	
