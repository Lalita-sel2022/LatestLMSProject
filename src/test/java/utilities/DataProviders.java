package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
//	Data Provider 1
	
	@DataProvider(name = "loginData")
	public static String[][] loginTestData() throws IOException {
	    String filePath = "/home/dalam/eclipse-workspace/openCart/testData/LoginTestData.xlsx";
	    ReadExcel xlUtil = new ReadExcel(filePath);

	    int totalRow = xlUtil.getRowCount("Sheet1");
	    int totalCell = xlUtil.getCellCount("Sheet1", 1);
	    
	    System.out.println(totalRow+"***************"+totalCell
	    		);

	    String loginData[][] = new String[totalRow][totalCell];

	    for (int i = 1; i <= totalRow; i++) {
	        for (int j = 0; j < totalCell; j++) {
	            loginData[i - 1][j] = xlUtil.getCellData("Sheet1", i, j);
	        }
	        
	    }
	    return loginData;
	}

}
