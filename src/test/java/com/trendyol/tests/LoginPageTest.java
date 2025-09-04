package com.trendyol.tests;

import com.trendyol.pages.LoginPage;
import com.trendyol.utils.ConfigReader;
import org.testng.annotations.Test;

public class LoginPageTest extends BasePageTest {

    @Test
    public void invalidLoginTest() {
        email = ConfigReader.get("username");
        password = ConfigReader.get("password");
        LoginPage loginPage = new LoginPage(driver);
        //loginPage.login(email,password);

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
