package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.CabsPage;
import org.automation.testing.pages.HomePage;
import org.automation.testing.pages.ResultsPage;
import org.automation.testing.utility.Utility;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_05_FindAndValidateCheapestSUV extends BaseClass {

    @Test
    public void verifyCheapestSUV() {

        HomePage home = new HomePage(driver);
        CabsPage cabs = new CabsPage(driver);
        ResultsPage results = new ResultsPage(driver);

        home.clickOnCabs();
        cabs.selectOutstation();

        cabs.selectFromCity(Utility.getProperty("fromCity"));

        cabs.selectToCity(Utility.getProperty("toCity"));

        cabs.clickSearch();
        cabs.applySUVFilters();

        System.out.println("Get cheapest SUV price");
        int lowestPrice = results.getCheapestSUVPrice();

        System.out.println("Cheapest SUV Price: ₹ " + lowestPrice);

        Assert.assertTrue(lowestPrice > 0,
                "Cheapest SUV price validation failed");
    }
}