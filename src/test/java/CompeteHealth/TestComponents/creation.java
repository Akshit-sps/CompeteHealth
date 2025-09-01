package CompeteHealth.TestComponents;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import CompeteHealth.Utils.BaseTest;

public class Creation extends BaseTest {
    @DataProvider(name = "challengeData")
    public Object[][] getChallengeData() throws IOException {
        if (utils == null) {
            throw new RuntimeException("AndroidActionClass (utils) is not initialized. Check if driver is properly set up.");
        }
        List<HashMap<String, Object>> data = utils.getJasonDataToMap(System.getProperty("user.dir") + "//src//test//java//CompeteHealth//TestData//creationdata.json");
        return new Object[][] { { data } }; 
    }

    @SuppressWarnings({ "unchecked"})
	@Test(dataProvider = "challengeData", groups = {"regression"})
    public void ChallengeCreation(List<HashMap<String, Object>> dataList) throws InterruptedException {
        // Ensure required objects are initialized
        if (login == null) {
            throw new RuntimeException("Loginpage object is not initialized. Check if driver is properly set up.");
        }
    	login.onboarding();
        String email = (String) dataList.get(0).get("email");
        String password = (String) dataList.get(0).get("password");
        login.login(email, password);
        challenge = login.permissions();

        for (HashMap<String, Object> map : dataList) {
            try {
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

                System.out.println("Creating challenge: " + name);
                
                challenge.nameandtype(name, typeName, subType);
                challenge.dateandtime(startDate, startTime, endDate, endTime);
                challenge.entryfee(entryFee);
                if(numWinners != null && percentages!= null) {
                	challenge.nontieredchallenge(numWinners, percentages);
                }
                if (tieredStructure != null) {
                    challenge.tieredchallenge(tieredStructure, numOfGroups);
                }
                if (privatePass != null) {
                    challenge.privatechallenge(privatePass);
                }
                challenge.discription(description);
                challenge.camara();
                challenge.backtohomepage();
                
                System.out.println("Successfully created challenge: " + name);
                
            } catch (Exception e) {
                String challengeName = (String) map.get("name");
                System.err.println("Failed to create challenge '" + challengeName + "': " + e.getMessage());
                System.err.println("Continuing with next challenge...");
                e.printStackTrace();
                
            }
        }
    }
    
    @DataProvider(name = "joinData")
    public Object[][] joinChallengeData() throws IOException {
        if (utils == null) {
            throw new RuntimeException("AndroidActionClass (utils) is not initialized. Check if driver is properly set up.");
        }
        List<HashMap<String, Object>> data = utils.getJasonDataToMap(System.getProperty("user.dir") + "//src//test//java//CompeteHealth//TestData//joindata.json");
        return new Object[][] { { data } }; 
    }
    
    @Test(dataProvider = "joinData")
    public void challengejoin(List<HashMap<String, Object>> dataList) {
    	login.onboarding();
    	String email = (String) dataList.get(0).get("email");
        String password = (String) dataList.get(0).get("password");
        login.login(email, password);        
        for (HashMap<String, Object> map : dataList) {
            try {
                String challengename = (String) map.get("challengename");
                if (challengename != null && !challengename.trim().isEmpty()) {
                    System.out.println("Joining challenge: " + challengename);
                    discover.challengesearch(challengename);
                    discover.joinchallenge();
                    discover.backtohome(); // intermittent issue so need to handle
                    System.out.println("Successfully joined challenge: " + challengename);
                }
            } catch (Exception e) {
                String challengeName = (String) map.get("challengename");
                System.err.println("Failed to join challenge '" + challengeName + "': " + e.getMessage());
                System.err.println("Continuing with next challenge...");
                e.printStackTrace();
            }
        }
    }
}
