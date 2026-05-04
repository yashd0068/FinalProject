package org.automation.testing.baseclass;

import org.automation.testing.utility.ConfigUtility;
import org.automation.testing.utility.LogUtil;
import org.automation.testing.utility.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseClass {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

        String browser = ConfigUtility.getProperty("browser").toLowerCase();
        LogUtil.log("Launching browser: " + browser);

        switch (browser) {

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-notifications");
                edgeOptions.addArguments("--disable-popup-blocking");
               // edgeOptions.addArguments("--headless=new");
                driver = new EdgeDriver(edgeOptions);
                LogUtil.log("Edge browser launched");
                break;

            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
               // chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
                LogUtil.log("Chrome browser launched");
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        String url = ConfigUtility.getProperty("url");
        LogUtil.log("Navigating to application URL: " + url);
        driver.get(url);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenshotUtil.takeScreenshot(driver, result.getName());
            LogUtil.log("Screenshot captured for failed test: " + result.getName());
        }

        if (driver != null) {
            driver.quit();
            LogUtil.log("Browser closed");
        }
    }
}