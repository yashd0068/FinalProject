package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.HotelsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_14_ExtractAdultCounts extends BaseClass {

    @Test
    public void extractAndValidateAdultCounts() {

        HotelsPage hotels = new HotelsPage(driver);

        hotels.openHotels();
        hotels.openAdultDropdown();

        List<Integer> adultCounts = hotels.getAllAdultCounts();

        Assert.assertFalse(adultCounts.isEmpty(),
                "Adult count list should not be empty");

        for (Integer count : adultCounts) {
            Assert.assertTrue(count > 0,
                    "Adult count should always be greater than zero");
        }

        int primaryCount = hotels.getAdultCount();
        for (Integer count : adultCounts) {
            Assert.assertEquals(count.intValue(), primaryCount,
                    "Adult count mismatch between primary and room values");
        }
    }
}