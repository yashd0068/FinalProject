package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.HotelsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_16_IncrementAdultByOne extends BaseClass {

//    @Test
//    public void incrementAdultByOneAndValidateState() {
//
//        HotelsPage hotels = new HotelsPage(driver);
//
//        hotels.openHotels();
//        hotels.openAdultDropdown();
//
//        int initialCount = hotels.getAdultCount();
//        Assert.assertTrue(initialCount >= 1,
//                "Initial adult count must be valid");
//
//        hotels.incrementAdult(1);
//
//        int updatedCount = hotels.getAdultCount();
//        Assert.assertEquals(updatedCount, initialCount + 1,
//                "Adult count did not increment by one");
//
//        hotels.incrementAdult(1);
//
//        int secondUpdate = hotels.getAdultCount();
//        Assert.assertEquals(secondUpdate, updatedCount + 1,
//                "Second increment by one failed");
//
//        List<Integer> roomCounts = hotels.getAllAdultCounts();
//        for (Integer count : roomCounts) {
//            Assert.assertEquals(count.intValue(), secondUpdate,
//                    "Adult count mismatch across rooms");
//        }
@Test
public void incrementAdultByOneAndValidateState() {

    HotelsPage hotels = new HotelsPage(driver);

    hotels.openHotels();

    int initialCount = hotels.getAdultCount();
    Assert.assertTrue(initialCount >= 1,
            "Initial adult count must be valid");

    hotels.incrementAdult(1);

    int updatedCount = hotels.getAdultCount();
    Assert.assertEquals(updatedCount, initialCount + 1,
            "Adult count did not increment by one");

    hotels.incrementAdult(1);

    int secondUpdate = hotels.getAdultCount();
    Assert.assertEquals(secondUpdate, updatedCount + 1,
            "Second increment by one failed");

    List<Integer> roomCounts = hotels.getAllAdultCounts();
    for (Integer count : roomCounts) {
        Assert.assertEquals(count.intValue(), secondUpdate,
                "Adult count mismatch across rooms");
    }
}
}