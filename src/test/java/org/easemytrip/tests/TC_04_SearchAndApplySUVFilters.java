package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.CabsPage;
import org.automation.testing.pages.HomePage;
import org.automation.testing.utility.ConfigUtility;
import org.automation.testing.utility.LogUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_04_SearchAndApplySUVFilters extends BaseClass {

    @Test
    public void searchCabsAndApplySUVFilters() {

        HomePage homePage = new HomePage(driver);
        CabsPage cabsPage = new CabsPage(driver);

        LogUtil.log("Clicking on Cabs module");
        homePage.clickOnCabs();

        LogUtil.log("Selecting Outstation travel type");
        cabsPage.selectOutstation();

        String fromCity = ConfigUtility.getProperty("fromCity");
        String toCity = ConfigUtility.getProperty("toCity");

        LogUtil.log("Selecting FROM city: " + fromCity);
        cabsPage.selectFromCity(fromCity);

        LogUtil.log("Selecting TO city: " + toCity);
        cabsPage.selectToCity(toCity);

        LogUtil.log("Clicking Search button");
        cabsPage.clickSearch();

        LogUtil.log("Applying SUV filters");
        cabsPage.applySUVFilters();

        LogUtil.log("Validating results page loaded");
        Assert.assertTrue(
                driver.getPageSource().contains("Book Now"),
                "Cab results page not loaded"
        );

        LogUtil.log("Search executed and SUV filters applied successfully");
    }
}