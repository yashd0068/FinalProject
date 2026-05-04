package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.HotelsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_11_DecrementAdultByOne extends BaseClass {

    @Test
    public void decrementAdultByOne() {
        HotelsPage hotels = new HotelsPage(driver);
        hotels.openHotels();
        hotels.openAdultDropdown();

        int before = hotels.getAdultCount();
        hotels.decrementAdult(1);
        int after = hotels.getAdultCount();

        Assert.assertEquals(after, before - 1);
    }
}