package com.trendyol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class FilterByCost extends BasePage {

    private By searchBox = By.cssSelector("input[data-testid='suggestion']");
    private By searchButton = By.cssSelector("[data-testid='search-submit-button']");

    public FilterByCost(WebDriver driver) {
        super(driver);
    }

    public void searchProduct(String productName) {
        elementUtil.waitForElementToBeVisible(searchBox,10);
        elementUtil.doClick(searchBox);
        elementUtil.doSendKeys(searchBox, productName);//+ Keys.ENTER
        elementUtil.doClick(searchButton);
    }
}
