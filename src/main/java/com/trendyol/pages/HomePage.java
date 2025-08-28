package com.trendyol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private By accountName = By.cssSelector("p.link-text");

    public HomePage(WebDriver driver) {
        super(driver);
    }
    public boolean isUserLoggedIn() {
        return elementUtil.waitForElementToBeVisible(accountName,10);
    }


}
