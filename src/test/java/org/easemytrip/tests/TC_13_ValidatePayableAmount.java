package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.GiftCardFormPage;
import org.automation.testing.pages.GiftCardPage;
import org.automation.testing.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_13_ValidatePayableAmount extends BaseClass {

    @Test
    public void validateAndDisplayPayableAmount() {
        HomePage home = new HomePage(driver);
        home.clickOnGiftCards();
        GiftCardPage gift = new GiftCardPage(driver);
        gift.clickWeddingGift();
        GiftCardFormPage form = new GiftCardFormPage(driver);

        int unitAmount = 1000;
        int quantity = 4;
        int expectedPayable = unitAmount * quantity;

        form.enterAmount(String.valueOf(unitAmount));
        form.selectQuantity(String.valueOf(quantity));
        form.enterSenderName("TestUser");
        form.enterSenderMobile("9876543210");
        form.enterSenderEmail("sender@test.com");

        int actualPayable = form.getPayableAmount();
        Assert.assertEquals(actualPayable, expectedPayable);
    }
}
