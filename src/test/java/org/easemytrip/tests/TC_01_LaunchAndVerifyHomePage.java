package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.utility.LogUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_01_LaunchAndVerifyHomePage extends BaseClass {

    @Test
    public void verifyEaseMyTripHomePage() {

        LogUtil.info("Verifying application URL");
        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(currentUrl.contains("easemytrip"),
                "Application URL validation failed");

        LogUtil.info("Verifying page title");
        String title = driver.getTitle();

        Assert.assertTrue(title.contains("EaseMyTrip"),
                "Home page title validation failed");

        LogUtil.info("Verifying page loaded successfully");
        Assert.assertTrue(title.length() > 5,
                "Page did not load properly");

        LogUtil.info("Home page launched and verified successfully");
    }
}