package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.MonumentsPage;
import org.automation.testing.utility.LogUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class TC_24_TicketPriceAndVisitorTypeValidationTest extends BaseClass {

    @Test
    public void validateVisitorTypesAndPrices() {

        LogUtil.log("Starting validation of visitor types and ticket prices");

        MonumentsPage monumentsPage = new MonumentsPage(driver, wait);

        monumentsPage.openMonumentsPage();

        monumentsPage.searchMonument("taj");

        monumentsPage.openTicketsTab();

        int ticketCount = monumentsPage.getDisplayedTicketCount();
        Set<String> visitorTypes = monumentsPage.getVisitorTypes();
        Set<String> prices = monumentsPage.getTicketPrices();

        LogUtil.log("Ticket boxes displayed: " + ticketCount);
        LogUtil.log("Visitor types found: " + visitorTypes);
        LogUtil.log("Prices found: " + prices);

        // Step 5: Validations
        Assert.assertTrue(ticketCount > 0,
                "No ticket boxes displayed");

        Assert.assertEquals(visitorTypes.size(), ticketCount,
                "Visitor type count mismatch with ticket count");

        Assert.assertEquals(prices.size(), ticketCount,
                "Ticket price count mismatch with ticket count");

        for (String price : prices) {
            Assert.assertTrue(price.matches("\\d+"),
                    "Invalid (non-numeric) ticket price found: " + price);
        }

        LogUtil.log("Visitor type and ticket price validation completed successfully");
    }
}
