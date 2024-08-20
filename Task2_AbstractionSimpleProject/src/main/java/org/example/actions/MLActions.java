package org.example.actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class MLActions {

    private static final Logger LOGGER = org.apache.log4j.LogManager.getLogger(MLActions.class);

    private final WebDriver driver;
    private final WebDriverWait wait;

    public MLActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void waitUntilElementAppear(WebElement webElement, int timeOut){
        new WebDriverWait(driver, Duration.ofSeconds(timeOut)).until(ExpectedConditions.visibilityOf(webElement));
    }

    public WebDriver getMLActionsDriver() {
        return driver;
    }

    public void clickly(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            LOGGER.info("Clicked on element: " + element.toString());
        } catch (Exception exe) {
            LOGGER.error("Failed to click on element: " + element, exe);
            throw exe;
        }
    }

    public void type(WebElement element, String text) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(text);
            LOGGER.info("Sent keys to element: " + element.toString() + " with text: " + text);
        } catch (Exception exe) {
            LOGGER.error("Failed to send keys to element: " + element.toString(), exe);
            throw exe;
        }
    }
}
