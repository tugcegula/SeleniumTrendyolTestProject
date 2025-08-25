package com.trendyol.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class AddToCardTest extends BaseTest{
    @Test
    public void testAddProductToCart() {
        //driver.navigate().refresh();
        // 1. Ana sayfa zaten açık (BaseTest'te driver.get() ile açılıyor)

        // 2. Ürün arama
        WebElement searchBox = driver.findElement(By.cssSelector("input[data-testid='suggestion']"));
        searchBox.sendKeys("ayakkabı");

        WebElement searchButton = driver.findElement(By.cssSelector("i[data-testid='search-icon']"));
        searchButton.click();

        logger.info("Ürün araması yapıldı: ayakkabı");

        // 3. Ürün listesinden ilk ürünü tıkla
        WebElement firstProduct = driver.findElements(By.cssSelector("div.p-card-wrppr.with-campaign-view a")).get(2);
        firstProduct.click();
        logger.info("İlk ürün detay sayfası açıldı.");

        // 4. Yeni sekmeye geç (ürün yeni sekmede açılabilir)
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        // 5. Varsa beden seç, sonra Sepete Ekle butonuna bas
        try {
            WebElement addressApprovalButton = driver.findElement(By.cssSelector("button.onboarding__default-renderer-primary-button"));
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated((By) addressApprovalButton));
            addressApprovalButton.click();
            logger.info("Adres onay popup'ı kapatıldı.");
        } catch (TimeoutException e) {
            logger.info("Adres onay popup'ı görünmedi, devam ediliyor.");
        }
        try {
            WebElement sliderContainer = driver.findElement(By.cssSelector("div.slider__container"));
            List<WebElement> sizes = sliderContainer.findElements(By.cssSelector("button[data-testid='size-box']"));
            if (!sizes.isEmpty()) {
                sizes.get(0).click();
                logger.info("Beden seçildi.");
            }
        } catch (Exception e) {
            logger.warn("Beden seçimi yok veya otomatik seçildi.");
        }

        // 6. Sepete ekle
        WebElement addToCartBtn = driver.findElement(By.cssSelector("button[data-testid='add-to-cart-button']"));
        addToCartBtn.click();
        logger.info("Ürün sepete eklendi.");

        // 7. Sepete git
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement goToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.redirect-to-basket")));
        goToCartBtn.click();
        logger.info("Sepete gidildi.");


        // 8. Sepette ürünün olup olmadığını doğrula
        List<WebElement> cartProducts = driver.findElements(By.cssSelector("div.pb-basket-item-wrapper-v2"));

        if (!cartProducts.isEmpty()) {
            logger.info("Sepette " + cartProducts.size() + " ürün var.");
            //WebElement firstProduct = cartProducts.get(0);
            // Örneğin ürün adı, fiyatı vs. burada çekebilirsin
        } else {
            logger.warn("Sepet boş.");
        }

        Assert.assertFalse(cartProducts.isEmpty(), "Sepette ürün bulunamadı! Ürün sepete eklenememiş olabilir.");
        logger.info("Ürün sepette başarıyla bulundu.");
    }
}
