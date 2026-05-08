package org.automation.testing.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HotelsPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "a[href*='hotels']")
    private WebElement hotelsTab;

    @FindBy(css = "i.down_arw_htl")
    private WebElement adultDropdown;

    @FindBy(xpath = "//span[contains(@id,'Adults_room')]")
    private WebElement adultCount;

    @FindBy(xpath = "//span[contains(@id,'Adults_room') and contains(@class,'PlusMinus_number')]")
    private List<WebElement> adultElements;

    public HotelsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void openHotels() {
        wait.until(ExpectedConditions.elementToBeClickable(hotelsTab));
        hotelsTab.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("i.down_arw_htl")
        ));
    }

    private void ensureAdultDropdownOpen() {
        wait.until(ExpectedConditions.elementToBeClickable(adultDropdown));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", adultDropdown);
    }

    public int getAdultCount() {

        ensureAdultDropdownOpen();

        return wait.until(driver -> {
            String text = adultCount.getText().trim();

            if (text.isEmpty()) {
                text = adultCount.getAttribute("textContent").trim();
            }

            if (text.matches("\\d+")) {
                return Integer.parseInt(text);
            }
            return null;
        });
    }

    public List<Integer> getAllAdultCounts() {

        ensureAdultDropdownOpen();

        List<Integer> values = new ArrayList<>();

        for (WebElement el : adultElements) {

            String text = el.getText().trim();

            if (text.matches("\\d+")) {
                values.add(Integer.parseInt(text));
            }
        }

        return values;
    }

    public void incrementAdult(int times) {
        By plusLocator =
                By.xpath("//a[contains(@id,'Adults_room') and contains(@id,'plus')]");

        ensureAdultDropdownOpen();

        for (int i = 0; i < times; i++) {
            WebElement plusBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(plusLocator)
            );

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", plusBtn);
        }
    }

    public void decrementAdult(int times) {
        By minusLocator =
                By.xpath("//a[contains(@id,'Adults_room') and contains(@id,'minus')]");

        ensureAdultDropdownOpen();

        for (int i = 0; i < times; i++) {
            WebElement minusBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(minusLocator)
            );

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", minusBtn);
        }
    }
}