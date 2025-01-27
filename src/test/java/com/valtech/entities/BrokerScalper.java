package com.valtech.entities;

import com.valtech.utils.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The {@code BrokerScalper} class is a utility designed to extract unique names
 * from a webpage using Selenium WebDriver. It retrieves the text from elements
 * matching a specific XPath and ensures uniqueness by utilizing a {@code Set}.
 */
public class BrokerScalper {
    /**
     * Retrieves all unique names from elements on a webpage.
     * <p>
     * This method uses Selenium WebDriver to locate elements on a page
     * based on their XPath. It scrolls through the elements using
     * {@link Actions#scrollToElement(WebElement)} and adds the text of
     * each unique element to a list.
     * </p>
     *
     * @return A {@link List} of unique names retrieved from the webpage.
     * If no elements are found, an empty list is returned.
     */
    public static List<String> getAllNames() {
        List<String> names = new ArrayList<>();
        Actions actions = new Actions(Driver.get());
        WebElement element;
        Set<WebElement> uniqueElements = new HashSet<>();
        int currentSize = 1;

        do {
            try {
                element = Driver.get().findElement(By.xpath("(//h6)" + "[" + currentSize + "]"));
                uniqueElements.add(element);
                actions.scrollToElement(element).perform();
                currentSize++;
            } catch (NoSuchElementException e) {
                break;
            }
        } while (true);

        uniqueElements.forEach(c -> {
            System.out.println(c.getText());
            names.add(c.getText());
        });

        return names;
    }
}
