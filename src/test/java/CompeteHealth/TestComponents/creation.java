package CompeteHealth.TestComponents;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import CompeteHealth.Utils.BaseTest;

public class creation extends BaseTest {
    @DataProvider(name = "challengeData")
    public Object[][] getChallengeData() throws IOException {
        List<HashMap<String, Object>> data = utils.getJasonDataToMap(System.getProperty("user.dir") + "//src//test//java//CompeteHealth//TestData//data.json");
        return new Object[][] { { data } }; 
    }

    @SuppressWarnings({ "unchecked"})
	@Test(dataProvider = "challengeData", groups = {"regression"})
    public void ChallengeCreation(List<HashMap<String, Object>> dataList) throws InterruptedException {
    	login.onboarding();
        String email = (String) dataList.get(0).get("email");
        String password = (String) dataList.get(0).get("password");
        login.login(email, password);
        challenge = login.permissions();

        for (HashMap<String, Object> map : dataList) {
            String name = (String) map.get("name");
            String typeName = (String) map.get("typeName");
            String subType = (String) map.get("subType");
            String entryFee = (String) map.get("entryFee");
            String numWinners = (String) map.get("numWinners");
            List<String> percentagesList = (List<String>) map.get("percentages");
            String[] percentages = (percentagesList != null) ? percentagesList.toArray(new String[0]) : null;
            String tieredStructure = (String) map.get("tieredStructure");
            String numOfGroups = (String) map.get("numOfGroups");
            String privatePass = (String) map.get("privatePass");
            String description = (String) map.get("description");
            String startDate = (String) map.get("startDate");
            String startTime = (String) map.get("startTime");
            String endDate = (String) map.get("endDate");
            String endTime = (String) map.get("endTime");

            challenge.nameandtype(name, typeName, subType);
            challenge.dateandtime(startDate, startTime, endDate, endTime);
            challenge.entryfee(entryFee);
            if(numWinners != null && percentages!= null) {
            	challenge.nontieredchallenge(numWinners, percentages);
            }
            if (tieredStructure != null && numOfGroups != null) {
                challenge.tieredchallenge(tieredStructure, numOfGroups);
            }
            if (privatePass != null) {
                challenge.privatechallenge(privatePass);
            }
            challenge.discription(description);
            challenge.camara();
            challenge.backtohomepage();
        }
    }
}
