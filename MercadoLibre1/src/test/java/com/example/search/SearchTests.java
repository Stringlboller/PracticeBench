package com.example.search;

import com.example.base.BaseTests;
import com.example.dataProviders.FileDataProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.annotations.TestInfo;
import org.example.components.ProductListItemComponent;
import org.example.pages.ProductDetailsPage;
import org.example.pages.ProductSearchListPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchTests extends BaseTests {

    private static final Logger LOGGER = LogManager.getLogger(SearchTests.class);

    // Task 1 and Task 2
    // Case ID: lbs01 - Search for Product on Homepage
    @Test(dataProvider = "lionsCSVFileDP", dataProviderClass = FileDataProvider.class)
    @TestInfo(count = 1, path = "src/main/resources/inputFiles/testSearchProduct.csv")
    public void verifySearchTest(String searchText, String searchResultCsv){
        assertTrue(homePage.isPageOpened(), "Home page is not opened...");
        homePage.typeSearchBar(searchText);
        ProductSearchListPage productSearchListPage = homePage.clickSearchBtn();
        ProductListItemComponent listItemComponent = productSearchListPage.getProductListItemComponent(0);
        String searchResult = listItemComponent.getProductTitle();
        assertTrue(searchResult.contains(searchResultCsv), "The search is not matching");
    }

    // Case ID: lbs04 - Check product details on product details page
    @Test(dataProvider = "lionsCSVFileDP", dataProviderClass = FileDataProvider.class)
    @TestInfo(count = 1, path = "src/main/resources/inputFiles/testSearchProduct.csv")
    public void verifyPDPTest(String searchText, String searchResultCsv){
        assertTrue(homePage.isPageOpened(), "Home page is not opened...");
        homePage.typeSearchBar(searchText);
        ProductSearchListPage productSearchListPage = homePage.clickSearchBtn();

        ProductListItemComponent listItemComponent = productSearchListPage.getProductListItemComponent(0);
        String searchResult = listItemComponent.getProductTitle();
        assertTrue(searchResult.contains(searchResultCsv), "The search is not matching");
        ProductDetailsPage detailsPage = listItemComponent.clickProductByIndex(0);

        assertTrue(detailsPage.isTitlePresent(), "title is not displayed");
        assertTrue(detailsPage.getTitleText().contains(searchResultCsv), "title text is not matching");
        assertTrue(detailsPage.isCheckOutBtnPresent(), "Check out btn is not displayed");
        assertTrue(detailsPage.isImgDisplayed(), "Image is not displayed");
        assertTrue(detailsPage.isPriceDisplayed(), "Price is not displayed");
        assertTrue(detailsPage.isDescriptionsDisplayed(), "description is not displayed");
    }

    // Case ID: lbs05 - Review ‘arrive tomorrow’ filter on PLP
    // I found a defect here that is bc the test is failing :l
    @Test(dataProvider = "lionsCSVFileDP", dataProviderClass = FileDataProvider.class)
    @TestInfo(count = 1, path = "src/main/resources/inputFiles/testSearchProduct.csv")
    public void verifyFilterTest(String searchText, String searchResultCsv) {
        assertTrue(homePage.isPageOpened(), "Home page is not opened...");
        homePage.typeSearchBar(searchText);
        ProductSearchListPage productSearchListPage = homePage.clickSearchBtn();
        ProductListItemComponent listItemComponent = productSearchListPage.getProductListItemComponent(0);
        String searchResult = listItemComponent.getProductTitle();
        assertTrue(searchResult.contains(searchResultCsv), "The search is not matching");
        productSearchListPage.clickArriveTomorrowFilter();
        assertEquals(productSearchListPage.getProductListSize(), productSearchListPage.getArriveTomorrowListSize(), "Banner is not on all products");
    }

}
