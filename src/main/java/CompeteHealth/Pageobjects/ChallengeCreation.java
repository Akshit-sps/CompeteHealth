package CompeteHealth.Pageobjects;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CompeteHealth.utils.AndroidActionClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ChallengeCreation extends AndroidActionClass{
	
	AndroidDriver driver;  
	public ChallengeCreation(AndroidDriver driver) {
		super(driver);
		this.driver=driver; 
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget"
			+ ".FrameLayout/android.view.ViewGroup/android"
			+ ".view.ViewGroup/android.view.ViewGroup[1]/android.widget"
			+ ".FrameLayout/android.view.ViewGroup/android.view"
			+ ".ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view"
			+ ".ViewGroup/android.view.ViewGroup[2]/android.view"
			+ ".ViewGroup[2]/com.horcrux.svg.SvgView/com.horcrux.svg.GroupView/com.horcrux.svg.PathView")
	private WebElement plusicon;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Challenge Title']")
	private WebElement challengename;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Challenge Type']")
	private WebElement challengentype;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Challenge Start Date']")
	private WebElement startdate;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='Confirm']")
	private WebElement confirmclick;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Challenge Start Time']")
	private WebElement starttime;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Challenge End Date']")
	private WebElement enddate;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='android:id/button1']")
	private WebElement button;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Challenge End Time']")
	private WebElement endtime;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Participant Entry Fee (USD)']")
	private WebElement entryFeeField;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Number of Winners']")
	private WebElement numWinnersDropdown;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text=\"Description\"]")
	private WebElement descField;

	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"Tap to upload, challenge picture\"]")
	private WebElement uploadBtn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Take a Picture\"]")
	private WebElement takePicBtn;

	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc=\"Shutter\"]")
	private WebElement shutterBtn;

	@AndroidFindBy(xpath="//android.widget.Button[@resource-id=\"android:id/button1\"]")
	private WebElement confirmBtn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Submit\"]")
	private WebElement submitBtn;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button']")
	private WebElement permissionforpictures;
	
	@AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.widget.Switch[2]")
	private WebElement privatechallengetoggle;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Challenge Password']")
	private WebElement privatepassword;
	
	@AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.widget.Switch[1]")
	private WebElement tieredchallenge;
	 
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Grouping structure']")
	private WebElement groupstructure;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Number of Groups']")
	private WebElement numberofgroup;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Select date']")
	private WebElement selectdate;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Select time']")
	private WebElement selecttime;
	
	public void nameandtype(String name,String typeName,String subtypename){
		plusicon.click();
		waitForSeconds(10);
		challengename.sendKeys(name);
		selectChallengeTypeAndSubType(typeName,subtypename);
		
	}
    
    public void dateandtime(String startDate, String startTime, String endDate, String endTime) throws InterruptedException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate currentDate = LocalDate.now();
        LocalDate inputStartDate = LocalDate.parse(startDate, dateFormatter);
        int dayDiff = inputStartDate.getDayOfMonth() - currentDate.getDayOfMonth();
        int monthDiff = inputStartDate.getMonthValue() - currentDate.getMonthValue();
        int yearDiff = inputStartDate.getYear() - currentDate.getYear();

        startdate.click();
        waitUntilVisible(selectdate); // Wait for date picker to be ready
        // day
        if (dayDiff != 0) scrolldate(Math.abs(dayDiff), 248, 799, 248, dayDiff > 0 ? 699 : 899);
        // month
        if (monthDiff != 0) scrolldate(Math.abs(monthDiff), 360, 799, 360, monthDiff > 0 ? 699 : 899);
        // year
        if (yearDiff != 0) scrolldate(Math.abs(yearDiff), 472, 799, 472, yearDiff > 0 ? 699 : 899);
        confirmclick.click();

        LocalTime inputStartTime = LocalTime.parse(startTime, timeFormatter);
        int hourDiff = inputStartTime.getHour() - LocalTime.now().getHour();
        int minuteDiff = inputStartTime.getMinute() - LocalTime.now().getMinute();
        starttime.click();
        waitUntilVisible(selecttime); // Wait for time picker to be ready
        // Always scroll hour first
        if (hourDiff != 0) scrolldate(Math.abs(hourDiff), 314, 799, 314, hourDiff > 0 ? 699 : 899);
        waitForSeconds(1); // Wait 1 second before scrolling minutes
        if (minuteDiff != 0) scrollminutes(406,799,406,899);
        confirmclick.click();

        LocalDate inputEndDate = LocalDate.parse(endDate, dateFormatter);
        int endDayDiff = inputEndDate.getDayOfMonth() - currentDate.getDayOfMonth();
        int endMonthDiff = inputEndDate.getMonthValue() - currentDate.getMonthValue();
        int endYearDiff = inputEndDate.getYear() - currentDate.getYear();

        enddate.click();
        waitUntilVisible(selectdate); // Wait for date picker to be ready
        if (endDayDiff != 0) scrolldate(Math.abs(endDayDiff), 248, 799, 248, endDayDiff > 0 ? 699 : 899);
        if (endMonthDiff != 0) scrolldate(Math.abs(endMonthDiff), 360, 799, 360, endMonthDiff > 0 ? 699 : 899);
        if (endYearDiff != 0) scrolldate(Math.abs(endYearDiff), 472, 799, 472, endYearDiff > 0 ? 699 : 899);
        button.click();

        LocalTime inputEndTime = LocalTime.parse(endTime, timeFormatter);
        int endHourDiff = inputEndTime.getHour() - inputStartTime.getHour();
        int endMinuteDiff = inputEndTime.getMinute() - inputStartTime.getMinute();
        endtime.click();
        waitUntilVisible(selecttime); // Wait for time picker to be ready
        // Always scroll hour first
        if (endHourDiff != 0) scrolldate(Math.abs(endHourDiff), 314, 799, 314, endHourDiff > 0 ? 699 : 899);
        waitForSeconds(1); // Wait 1 second before scrolling minutes
        if (endMinuteDiff != 0) scrollminutes(406,799,406,899);
        waitForSeconds(1);
        confirmclick.click();
        ScrolltoText("Submit");
    }
	public void entryfee(String entryFee, int numWinners,Integer[] percentages) throws InterruptedException {
        waitUntilVisible(entryFeeField);
        entryFeeField.sendKeys(entryFee);
        setWinnersAndPercentages(numWinners,percentages);
        ScrolltoText("Submit");
    }
	
	public void tieredchallenge(String grpstructure,String numofgroup) {
    	tieredchallenge.click();
    	groupstructure.click();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	String subTypeXpath = "//android.widget.TextView[@text='" + grpstructure + "']";
    	WebElement subTypeElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(subTypeXpath)));
        subTypeElement.click();
        if (grpstructure.equalsIgnoreCase("12-Month Average") ||grpstructure.equalsIgnoreCase("6-Month Average")) {
        	numberofgroup.click();
	        WebElement subgroup = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//android.widget.TextView[@text='"+ numofgroup + "']")));
	        subgroup.click();
    	}
        ScrolltoText("Submit");
    }
	
	public void privatechallenge(String privatepass) {
    	privatechallengetoggle.click();
    	waitForElementToAppear(privatepassword, driver);
    	privatepassword.sendKeys(privatepass);
    	ScrolltoText("Submit");
    }
	
	public void discriptionandcamara(String description) {
		waitUntilVisible(descField);
        descField.sendKeys(description);
        
        waitUntilClickable(uploadBtn);
        uploadBtn.click();
        
        waitUntilClickable(takePicBtn);
        takePicBtn.click();
        
        permissionforpictures.click();
        
        waitUntilClickable(shutterBtn);
        shutterBtn.click();
        
        waitForSeconds(2); 

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tapSequence = new Sequence(finger, 1);
        tapSequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 430, 1119));
        tapSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tapSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tapSequence));
        waitForSeconds(5);
        
        waitUntilClickable(confirmBtn);
        confirmBtn.click();

        waitUntilClickable(submitBtn);
        submitBtn.click();
	}
	public void scrolldate(int numofswipe,int x, int y, int x1, int y1) throws InterruptedException {
        for (int i = 0; i < numofswipe; i++) {
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipeDay = new Sequence(finger, 1);
            swipeDay.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y)); 
            swipeDay.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipeDay.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), x1, y1)); 
            swipeDay.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Arrays.asList(swipeDay));
            waitForSeconds(1);
        }
	}
	public void scrollminutes(int x, int y, int x1, int y1) throws InterruptedException {
		LocalTime now = LocalTime.now();
        int minute = now.getMinute();
        if (minute >= 30) {
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipeMinute = new Sequence(finger, 1);
            swipeMinute.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
            swipeMinute.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipeMinute.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), x1, y1)); 
            swipeMinute.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Arrays.asList(swipeMinute));
            waitForSeconds(1);
        }
	}
	public void setWinnersAndPercentages(int numWinners, Integer[] percentages) {
	    // 1. Click the dropdown and select the number of winners
		waitUntilClickable(numWinnersDropdown);
	    numWinnersDropdown.click();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement numWinnersOption = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//android.widget.TextView[@text='" + numWinners + "']")));
	    numWinnersOption.click();

	    // 2. Fill in the percentage fields for each place
	    String[] placeSuffix = {"st", "nd", "rd", "th", "th"}; // Extend as needed
	    for (int i = 0; i < numWinners; i++) {
	        String place = (i + 1) + placeSuffix[Math.min(i, 4)];
	        String xpath = "//android.widget.EditText[@text='" + place + " place payout (%)']";
	        WebElement percentField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	        percentField.clear();
	        percentField.sendKeys(String.valueOf(percentages[i]));
	    }
	}
	public void selectChallengeTypeAndSubType(String typeName, String subType) {
		challengentype.click();
	    String typeXpath = "//android.view.ViewGroup[@content-desc='" + typeName + "']";
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement typeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(typeXpath)));
	    typeElement.click();

	    if (typeName.equalsIgnoreCase("Active Minutes") ||
	        typeName.equalsIgnoreCase("Distance") ||
	        typeName.equalsIgnoreCase("Calories Burned")) {

	        WebElement subTypeField = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//android.widget.TextView[@text='Challenge Sub Type']")));
	        subTypeField.click();

	        String subTypeXpath = "//android.view.ViewGroup[@content-desc='" + subType + "']";
	        WebElement subTypeElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(subTypeXpath)));
	        subTypeElement.click();
	    }
	}    
}
