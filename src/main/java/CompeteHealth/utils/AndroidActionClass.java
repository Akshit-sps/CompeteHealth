package CompeteHealth.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActionClass {
	AndroidDriver driver; 
	AndroidActionClass action;
	
	public AndroidActionClass(AndroidDriver driver) {
		 this.driver = driver;
	 }
	
	public List<HashMap<String,Object>> getJasonDataToMap(String path) throws IOException {
		//Read Json to String
		//System.getProperty("user.dir")+"//src//test//java//TestNgAppium//TestComponents//TestData//eCommerce.json"
		String jsonContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);

		//String to HashMap -> Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, Object>> data = mapper.readValue(jsonContent, 
				new TypeReference<List<HashMap<String, Object>>>(){});

		return data;
	}
	public void waitForElementToAppear(WebElement ele,AndroidDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains((ele), "text", "cart"));
	}
	public void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

	public void waitUntilClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

	public void waitUntilVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

	public void longpressKey(WebElement ele) {
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId",
						((RemoteWebElement)ele).getId(),
						"duration",2000));
	}
	public void ScrollToEndDemo() {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor)driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
					"left", 100,
				    "top", 100,
				    "width", 200,
				    "height", 200,
				    "direction", "down",
				    "percent", 3.0));
		}while(canScrollMore);
	}
	public void ScrolltoText(String text) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))")); 
	}
	
	public void scrollToTop() {
	    driver.findElement(AppiumBy.androidUIAutomator(
	    	"new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(10)"));
	}

	public void SwipeDemo(WebElement ele,String direction) {
		((JavascriptExecutor)driver).executeScript("mobile: swipeGesture",
				ImmutableMap.of("elementId",
						((RemoteWebElement)ele).getId(),
						"direction", direction,
						"percent",0.75));
	}
	public void tapOnElement(int offsetX, int offsetY) {
	    ((JavascriptExecutor) driver).executeScript("mobile: gesture", 
	        ImmutableMap.of(
	            "action", "tap",
	            "x", offsetX,
	            "y", offsetY
	        )
	    );
	}
	public String getScreenshotPath(String testCaseName, AndroidDriver driver) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String filedestination = System.getProperty("user.dir")+"//reports"+testCaseName+".png";
	 	FileUtils.copyFile(source, new File(filedestination));
	 	return filedestination;
	}
}