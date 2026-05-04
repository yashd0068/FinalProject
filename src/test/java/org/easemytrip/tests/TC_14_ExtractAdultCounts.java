package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.HotelsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_14_ExtractAdultCounts extends BaseClass {

    @Test
    public void extractAdultCounts() {
        HotelsPage hotels = new HotelsPage(driver);
        hotels.openHotels();
        hotels.openAdultDropdown();

        Assert.assertFalse(hotels.getAllAdultCounts().isEmpty(),
                "Adult count list is empty");
    }
}