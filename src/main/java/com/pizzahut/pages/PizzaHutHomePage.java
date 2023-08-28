package com.pizzahut.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PizzaHutHomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public PizzaHutHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10); // Initialize WebDriverWait with a timeout of 10 seconds
    }

    public void clickOrderNowButton() {
        WebElement orderNowButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("smart-order__cta--delivery")));
        orderNowButton.click();
    }

    public void searchForLocation(String location, String city, String suite, String province, String postalCode) {
        WebElement streetInput = driver.findElement(By.id("Street"));
        WebElement cityInput = driver.findElement(By.id("City"));
        WebElement regionDropdown = driver.findElement(By.id("Region"));
        WebElement postalCodeInput = driver.findElement(By.id("Postal_Code"));
        WebElement continueButton = driver.findElement(By.linkText("Continue for Delivery"));

        streetInput.sendKeys(location);
        cityInput.sendKeys(city);
        Select dropdown = new Select(regionDropdown);
        dropdown.selectByValue(province);
        postalCodeInput.sendKeys(postalCode);

        continueButton.click();
    }

    public void pickPizzaAndCheckout() {
        WebElement pizzaLink = wait.until(ExpectedConditions.elementToBeClickable(By.className("qa-PastPopular")));
        pizzaLink.click();

        java.util.List<WebElement> linkElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("js-popularItem")));
        for (WebElement linkElement : linkElements) {
            String linkText = linkElement.findElement(By.cssSelector("h5.card__list-item__title")).getText();
            if (linkText.equals("14\" Hand Tossed")) {
                linkElement.click();
                break;
            }
        }

        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("c-order-buttonCheckout")));
        checkoutButton.click();

        java.util.List<WebElement> popupCloser = driver.findElements(By.className("card--overlay__close.js-closeButton"));
        if (!popupCloser.isEmpty()) {
            popupCloser.get(0).click();
        }

        WebElement continueCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn--block.btn.btn--large.btn--continue-checkout.submitButton.qa-OrCheck.js-continueCheckout.c-order-continueCheckout")));
        continueCheckoutButton.click();

        WebElement confirmationButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn.sam-continue--button.js-sam-continue.sam-continue--button__confirmation")));
        confirmationButton.click();

    }

    // Add more methods for other interactions on the home page
}