# Appium Setup Troubleshooting Guide

## Issues Fixed

1. **NullPointerException in creation.java**: Added null checks and better error handling
2. **SessionNotCreated Error**: Enhanced BaseTest with better error handling and configuration
3. **NullPointerException in teardown**: Added null checks for driver and service cleanup

## Pre-requisites Check

Before running tests, ensure you have:

1. **Android SDK** installed and `adb` in PATH
2. **Appium** installed globally: `npm install -g appium`
3. **ChromeDriver** for Android WebView testing
4. **Android Device/Emulator** connected and visible via `adb devices`

## Troubleshooting Steps

### Step 1: Run the Troubleshooting Script
```bash
troubleshoot_setup.bat
```

This will check:
- ADB availability
- Connected devices
- Appium installation
- APK file existence
- ChromeDriver path
- Appium JS file path

### Step 2: Verify Device Connection
```bash
adb devices
```
You should see your device listed. If not:
- Enable USB debugging on your device
- Install device drivers
- Try different USB cables/ports

### Step 3: Test Basic Setup
```bash
mvn test -PRegression -Dtest=BasicSetupTest
```
This runs only the basic setup test to verify Appium configuration.

### Step 4: Check Device Name
If you're using an emulator, try changing the device name in `data.properties`:
```properties
deviceName=emulator-5554
```

For real devices, use the device ID from `adb devices`:
```properties
deviceName=your_device_id_here
```

## Common Issues and Solutions

### Issue: "Cannot start the 'com.competehealth' application"
**Solutions:**
1. Verify the APK file exists and is not corrupted
2. Check if the app is compatible with your Android version
3. Try uninstalling the app first: `adb uninstall com.competehealth`
4. Ensure the device has enough storage space

### Issue: "SessionNotCreated" with Response code 500
**Solutions:**
1. Restart the Android device/emulator
2. Clear Appium cache: `appium --reset`
3. Check if another Appium session is running
4. Verify the device is unlocked and not in sleep mode

### Issue: "ChromeDriver not found"
**Solutions:**
1. Download the correct ChromeDriver version for your Chrome version
2. Update the path in `data.properties`
3. Ensure ChromeDriver is executable

### Issue: "Appium JS file not found"
**Solutions:**
1. Reinstall Appium: `npm uninstall -g appium && npm install -g appium`
2. Check the path in BaseTest.java
3. Use the correct path for your system

## Configuration Options

### Alternative Device Configuration
If "Akshit_Pixel_4" doesn't work, try:
```properties
deviceName=emulator-5554
# or
deviceName=your_actual_device_id
```

### Alternative Appium Server Path
If the default path doesn't work, update in BaseTest.java:
```java
.withAppiumJS(new File("your_appium_path_here"))
```

## Running Tests

### Run Basic Setup Test Only
```bash
mvn test -PRegression -Dtest=BasicSetupTest
```

### Run All Tests
```bash
mvn test -PRegression
```

### Run with Custom IP Address
```bash
mvn test -PRegression -DipAddress=192.168.1.100
```

## Debug Mode

To get more detailed error information, run Maven with debug:
```bash
mvn test -PRegression -X
```

## Logs Location

Test reports and logs are available in:
- `target/surefire-reports/` - Maven Surefire reports
- `test-output/` - TestNG reports

## Still Having Issues?

1. Check the detailed error logs in the reports
2. Verify all pre-requisites are met
3. Try running Appium manually first: `appium`
4. Test with a simple Appium script before running the full test suite 