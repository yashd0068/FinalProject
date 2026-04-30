package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.GiftCardFormPage;
import org.automation.testing.pages.GiftCardPage;
import org.automation.testing.pages.HomePage;
import org.automation.testing.utility.ExcelReader;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TC_10_SenderEmailValidation extends BaseClass {

    @Test
    public void validateSenderEmailAndPrintMessage() {

        SoftAssert softAssert = new SoftAssert();
        HomePage home = new HomePage(driver);
        home.clickOnGiftCards();
        GiftCardPage gift = new GiftCardPage(driver);
        gift.clickWeddingGift();
        GiftCardFormPage form = new GiftCardFormPage(driver);

        List<String[]> data = ExcelReader.readExcelData(
                "src/test/resources/testData/GiftCard_InputValidation.xlsx",
                "EmailValidation"
        );

        for (String[] row : data) {
            String email = row[0];
            String expected = row[1];
            form.fillMandatoryFieldsExceptEmail();
            form.enterSenderEmail(email);
            form.clickPayNow();
            String message = form.getDisplayedFormMessage();

            if (expected.equalsIgnoreCase("invalid")) {
                softAssert.assertTrue(message.toLowerCase().contains("email"));
            } else {
                softAssert.assertFalse(message.toLowerCase().contains("email"));
            }
        }
        softAssert.assertAll();
    }
}