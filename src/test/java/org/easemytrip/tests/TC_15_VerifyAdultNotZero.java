package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.HotelsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_15_VerifyAdultNotZero extends BaseClass {

    @Test
    public void verifyAdultCountIsNeverZeroAndConsistent() {

        HotelsPage hotels = new HotelsPage(driver);

        hotels.openHotels();
        hotels.openAdultDropdown();

        int initialCount = hotels.getAdultCount();
        Assert.assertTrue(initialCount > 0,
                "Initial adult count should be greater than zero");

        hotels.decrementAdult(5);

        int countAfterDecrement = hotels.getAdultCount();
        Assert.assertTrue(countAfterDecrement > 0,
                "Adult count became zero or negative after decrement");

        hotels.incrementAdult(1);

        int updatedCount = hotels.getAdultCount();
        Assert.assertTrue(updatedCount > 0,
                "Adult count invalid after increment");

        List<Integer> roomCounts = hotels.getAllAdultCounts();
        for (Integer count : roomCounts) {
            Assert.assertEquals(count.intValue(), updatedCount,
                    "Adult count mismatch across rooms");
        }
    }
}
