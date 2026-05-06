package org.automation.testing.pages;

import org.automation.testing.utility.JavaScriptUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GiftCardPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "poplr_gft")
    private WebElement giftCardSection;

    @FindBy(xpath = "//div[@id='All']/div/div/div[2]/div")
    private List<WebElement> giftCardNames;

    @FindBy(xpath = "//div[@id='All']/div/div[2]/div")
    private WebElement weddingGift;

    public GiftCardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public int getGiftCardCount() {
        wait.until(ExpectedConditions.visibilityOf(giftCardSection));
        JavaScriptUtil.scrollIntoViewCenter(driver, giftCardSection);
        return giftCardNames.size();
    }

    public void printGiftCardNames() {
        for (WebElement card : giftCardNames) {
            String name = card.getText().trim();
            if (!name.isEmpty()) {
                System.out.println(name);
            }
        }
    }

    public void clickWeddingGift() {
        JavaScriptUtil.scrollIntoViewCenter(driver, weddingGift);
        wait.until(ExpectedConditions.elementToBeClickable(weddingGift)).click();
    }
}