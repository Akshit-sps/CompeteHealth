package CompeteHealth.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import CompeteHealth.Pageobjects.ChallengeCreation;
import CompeteHealth.Pageobjects.Loginpage;
import CompeteHealth.utils.AndroidActionClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest{
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public ChallengeCreation challenge;
	public Loginpage login;
	public AndroidActionClass utils;
	
	@BeforeClass(alwaysRun=true)
	public void configureAppium() throws IOException, URISyntaxException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//CompeteHealth//resources//data.properties");
		prop.load(fis);
		// if we give ip address in the terminal mvn test -PRegression -DipAddress=21.344.32	
		String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress") ;
//		String ipAddress = prop.getProperty("ipAddress");
		int port = Integer.parseInt(prop.getProperty("port"));
		String deviceName = prop.getProperty("deviceName");
		String chromedriverPath = prop.getProperty("chromedriverPath");
		String apkRelativePath = prop.getProperty("apkPath");
		
		// code to start the appium server (which we use in cmd using appium)
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("C://Users//user.DESKTOP-I9IFEEO//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
				.withIPAddress(ipAddress)
				.usingPort(port)
				.withTimeout(Duration.ofSeconds(120)).build();
		service.start();
		
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(deviceName);
		options.setChromedriverExecutable(chromedriverPath);
		options.setApp(System.getProperty("user.dir") + "//" + apkRelativePath);

		driver = new AndroidDriver(new URI("http://" + ipAddress + ":" + port).toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		login = new Loginpage(driver);
		utils = new AndroidActionClass(driver);
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		//stop the application and server
		driver.quit();
		service.stop();
	}
}
