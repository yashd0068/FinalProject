package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.GiftCardPage;
import org.automation.testing.pages.HomePage;
import org.automation.testing.utility.LogUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_08_WeddingGiftNavigation extends BaseClass {

    @Test
    public void validateWeddingGiftNavigation() {

        HomePage home = new HomePage(driver);

        LogUtil.info("Navigating to Gift Cards page");
        home.clickOnGiftCards();

        GiftCardPage gift = new GiftCardPage(driver);

        LogUtil.info("Clicking on Wedding Gift card");
        gift.clickWeddingGift();

        LogUtil.info("Validating Wedding Gift card form is displayed");
        String pageSource = driver.getPageSource().toLowerCase();

        Assert.assertTrue(
                pageSource.contains("sender"),
                "Assertion Failed: Wedding Gift card form is not displayed after navigation"
        );

        LogUtil.info("Wedding Gift card navigation validated successfully");
    }
}