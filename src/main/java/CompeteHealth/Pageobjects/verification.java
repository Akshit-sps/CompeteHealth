package CompeteHealth.Pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class verification{
	AppiumDriver driver;
	public verification (AppiumDriver driver) {
		super();
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@AndroidFindBy(id = "com.android.chrome:id/url_bar")
	private WebElement addressBar;

	@AndroidFindBy(id = "com.android.chrome:id/search_box_text")
	private WebElement firstRunSearchBox;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='login']")
	private WebElement yopmailemail;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='']")
	private WebElement yopmailenter;
	
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'Compete Health Verify Your Email Address')]")
	private WebElement verificationmail;
	
	@AndroidFindBy(id = "//android.view.View[@content-desc=\"Verify Your Email Address\"]")
	private WebElement finalverification;
	
	public void openChromeAndVerify(String mail) {

		((InteractsWithApps) driver).activateApp("com.android.chrome");
		if (driver instanceof AndroidDriver) {
			((AndroidDriver) driver).context("NATIVE_APP");
		}
		org.openqa.selenium.WebElement urlField;
		try {
			urlField = driver.findElement(AppiumBy.id("com.android.chrome:id/url_bar"));
		} catch (Exception e) {
			urlField = driver.findElement(AppiumBy.id("com.android.chrome:id/search_box_text"));
		}
		urlField.click();
		urlField.clear();
		urlField.sendKeys("https://yopmail.com/en/");
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
        
	}
}