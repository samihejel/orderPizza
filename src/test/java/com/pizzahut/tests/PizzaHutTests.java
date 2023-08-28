package com.pizzahut.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.pizzahut.pages.PizzaHutHomePage;

public class PizzaHutTests {

    private WebDriver driver;
    private PizzaHutHomePage homePage;

    @BeforeClass
    public void setUp() {
        System.out.println("Setting up WebDriver...");
        System.setProperty("webdriver.chrome.driver", "K:/programming/qa/drivers/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Maximize the browser window
        WebDriverManager.chromedriver().setup();  // Set up ChromeDriver using WebDriverManager
        driver = new ChromeDriver(options);  // Initialize ChromeDriver
        driver.manage().window().maximize();  // Maximize the browser window

        System.out.println("WebDriver set up successfully.");

        homePage = new PizzaHutHomePage(driver);  // Initialize the Page Object
        driver.get("https://www.dominos.ca");  // Open the Pizza Hut website
    }

    @Test
    public void testOrderPizzaAndCheckout() {
        System.out.println("Starting test: Order Pizza and Checkout");

        homePage.clickOrderNowButton();
        System.out.println("Clicked on 'Order Now' button");

        homePage.searchForLocation("41 Antrim Crescent", "Scarborough", "Apt#1010", "ON", "M1P 4T1");
        System.out.println("Searched for location and entered address");

        homePage.pickPizzaAndCheckout();
        System.out.println("Picked pizza and proceeded to checkout");

        // Add assertions or verifications here based on the expected behavior

        System.out.println("Test completed successfully.");
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Tearing down WebDriver...");

        if (driver != null) {
            driver.quit();
        }

        System.out.println("WebDriver closed.");
    }
}