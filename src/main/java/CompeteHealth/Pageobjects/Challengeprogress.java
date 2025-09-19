package CompeteHealth.Pageobjects;

import org.openqa.selenium.support.PageFactory;

import CompeteHealth.utils.AndroidActionClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Challengeprogress extends AndroidActionClass{
	AndroidDriver driver;  
	
	public Challengeprogress(AndroidDriver driver) {
		super(driver);
		this.driver=driver; 
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

}
