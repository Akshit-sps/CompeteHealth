package CompeteHealth.Pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import CompeteHealth.utils.AndroidActionClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DiscoverChallenge extends AndroidActionClass{
	AndroidDriver driver;
	
	public DiscoverChallenge(AndroidDriver driver) {
		super(driver);
		this.driver=driver; 
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.view.View[@content-desc=\"Discover\"]/com.horcrux.svg.SvgView"
			+ "/com.horcrux.svg.GroupView/com.horcrux.svg.PathView")
	private WebElement discovericon;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Trending challenges']")
	private WebElement trendingchallenges;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='View all'])[1]")
	private WebElement trendingviewall;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Upcoming featured challenges']")
	private WebElement upcommingchallenges;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='View all'])[2]")
	private WebElement upcommingviewall;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Personalized challenges']")
	private WebElement personalizedchallenges;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='View all'])[3]")
	private WebElement personalizedviewall;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search by title, host, or activity type']")
	private WebElement searchchallenge;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Search Results']")
	private WebElement searchresults;

	@AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]")
	private WebElement entertosearch;

	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"No results found\"]")
	private WebElement noresult;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Join Challenge']")
	private WebElement joinchallenges;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Accept\"]")
	private WebElement rulebookaccept;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Pay\"]")
	private WebElement checkoutpay;
	
	@AndroidFindBy(xpath="//android.view.View[@resource-id='payment-form']/android.view.View[2]")
	private WebElement stripepay;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='android:id/button1']")
	private WebElement successfullmessage;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Trending Challenges']")
	private WebElement hometrendingchallenge;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Leaderboard']")
	private WebElement leaderboardtext; 
	
	public void challengesearch(String Challengename) {
		discovericon.click();
		waitUntilVisible(trendingchallenges);
		searchchallenge.sendKeys(Challengename);
		waitForSeconds(2);
		entertosearch.click();
		waitUntilVisible(searchresults);
		ScrolltoText(Challengename);
		String subTypeXpath = "//android.widget.TextView[@text='" + Challengename + "']";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement subTypeElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(subTypeXpath)));
        subTypeElement.click();
	}	
	public void joinchallenge() {
		ScrolltoText("Join Challenge");
		waitUntilClickable(joinchallenges);
		joinchallenges.click();
		waitUntilClickable(rulebookaccept);
		rulebookaccept.click();
		ScrolltoText("Pay");
		checkoutpay.click();
		waitUntilClickable(stripepay);
		stripepay.click();
		waitUntilClickable(successfullmessage);
		successfullmessage.click();
	}
	public void backtohome() {
		waitUntilClickable(leaderboardtext);
		tapOnElement(67, 67);
		waitUntilVisible(hometrendingchallenge);
	}
}
