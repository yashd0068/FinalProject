package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.HotelsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_19_VerifyAdultDoesNotGoBelowOne extends BaseClass {

    @Test
    public void verifyAdultDoesNotGoBelowOne() {
        HotelsPage hotels = new HotelsPage(driver);
        hotels.openHotels();
        hotels.openAdultDropdown();

        hotels.decrementAdult(10);

        Assert.assertTrue(hotels.getAdultCount() >= 1,
                "Adult count went below one");
    }
}