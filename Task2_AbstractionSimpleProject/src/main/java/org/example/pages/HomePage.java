package org.example.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.actions.MLActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends MLActions {

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);

    @FindBy(xpath = "//a[@class='nav-logo']")
    private WebElement navLogo;

    @FindBy(id = "cb1-edit")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@class='nav-search-btn']")
    private WebElement searchBtn;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        return navLogo.isDisplayed();
    }

    public void typeSearchBar(String searchText) {
        waitUntilElementAppear(searchInput, 3);
        type(searchInput, searchText);
    }

    public ProductSearchListPage clickSearchBtn() {
        waitUntilElementAppear(searchBtn, 3);
        clickly(searchBtn);
        return new ProductSearchListPage(getMLActionsDriver());
    }
}
