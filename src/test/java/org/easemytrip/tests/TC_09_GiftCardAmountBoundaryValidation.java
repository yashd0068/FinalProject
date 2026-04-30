package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.GiftCardFormPage;
import org.automation.testing.pages.GiftCardPage;
import org.automation.testing.pages.HomePage;
import org.automation.testing.utility.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_09_GiftCardAmountBoundaryValidation extends BaseClass {

    @Test
    public void validateAmountBoundaryValues() {
        HomePage home = new HomePage(driver);
        home.clickOnGiftCards();
        GiftCardPage gift = new GiftCardPage(driver);
        gift.clickWeddingGift();
        GiftCardFormPage form = new GiftCardFormPage(driver);

        List<String[]> data = ExcelReader.readExcelData(
                "src/test/resources/testData/AmountBoundary.xlsx",
                "AmountBoundary"
        );

        for (String[] row : data) {
            String amount = row[0];
            String expected = row[1];
            form.clearAmount();
            form.enterAmount(amount);
            form.selectQuantity("1");
            form.clickPayNow();

            if (expected.equalsIgnoreCase("valid")) {
                Assert.assertFalse(form.isAmountBoundaryErrorDisplayed());
            } else {
                Assert.assertTrue(form.isAmountBoundaryErrorDisplayed());
            }
        }
    }
}