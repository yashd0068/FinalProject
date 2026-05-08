package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.HotelsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_13_VerifyAdultDropdownOpened extends BaseClass {

    @Test
    public void verifyAdultDropdownOpenedAndDataIsAccessible() {

        HotelsPage hotels = new HotelsPage(driver);

        hotels.openHotels();

        int primaryAdultCount = hotels.getAdultCount();
        Assert.assertTrue(primaryAdultCount >= 1,
                "Adult dropdown did not open or adult count is invalid");

        List<Integer> adultCounts = hotels.getAllAdultCounts();
        Assert.assertFalse(adultCounts.isEmpty(),
                "Adult count elements were not populated after opening dropdown");

        for (Integer count : adultCounts) {
            Assert.assertEquals(count.intValue(), primaryAdultCount,
                    "Adult count mismatch indicates dropdown not opened correctly");
        }
    }
}