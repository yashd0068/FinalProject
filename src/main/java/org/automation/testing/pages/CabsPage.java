package org.automation.testing.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CabsPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "label[for='rdbTravelTypeOther']")
    private WebElement outstation;

    @FindBy(id = "sourceName")
    private WebElement fromContainer;

    @FindBy(id = "a_FromSector_show")
    private WebElement fromInput;

    @FindBy(id = "a_ToSector_show")
    private WebElement toInput;

    @FindBy(css = ".srch-btn-c")
    private WebElement searchBtn;

    @FindBy(xpath = "//span[normalize-space()='suv']")
    private WebElement suvFilter;

    @FindBy(xpath = "//span[normalize-space()='suv_luxury']")
    private WebElement suvLuxuryFilter;

    public CabsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        PageFactory.initElements(driver, this);
    }

    public void selectOutstation() {
        wait.until(ExpectedConditions.elementToBeClickable(outstation)).click();
    }

    public void selectFromCity(String city) {

        wait.until(ExpectedConditions.elementToBeClickable(fromContainer)).click();
        wait.until(ExpectedConditions.elementToBeClickable(fromInput)).clear();
        fromInput.sendKeys(city);
        waitForAutoSuggestToLoad();
        selectCityFromAutoSuggest(city);
    }

    public void selectToCity(String city) {

        wait.until(ExpectedConditions.elementToBeClickable(toInput)).clear();
        toInput.sendKeys(city);
        waitForAutoSuggestToLoad();
        selectCityFromAutoSuggest(city);
    }

    private void waitForAutoSuggestToLoad() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'auto_sugg')]")
        ));
    }

    private void selectCityFromAutoSuggest(String city) {

        By cityOption = By.xpath(
                "//div[contains(@class,'auto_sugg_tttl') and normalize-space()='"
                        + city + "']"
        );

        int attempts = 0;

        while (attempts < 3) {
            try {
                WebElement element = wait.until(
                        ExpectedConditions.refreshed(
                                ExpectedConditions.elementToBeClickable(cityOption)
                        )
                );
                element.click();
                return;

            } catch (StaleElementReferenceException | TimeoutException e) {
                attempts++;
            }
        }

        throw new RuntimeException("Failed to select city from auto-suggest: " + city);
    }

    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
    }

    public void applySUVFilters() {

        wait.until(ExpectedConditions.elementToBeClickable(suvFilter)).click();
        wait.until(ExpectedConditions.elementToBeClickable(suvLuxuryFilter)).click();
    }
}