package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.CabsPage;
import org.automation.testing.pages.HomePage;
import org.automation.testing.utility.ScreenshotUtil;
import org.automation.testing.utility.Utility;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_03_SelectFromAndToCities extends BaseClass {

    @Test
    public void selectFromAndToCities() {

        HomePage homePage = new HomePage(driver);
        CabsPage cabsPage = new CabsPage(driver);

        homePage.clickOnCabs();
        ScreenshotUtil.takeScreenshot(driver, "TC03_01_CabsPageOpened");

        cabsPage.selectOutstation();
        ScreenshotUtil.takeScreenshot(driver, "TC03_02_OutstationSelected");

        String fromCity = Utility.getProperty("fromCity");
        String toCity = Utility.getProperty("toCity");

        System.out.println("Select FROM city: " + fromCity);
        cabsPage.selectFromCity(fromCity);
        ScreenshotUtil.takeScreenshot(driver, "TC03_03_FromCitySelected");

        Assert.assertTrue(driver.getPageSource().toLowerCase().contains(fromCity),
                "FROM city not selected correctly");

        System.out.println("Select TO city: " + toCity);

        cabsPage.selectToCity(toCity);
        ScreenshotUtil.takeScreenshot(driver, "TC03_04_ToCitySelected");

        Assert.assertTrue(driver.getPageSource().toLowerCase().contains(toCity),
                "TO city not selected correctly");

        System.out.println("FROM and TO cities selected successfully");
    }
}