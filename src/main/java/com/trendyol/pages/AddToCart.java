package com.trendyol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AddToCart extends BasePage {

    private By searchBox = By.cssSelector("input[data-testid='suggestion']");
    private By product = By.cssSelector("div.p-card-wrppr.with-campaign-view a");
    private By searchButton = By.cssSelector("i[data-testid='search-icon']");
    private  By addressApprovalBy =  By.cssSelector("button.onboarding__default-renderer-primary-button");
    private By addToBasketButton = By.cssSelector("button[data-testid='add-to-cart-button']");
    private By goToCartButton = By.cssSelector("a.redirect-to-basket");

    public AddToCart(WebDriver driver) {
        super(driver);
    }

    public void searchProduct(String productName) {
        elementUtil.doSendKeys(searchBox, productName);
        elementUtil.doClick(searchBox);
    }

    public void selectProduct(int index) {
        List<WebElement> products = elementUtil.getElements(product);

        if (index >= 0 && index < products.size()) {
            elementUtil.waitForElementToBeVisible(product, 5);
            products.get(index).click();
        } else {
            throw new IllegalArgumentException("Invalid product index " + index);
        }
    }

    public void addToCart() {
        elementUtil.waitForElementToBeClickable(addToBasketButton, 5);
        elementUtil.doClick(addToBasketButton);
    }

    public void goToCart() {
        elementUtil.doClick(goToCartButton);
    }
}