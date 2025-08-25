@echo off
echo ========================================
echo Appium Setup Troubleshooting Script
echo ========================================
echo.

echo 1. Checking if ADB is available...
adb version
if %errorlevel% neq 0 (
    echo ERROR: ADB is not found in PATH
    echo Please install Android SDK and add platform-tools to PATH
    goto :end
)
echo ADB is available
echo.

echo 2. Checking connected devices...
adb devices
echo.

echo 3. Checking if Appium is installed...
appium --version
if %errorlevel% neq 0 (
    echo ERROR: Appium is not installed or not in PATH
    echo Please install Appium using: npm install -g appium
    goto :end
)
echo Appium is available
echo.

echo 4. Checking if the APK file exists...
if exist "src\test\java\CompeteHealth\Resources\competehealth.apk" (
    echo APK file found
    dir "src\test\java\CompeteHealth\Resources\competehealth.apk"
) else (
    echo ERROR: APK file not found at src\test\java\CompeteHealth\Resources\competehealth.apk
)
echo.

echo 5. Checking if ChromeDriver exists...
if exist "C:\Users\user.DESKTOP-I9IFEEO\Downloads\chromedriver-win64\chromedriver.exe" (
    echo ChromeDriver found
) else (
    echo ERROR: ChromeDriver not found at specified path
)
echo.

echo 6. Checking if Appium JS file exists...
if exist "C:\Users\user.DESKTOP-I9IFEEO\AppData\Roaming\npm\node_modules\appium\build\lib\main.js" (
    echo Appium JS file found
) else (
    echo ERROR: Appium JS file not found
)
echo.

echo ========================================
echo Troubleshooting Complete
echo ========================================
echo.
echo If you see any ERROR messages above, please fix them before running tests.
echo.
echo To run tests after fixing issues:
echo mvn test -PRegression
echo.

:end
pause 