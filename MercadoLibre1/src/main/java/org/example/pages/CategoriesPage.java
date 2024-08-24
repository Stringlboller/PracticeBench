package org.example.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.actions.MLActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CategoriesPage extends MLActions {

    private static final Logger LOGGER = LogManager.getLogger(CategoriesPage.class);

    @FindBy(xpath = "")
    private List<WebElement> a;

    public CategoriesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isCategoriesPageOpened() {
        return getMLActionsDriver().getCurrentUrl().contains("categories");
    }

    public boolean isCorrectCategoryPageOpened(String category) {
        return getMLActionsDriver().getCurrentUrl().toLowerCase().contains(category);
    }
}
