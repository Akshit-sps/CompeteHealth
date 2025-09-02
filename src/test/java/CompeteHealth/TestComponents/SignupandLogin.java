package CompeteHealth.TestComponents;

import org.testng.annotations.Test;

import CompeteHealth.Utils.BaseTest;

public class SignupandLogin extends BaseTest { 
    @Test()
    public void loginverification() {
    	String emails = "softdemo14@yopmail.com";
    	String password = "Hello@123";
    	login.onboarding();
    	signup.signupflow(emails, password, null);
    	// Open Chrome, complete Yopmail verification, return to app
    	verify.openChromeAndVerify(emails);
    	// Navigate back to login if needed and login
//    	login.login(emails, password);
    }
}
