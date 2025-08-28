package com.trendyol.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementUtil {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait= new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement getElement(By locator){
        try {
            return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch (TimeoutException e){
            throw new NoSuchElementException("Element not found" + locator);
        }
    }

    public  void doSendKeys(By locator, String value){
        WebElement element= getElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    public void doClick(By locator){
        getElement(locator).click();
    }

    public String getText(By locator){
        return getElement(locator).getText();
    }

    public boolean waitForElementToBeVisible(By locator, int timeoutInSeconds){
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return false;
    }

    public void waitForElementToBeClickable(By locator, int timeoutInSeconds){
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        customWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public List<WebElement> getElements(By locator) {
        try {
            return driver.findElements(locator);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve element list: " + locator);
        }
    }

   /* *//**
     * Alert var mı kontrol eder
     *//*
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    *//**
     * Alert'i kabul eder
     *//*
    public void acceptAlertIfPresent() {
        if (isAlertPresent()) {
            driver.switchTo().alert().accept();
        }
    }
*/
}
