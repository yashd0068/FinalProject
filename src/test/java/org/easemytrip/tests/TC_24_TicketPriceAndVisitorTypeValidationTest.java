package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.MonumentsPage;
import org.automation.testing.utility.LogUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TC_24_TicketPriceAndVisitorTypeValidationTest extends BaseClass {

    @Test
    public void validateVisitorTypesAndPrices() {

        LogUtil.info("Starting validation of visitor types and ticket prices");

        MonumentsPage monumentsPage = new MonumentsPage(driver, wait);

        monumentsPage.openMonumentsPage();
        monumentsPage.searchMonument("taj");
        monumentsPage.openTicketsTab();

        int ticketCount = monumentsPage.getDisplayedTicketCount();
        List<String> visitorTypes = monumentsPage.getVisitorTypes();
        List<String> prices = monumentsPage.getTicketPrices();

        LogUtil.info("Ticket boxes displayed: " + ticketCount);
        LogUtil.info("Visitor types found: " + visitorTypes);
        LogUtil.info("Prices found: " + prices);

        Assert.assertTrue(ticketCount > 0,
                "No ticket boxes displayed");

        Assert.assertEquals(visitorTypes.size(), ticketCount,
                "Visitor type count mismatch");

        Assert.assertEquals(prices.size(), ticketCount,
                "Ticket price count mismatch");

        for (String price : prices) {
            Assert.assertTrue(price.matches("\\d+"),
                    "Invalid ticket price detected: " + price);
        }

        Set<String> uniquePrices = new HashSet<>(prices);
        Assert.assertTrue(uniquePrices.contains("35"),
                "Indian ticket price missing");
        Assert.assertTrue(uniquePrices.contains("550"),
                "Foreigner ticket price missing");

        LogUtil.info("Visitor type and ticket price validation completed successfully");
    }
}