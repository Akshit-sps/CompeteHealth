package CompeteHealth.Pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CompeteHealth.utils.AndroidActionClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class verification extends AndroidActionClass{
	AppiumDriver driver;
	public verification (AndroidDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@AndroidFindBy(id = "com.android.chrome:id/url_bar")
	private WebElement addressBar;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='login']")
	private WebElement yopmailemail;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='']")
	private WebElement yopmailenter;
	
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'Compete Health Verify Your Email Address')]")
	private WebElement verificationmail;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Verify Your Email Address\"]")
	private WebElement finalverification;
	
	public void openChromeAndVerify(String mail) {

	    ((InteractsWithApps) driver).activateApp("com.android.chrome");

	    if (driver instanceof AndroidDriver) {
	        ((AndroidDriver) driver).context("NATIVE_APP");
	    }

	    WebElement urlField;
	    try {
	        urlField = driver.findElement(AppiumBy.id("com.android.chrome:id/url_bar"));
	    } catch (Exception e) {
	        urlField = driver.findElement(AppiumBy.id("com.android.chrome:id/search_box_text"));
	    }
	    urlField.click();
	    urlField.clear();
	    urlField.sendKeys("https://yopmail.com/en/");
	    waitForSeconds(3);
	    ((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

	    try { Thread.sleep(5000); } catch (InterruptedException e) {}

	    for (String contextName : ((AndroidDriver) driver).getContextHandles()) {
	        System.out.println("Available context: " + contextName);
	        if (contextName.contains("WEBVIEW_chrome")) {	
	            ((AndroidDriver) driver).context(contextName);
	            break;
	        }
	    }
	    driver.findElement(By.id("login")).clear();
	    driver.findElement(By.id("login")).sendKeys(mail);
	    driver.findElement(By.xpath("//button[@title='Check Inbox @yopmail.com']")).click();
	    
	    //context switch in inbox tab
	    driver.switchTo().frame("ifinbox");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    WebElement firstMail = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[@class='m']//span[contains(text(),'Compete Health')]")
	    ));
	    firstMail.click();
	    //switch back to defaiult
	    driver.switchTo().defaultContent(); // only if needed
	    

	    for (String contextName : ((AndroidDriver) driver).getContextHandles()) {
	        System.out.println("Available context after captcha: " + contextName);
	        if (contextName.contains("WEBVIEW_chrome")) {
	            ((AndroidDriver) driver).context(contextName);
	            break;
	        }
	    }
	     driver.switchTo().defaultContent();

		 // switch into mobile mail frame
		 driver.switchTo().frame("ifmobmail");
		 WebElement verifyLink = wait.until(ExpectedConditions.elementToBeClickable(
		     By.xpath("//a[contains(.,'Verify')]")
		 ));
		 verifyLink.click();


	    driver.switchTo().defaultContent();
	    ((AndroidDriver) driver).context("NATIVE_APP");
	}
	
}