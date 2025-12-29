package com.trendyol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FilterByCost extends BasePage {

    private By searchBox = By.cssSelector("input[data-testid='suggestion']");
    private By searchButton = By.cssSelector("i[data-testid='search-submit-button']");

    public FilterByCost(WebDriver driver) {
        super(driver);
    }

    public void searchProduct(String productName) {
        elementUtil.doSendKeys(searchBox, productName);
        elementUtil.doClick(searchButton);
    }
}
