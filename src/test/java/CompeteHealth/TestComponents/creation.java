package CompeteHealth.TestComponents;

//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import CompeteHealth.Utils.BaseTest;

public class creation extends BaseTest{
//	@BeforeMethod
//	public void Setup() {
//		login.PreSetup();
//	}
	@Test
	public void challengecreation() throws InterruptedException {
		login.login("akshittested@yopmail.com", "Hello@123");
		challenge = login.permissions();
		challenge.challengecreation("Automation Test-2","Step" );
		challenge.creation("100", "1", "Testing");
	}
}
