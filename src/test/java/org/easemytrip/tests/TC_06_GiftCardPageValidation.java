package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.HomePage;
import org.automation.testing.utility.LogUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_06_GiftCardPageValidation extends BaseClass {

    @Test
    public void navigateGiftCardPage() {

        // Page object creation
        HomePage home = new HomePage(driver);

        // Action: Click on Gift Cards link
        LogUtil.log("Clicking on Gift Cards link");
        home.clickOnGiftCards();

        // Validation 1: URL validation
        String currentUrl = driver.getCurrentUrl();
        LogUtil.log("Gift Card Page URL: " + currentUrl);

        Assert.assertTrue(
                currentUrl.toLowerCase().contains("gift"),
                "Gift Card page URL validation failed"
        );

        // Validation 2: Title validation
        String title = driver.getTitle();
        LogUtil.log("Gift Card Page Title: " + title);

        Assert.assertTrue(
                title.toLowerCase().contains("gift"),
                "Gift Card page title validation failed"
        );

        // Final success log
        LogUtil.log("Gift Card page navigation and validation successful");
    }
}