package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.GiftCardFormPage;
import org.automation.testing.pages.GiftCardPage;
import org.automation.testing.pages.HomePage;
import org.automation.testing.utility.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_12_ValidatePayableAmount extends BaseClass {

    @Test
    public void validateAndDisplayPayableAmountUsingExcel() {

        HomePage home = new HomePage(driver);
        home.clickOnGiftCards();

        GiftCardPage gift = new GiftCardPage(driver);
        gift.clickWeddingGift();

        GiftCardFormPage form = new GiftCardFormPage(driver);

        List<String[]> data = ExcelReader.readExcelData(
                "src/test/resources/testData/GiftCard_PayableAmount 1.xlsx",
                "PayableAmount"
        );

        for (String[] row : data) {

            int unitAmount = (int) Double.parseDouble(row[0]);
            int quantity = (int) Double.parseDouble(row[1]);

            int expectedPayable = unitAmount * quantity;

            form.clearAmount();
            form.enterAmount(String.valueOf(unitAmount));
            form.selectQuantity(String.valueOf(quantity));

            int actualPayable = form.getPayableAmount();

            System.out.println(
                    "Amount: " + unitAmount +
                            " | Quantity: " + quantity +
                            " | Payable Amount: " + actualPayable
            );

            Assert.assertEquals(
                    actualPayable,
                    expectedPayable,
                    "Payable amount mismatch for Amount: "
                            + unitAmount + " Quantity: " + quantity
            );
        }
    }
}