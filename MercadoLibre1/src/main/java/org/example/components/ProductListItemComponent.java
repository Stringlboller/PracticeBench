package org.example.components;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.actions.MLActions;
import org.example.pages.ProductDetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductListItemComponent extends MLActions {

    private static final Logger LOGGER = LogManager.getLogger(ProductListItemComponent.class);

    private WebElement rootElement;

    public ProductListItemComponent(WebDriver driver, WebElement rootElement) {
        super(driver);
        this.rootElement = rootElement;
    }

    public String getProductTitle() {
        WebElement titleElement = rootElement.findElement(By.xpath(".//h2"));
        return titleElement.getText().toLowerCase().replaceAll("\\s+", "");
    }

    public ProductDetailsPage clickProductByIndex(int index) {
        WebElement titleElement = rootElement.findElement(By.xpath(".//h2"));
        waitUntilElementAppear(titleElement, 3);
        clickByJs(titleElement);
        return new ProductDetailsPage(getMLActionsDriver());
    }
}
