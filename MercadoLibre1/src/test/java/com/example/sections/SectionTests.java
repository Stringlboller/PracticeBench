package com.example.sections;

import com.example.base.BaseTests;
import com.example.dataProviders.FileDataProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.annotations.TestInfo;
import org.example.pages.CategoriesPage;
import org.example.pages.PleasCreateAccountPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SectionTests extends BaseTests {

    private static final Logger LOGGER = LogManager.getLogger(SectionTests.class);

    // Case ID: lbs06 - Review “My purchases” section - log out user
    @Test()
    @TestInfo(count = 1, path = "src/main/resources/inputFiles/testSearchProduct.csv")
    public void verifyMyPurchaseSectionTest() {
        assertTrue(homePage.isPageOpened(), "Home page is not opened...");
        PleasCreateAccountPage pleasCreateAccountPage = homePage.clickMyPurchasesSectionLoggedOut();
        assertTrue(pleasCreateAccountPage.isTitlePresent(), "Title is not present");
        assertTrue(pleasCreateAccountPage.isLoginBtnDisplayed(), "Login btn is not displayed");
        assertTrue(pleasCreateAccountPage.isContinueBtnPresent(), "Continue btn is not displayed");
        assertTrue(pleasCreateAccountPage.isEmailInputPresent(), "Email input is not displayed");
    }

    // Case ID: lbs02 - Filter Products by Category
    @Test(dataProvider = "lionsCSVFileDP", dataProviderClass = FileDataProvider.class)
    @TestInfo(count = 1, path = "src/main/resources/inputFiles/testReviewCategory.csv")
    public void verifyCategoriesSectionTest(String category) throws InterruptedException {
        assertTrue(homePage.isPageOpened(), "Home page is not opened...");
        homePage.clickCategoriesDropdown();
        homePage.pause(1000);
        CategoriesPage categoriesPage = homePage.clickCategoryByIndex(1);
        assertTrue(categoriesPage.isCategoriesPageOpened(), "Categories page is not opened");
        assertTrue(categoriesPage.isCorrectCategoryPageOpened(category));
    }
}
