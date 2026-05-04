package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.GiftCardFormPage;
import org.automation.testing.pages.GiftCardPage;
import org.automation.testing.pages.HomePage;
import org.automation.testing.utility.ExcelReader;
import org.automation.testing.utility.LogUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_09_GiftCardAmountBoundaryValidation extends BaseClass {

    @Test
    public void validateAmountBoundaryValues() {

        LogUtil.log("Initializing HomePage and navigating to Gift Cards page");
        HomePage home = new HomePage(driver);
        home.clickOnGiftCards();

        LogUtil.log("Selecting Wedding Gift card");
        GiftCardPage gift = new GiftCardPage(driver);
        gift.clickWeddingGift();

        LogUtil.log("Initializing GiftCardFormPage");
        GiftCardFormPage form = new GiftCardFormPage(driver);

        LogUtil.log("Reading boundary test data from Excel");
        List<String[]> data = ExcelReader.readExcelData(
                "src/test/resources/testData/AmountBoundary.xlsx",
                "AmountBoundary"
        );

        for (String[] row : data) {

            String amount = row[0];
            String expectedResult = row[1];

            LogUtil.log("--------------------------------------------------");
            LogUtil.log("Executing boundary test with Amount: " + amount);
            LogUtil.log("Expected Result: " + expectedResult);

            form.clearAmount();
            form.enterAmount(amount);
            form.selectQuantity("1");
            form.clickPayNow();

            boolean errorDisplayed = form.isAmountBoundaryErrorDisplayed();
            String errorMessage = errorDisplayed ? form.getAmountBoundaryErrorMessage() : "No error message displayed";

            LogUtil.log("Actual Result - Error Displayed: " + errorDisplayed);
            LogUtil.log("Error Message: " + errorMessage);

            if (expectedResult.equalsIgnoreCase("valid")) {

                Assert.assertFalse(
                        errorDisplayed,
                        "Validation Failed: Error message displayed for valid amount " + amount
                );

                LogUtil.log("✅ Test Passed for valid amount: " + amount);

            } else {

                Assert.assertTrue(
                        errorDisplayed,
                        "Validation Failed: Error message NOT displayed for invalid amount " + amount
                );

                LogUtil.log("✅ Test Passed for invalid amount: " + amount);
            }
        }

        LogUtil.log("Boundary amount validation completed successfully");
    }
}
