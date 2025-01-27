package com.valtech.entities;

import com.valtech.utils.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BrokerScalper {

    public static List<String> getAllNames() {
        List<String> names = new ArrayList<>();
        Actions actions = new Actions(Driver.get());
        WebElement element;
        Set<WebElement> uniqueElements = new HashSet<>();
        int currentSize = 1;

        do  {
            try {
                element = Driver.get().findElement(By.xpath("(//h6)"+"["+ currentSize +"]"));
                uniqueElements.add(element);
                actions.scrollToElement(element).perform();
                currentSize++;
            }catch (NoSuchElementException e){
                break;
            }
        }while(true);

        uniqueElements.forEach(c -> {
            System.out.println(c.getText());
            names.add(c.getText());
        });

        return names;
    }
}
