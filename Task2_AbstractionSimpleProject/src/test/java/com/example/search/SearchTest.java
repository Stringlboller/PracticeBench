package com.example.search;


import com.example.base.BaseTests;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.components.ProductListItemComponent;
import org.example.pages.ProductSearchListPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SearchTest extends BaseTests {

    private static final Logger LOGGER = LogManager.getLogger(SearchTest.class);

    @Test
    public void verifySearchTest() {
        assertTrue(homePage.isPageOpened(), "Home page is not opened...");
        homePage.typeSearchBar("Go Pro");
        ProductSearchListPage productSearchListPage = homePage.clickSearchBtn();
        ProductListItemComponent listItemComponent = productSearchListPage.getProductListItemComponent(0);
        String searchResult = listItemComponent.getProductTitle();
        assertTrue(searchResult.contains("gopro"), "The search is not matching");
    }

}
