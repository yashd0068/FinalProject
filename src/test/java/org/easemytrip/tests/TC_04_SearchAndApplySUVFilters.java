package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.CabsPage;
import org.automation.testing.pages.HomePage;
import org.automation.testing.utility.Utility;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_04_SearchAndApplySUVFilters extends BaseClass {

    @Test
    public void searchCabsAndApplySUVFilters() {

        HomePage homePage = new HomePage(driver);
        CabsPage cabsPage = new CabsPage(driver);

        homePage.clickOnCabs();
        cabsPage.selectOutstation();

        System.out.println("Selected FROM and TO cities");
        cabsPage.selectFromCity(Utility.getProperty("fromCity"));
        cabsPage.selectToCity(Utility.getProperty("toCity"));

        System.out.println("Clicked Search button");
        cabsPage.clickSearch();

        System.out.println("Apply SUV and SUV Luxury filters");
        cabsPage.applySUVFilters();

        System.out.println("Step 5: Validate results page loaded");
        Assert.assertTrue(driver.getPageSource().contains("Book Now"),
                "Cab results page not loaded");

        System.out.println("Search executed and SUV filters applied successfully");
    }
}