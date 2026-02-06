package com.trendyol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class FilterByCost extends BasePage {

    private By searchBox2 = By.cssSelector("input[data-testid='suggestion']");
    private By searchBox = By.cssSelector("button[data-testid='suggestion-placeholder']");
    private By searchButton = By.cssSelector("[data-testid='search-submit-button']");
    private By searchBrand = By.id("web-search-input--103");
    private By brand = By.xpath("//span[@class='checkbox-label' and text()='Pull & Bear']");
    //private By price = By.cssSelector("(//button[@aria-label='Open Fiyat filters'] )");



    public FilterByCost(WebDriver driver) {
        super(driver);
    }

    public void searchProduct(String productName,String brandName) throws InterruptedException {

        elementUtil.waitForElementToBeClickable(searchBox,15);
        driver.findElement(searchBox).click();
        elementUtil.doSendKeys(searchBox2, productName + Keys.ENTER);
        elementUtil.waitForElementToBeClickable(searchBrand,10);
        elementUtil.doClick(searchBrand);
        elementUtil.doSendKeys(searchBrand,brandName);
        elementUtil.doClick(brand);
//        elementUtil.waitForElementToBeClickable(price,10);
//        elementUtil.doClick(price);
//        Thread.sleep(10000);
    }

    public void checkTheBrand(String brandName){

    }
}
