package org.automation.testing.utility;

import org.openqa.selenium.*;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String testName) {
        try {
            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(
                    src,
                    new File("screenshots/" + testName + ".png")
            );
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}