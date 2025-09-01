package CompeteHealth.Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class verification{
	WebDriver driver;
	public verification (WebDriver driver) {
		super();
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Enter your inbox here']")
	WebElement enteryouremail;
	
	@FindBy(xpath="//i[contains(text(),'')]")
	WebElement sendingmail;
	
	@FindBy(xpath="//div[contains(text(),'today')]/following-sibling::div")
	WebElement firstmail;
	
	@FindBy(xpath="//a[contains(text(),'Verify Your Email Address')]")
	WebElement verifymail;
	
	public void verify(String mail) {
		enteryouremail.sendKeys(mail);
		sendingmail.click();
		firstmail.click();
		verifymail.click();
	}
}
