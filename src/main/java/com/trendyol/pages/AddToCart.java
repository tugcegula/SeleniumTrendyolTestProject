package com.trendyol.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class AddToCart extends BasePage {

    private By searchBox2 = By.cssSelector("input[data-testid='suggestion']");
    private By searchBox = By.cssSelector("button[data-testid='suggestion-placeholder']");
    private By product = By.cssSelector("a.product-card[data-testid='product-card']");
    private By searchButton = By.cssSelector("i[data-testid='search-icon']");
    private By addressApprovalButton = By.cssSelector(".onboarding__default-renderer-primary-button");
    private By addToBasketButton = By.cssSelector("button[data-testid='add-to-cart-button']");
    private By goToCartButton = By.cssSelector("a.redirect-to-basket");
    private By sizeSelector = By.cssSelector("button[data-testid='size-box']");
    private By cartItems = By.cssSelector("div.merchant-item-container");

    public AddToCart(WebDriver driver) {
        super(driver);
    }

    public void searchProduct(String productName) {

        elementUtil.waitForElementToBeClickable(searchBox,10);
        driver.findElement(searchBox).click();
        elementUtil.waitForElementToBeClickable(searchBox2,10);
        elementUtil.doSendKeys(searchBox2, productName + Keys.ENTER);
        //elementUtil.doClick(searchButton);
    }

    public void selectProduct() {

        List<WebElement> products = elementUtil.getElements(product);
        elementUtil.waitForElementToBeClickable(product, 15);
        int randomIndex = new Random().nextInt(products.size());
        System.out.println(randomIndex);
        products.get(randomIndex).click();

    }


    public void addToCart() {
        elementUtil.waitForElementToBeClickable(addToBasketButton, 15);
        elementUtil.doClick(addToBasketButton);
    }

    public void goToCart() {
        elementUtil.waitForElementToBeClickable(goToCartButton, 15);
        elementUtil.doClick(goToCartButton);
    }

    public boolean isProductInCart() {
        List<WebElement> cartItemsList = elementUtil.getElements(cartItems);
        elementUtil.waitForElementToBeVisible( cartItems, 15);
        return !cartItemsList.isEmpty();
    }

    // Handle popups such as address approval
    public void closeAddressPopupIfPresent() {
//        WebElement addressApproval = driver.findElement(addressApprovalButton);
//        addressApproval.click();

//            try {

        elementUtil.waitForElementToBeClickable(addressApprovalButton,10);
        elementUtil.doClick(addressApprovalButton);
        /*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = driver.findElement(addressApprovalButton);
        button.click();*/
//                System.out.println("Adres onay popup'ı kapatıldı.");
//            } catch (TimeoutException e) {
//                System.out.println("Adres onay popup'ı görünmedi, devam ediliyor.");
//
//            }
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
            elementUtil.waitForElementToBeClickable(sizeSelector, 15);
            List<WebElement> sizes = elementUtil.getElements(sizeSelector);
            int randomSize=new Random().nextInt(sizes.size());;
            if (!sizes.isEmpty()) {
                sizes.get(randomSize).click();
                logger.info("Beden seçildi.");
            }
        } catch (Exception e) {
            logger.warn("Beden seçimi yok veya otomatik seçildi.");
        }
    }
}