package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.GiftCardPage;
import org.automation.testing.pages.HomePage;
import org.automation.testing.utility.LogUtil;
import org.automation.testing.utility.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_07_DisplayAndCountGiftCards extends BaseClass {

    @Test
    public void DisplayAndCountGiftCards() {


        HomePage home = new HomePage(driver);

        LogUtil.log("Navigating to Gift Cards page");
        home.clickOnGiftCards();


        GiftCardPage gift = new GiftCardPage(driver);

        LogUtil.log("Fetching total number of gift cards");
        int cardCount = gift.getGiftCardCount();
        LogUtil.log("Total Gift Cards displayed: " + cardCount);

        // Standard assertion to validate gift cards are displayed
        Assert.assertTrue(
                cardCount > 0,
                "Assertion Failed: No gift cards are displayed on the Gift Card page"
        );

        LogUtil.log("Printing Gift Card names");
        gift.printGiftCardNames();

        LogUtil.log("Gift Cards display and count validation successful");
        ScreenshotUtil.takeScreenshot(driver, "TC07_DisplayGiftCards");
    }
}