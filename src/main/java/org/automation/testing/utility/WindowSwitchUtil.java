package org.automation.testing.utility;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WindowSwitchUtil {

    public static void switchToNewWindow(WebDriver driver) {

        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }
}