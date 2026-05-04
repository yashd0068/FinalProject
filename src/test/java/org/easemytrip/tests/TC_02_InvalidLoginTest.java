package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.utility.ExcelReader;
import org.automation.testing.utility.LogUtil;
import org.easemytrip.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TC_02_InvalidLoginTest extends BaseClass {

    LoginPage loginPage;
    SoftAssert softAssert;

    @BeforeMethod
    public void initialize() {

        loginPage = new LoginPage(driver, wait);
        softAssert = new SoftAssert();

        LogUtil.log("Opening login popup");
        loginPage.openLoginPopup();
    }

    @Test
    public void verifyInvalidLoginFromExcel() {

        String excelPath = "src/test/resources/testdata/InvalidLoginData.xlsx";
        LogUtil.log("Reading test data from Excel");

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
                    actualError.toLowerCase().contains(expectedKeyword.toLowerCase()),
                    "Validation failed for input: " + inputValue +
                            " | Expected keyword: " + expectedKeyword +
                            " | Actual error: " + actualError
            );

            // ✅ Important to avoid Angular reuse issues
            loginPage.clearExistingErrors();
        }

        // ✅ MUST be called, otherwise failures are ignored
        softAssert.assertAll();
    }
}
