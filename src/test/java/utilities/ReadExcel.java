package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	 public FileInputStream fi;
	 public XSSFWorkbook workbook;
	 public XSSFSheet sheetName;
	 public XSSFRow row;
	 public XSSFCell cell;
	 String path=null;
	 
	 public ReadExcel(String path)
	 {
		 this.path=path;
	 }
	 
	 public int getRowCount(String sheetname) throws IOException
	 {
		 fi= new FileInputStream(path);
		 workbook = new XSSFWorkbook(fi);
		 sheetName= workbook.getSheet(sheetname);
		 int rowCount = sheetName.getLastRowNum();
		 workbook.close();
		 fi.close();
		 return rowCount;
	}
	 public int getCellCount(String sheetname, int rowNum) throws IOException
	 {
		 fi= new FileInputStream(path);
		 workbook = new XSSFWorkbook(fi);
		 sheetName= workbook.getSheet(sheetname);
		 row = sheetName.getRow(rowNum);
		 int cellCount = row.getLastCellNum();
		 workbook.close();
		 fi.close();
		 return cellCount;
	}
	 // will return data from cell
	 public String getCellData(String sheetname, int rowNum,int cellNum) throws IOException
	 {
		 fi= new FileInputStream(path);
		 workbook = new XSSFWorkbook(fi);
		 sheetName= workbook.getSheet(sheetname);
		 row=sheetName.getRow(rowNum);
		 cell=row.getCell(cellNum);
		 DataFormatter formatter= new DataFormatter();
		 String data;
		 try {
			 data=formatter.formatCellValue(cell);//Return formatted value of a cell as  string regardless of the cell type
		 }catch(Exception e)
		 {
			 data="";
		 }
		 workbook.close();
		 fi.close();
		 return data;
	}
	
		/*
		 * String testDataFilePath=
		 * "/home/dalam/eclipse-workspace/openCart/testData/LoginTestData.xlsx";
		 * FileInputStream inputStrem= new FileInputStream(testDataFilePath);
		 * 
		 * XSSFWorkbook workbook= new XSSFWorkbook(inputStrem);
		 * 
		 * // XSSFSheet sheetName=workbook.getSheetAt(0); XSSFSheet sheetName =
		 * workbook.getSheet("Sheet1");
		 * 
		 * // *********************Using for loop get data first get how many row &
		 * columns(cells)******************* int rows=sheetName.getLastRowNum(); int
		 * cols=sheetName.getRow(0).getLastCellNum();
		 * 
		 * System.out.println(rows +"....."+cols);
		 */
	
	/*for(int r=0;r<=rows;r++)
	{
		XSSFRow row = sheetName.getRow(r); //0 row
		for(int c=0;c<cols;c++)
		{
			XSSFCell cell = row.getCell(c);
			// get what type of cell data
			
			switch(cell.getCellType())
			{
			case STRING: System.out.print(cell.getStringCellValue());
			break;
			case NUMERIC: System.out.print(cell.getNumericCellValue());
			break;
			case BOOLEAN: System.out.print(cell.getBooleanCellValue());
			break;
			default: System.out.println("Data not matching");
			}
			System.out.println( );}
	}
	 */
	//************* Using iterator*****************************
	  //return all rows and iterator each row
	/*
	 * Iterator<Row> iteratory = sheetName.rowIterator();
	 * 
	 * while (iteratory.hasNext()) { Row row = iteratory.next();
	 * 
	 * Iterator<Cell> cellIterator = row.cellIterator();
	 * while(cellIterator.hasNext()) { Cell cell = cellIterator.next();
	 * switch(cell.getCellType()) { case STRING:
	 * System.out.print(cell.getStringCellValue()); break; case NUMERIC:
	 * if(DateUtil.isCellDateFormatted(cell)) { Date date = cell.getDateCellValue();
	 * SimpleDateFormat formator= new SimpleDateFormat("dd/MMM/yyyy"); String
	 * formatedDate = formator.format(date); System.out.print(formatedDate); //
	 * System.out.print(cell.getDateCellValue());
	 * 
	 * }else { System.out.print(cell.getNumericCellValue()); } break; case BOOLEAN:
	 * System.out.print(cell.getBooleanCellValue()); break; case BLANK:
	 * System.out.print(""); break; default:
	 * System.out.println("Data not matching"); } System.out.println( ); } }
	 */
           }



