package com.example.search;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.pages.HomePage;
import org.example.pages.ProductSearchListPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SearchTest {

    private static final Logger LOGGER = LogManager.getLogger(SearchTest.class);

    WebDriver driver;
    HomePage homePage;
    ProductSearchListPage searchListPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/leonboller/Downloads/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        searchListPage = new ProductSearchListPage(driver);
    }

    @Test
    public void verifySearchTest () {
        LOGGER.info("Opening Mercado Libre...");
        driver.get("https://www.mercadolibre.com.ar");
        LOGGER.info("Mercado Libre is on main page");
        LOGGER.info("- - - - - - - - - - - - - - - -");

        assertTrue(homePage.isPageOpened(), "Home page is not opened...");
        homePage.typeSearchBar("Go Pro");
        ProductSearchListPage productSearchListPage = homePage.clickSearchBtn();
        String searchResult = productSearchListPage.getProductTitlesList();
        assertTrue(searchResult.contains("gopro"), "The search is not matching");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
