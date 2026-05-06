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

        LogUtil.info("Navigating to Gift Cards page");
        home.clickOnGiftCards();

        GiftCardPage gift = new GiftCardPage(driver);

        LogUtil.info("Fetching total number of gift cards");
        int cardCount = gift.getGiftCardCount();
        LogUtil.info("Total Gift Cards displayed: " + cardCount);

        Assert.assertTrue(
                cardCount > 0,
                "Assertion Failed: No gift cards are displayed on the Gift Card page"
        );

        LogUtil.info("Printing Gift Card names");
        gift.printGiftCardNames();

        LogUtil.info("Gift Cards display and count validation successful");

    }
}