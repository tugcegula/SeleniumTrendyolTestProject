package com.trendyol.tests;

import com.trendyol.pages.AddToCart;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class AddToCardTest extends BasePageTest {

    private AddToCart addToCartPage;

    @BeforeClass
    public void setUp() {
        // Initialize AddToCart page before the test starts
        addToCartPage = new AddToCart(driver);
        logger.info("AddToCart page has been initialized.");
    }
    @Test
    public void testAddProductToCart() {
        String productName = "Ayakkabı";  // The product to search for

        // 1. Perform product search
        addToCartPage.searchProduct(productName);
        logger.info("Product search performed: " + productName);

        // 2. Select the product (first product in the list)
        addToCartPage.selectProduct(0);
        logger.info("First product selected.");

        // 3. Close the address confirmation popup (if it appears)
        addToCartPage.closeAddressPopupIfPresent();

        // 4. Select size (if available)
        addToCartPage.selectSizeIfAvailable();

        // 5. Add the product to the cart
        addToCartPage.addToCart();
        logger.info("Product has been added to the cart.");

        // 6. Go to the cart
        addToCartPage.goToCart();
        logger.info("Navigated to the cart.");

        // 7. Verify if the product is in the cart
        boolean isProductInCart = addToCartPage.isProductInCart();
        assertTrue(isProductInCart, "Product could not be added to the cart!");
        logger.info("Product successfully found in the cart.");


       /* //driver.navigate().refresh();
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
            By addressApprovalBy =  By.cssSelector("button.onboarding__default-renderer-primary-button");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement addressApprovalButton = wait.until(ExpectedConditions.visibilityOfElementLocated(addressApprovalBy));
            addressApprovalButton.click();
            logger.info("Adres onay popup'ı kapatıldı.");
        } catch (TimeoutException e) {
            logger.info("Adres onay popup'ı görünmedi, devam ediliyor.");
        } catch (Exception e) {
            logger.error("Adres onay popup'ı kapatılırken hata oluştu: ", e);
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
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement goToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.redirect-to-basket")));
        goToCartBtn.click();
        logger.info("Sepete gidildi.");


        // 8. Sepette ürünün olup olmadığını doğrula
        List<WebElement> cartProducts = driver.findElements(
                By.cssSelector(".pb-basket-item-wrapper-v2")
        );
        if (!cartProducts.isEmpty()) {
            logger.info("Sepette " + cartProducts.size() + " ürün var.");
            //WebElement firstProduct = cartProducts.get(0);
            // Örneğin ürün adı, fiyatı vs. burada çekebilirsin
        } else {
            logger.warn("Sepet boş.");
        }

      // Assert.assertFalse(cartProducts.isEmpty(), "Sepette ürün bulunamadı! Ürün sepete eklenememiş olabilir.");
        logger.info("Ürün sepette başarıyla bulundu.");*/
    }
}
