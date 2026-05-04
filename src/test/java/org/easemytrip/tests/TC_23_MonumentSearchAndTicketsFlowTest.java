package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.MonumentsPage;
import org.automation.testing.utility.LogUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_23_MonumentSearchAndTicketsFlowTest extends BaseClass {

    @Test
    public void validateMonumentSearchAndTicketsFlow() {

        LogUtil.log("Starting monument search and tickets flow validation");

        // ✅ Correct constructor usage
        MonumentsPage monumentsPage = new MonumentsPage(driver, wait);

        // Step 1: Navigate to Monuments page
        monumentsPage.openMonumentsPage();

        // Step 2: Search for monument using auto-suggest
        LogUtil.log("Searching for Taj Mahal using auto-suggest");
        monumentsPage.searchMonument("taj");

        // Step 3: Validate monument page loaded
        LogUtil.log("Validating monument page loaded correctly");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("taj")
                        || driver.getPageSource().contains("Taj"),
                "Monument details not found after search");

        // Step 4: Open Tickets tab
        LogUtil.log("Opening Tickets tab");
        monumentsPage.openTicketsTab();

        int ticketCountFirst = monumentsPage.getDisplayedTicketCount();
        LogUtil.log("Ticket count after first load: " + ticketCountFirst);

        Assert.assertTrue(ticketCountFirst > 0,
                "Ticket data not loaded");

        // Step 5: Re-open Tickets tab to check stability
        LogUtil.log("Re-clicking Tickets tab to validate stability");
        monumentsPage.openTicketsTab();

        int ticketCountSecond = monumentsPage.getDisplayedTicketCount();
        LogUtil.log("Ticket count after second load: " + ticketCountSecond);

        Assert.assertEquals(ticketCountSecond, ticketCountFirst,
                "Ticket data changed on repeated Tickets tab click");

        LogUtil.log("Monument search and tickets flow validated successfully");
    }
}
