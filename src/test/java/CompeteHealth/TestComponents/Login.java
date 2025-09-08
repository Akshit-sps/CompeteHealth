package CompeteHealth.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import CompeteHealth.Utils.BaseTest;

public class Login extends BaseTest { 
    @Test()
    public void loginverification() throws IOException {
//    	login.onboarding();
    	FileInputStream file =new FileInputStream("C:\\Users\\user.DESKTOP-I9IFEEO\\eclipse-workspace\\CompeteHealth\\src\\test\\java\\CompeteHealth\\TestData\\Logindata.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook work =new XSSFWorkbook(file);
        XSSFSheet sheet= work.getSheet("Sheet1");
        int row =sheet.getLastRowNum();
        int cel=  sheet.getRow(row).getLastCellNum();
        for(int r=1;r<=row;r++)
        {	
        	XSSFRow rw=sheet.getRow(r);
        	for(int c=0;c<=cel-1;c++)
        	{
        		XSSFCell cl= rw.getCell(c);
        		String cellvalue=cl.toString();
        		String[] parts = cellvalue.split(",");
        		
        		String emails = parts[0].trim();
        		String passwords = parts[1].trim();

        		System.out.println("First: " + emails);
        		System.out.println("Second: " + passwords);
//            	login.login(emails, passwords);
        	}
        	System.out.println();		
        }

    }
}
