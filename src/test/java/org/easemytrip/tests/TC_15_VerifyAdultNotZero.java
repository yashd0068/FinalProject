package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.HotelsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_15_VerifyAdultNotZero extends BaseClass {

    @Test
    public void verifyAdultNotZero() {
        HotelsPage hotels = new HotelsPage(driver);
        hotels.openHotels();
        hotels.openAdultDropdown();

        Assert.assertTrue(hotels.getAdultCount() > 0,
                "Adult count is ZERO");
    }
}
