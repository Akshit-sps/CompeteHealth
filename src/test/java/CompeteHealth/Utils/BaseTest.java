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
import CompeteHealth.Pageobjects.DiscoverChallenge;
import CompeteHealth.Pageobjects.Loginpage;
import CompeteHealth.Pageobjects.Signuppage;
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
	public DiscoverChallenge discover;
	public Signuppage signup;
	
	@BeforeClass(alwaysRun=true)
	public void configureAppium() throws IOException, URISyntaxException, InterruptedException {
		try {
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//CompeteHealth//resources//data.properties");
			prop.load(fis);
			// if we give ip address in the terminal mvn test -PRegression -DipAddress=21.344.32	
			String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress") ;
	//		String ipAddress = prop.getProperty("ipAddress");
			int port = Integer.parseInt(prop.getProperty("port"));
			String deviceName = prop.getProperty("deviceName");
			String chromedriverPath = prop.getProperty("chromedriverPath");
			String apkRelativePath = System.getProperty("user.dir") + "//src//test//java//CompeteHealth//Resources//competehealth.apk";
			
			// Verify APK file exists
			File apkFile = new File(apkRelativePath);
			if (!apkFile.exists()) {
				throw new RuntimeException("APK file not found at: " + apkRelativePath);
			}
			
			// code to start the appium server (which we use in cmd using appium)
			service = new AppiumServiceBuilder()
					.withAppiumJS(new File("C://Users//user.DESKTOP-I9IFEEO//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
					.withIPAddress(ipAddress)
					.usingPort(port)
					.withTimeout(Duration.ofSeconds(120)).build();
			service.start();
			
			// Wait a moment for the service to fully start
			Thread.sleep(2000);
			
			UiAutomator2Options options = new UiAutomator2Options();
			options.setDeviceName(deviceName);
			options.setChromedriverExecutable(chromedriverPath);
			options.setApp(apkRelativePath);
			// Add additional options for better compatibility
			options.setAutoGrantPermissions(true);
			options.setNoReset(false);
			options.setFullReset(true);

			driver = new AndroidDriver(new URI("http://" + ipAddress + ":" + port).toURL(), options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			// Initialize page objects only if driver is successfully created
			if (driver != null) {
				login = new Loginpage(driver);
				utils = new AndroidActionClass(driver);
				discover = new DiscoverChallenge(driver);
				signup = new Signuppage(driver);
			} else {
				throw new RuntimeException("Failed to create AndroidDriver instance");
			}
			
		} catch (Exception e) {
			System.err.println("Error during Appium configuration: " + e.getMessage());
			e.printStackTrace();
			// Clean up if there's an error
			if (driver != null) {
				try {
					driver.quit();
				} catch (Exception ex) {
					System.err.println("Error quitting driver: " + ex.getMessage());
				}
			}
			if (service != null && service.isRunning()) {
				try {
					service.stop();
				} catch (Exception ex) {
					System.err.println("Error stopping service: " + ex.getMessage());
				}
			}
			throw e;
		}
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		//stop the application and server
		try {
			if (driver != null) {
				driver.quit();
			}
		} catch (Exception e) {
			System.err.println("Error quitting driver: " + e.getMessage());
		}
		
		try {
			if (service != null && service.isRunning()) {
				service.stop();
			}
		} catch (Exception e) {
			System.err.println("Error stopping service: " + e.getMessage());
		}
	}
}
