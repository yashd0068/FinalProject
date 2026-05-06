package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.HomePage;
import org.automation.testing.utility.LogUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_06_GiftCardPageValidation extends BaseClass {

    @Test
    public void navigateGiftCardPage() {

        HomePage home = new HomePage(driver);

        LogUtil.log("Clicking on Gift Cards link");
        home.clickOnGiftCards();

        String currentUrl = driver.getCurrentUrl();
        LogUtil.log("Gift Card Page URL: " + currentUrl);

        Assert.assertTrue(
                currentUrl.toLowerCase().contains("gift"),
                "Gift Card page URL validation failed"
        );

        String title = driver.getTitle();
        LogUtil.log("Gift Card Page Title: " + title);

        Assert.assertTrue(
                title.toLowerCase().contains("gift"),
                "Gift Card page title validation failed"
        );

        LogUtil.log("Gift Card page navigation and validation successful");
    }
}