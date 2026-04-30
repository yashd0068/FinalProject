package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.HomePage;
import org.automation.testing.utility.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_06_GiftCardPageValidation extends BaseClass {

    @Test
    public void NavigateGiftCardPage() {
        HomePage home = new HomePage(driver);
        home.clickOnGiftCards();
        ScreenshotUtil.takeScreenshot(driver, "TC06_GiftCardPage");
        boolean pageLoaded =
                driver.getTitle().toLowerCase().contains("gift")
                        || driver.getCurrentUrl().toLowerCase().contains("gift");
        Assert.assertTrue(pageLoaded, "Navigation to Gift Card page failed");
    }
}