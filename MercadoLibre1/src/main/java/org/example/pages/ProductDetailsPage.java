package org.example.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.actions.MLActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductDetailsPage extends MLActions {

    private static final Logger LOGGER = LogManager.getLogger(ProductDetailsPage.class);

    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(xpath = "(//figure/img[contains(@class,'ui-pdp-image')])[1]")
    private WebElement img;

    @FindBy(xpath = "//meta[@itemprop='price']/../span[2]")
    private WebElement price;

    @FindBy(xpath = "//ul[contains(@class,'specs')]")
    private WebElement description;

    @FindBy(xpath = "//span[@class='ui-pdp-buybox__quantity__selected']")
    private WebElement quantityDropdown;

    @FindBy(xpath = "//ul[contains(@class,'dropdown')]/li")
    private List<WebElement> quantityDropdownOptions;

    @FindBy(xpath = "//button[contains(@formaction,'checkout')]")
    private WebElement checkOutBtn;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickQuantityDropdown() {
        waitUntilElementAppear(quantityDropdown, 3);
        clickly(quantityDropdown);
    }

    public void clickQuantityByIndex(int index) {
        waitUntilElementAppear(quantityDropdownOptions.get(index), 3);
        clickly(quantityDropdownOptions.get(index));
    }

    public PleasCreateAccountPage clickCheckOutBtnWithNoLoggedIn() {
        clickly(checkOutBtn);
        return new PleasCreateAccountPage(getMLActionsDriver());
    }

    public boolean isTitlePresent() {
        return title.isDisplayed();
    }

    public String getTitleText() {
        return title.getText().toLowerCase().replaceAll("\\s+", "");
    }

    public boolean isCheckOutBtnPresent() {
        return checkOutBtn.isDisplayed();
    }

    public boolean isImgDisplayed() {
        return img.isDisplayed();
    }

    public boolean isPriceDisplayed() {
        return price.isDisplayed();
    }

    public boolean isDescriptionsDisplayed() {
     return description.isDisplayed();
    }
}
