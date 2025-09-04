package com.trendyol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddToCart extends BasePage {

    private By searchBox = By.cssSelector("input[data-testid='suggestion']");
    private By product = By.cssSelector("div.p-card-wrppr.with-campaign-view a");
    private By searchButton = By.cssSelector("i[data-testid='search-icon']");
    private  By addressApprovalButton =  By.xpath("//button[text()='Anladım']");
    private By addToBasketButton = By.cssSelector("button[data-testid='add-to-cart-button']");
    private By goToCartButton = By.cssSelector("a.redirect-to-basket");
    private By sizeSelector = By.cssSelector("button[data-testid='size-box']");
    private By cartItems = By.cssSelector(".pb-basket-item-wrapper-v2");

    public AddToCart(WebDriver driver) {
        super(driver);
    }

    public void searchProduct(String productName) {
        elementUtil.doSendKeys(searchBox, productName);
        elementUtil.doClick(searchButton);
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

    public boolean isProductInCart() {
        List<WebElement> cartItemsList = elementUtil.getElements(cartItems);
        return !cartItemsList.isEmpty();
    }

    // Handle popups such as address approval
    public void closeAddressPopupIfPresent() {
//        WebElement addressApproval = driver.findElement(addressApprovalButton);
//        addressApproval.click();

            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addressApprovalButton));
                button.click();
                System.out.println("Adres onay popup'ı kapatıldı.");
            } catch (TimeoutException e) {
                System.out.println("Adres onay popup'ı görünmedi, devam ediliyor.");

            }
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//            WebElement addressApproval = wait.until(ExpectedConditions.elementToBeClickable(addressApprovalButton));
//            addressApproval.click();
//            logger.info("Adres onay popup'ı kapatıldı.");
//        } catch (TimeoutException e) {
//            logger.info("Adres onay popup'ı görünmedi veya tıklanabilir değil, test devam ediyor.");
//        } catch (Exception e) {
//            logger.warn("Adres popup kapatılırken beklenmeyen bir hata oluştu: " + e.getMessage());
//        }
    }

    // Select size if available
    public void selectSizeIfAvailable() {
        try {
            List<WebElement> sizes = elementUtil.getElements(sizeSelector);
            if (!sizes.isEmpty()) {
                sizes.get(0).click();
                logger.info("Beden seçildi.");
            }
        } catch (Exception e) {
            logger.warn("Beden seçimi yok veya otomatik seçildi.");
        }
    }
}