package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.HotelsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_18_DecrementAdultByOne extends BaseClass {

    @Test
    public void decrementAdultByOneAndValidateConsistency() {

        HotelsPage hotels = new HotelsPage(driver);

        hotels.openHotels();
        hotels.openAdultDropdown();

        int initialCount = hotels.getAdultCount();
        Assert.assertTrue(initialCount > 1,
                "Initial adult count must be greater than 1 to validate decrement");

        hotels.decrementAdult(1);
        int afterFirstDecrement = hotels.getAdultCount();

        Assert.assertEquals(afterFirstDecrement, initialCount - 1,
                "Adult count did not decrement by one");

        hotels.decrementAdult(1);
        int afterSecondDecrement = hotels.getAdultCount();

        Assert.assertEquals(afterSecondDecrement, 1,
                "Adult count should not go below minimum allowed value (1)");

        List<Integer> roomCounts = hotels.getAllAdultCounts();
        for (Integer count : roomCounts) {
            Assert.assertEquals(count.intValue(), afterSecondDecrement,
                    "Adult count mismatch across rooms after decrement");
        }
    }
}