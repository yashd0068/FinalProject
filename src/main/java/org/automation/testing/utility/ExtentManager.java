package org.automation.testing.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {
            ExtentSparkReporter spark =
                    new ExtentSparkReporter("test-output/ExtentReport.html");

            spark.config().setReportName("EaseMyTrip Automation Report");
            spark.config().setDocumentTitle("Selenium TestNG Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Project", "MajorProject_EaseMyTrip");
            extent.setSystemInfo("Tester", "Yash Dubey");
            extent.setSystemInfo("Browser", ConfigUtility.getProperty("browser"));
        }
        return extent;
    }
}
