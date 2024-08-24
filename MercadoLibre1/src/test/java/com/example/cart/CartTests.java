package com.example.cart;

import com.example.base.BaseTests;
import com.example.dataProviders.FileDataProvider;
import com.example.sections.SectionTests;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.annotations.TestInfo;
import org.example.components.ProductListItemComponent;
import org.example.pages.PleasCreateAccountPage;
import org.example.pages.ProductDetailsPage;
import org.example.pages.ProductSearchListPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CartTests extends BaseTests {

    private static final Logger LOGGER = LogManager.getLogger(SectionTests.class);

    // Case ID: lbs03 - Add Product to Cart
    @Test(dataProvider = "lionsCSVFileDP", dataProviderClass = FileDataProvider.class)
    @TestInfo(count = 1, path = "src/main/resources/inputFiles/testAddToCart.csv")
    public void verifyAddProductToCartFunctionTest(String searchText, String searchResultCsv) throws InterruptedException {
        assertTrue(homePage.isPageOpened(), "Home page is not opened...");
        homePage.typeSearchBar(searchText);
        ProductSearchListPage productSearchListPage = homePage.clickSearchBtn();
        ProductListItemComponent listItemComponent = productSearchListPage.getProductListItemComponent(0);
        String searchResult = listItemComponent.getProductTitle();
        assertTrue(searchResult.contains(searchResultCsv), "The search is not matching");
        ProductDetailsPage detailsPage = listItemComponent.clickProductByIndex(0);
        detailsPage.pause(2000);
        detailsPage.scrollByPixels(400);
        detailsPage.clickQuantityDropdown();
        detailsPage.pause(2000);
        detailsPage.clickQuantityByIndex(1);
        PleasCreateAccountPage createAccountPage = detailsPage.clickCheckOutBtnWithNoLoggedIn();
        assertTrue(createAccountPage.isTitlePresent(), "Page was not opened or title is not present");
        assertTrue(createAccountPage.isLoginBtnDisplayed(), "Login btn is not displayed");
    }
}
