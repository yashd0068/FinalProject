package org.automation.testing.utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver,
                                      String testName,
                                      String testStatus) {
        try {
            String timeStamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss")
                            .format(new Date());

            String baseDir = System.getProperty("user.dir")
                    + File.separator + "screenshots"
                    + File.separator + testStatus.toLowerCase();

            File directory = new File(baseDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            File dest = new File(
                    baseDir + File.separator
                            + testName + "_" + timeStamp + ".png");

            FileUtils.copyFile(src, dest);

        } catch (Exception e) {
            LogUtil.log("Screenshot failed: " + e.getMessage());
        }
    }

    public static String captureScreenshot(WebDriver driver,
                                           String testName) {

        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String screenshotPath =
                System.getProperty("user.dir")
                        + "/screenshots/extent/"
                        + testName + "_" + timeStamp + ".png";

        try {
            File directory =
                    new File(System.getProperty("user.dir")
                            + "/screenshots/extent/");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(src, new File(screenshotPath));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }
}