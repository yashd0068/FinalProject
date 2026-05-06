package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.MonumentsPage;
import org.automation.testing.utility.LogUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_23_MonumentSearchAndTicketsFlowTest extends BaseClass {

    @Test
    public void validateMonumentSearchAndTicketsFlow() {

        LogUtil.info("Starting monument search and tickets flow validation");

        MonumentsPage monumentsPage = new MonumentsPage(driver, wait);

        monumentsPage.openMonumentsPage();

        LogUtil.info("Searching for Taj Mahal using auto-suggest");
        monumentsPage.searchMonument("taj");

        LogUtil.info("Validating monument page loaded correctly");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("taj")
                        || driver.getPageSource().contains("Taj"),
                "Monument details not found after search");

        LogUtil.info("Opening Tickets tab");
        monumentsPage.openTicketsTab();

        int ticketCountFirst = monumentsPage.getDisplayedTicketCount();
        LogUtil.info("Ticket count after first load: " + ticketCountFirst);

        Assert.assertTrue(ticketCountFirst > 0,
                "Ticket data not loaded");

        LogUtil.info("Re-clicking Tickets tab to validate stability");
        monumentsPage.openTicketsTab();

        int ticketCountSecond = monumentsPage.getDisplayedTicketCount();
        LogUtil.info("Ticket count after second load: " + ticketCountSecond);

        Assert.assertEquals(ticketCountSecond, ticketCountFirst,
                "Ticket data changed on repeated Tickets tab click");

        LogUtil.info("Monument search and tickets flow validated successfully");
    }
}
