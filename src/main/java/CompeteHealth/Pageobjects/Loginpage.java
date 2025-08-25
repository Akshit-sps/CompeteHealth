package CompeteHealth.Pageobjects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import CompeteHealth.utils.AndroidActionClass;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Loginpage extends AndroidActionClass{
	AndroidDriver driver;
	
	public Loginpage(AndroidDriver driver) {
		super(driver);
		this.driver=driver; 
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Enter your email address']")
	private WebElement username;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Enter your password']")
	private WebElement password;
	
	@AndroidFindBy(accessibility="Sign in")
	private WebElement signin; 
	
	@AndroidFindBy(xpath="//android.widget.Switch[@resource-id='android:id/switch_widget']")
	private WebElement permissionone; 
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.android.healthconnect.controller:id/primary_button_outline']")
	private WebElement permissiontwo; 
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.android.healthconnect.controller:id/primary_button_outline']")
	private WebElement permissionthree; 
	
	@AndroidFindBy(id="com.android.permissioncontroller:id/permission_allow_button")
	private WebElement permissionfour;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='Next']")
	private WebElement orboardingnext;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Steps, active minutes, distance, and more']")
	private WebElement secondpageonboarding;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Compete 1-on-1, in a tier, or all together']")
	private WebElement thirdpageonboarding;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Use a watch or ring to track activity then receive payouts']")
	private WebElement forthpageonboarding;
	
	public void onboarding() {
		permissionfour.click();
		orboardingnext.click();
		waitUntilVisible(secondpageonboarding);
		orboardingnext.click();
		waitUntilVisible(thirdpageonboarding);
		orboardingnext.click();
		waitUntilVisible(forthpageonboarding);
		orboardingnext.click();
	}
	
	public void PreSetup() {
		((InteractsWithApps) driver).terminateApp("com.competehealth");
		((InteractsWithApps) driver).activateApp("com.competehealth");
	}
	
	public void login(String name,String pass) {
		
		username.sendKeys(name);
		password.sendKeys(pass);
		signin.click();
	}
	public ChallengeCreation permissions() throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.elementToBeClickable(permissionone)).click();
//		wait.until(ExpectedConditions.elementToBeClickable(permissiontwo)).click();s
//		wait.until(ExpectedConditions.elementToBeClickable(permissionthree)).click();
		return new ChallengeCreation(driver);
	}
}
