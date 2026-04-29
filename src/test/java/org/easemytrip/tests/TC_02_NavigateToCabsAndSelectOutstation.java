package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.HomePage;
import org.automation.testing.pages.CabsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_02_NavigateToCabsAndSelectOutstation extends BaseClass {

    @Test
    public void navigateToCabsAndSelectOutstation() {

        HomePage homePage = new HomePage(driver);
        CabsPage cabsPage = new CabsPage(driver);

        System.out.println("Clicked on Cabs module");
        homePage.clickOnCabs();

        System.out.println("Verify navigation to Cabs page");
        Assert.assertTrue(driver.getCurrentUrl().contains("cabs"),
                "Cabs page navigation failed");

        System.out.println("Select Outstation travel type");
        cabsPage.selectOutstation();

        System.out.println("Validate Outstation selection");
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("outstation"),
                "Outstation option not selected");

        System.out.println("Navigated to Cabs and selected Outstation successfully");
    }
}