package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.utility.LogUtil;
import org.automation.testing.utility.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_01_LaunchAndVerifyHomePage extends BaseClass {

    @Test
    public void verifyEaseMyTripHomePage() {

        LogUtil.log("Verifying application URL");
        String currentUrl = driver.getCurrentUrl();
//        ScreenshotUtil.takeScreenshot(driver, "TC01_01_ApplicationLaunched");

        Assert.assertTrue(currentUrl.contains("easemytrip"),
                "Application URL validation failed");

        LogUtil.log("Verifying page title");
        String title = driver.getTitle();
//        ScreenshotUtil.takeScreenshot(driver, "TC01_02_HomePageTitle");

        Assert.assertTrue(title.contains("EaseMyTrip"),
                "Home page title validation failed");

        LogUtil.log("Verifying page loaded successfully");
        Assert.assertTrue(title.length() > 5,
                "Page did not load properly");

//        ScreenshotUtil.takeScreenshot(driver, "TC01_03_HomePageVerified");

        LogUtil.log("Home page launched and verified successfully");
    }
}
