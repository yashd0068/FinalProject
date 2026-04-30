package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.HomePage;
import org.automation.testing.pages.CabsPage;
import org.automation.testing.utility.LogUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_02_NavigateToCabsAndSelectOutstation extends BaseClass {

    @Test
    public void navigateToCabsAndSelectOutstation() {

        HomePage homePage = new HomePage(driver);
        CabsPage cabsPage = new CabsPage(driver);

        LogUtil.log("Clicking on Cabs module");
        homePage.clickOnCabs();

        LogUtil.log("Verifying navigation to Cabs page");
        Assert.assertTrue(driver.getCurrentUrl().contains("cabs"),
                "Cabs page navigation failed");

        LogUtil.log("Selecting Outstation travel type");
        cabsPage.selectOutstation();

        LogUtil.log("Validating Outstation selection");
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("outstation"),
                "Outstation option not selected");

        LogUtil.log("Navigated to Cabs and selected Outstation successfully");
    }
}