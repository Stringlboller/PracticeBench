package com.example.base;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTests {

    private static final Logger LOGGER = LogManager.getLogger(BaseTests.class);

    private WebDriver driver;
    protected HomePage homePage;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/leonboller/Downloads/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        LOGGER.info("Opening driver...");
        LOGGER.info("- - - - - - - - - - - - - - - -");
        homePage = new HomePage(driver);
    }

    @BeforeMethod
    public void goHome(){
        LOGGER.info("Opening Mercado Libre...");
        driver.get("https://www.mercadolibre.com.ar");
        LOGGER.info("Mercado Libre is on main page");
        LOGGER.info("- - - - - - - - - - - - - - - -");
    }

    @AfterMethod
    public void printEndLines(){
        LOGGER.info("- - - - - - - - - - - - - - - -");
    }

    @AfterClass
    public void tearDown(){
        LOGGER.info("Closing driver...");
        driver.quit();
    }
}
