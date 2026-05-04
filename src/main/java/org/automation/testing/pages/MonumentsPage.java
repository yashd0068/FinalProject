package org.automation.testing.pages;

import org.automation.testing.utility.LogUtil;
import org.automation.testing.utility.WindowSwitchUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MonumentsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public MonumentsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        LogUtil.log("MonumentsPage initialized");
    }

    /* ===================== Locators ===================== */

    @FindBy(xpath = "//span[text()='More']")
    private WebElement moreMenu;

    @FindBy(xpath = "//a[contains(@href,'monuments')]")
    private WebElement monumentsLink;

    @FindBy(xpath = "//input[@placeholder='Search monuments...']")
    private WebElement searchBox;

    @FindBy(xpath = "//span[normalize-space()='Taj Mahal']")
    private WebElement tajMahalSuggestion;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//button[normalize-space()='Tickets']")
    private WebElement ticketsTab;

    @FindBy(xpath = "//div[contains(@class,'tctbx')]")
    private List<WebElement> ticketBoxes;

    /* ===================== Actions ===================== */

    public void openMonumentsPage() {

        LogUtil.log("Hovering on More menu");

        wait.until(ExpectedConditions.visibilityOf(moreMenu));
        new Actions(driver).moveToElement(moreMenu).perform();

        LogUtil.log("Clicking Monuments link");
        wait.until(ExpectedConditions.elementToBeClickable(monumentsLink)).click();

        LogUtil.log("Switching to Monuments window");
        WindowSwitchUtil.switchToNewWindow(driver);
    }

    public void searchMonument(String monumentName) {

        LogUtil.log("Typing monument name: " + monumentName);

        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.clear();
        searchBox.sendKeys(monumentName);

        LogUtil.log("Selecting Taj Mahal from auto-suggest");
        wait.until(ExpectedConditions.elementToBeClickable(tajMahalSuggestion)).click();

        LogUtil.log("Clicking Search button");
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void openTicketsTab() {
        LogUtil.log("Opening Tickets tab");
        wait.until(ExpectedConditions.elementToBeClickable(ticketsTab)).click();
    }

    /* ===================== Data Getters ===================== */

    public int getDisplayedTicketCount() {
        return ticketBoxes.size();
    }

    public Set<String> getVisitorTypes() {

        Set<String> visitorTypes = new HashSet<>();
        LogUtil.log("Fetching visitor types");

        for (WebElement box : ticketBoxes) {
            String type = box.findElement(
                    By.xpath(".//div[contains(@class,'tbttl')]")
            ).getText().trim();
            visitorTypes.add(type);
        }
        return visitorTypes;
    }

    public Set<String> getTicketPrices() {

        Set<String> prices = new HashSet<>();
        LogUtil.log("Fetching ticket prices");

        for (WebElement box : ticketBoxes) {
            String price = box.findElement(
                    By.xpath(".//div[contains(@class,'tctprc')]//span")
            ).getText().trim();
            prices.add(price);
        }
        return prices;
    }
}
