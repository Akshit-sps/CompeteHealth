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
    	login.onboarding();
    	try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
    	        + "\\src\\test\\java\\CompeteHealth\\TestData\\Logindata.xlsx");
    	     XSSFWorkbook work = new XSSFWorkbook(fis)) {

    	    XSSFSheet sheet = work.getSheet("Sheet1");
    	    int lastRow = sheet.getLastRowNum();

    	    for (int r = 1; r <= lastRow; r++) { 
    	        XSSFRow rw = sheet.getRow(r);
    	        if (rw == null) continue;

    	        XSSFCell emailCell = rw.getCell(0);
    	        XSSFCell passCell  = rw.getCell(1);
    	        if (emailCell == null || passCell == null) continue;

    	        String email = emailCell.toString().trim();
    	        String pass  = passCell.toString().trim();
    	        boolean success = login.attemptLogin(email, pass);
    	        if (success) {
    	        	login.logout();
    	        }else {
    	        	login.validationpopup();
    	        }
    	    }
    	}
    }
}
