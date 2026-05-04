package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.MonumentsPage;
import org.automation.testing.utility.LogUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class TC_25_DuplicateVisitorTypeValidationTest extends BaseClass {

    @Test
    public void validateNoDuplicateVisitorTypes() {

        LogUtil.log("Starting validation for duplicate visitor types in Tickets section");

        MonumentsPage monumentsPage = new MonumentsPage(driver, wait);

        monumentsPage.openMonumentsPage();

        monumentsPage.searchMonument("taj");

        monumentsPage.openTicketsTab();

        int ticketBoxCount = monumentsPage.getDisplayedTicketCount();
        Set<String> visitorTypes = monumentsPage.getVisitorTypes();

        LogUtil.log("Total ticket boxes displayed: " + ticketBoxCount);
        LogUtil.log("Unique visitor types found: " + visitorTypes.size());

        Assert.assertEquals(visitorTypes.size(), ticketBoxCount,
                "Duplicate visitor types found in Tickets section");

        LogUtil.log("Duplicate visitor type validation completed successfully");
    }
}
