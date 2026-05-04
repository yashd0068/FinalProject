package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.HotelsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_20_ValidateFinalAdultCount extends BaseClass {

    @Test
    public void validateAdultCountFlowAndBoundaries() {

        HotelsPage hotels = new HotelsPage(driver);

        hotels.openHotels();
        hotels.openAdultDropdown();

        int defaultCount = hotels.getAdultCount();
        Assert.assertTrue(
                defaultCount >= 1,
                "Default adult count should be at least 1 but was: " + defaultCount
        );

        hotels.incrementAdult(2);
        int incrementedCount = hotels.getAdultCount();

        Assert.assertEquals(
                incrementedCount,
                defaultCount + 2,
                "Adult count did not increment correctly"
        );

        List<Integer> allCountsAfterIncrement = hotels.getAllAdultCounts();
        for (Integer count : allCountsAfterIncrement) {
            Assert.assertEquals(
                    count.intValue(),
                    incrementedCount,
                    "Mismatch in adult count across rooms after increment"
            );
        }

        hotels.decrementAdult(1);
        int decrementedCount = hotels.getAdultCount();

        Assert.assertEquals(
                decrementedCount,
                incrementedCount - 1,
                "Adult count did not decrement correctly"
        );

        hotels.decrementAdult(5); // intentionally large

        int finalCount = hotels.getAdultCount();
        Assert.assertTrue(
                finalCount >= 1,
                "Adult count should never go below 1, but was: " + finalCount
        );

        List<Integer> finalRoomCounts = hotels.getAllAdultCounts();
        for (Integer count : finalRoomCounts) {
            Assert.assertEquals(
                    count.intValue(),
                    finalCount,
                    "Final adult count mismatch across rooms"
            );
        }
    }
}
