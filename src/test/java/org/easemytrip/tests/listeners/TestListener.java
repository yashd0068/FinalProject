package org.easemytrip.tests.listeners;

import org.automation.testing.baseclass.BaseClass;
import org.automation.testing.utility.ExtentManager;
import org.automation.testing.utility.ExtentTestManager;
import org.automation.testing.utility.ScreenshotUtil;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BaseClass implements ITestListener {

    ExtentReports extent = ExtentManager.getInstance();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.setTest(
                extent.createTest(result.getMethod().getMethodName())
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest()
                .log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest()
                .log(Status.FAIL, result.getThrowable());

        String screenshotPath =
                ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());

        ExtentTestManager.getTest()
                .addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest()
                .log(Status.SKIP, "Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
