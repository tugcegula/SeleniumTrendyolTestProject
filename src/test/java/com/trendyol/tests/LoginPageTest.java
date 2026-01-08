package com.trendyol.tests;

import com.trendyol.pages.BasePage;
import com.trendyol.pages.HomePage;
import com.trendyol.pages.LoginPage;
import com.trendyol.utils.ConfigReader;
import org.testng.annotations.Test;

public class LoginPageTest extends BasePageTest {

    @Test
    public void invalidLoginTest() throws InterruptedException {

        // 1. Login işlemi
        HomePage homePage = loginPage.login(email, password);
        //basePage.refreshPage();
        // Assert.assertTrue(isLoggedIn(), "Login işlemi başarısız!");
        // 2. BasePage üzerinden testler
        BasePage basePage = new HomePage(driver);
        email = ConfigReader.get("username");
        password = ConfigReader.get("password");
        //LoginPage loginPage = new LoginPage(driver);
        extentTest.info("Login başarılı");
        logger.info("Login başarılı");


        //boolean loginSuccessful = loginPage.login(email, password);

        //Assert.assertTrue(loginSuccessful, "Kullanıcı giriş yapamadı!");

        extentTest.info("Login sayfası açıldı.");


      /*  loginPage.login("invalid@email.com", "wrongPassword");
        extentTest.info("Geçersiz bilgilerle login denendi.");*/

        /*String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("başarısız"), "Hata mesajı alınamadı.");*/
        //extentTest.pass("Geçersiz login doğrulandı.");

    }
}
