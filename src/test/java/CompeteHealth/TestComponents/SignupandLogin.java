package CompeteHealth.TestComponents;
import org.testng.annotations.Test;

import CompeteHealth.Utils.BaseTest;

public class SignupandLogin extends BaseTest { 
    @Test()
    public void loginverification() {
    	login.onboarding();
    	signup.signupflow("softdemo1@yopmail.com","Hello@123",null);    	
    	
    	//chrome
    }
}
