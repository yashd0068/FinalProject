package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.GiftCardFormPage;
import org.automation.testing.pages.GiftCardPage;
import org.automation.testing.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_11_ReceiverEmailValidation extends BaseClass {

    @Test
    public void validateInvalidReceiverEmail() {
        HomePage home = new HomePage(driver);
        home.clickOnGiftCards();
        GiftCardPage gift = new GiftCardPage(driver);
        gift.clickWeddingGift();
        GiftCardFormPage form = new GiftCardFormPage(driver);

        form.fillMandatoryFieldsExceptEmail();
        form.enterSenderEmail("sender@test.com");
        form.enterReceiverName("ReceiverUser");
        form.enterReceiverEmail("invalidemail");
        form.enterReceiverRetypeEmail("invalidemail");
        form.clickPayNow();

        String message = form.getDisplayedFormMessage();
        Assert.assertTrue(message.toLowerCase().contains("invalid receiver email"));
    }
}

