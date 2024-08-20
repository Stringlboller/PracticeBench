package org.example.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.components.ProductListItemComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class ProductSearchListPage {

    private static final Logger LOGGER = LogManager.getLogger(ProductSearchListPage.class);

    private final WebDriver driver;

    @FindBy(xpath = "//div[@class='poly-card poly-card--list']")
    private List<WebElement> productListElements;

    public ProductSearchListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private List<ProductListItemComponent> getProductListItems() {
        List<ProductListItemComponent> productListItems = new ArrayList<>();
        for (WebElement element : productListElements) {
            productListItems.add(new ProductListItemComponent(driver, element));
        }
        return productListItems;
    }

    public ProductListItemComponent getProductListItemComponent(int index) {
        return getProductListItems().get(index);
    }
}
