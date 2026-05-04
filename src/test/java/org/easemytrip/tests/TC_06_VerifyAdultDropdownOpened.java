package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.HotelsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_06_VerifyAdultDropdownOpened extends BaseClass {

    @Test
    public void verifyAdultDropdownOpened() {
        HotelsPage hotels = new HotelsPage(driver);
        hotels.openHotels();
        hotels.openAdultDropdown();

        Assert.assertTrue(hotels.getAdultCount() >= 1,
                "Adult dropdown did not open properly");
    }
}