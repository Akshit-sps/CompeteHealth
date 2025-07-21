package CompeteHealth.Pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Loginpage {
	AndroidDriver driver;
	
	public Loginpage(AndroidDriver driver) {
//		super(driver);
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
	
	public void PreSetup() {
		((InteractsWithApps) driver).terminateApp("com.competehealth");
		((InteractsWithApps) driver).activateApp("com.competehealth");
	}
	
	public void login(String name,String pass) {
		permissionfour.click();
		username.sendKeys(name);
		password.sendKeys(pass);
		signin.click();
	}
	public ChallengeCreation permissions() throws InterruptedException {
//		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(permissionone)).click();
		wait.until(ExpectedConditions.elementToBeClickable(permissiontwo)).click();
//  	Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(permissionthree)).click();
		return new ChallengeCreation(driver);
	}
}
