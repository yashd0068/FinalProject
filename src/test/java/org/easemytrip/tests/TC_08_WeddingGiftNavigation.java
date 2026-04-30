package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.GiftCardPage;
import org.automation.testing.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_08_WeddingGiftNavigation extends BaseClass {

    @Test
    public void validateWeddingGiftNavigation() {
        HomePage home = new HomePage(driver);
        home.clickOnGiftCards();
        GiftCardPage gift = new GiftCardPage(driver);
        gift.clickWeddingGift();
        Assert.assertTrue(
                driver.getPageSource().toLowerCase().contains("sender"),
                "Gift card form not displayed"
        );
    }
}