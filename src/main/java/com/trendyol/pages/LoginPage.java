package com.trendyol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    // Elementler
    private By loginButtonOnHomePage = By.cssSelector("div.account-user > span"); // Ana sayfa giriş butonu
    private By emailField = By.id("login-email");
    private By passwordField = By.id("login-password-input");
    private By submitButton = By.cssSelector("button[type='submit']");
    private By errorMessage = By.cssSelector(".message"); // Hatalı giriş mesajı için

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginPage() {
        driver.findElement(loginButtonOnHomePage).click();
    }

    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(submitButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
