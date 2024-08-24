package org.example.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.actions.MLActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PleasCreateAccountPage extends MLActions {

    private static final Logger LOGGER = LogManager.getLogger(ProductDetailsPage.class);

    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(css = "[data-testid='login-link']")
    private WebElement loginBtn;

    @FindBy(xpath = "//a[@data-testid='login-link']/../button")
    private WebElement continueBtn;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInput;

    public PleasCreateAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isTitlePresent() {
        return title.isDisplayed();
    }

    public boolean isLoginBtnDisplayed() {
        return loginBtn.isDisplayed();
    }

    public boolean isContinueBtnPresent() {
        return continueBtn.isDisplayed();
    }

    public boolean isEmailInputPresent() {
        return emailInput.isDisplayed();
    }
}
