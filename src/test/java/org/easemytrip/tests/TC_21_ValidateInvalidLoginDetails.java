package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.utility.ExcelReader;
import org.automation.testing.utility.LogUtil;
import org.easemytrip.pages.LoginPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TC_21_ValidateInvalidLoginDetails extends BaseClass {

    @Test
    public void verifyInvalidLoginFromExcel() {

        LoginPage loginPage = new LoginPage(driver, wait);
        SoftAssert softAssert = new SoftAssert();

        LogUtil.log("Opening login popup");
        loginPage.openLoginPopup();

        String excelPath = "src/test/resources/testData/InvalidLoginData.xlsx";
        LogUtil.log("Reading test data from Excel file");

        List<String[]> testData =
                ExcelReader.readExcelData(excelPath, "InvalidLogin");

        for (String[] data : testData) {

            String inputValue = data[0];
            String expectedKeyword = data[1];

            LogUtil.log("-------------------------------------------------");
            LogUtil.log("Testing invalid input: " + inputValue);

            loginPage.enterValue(inputValue);
            loginPage.clickContinue();

            String actualError = loginPage.getErrorMessage();
            LogUtil.log("Error message displayed: " + actualError);

            softAssert.assertTrue(
                    actualError.toLowerCase()
                            .contains(expectedKeyword.toLowerCase()),
                    "Invalid login validation failed | Input: " + inputValue +
                            " | Expected keyword: " + expectedKeyword +
                            " | Actual error: " + actualError
            );

            // ✅ Important to avoid Angular / DOM reuse issues
            loginPage.clearExistingErrors();
        }

        // ✅ Mandatory when using SoftAssert
        softAssert.assertAll();

        LogUtil.log("Invalid login scenarios validated successfully");
    }
}