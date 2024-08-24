package org.example.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.actions.MLActions;
import org.example.components.ProductListItemComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductSearchListPage extends MLActions {

    private static final Logger LOGGER = LogManager.getLogger(ProductSearchListPage.class);

    @FindBy(xpath = "(//label/input[@class='andes-switch__input'])[1]")
    private WebElement arriveTomorrowFilter;

    @FindBy(xpath = "//div[@class='poly-card poly-card--list']")
    private List<WebElement> productListElements;

    @FindBy(xpath = "//a/h2")
    private List<WebElement> productListTitles;

    @FindBy(xpath = "//span[@class='ui-pb-highlight'] | //span[@class='poly-shipping--same_day']")
    private List<WebElement> arriveTodayTomorrowBanners;

    public ProductSearchListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private List<ProductListItemComponent> getProductListItems() {
        List<ProductListItemComponent> productListItems = new ArrayList<>();
        for (WebElement element : productListElements) {
            productListItems.add(new ProductListItemComponent(getMLActionsDriver(), element));
        }
        return productListItems;
    }

    public ProductListItemComponent getProductListItemComponent(int index) {
        return getProductListItems().get(index);
    }

    public void clickArriveTomorrowFilter() {
        clickByJs(arriveTomorrowFilter);
    }

    public int getProductListSize() {
        return productListTitles.size();
    }

    public int getArriveTomorrowListSize() {
        return arriveTodayTomorrowBanners.size();
    }
}
