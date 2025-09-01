package CompeteHealth.TestComponents;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import CompeteHealth.Utils.BaseTest;

public class Joining extends BaseTest {
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
