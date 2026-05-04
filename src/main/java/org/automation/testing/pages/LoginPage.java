package org.automation.testing.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a._btnclick")
    private WebElement loginHoverBtn;

    @FindBy(xpath = "//span[@id='shwlogn']/ancestor::a")
    private WebElement customerLogin;

    @FindBy(id = "txtEmail")
    private WebElement loginInput;

    @FindBy(id = "shwotp")
    private WebElement continueBtn;

    @FindBy(xpath =
            "//*[@id='RegValidPhone' and not(contains(@style,'display: none'))] | " +
                    "//*[@id='RegValidEmail' and not(contains(@style,'display: none'))] | " +
                    "//*[@id='RegValidEmPh'  and not(contains(@style,'display: none'))]"
    )
    private WebElement anyVisibleError;

    public void openLoginPopup() {

        wait.until(ExpectedConditions.visibilityOf(loginHoverBtn));
        new Actions(driver).moveToElement(loginHoverBtn).perform();

        wait.until(ExpectedConditions.elementToBeClickable(customerLogin)).click();
    }

    public void enterValue(String value) {

        wait.until(ExpectedConditions.visibilityOf(loginInput));
        loginInput.clear();
        loginInput.sendKeys(value);
    }

    public void clickContinue() {

        wait.until(ExpectedConditions.visibilityOf(continueBtn));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].removeAttribute('disabled'); arguments[0].click();",
                continueBtn
        );
    }

    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(anyVisibleError));
            return anyVisibleError.getText().trim();
        } catch (TimeoutException e) {
            return "Not displayed";
        }
    }

    public void clearExistingErrors() {

        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('._err_log')" +
                        ".forEach(el => el.style.display='none');"
        );
    }
}