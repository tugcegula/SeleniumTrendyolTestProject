package com.trendyol.pages;

import com.trendyol.utils.ElementUtil;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected ElementUtil elementUtil;
    protected WebDriverWait wait;
    protected static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil =  new ElementUtil(driver);
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10) );
    }

    /**
     * Sayfa başlığını döner
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Sayfanın geçerli URL'ini döner
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Sayfayı yeniler
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }

    /**
     * Tarayıcı geçmişinde geri gider
     */
    public void navigateBack() {
        driver.navigate().back();
    }

    /**
     * Sayfayı aşağıya kaydırır
     */
    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Sayfanın üstüne kaydırır
     */
    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    /**
     * Belirli bir elemente scroll yapar
     */
    public void scrollToElement(org.openqa.selenium.WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
