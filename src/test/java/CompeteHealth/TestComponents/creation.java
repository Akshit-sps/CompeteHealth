package CompeteHealth.TestComponents;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
	public Object[][] getChallengeData() throws IOException {
		List<HashMap<String, Object>> data = utils.getJasonDataToMap(System.getProperty("user.dir") + "//src//test//java//CompeteHealth//TestData//data.json");
		Object[][] result = new Object[data.size()][16];
		for (int i = 0; i < data.size(); i++) {
			HashMap<String, Object> map = data.get(i);
			result[i][0] = map.get("email");
			result[i][1] = map.get("password");
			result[i][2] = map.get("name");
			result[i][3] = map.get("typeName");
			result[i][4] = map.get("subType");
			result[i][5] = map.get("entryFee");
			result[i][6] = map.get("numWinners");
			List<?> percentagesList = (List<?>) map.get("percentages");
			Integer[] percentagesArr = percentagesList.toArray(new Integer[0]);
			result[i][7] = percentagesArr;
			result[i][8] = map.get("tieredStructure");
			result[i][9] = map.get("numOfGroups");
			result[i][10] = map.get("privatePass");
			result[i][11] = map.get("description");
			result[i][12] = map.get("startDate");
			result[i][13] = map.get("startTime");
			result[i][14] = map.get("endDate");
			result[i][15] = map.get("endTime");
		}
		return result;
	}
	
	@Test(dataProvider = "challengeData",groups={"regression"})
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
		challenge.backtohomepage();
	}
}
