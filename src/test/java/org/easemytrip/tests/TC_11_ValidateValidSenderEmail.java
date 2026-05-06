package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.GiftCardFormPage;
import org.automation.testing.pages.GiftCardPage;
import org.automation.testing.pages.HomePage;
import org.automation.testing.utility.ExcelReader;
import org.automation.testing.utility.LogUtil;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TC_11_ValidateValidSenderEmail extends BaseClass {

    @Test
    public void validateValidSenderEmail() {

        SoftAssert softAssert = new SoftAssert();

        LogUtil.info("Navigating to Gift Cards");
        HomePage home = new HomePage(driver);
        home.clickOnGiftCards();

        LogUtil.info("Selecting Wedding Gift Card");
        GiftCardPage gift = new GiftCardPage(driver);
        gift.clickWeddingGift();

        GiftCardFormPage form = new GiftCardFormPage(driver);

        LogUtil.info("Reading VALID email data from Excel");
        List<String[]> data = ExcelReader.readExcelData(
                "src/test/resources/testData/GiftCard_InputValidation.xlsx",
                "EmailValidation"
        );

        for (String[] row : data) {

            String email = row[0];
            String expected = row[1];

            if (!expected.equalsIgnoreCase("valid")) {
                continue;
            }

            LogUtil.info("Testing valid email: " + email);

            form.fillMandatoryFieldsExceptEmail();
            form.enterSenderEmail(email);
            form.clickPayNow();

            String message = form.getDisplayedFormMessage();
            LogUtil.info("Displayed Message: " + message);

            softAssert.assertFalse(
                    message.toLowerCase().contains("email"),
                    "Error message shown for valid email: " + email
            );

            LogUtil.info("Valid email accepted successfully");
        }

        softAssert.assertAll();
        LogUtil.info("Valid sender email test completed");
    }
}