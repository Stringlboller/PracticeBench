package org.example.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.actions.MLActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends MLActions{

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);


    @FindBy(xpath = "//a[@class='nav-logo']")
    private WebElement navLogo;

    @FindBy(id = "cb1-edit")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@class='nav-search-btn']")
    private WebElement searchBtn;

    @FindBy(xpath = "//a[@class='nav-menu-categories-link']")
    private WebElement categoriesDropdown;

    @FindBy(xpath = "//ul[@class='nav-categs-departments']/li")
    private List<WebElement> categoriesList;

    @FindBy(css = "[data-link-id='purchases']")
    private WebElement myPurchasesSection;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        waitUntilElementAppear(navLogo, 3);
        return navLogo.isDisplayed();
    }

    public void typeSearchBar(String searchText) {
        type(searchInput, searchText);
    }

    public ProductSearchListPage clickSearchBtn() {
        clickly(searchBtn);
        return new ProductSearchListPage(getMLActionsDriver());
    }

    public void clickCategoriesDropdown() {
        Actions actions = new Actions(getMLActionsDriver());
        actions.moveToElement(categoriesDropdown).perform();
    }

    public CategoriesPage clickCategoryByIndex(int index) {
        clickly(categoriesList.get(index));
        return new CategoriesPage(getMLActionsDriver());
    }

    public PleasCreateAccountPage clickMyPurchasesSectionLoggedOut() {
        clickly(myPurchasesSection);
        return new PleasCreateAccountPage(getMLActionsDriver());
    }
}
