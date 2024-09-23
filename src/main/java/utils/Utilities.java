package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {

	public static final int IMPLICIT_WAIT=25;
	public static final int PAGE_LOAD_WAIT =20;
	//public static final long  = 0;
	public static String timeStamp() {
		
		Date date=new Date();
		return  date.toString().replace(" ","_").replace(":", "_");
		
	}
      public static Object[][] getTestDataFromExcel(String sheetName) throws IOException {
    	  FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\testdata\\TestingNinjas.xlsx");
    	  @SuppressWarnings("resource")
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
    	  //getting the required sheet name from the excel file
    	 XSSFSheet sheet = workbook.getSheet(sheetName);
    	 //gives the total rows in the sheet
    	int rows = sheet.getLastRowNum();
    	//gives total cols in the row(particular)
    		 int cols = sheet.getRow(0).getLastCellNum();
    	 Object[][] data=new Object[rows][cols];
    	     		    	
    		    	for(int i=0;i<rows;i++) {
    		    		XSSFRow row = sheet.getRow(i+1);
    		    		for(int j=0;j<cols;j++) {
    		    			XSSFCell cell = row.getCell(j);
    		    			CellType celltype=cell.getCellType();
    		    			
    		    			switch(celltype) {
    		    			case STRING:
    		    				data[i][j]=cell.getStringCellValue();
    		    				break;
    		    			case NUMERIC:
    		    				data[i][j]=Integer.toString((int)cell.getNumericCellValue());
    		    				break;
    		    			case BOOLEAN:
    		    				data[i][j]=cell.getBooleanCellValue();
    		    				break;
							default:
								break;
    		    				
    		    			}
    		    	
    		    	
    		    			}
    		    		}
    		    	return data;
    		    	}

}

    	 
    	  
    	
      





