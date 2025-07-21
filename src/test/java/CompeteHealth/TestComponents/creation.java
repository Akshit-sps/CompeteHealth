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
		challenge.challengedetails("Automation Test-4","Calories Burned","Cycle");
		challenge.entryfee("100", "2");
		challenge.tieredchallenge("6-Month Average", "2");
		challenge.privatechallenge("Hello@123");
		challenge.discriptionandcamara("Automation testing");
	}
}
