package org.automation.testing.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ResultsPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//div[contains(@class,'cabFare')]")
    private List<WebElement> suvPrices;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        PageFactory.initElements(driver, this);
    }


    public int getCheapestSUVPrice() {

        // Wait until prices are visible
        wait.until(ExpectedConditions.visibilityOfAllElements(suvPrices));

        int lowest = Integer.MAX_VALUE;

        for (WebElement price : suvPrices) {

            int value = Integer.parseInt(
                    price.getText()
                            .replace("₹", "")
                            .replace(",", "")
                            .trim()
            );

            if (value < lowest) {
                lowest = value;
            }
        }
        return lowest;
    }
}