package org.easemytrip.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ================== CONSTRUCTOR ==================
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // ================== LOCATORS ==================

    private By loginHoverBtn = By.cssSelector("a._btnclick");
    private By customerLogin = By.xpath("//span[@id='shwlogn']/ancestor::a");
    private By loginInput = By.id("txtEmail");
    private By continueBtn = By.id("shwotp");

    /*
     * Error containers (Angular toggles display:none)
     */
    private By anyVisibleError = By.xpath(
            "//*[@id='RegValidPhone' and not(contains(@style,'display: none'))] | " +
                    "//*[@id='RegValidEmail' and not(contains(@style,'display: none'))] | " +
                    "//*[@id='RegValidEmPh'  and not(contains(@style,'display: none'))]"
    );

    // ================== PAGE ACTIONS ==================

    /**
     * Opens login popup by hovering and clicking customer login
     */
    public void openLoginPopup() {

        WebElement loginBtn =
                wait.until(ExpectedConditions.visibilityOfElementLocated(loginHoverBtn));

        new Actions(driver).moveToElement(loginBtn).perform();

        wait.until(ExpectedConditions.elementToBeClickable(customerLogin)).click();
    }

    /**
     * Enters value into Email / Phone input
     */
    public void enterValue(String value) {

        WebElement input =
                wait.until(ExpectedConditions.visibilityOfElementLocated(loginInput));

        input.clear();
        input.sendKeys(value);
    }

    /**
     * Clicks on Continue button (handles Angular disabled state)
     */
    public void clickContinue() {

        WebElement btn = driver.findElement(continueBtn);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].removeAttribute('disabled');", btn);

        btn.click();
    }

    /**
     * Returns the currently visible validation error
     * (Phone / Email / Email-or-Phone)
     */
    public String getErrorMessage() {

        try {
            WebElement error =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(anyVisibleError));

            return error.getText().trim();

        } catch (TimeoutException e) {
            return "Not displayed";
        }
    }

    /**
     * Clears error messages using JS
     * (Prevents Angular wait hang in loops)
     */
    public void clearExistingErrors() {

        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('._err_log')" +
                        ".forEach(el => el.style.display='none');"
        );
    }
}
