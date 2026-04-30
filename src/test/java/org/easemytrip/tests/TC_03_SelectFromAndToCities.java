package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.CabsPage;
import org.automation.testing.pages.HomePage;
import org.automation.testing.utility.ConfigUtility;
import org.automation.testing.utility.LogUtil;
import org.automation.testing.utility.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_03_SelectFromAndToCities extends BaseClass {

    @Test
    public void selectFromAndToCities() {

        HomePage homePage = new HomePage(driver);
        CabsPage cabsPage = new CabsPage(driver);

        LogUtil.log("Clicking on Cabs module");
        homePage.clickOnCabs();
        ScreenshotUtil.takeScreenshot(driver, "TC03_01_CabsPageOpened");

        LogUtil.log("Selecting Outstation travel type");
        cabsPage.selectOutstation();
        ScreenshotUtil.takeScreenshot(driver, "TC03_02_OutstationSelected");

        String fromCity = ConfigUtility.getProperty("fromCity");
        String toCity = ConfigUtility.getProperty("toCity");

        LogUtil.log("Selecting FROM city: " + fromCity);
        cabsPage.selectFromCity(fromCity);
        ScreenshotUtil.takeScreenshot(driver, "TC03_03_FromCitySelected");

        Assert.assertTrue(
                driver.getPageSource().toLowerCase().contains(fromCity.toLowerCase()),
                "FROM city not selected correctly"
        );

        LogUtil.log("Selecting TO city: " + toCity);
        cabsPage.selectToCity(toCity);
        ScreenshotUtil.takeScreenshot(driver, "TC03_04_ToCitySelected");

        Assert.assertTrue(
                driver.getPageSource().toLowerCase().contains(toCity.toLowerCase()),
                "TO city not selected correctly"
        );

        LogUtil.log("FROM and TO cities selected successfully");
    }
}