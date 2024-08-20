package org.example.components;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductListItemComponent {

    private static final Logger LOGGER = LogManager.getLogger(ProductListItemComponent.class);

    private WebDriver driver;
    private WebElement rootElement;

    public ProductListItemComponent(WebDriver driver, WebElement rootElement) {
        this.driver = driver;
        this.rootElement = rootElement;
    }

    public String getProductTitle() {
        WebElement titleElement = rootElement.findElement(By.xpath(".//h2"));
        return titleElement.getText().toLowerCase().replaceAll("\\s+", "");
    }
}
