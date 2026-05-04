package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.utility.ExcelReader;
import org.automation.testing.utility.LogUtil;
import org.automation.testing.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TC_02_InvalidLoginTest extends BaseClass {

    private LoginPage loginPage;
    private SoftAssert softAssert;

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
                            " | Expected: " + expectedKeyword +
                            " | Actual: " + actualError
            );

            loginPage.clearExistingErrors();
        }

        softAssert.assertAll();
    }
}