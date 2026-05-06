package org.easemytrip.tests;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.pages.MonumentsPage;
import org.automation.testing.utility.LogUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TC_25_DuplicateVisitorTypeValidationTest extends BaseClass {

    @Test
    public void validateNoDuplicateVisitorTypes() {

        LogUtil.info("Starting validation for duplicate visitor types in Tickets section");

        MonumentsPage monumentsPage = new MonumentsPage(driver, wait);

        monumentsPage.openMonumentsPage();
        monumentsPage.searchMonument("taj");
        monumentsPage.openTicketsTab();

        int ticketBoxCount = monumentsPage.getDisplayedTicketCount();

        List<String> visitorTypeList = monumentsPage.getVisitorTypes();

        Set<String> uniqueVisitorTypes = new HashSet<>(visitorTypeList);

        LogUtil.info("Total ticket boxes displayed: " + ticketBoxCount);
        LogUtil.info("Visitor types found (List): " + visitorTypeList);
        LogUtil.info("Unique visitor types found (Set): " + uniqueVisitorTypes);

        Assert.assertEquals(
                uniqueVisitorTypes.size(),
                ticketBoxCount,
                "Duplicate visitor types found in Tickets section"
        );

        LogUtil.info("Duplicate visitor type validation completed successfully");
    }
}