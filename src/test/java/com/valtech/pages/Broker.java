package com.valtech.pages;

import com.valtech.utils.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

public class Broker {
    WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(4));

    public Broker() {
        PageFactory.initElements(Driver.get(), this);
    }

    private String contextName;

    @FindBy(xpath = "//h6")
    public WebElement name;

    @FindBy(xpath = "//input[@id='broker-keyword']")
    public WebElement keyWordInput;

    @FindBy(xpath = "//button[contains(text(), 'Clear')]")
    public WebElement clearButton;

    @FindBy(xpath = "//a[contains(text(), 'properties')]")
    public WebElement propertiesButton;

    @FindBy(xpath = "//button[contains(text(), 'Details')]")
    public WebElement detailsButton;

    @FindBy(xpath = "//div[contains(@class, 'MuiCardContent-root')]//div/span")
    public WebElement addressText;

    @FindBy(xpath = "//div[contains(@class, 'MuiCardContent-root')]//div[contains(@class,'MuiStack-root')]//a")
    public List<WebElement> phoneNumbers;

    public int getPhoneNumberCount() {
        try {
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(phoneNumbers)));
            return phoneNumbers.size();
        }catch(Exception e) {
            retryClick(detailsButton);
        }
        return 0;
    }

    public String getAddress() {
        try {
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(addressText)));
            return addressText.getText();
        }catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e){
            retryClick(detailsButton);
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(addressText)));
            return addressText.getText();
        }
    }

    public void isOnlyOne() {
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//h6"), 1));
    }

    public void setContextName(String contextName) {
        this.contextName = contextName;
    }

    public void inputName(String name) {
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(keyWordInput)));
        wait.until(ExpectedConditions.elementToBeClickable(keyWordInput));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(keyWordInput)));
        keyWordInput.sendKeys(name);
    }

    public void clickDetailsButton() {
        wait.until(ExpectedConditions.visibilityOf(detailsButton));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(detailsButton)));
        wait.until(ExpectedConditions.elementToBeClickable(detailsButton));
        retryClick(detailsButton);
    }

    public boolean isProperitesVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(propertiesButton));
            return true;
        } catch (NoSuchElementException | StaleElementReferenceException el) {
            return false;
        }
    }

    public void clearInput() {
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(clearButton)));
        clearButton.click();
    }

    private static void retryClick(WebElement element) {
        int attempts = 0;
        while (attempts < 5) {
            try {
                WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(4));
                wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
                element.click();
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
    }
}
