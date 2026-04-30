package org.automation.testing.pages;

import org.automation.testing.utility.WindowSwitchUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(linkText = "Cabs")
    private WebElement cabsLink;
    @FindBy(xpath = "//span[text()='Gift Cards']")
    private WebElement giftCardLink;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    public void clickOnGiftCards() {
        wait.until(ExpectedConditions.elementToBeClickable(giftCardLink)).click();
        WindowSwitchUtil.switchToNewWindow(driver);
    }
    public void clickOnCabs() {
        wait.until(ExpectedConditions.elementToBeClickable(cabsLink)).click();
    }
}