package org.automation.testing.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

    @FindBy(xpath = "//a[contains(@id,'Adults_room') and contains(@id,'plus')]")
    private WebElement plusBtn;

    @FindBy(xpath = "//a[contains(@id,'Adults_room') and contains(@id,'minus')]")
    private WebElement minusBtn;

    @FindBy(xpath = "//span[contains(@id,'Adults_room') and contains(@class,'PlusMinus_number')]")
    private List<WebElement> adultElements;

    public HotelsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public void openHotels() {
        hotelsTab.click();
    }

    public void openAdultDropdown() {
        adultDropdown.click();
    }

    public int getAdultCount() {
        return Integer.parseInt(adultCount.getText());
    }

    public List<Integer> getAllAdultCounts() {
        List<Integer> values = new ArrayList<>();
        for (WebElement el : adultElements) {
            values.add(Integer.parseInt(el.getText()));
        }
        return values;
    }

    public void incrementAdult(int times) {
        for (int i = 0; i < times; i++) {
            plusBtn.click();
        }
    }

    public void decrementAdult(int times) {
        for (int i = 0; i < times; i++) {
            minusBtn.click();
        }
    }
}