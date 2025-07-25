package CompeteHealth.TestComponents;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import CompeteHealth.Utils.BaseTest;

public class creation extends BaseTest{
	@BeforeMethod
	public void Setup() {
		login.PreSetup();
	}
	@DataProvider(name = "challengeData")
	public Object[][] getChallengeData() {
		return new Object[][] {
			// email, password, name, typeName, subType, entryFee, numWinners, percentages, tieredStructure, numOfGroups, privatePass, description, startDate, startTime, endDate, endTime
			{
				"akshittested@yopmail.com", "Hello@123",
				"Time Challenge Automation", "Active Minutes", "Walk/Run",
				"20", 2, new Integer[]{60, 40},
				"6-Month Average", "2",
				"Hello@123", "Creating a tiered challenge for dynamic time",
				"24-07-2025", "14:00", "24-07-2025", "18:00"
			}
		};
	}
	
	@Test(dataProvider = "challengeData")
	public void challengecreationTest(
		String email, String password,
		String name, String typeName, String subType,
		String entryFee, int numWinners, Integer[] percentages,
		String tieredStructure, String numOfGroups,
		String privatePass, String description,
		String startDate, String startTime, String endDate, String endTime
	) throws InterruptedException {
		
		login.login(email, password);
		challenge = login.permissions();
		challenge.nameandtype(name, typeName, subType);
		challenge.dateandtime(startDate, startTime, endDate, endTime);
		challenge.entryfee(entryFee, numWinners,percentages);
		if (tieredStructure != null && numOfGroups != null) {
			challenge.tieredchallenge(tieredStructure, numOfGroups);
		}
		if (privatePass != null) {
			challenge.privatechallenge(privatePass);
		}
		challenge.discriptionandcamara(description);
	}
}
