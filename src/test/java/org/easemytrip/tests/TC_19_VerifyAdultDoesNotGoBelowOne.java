package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.HotelsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_19_VerifyAdultDoesNotGoBelowOne extends BaseClass {

    @Test
    public void verifyAdultDoesNotGoBelowOneAndStaysStable() {

        HotelsPage hotels = new HotelsPage(driver);

        hotels.openHotels();
        hotels.openAdultDropdown();

        int defaultCount = hotels.getAdultCount();
        Assert.assertTrue(
                defaultCount >= 1,
                "Default adult count should be at least 1 but was: " + defaultCount
        );

        hotels.decrementAdult(10);

        int countAfterDecrement = hotels.getAdultCount();
        Assert.assertEquals(
                countAfterDecrement,
                1,
                "Adult count should stop at 1 after excessive decrement"
        );

        hotels.decrementAdult(3);
        int finalCount = hotels.getAdultCount();

        Assert.assertEquals(
                finalCount,
                1,
                "Adult count should remain 1 even after further decrements"
        );

        List<Integer> roomCounts = hotels.getAllAdultCounts();
        for (Integer count : roomCounts) {
            Assert.assertEquals(
                    count.intValue(),
                    1,
                    "Adult count mismatch across rooms after hitting minimum"
            );
        }
    }
}