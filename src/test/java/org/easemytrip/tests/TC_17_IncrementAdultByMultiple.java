package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.HotelsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_17_IncrementAdultByMultiple extends BaseClass {

    @Test
    public void incrementAdultByMultipleAndValidateConsistency() {

        HotelsPage hotels = new HotelsPage(driver);

        hotels.openHotels();
        hotels.openAdultDropdown();

        int initialCount = hotels.getAdultCount();
        Assert.assertTrue(initialCount >= 1,
                "Initial adult count must be valid");

        hotels.incrementAdult(3);

        int updatedCount = hotels.getAdultCount();
        Assert.assertEquals(updatedCount, initialCount + 3,
                "Adult count did not increment by expected value");

        hotels.incrementAdult(2);

        int secondUpdate = hotels.getAdultCount();
        Assert.assertEquals(secondUpdate, updatedCount + 2,
                "Subsequent increment did not work correctly");

        List<Integer> roomCounts = hotels.getAllAdultCounts();
        for (Integer count : roomCounts) {
            Assert.assertEquals(count.intValue(), secondUpdate,
                    "Adult count mismatch across rooms after increment");
        }
    }
}