package CompeteHealth.Pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import CompeteHealth.utils.AndroidActionClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Signuppage extends AndroidActionClass{
	
	AndroidDriver driver;
	
	public Signuppage(AndroidDriver driver) {
		super(driver);
		this.driver=driver; 
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Create an Account\"]")
	private WebElement createaccbutton;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text=\"Enter your email address\"]")
	private WebElement emailadress;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text=\"Enter your password\"]")
	private WebElement password;
	
	@AndroidFindBy(xpath="//com.horcrux.svg.MaskView/com.horcrux.svg.PathView")
	private WebElement passeye;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"Referral Code (Optional)\"]/com.horcrux.svg.SvgView/com.horcrux.svg.GroupView/com.horcrux.svg.PathView")
	private WebElement refercodedropdown;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text=\"Enter your referral code\"]")
	private WebElement enterrefercode;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='󰄰']")
	private WebElement checkbboxtermandcondition;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"Sign up\"]")
	private WebElement signupbutton;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Log In\"]")
	private WebElement loginoption;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Resend verification link\"]")
	private WebElement resendemail;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Go back to login\"]")
	private WebElement backtologinpage;
	
	public void signupflow(String email,String pass,String refercode) {
		createaccbutton.click();
		emailadress.sendKeys(email);
		password.sendKeys(pass);
		refercodedropdown.click();
		waitUntilVisible(enterrefercode);
		enterrefercode.sendKeys(refercode);
		checkbboxtermandcondition.click();
		signupbutton.click();
		waitUntilVisible(backtologinpage);
	}
}
