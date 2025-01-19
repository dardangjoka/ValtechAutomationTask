package com.valtech.pages;

import com.valtech.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class Broker {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public Broker() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "//h6")
    public List<WebElement> names;

    @FindBy(xpath = "//label[contains(text(), 'Key word')]/following-sibling::div/input")
    public WebElement keyWordInput;

    public WebElement getKeyWordInput() {
        return Driver.get().findElement(By.xpath("//label[contains(text(), 'Key word')]"));
    }

    public WebElement getBrokerName(){
        return Driver.get().findElement(By.xpath("//div[contains(@class, 'MuiCard-root')]//h6[contains(text(),'"+name+"')]"));
    }

    public WebElement getDetailsButtonByName(){
        return Driver.get().findElement(By.xpath("//div[contains(@class, 'MuiCard-root')]//h6[contains(text(),'"+name+"')]/../../div/button"));
    }

    public WebElement getPropertiesByName(){
        return Driver.get().findElement(By.xpath("//div[contains(@class, 'MuiCard-root')]//h6[contains(text(),'"+name+"')]/../following-sibling::a[contains(text(), properties)]"));
    }

    public void clearInput() throws InterruptedException {
        keyWordInput.clear();
        Thread.sleep(1000);
        Driver.get().findElement(By.xpath("//button[contains(text(), 'Clear')]")).click();
        Thread.sleep(1000);
    }


    public List<WebElement> getAllNames() throws InterruptedException {
        List<WebElement> elements = names;
        Actions actions = new Actions(Driver.get());

        while (elements.size()< 134){

            Thread.sleep(10);
            actions.scrollByAmount(0, 100).perform();
            elements = Driver.get().findElements(By.xpath("//h6"));
        }

        elements.forEach(c-> System.out.println(c.getText()));
        return elements;
    }
}
