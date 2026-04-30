package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.GiftCardPage;
import org.automation.testing.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_07_DisplayAndCountGiftCards extends BaseClass {

    @Test
    public void DisplayAndCountGiftCards() {
        HomePage home = new HomePage(driver);
        home.clickOnGiftCards();
        GiftCardPage gift = new GiftCardPage(driver);
        int cardCount = gift.getGiftCardCount();
        Assert.assertTrue(cardCount > 0, "Gift cards are not displayed");
        gift.printGiftCardNames();
        //ScreenshotUtil.takeScreenshot(driver, "TC07_DisplayGiftCards");
    }
}
