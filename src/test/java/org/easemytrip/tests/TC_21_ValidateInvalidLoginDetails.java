package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.utility.ExcelReader;
import org.automation.testing.utility.LogUtil;
<<<<<<< HEAD:src/test/java/org/easemytrip/tests/TC_02_InvalidLoginTest.java
import org.automation.testing.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
=======
import org.easemytrip.pages.LoginPage;
>>>>>>> 3e72d15a03f5b840c22cdc7d65c230aaa346c8a5:src/test/java/org/easemytrip/tests/TC_21_ValidateInvalidLoginDetails.java
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

<<<<<<< HEAD:src/test/java/org/easemytrip/tests/TC_02_InvalidLoginTest.java
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
=======
public class TC_21_ValidateInvalidLoginDetails extends BaseClass {
>>>>>>> 3e72d15a03f5b840c22cdc7d65c230aaa346c8a5:src/test/java/org/easemytrip/tests/TC_21_ValidateInvalidLoginDetails.java

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
<<<<<<< HEAD:src/test/java/org/easemytrip/tests/TC_02_InvalidLoginTest.java
                    actualError.toLowerCase().contains(expectedKeyword.toLowerCase()),
                    "Validation failed for input: " + inputValue +
                            " | Expected: " + expectedKeyword +
                            " | Actual: " + actualError
            );

            loginPage.clearExistingErrors();
        }

=======
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
>>>>>>> 3e72d15a03f5b840c22cdc7d65c230aaa346c8a5:src/test/java/org/easemytrip/tests/TC_21_ValidateInvalidLoginDetails.java
        softAssert.assertAll();

        LogUtil.log("Invalid login scenarios validated successfully");
    }
}