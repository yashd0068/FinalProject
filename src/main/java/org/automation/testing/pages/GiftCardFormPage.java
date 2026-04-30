package org.automation.testing.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class GiftCardFormPage {

    WebDriver driver;

    @FindBy(xpath = "//input[@ng-model='User.Amount']")
    private WebElement amount;

    @FindBy(xpath = "//select[@ng-model='User.Quantity']")
    private WebElement quantity;

    @FindBy(id = "rcteml")
    private WebElement retypeEmail;

    @FindBy(id = "pny")
    private WebElement payNow;

    @FindBy(xpath = "//p[contains(@class,'err_msg') and normalize-space()]")
    private List<WebElement> errorMessages;

    @FindBy(xpath = "//p[contains(text(),'OTP will be sent')]")
    private WebElement otpMsg;

    @FindBy(xpath = "//input[@ng-model='User.SenderName']")
    private WebElement senderName;

    @FindBy(id = "txtEmailId")
    private WebElement senderEmail;

    @FindBy(xpath = "//input[@ng-model='User.SenderMobile']")
    private WebElement senderMobile;

    @FindBy(id = "rcnm")
    private WebElement receiverName;

    @FindBy(xpath = "//input[@ng-model='User.ReceiverEmail']")
    private WebElement receiverEmail;

    @FindBy(xpath = "//span[contains(text(),'Payable Amount')]/following-sibling::span")
    private WebElement payableAmount;

    public GiftCardFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterAmount(String value) {
        amount.clear();
        amount.sendKeys(value);
        ((JavascriptExecutor) driver).executeScript("arguments[0].blur();", amount);
    }

    public void clearAmount() {
        amount.clear();
    }

    public void selectQuantity(String value) {
        new Select(quantity).selectByVisibleText(value);
    }

    public void enterSenderName(String name) {
        senderName.clear();
        senderName.sendKeys(name);
    }

    public void enterSenderEmail(String email) {
        senderEmail.clear();
        senderEmail.sendKeys(email);
        ((JavascriptExecutor) driver).executeScript("arguments[0].blur();", senderEmail);
    }

    public void enterSenderMobile(String mobile) {
        senderMobile.clear();
        senderMobile.sendKeys(mobile);
    }

    public void enterReceiverName(String name) {
        receiverName.clear();
        receiverName.sendKeys(name);
    }

    public void enterReceiverEmail(String email) {
        receiverEmail.clear();
        receiverEmail.sendKeys(email);
        ((JavascriptExecutor) driver).executeScript("arguments[0].blur();", receiverEmail);
    }

    public void enterReceiverRetypeEmail(String email) {
        retypeEmail.clear();
        retypeEmail.sendKeys(email);
    }

    public void clickPayNow() {
        payNow.click();
    }

    public int getPayableAmount() {
        String text = payableAmount.getText()
                .replace("₹", "")
                .replace(",", "")
                .trim();
        return Integer.parseInt(text);
    }

    public boolean isNameValidationErrorDisplayed() {
        return errorMessages.stream()
                .anyMatch(e -> e.getText().toLowerCase().contains("name"));
    }

    public boolean isMobileValidationErrorDisplayed() {
        return errorMessages.stream()
                .anyMatch(e -> e.getText().contains("10"));
    }

    public boolean isOtpMessageDisplayed() {
        return otpMsg.isDisplayed();
    }

    public boolean isAnyValidationErrorDisplayed() {
        boolean errorFound = false;
        for (WebElement err : errorMessages) {
            String message = err.getText().trim();
            if (!message.isEmpty() && err.isDisplayed()) {
                errorFound = true;
            }
        }
        return errorFound;
    }

    public boolean isSpecificErrorDisplayed(String expectedMessage) {
        for (WebElement err : errorMessages) {
            if (err.getText().trim().equalsIgnoreCase(expectedMessage)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAmountBoundaryErrorDisplayed() {
        for (WebElement err : errorMessages) {
            String message = err.getText().toLowerCase();
            if (message.contains("between") && message.contains("and")) {
                return true;
            }
        }
        return false;
    }

    public void fillMandatoryFieldsExceptEmail() {
        enterAmount("500");
        selectQuantity("1");
        senderName.clear();
        senderName.sendKeys("TestUser");
        senderMobile.clear();
        senderMobile.sendKeys("9876543210");
    }

    public boolean isEmailValidationErrorDisplayed() {
        for (WebElement err : errorMessages) {
            String msg = err.getText().toLowerCase();
            if (msg.contains("email") && msg.contains("valid")) {
                return true;
            }
        }
        return false;
    }

    public String getDisplayedFormMessage() {
        for (WebElement err : errorMessages) {
            if (err.isDisplayed() && !err.getText().trim().isEmpty()) {
                return err.getText().trim();
            }
        }
        if (otpMsg.isDisplayed()) {
            return otpMsg.getText().trim();
        }
        return "No message displayed";
    }
}