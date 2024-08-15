package org.example.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);

    WebDriver driver;

    @FindBy(xpath = "//a[@class='nav-logo']")
    private WebElement navLogo;

    @FindBy(xpath = "//input[@class='nav-search-input']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@class='nav-search-btn']")
    private WebElement searchBtn;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        return navLogo.isDisplayed();
    }

    public void typeSearchBar(String searchText) {
        searchInput.sendKeys(searchText);
    }

    public ProductSearchListPage clickSearchBtn() {
        searchBtn.click();
        return new ProductSearchListPage(driver);
    }
}
