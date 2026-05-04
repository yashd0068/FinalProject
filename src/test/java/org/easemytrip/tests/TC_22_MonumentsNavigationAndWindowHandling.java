package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.MonumentsPage;
import org.automation.testing.utility.LogUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class TC_22_MonumentsNavigationAndWindowHandling extends BaseClass {

    @Test
    public void validateMonumentsNavigationAndWindowHandling() {

        LogUtil.log("Starting Monuments navigation and window handling validation");

        String parentWindow = driver.getWindowHandle();
        LogUtil.log("Captured parent window handle: " + parentWindow);

        MonumentsPage monumentsPage = new MonumentsPage(driver, wait);

        monumentsPage.openMonumentsPage();

        Set<String> allWindows = driver.getWindowHandles();
        LogUtil.log("Total windows opened: " + allWindows.size());

        Assert.assertTrue(allWindows.size() > 1,
                "Monuments page did not open in a new window");

        Assert.assertNotEquals(driver.getWindowHandle(), parentWindow,
                "Driver did not switch to Monuments window");

        String pageTitle = driver.getTitle();
        LogUtil.log("Monuments page title: " + pageTitle);

        Assert.assertFalse(pageTitle.isEmpty(),
                "Monuments page title should not be empty");

        Assert.assertTrue(pageTitle.toLowerCase().contains("monument"),
                "Incorrect page opened after Monuments navigation");

        LogUtil.log("Monuments navigation and window handling validated successfully");
    }
}
