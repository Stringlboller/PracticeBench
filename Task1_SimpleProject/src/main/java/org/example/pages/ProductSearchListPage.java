package org.example.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductSearchListPage {

    private static final Logger LOGGER = LogManager.getLogger(ProductSearchListPage.class);

    WebDriver driver;

    @FindBy(xpath = "//a/h2")
    private List<WebElement> productListTitles;

    public ProductSearchListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductTitlesList() {
        return productListTitles.get(1).getText().toLowerCase().replaceAll("\\s+", "");
    }
}
