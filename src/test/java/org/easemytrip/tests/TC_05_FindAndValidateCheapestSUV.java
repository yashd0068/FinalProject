package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.CabsPage;
import org.automation.testing.pages.HomePage;
import org.automation.testing.pages.ResultsPage;
import org.automation.testing.utility.ConfigUtility;
import org.automation.testing.utility.LogUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_05_FindAndValidateCheapestSUV extends BaseClass {

    @Test
    public void verifyCheapestSUV() {

        HomePage home = new HomePage(driver);
        CabsPage cabs = new CabsPage(driver);
        ResultsPage results = new ResultsPage(driver);

        LogUtil.log("Clicking on Cabs module");
        home.clickOnCabs();

        LogUtil.log("Selecting Outstation travel type");
        cabs.selectOutstation();

        String fromCity = ConfigUtility.getProperty("fromCity");
        String toCity = ConfigUtility.getProperty("toCity");

        LogUtil.log("Selecting FROM city: " + fromCity);
        cabs.selectFromCity(fromCity);

        LogUtil.log("Selecting TO city: " + toCity);
        cabs.selectToCity(toCity);

        LogUtil.log("Clicking Search button");
        cabs.clickSearch();

        LogUtil.log("Applying SUV filters");
        cabs.applySUVFilters();

        LogUtil.log("Fetching cheapest SUV price");
        int lowestPrice = results.getCheapestSUVPrice();

        LogUtil.log("Cheapest SUV Price: ₹ " + lowestPrice);

        Assert.assertTrue(
                lowestPrice > 0,
                "Cheapest SUV price validation failed"
        );
    }
}
