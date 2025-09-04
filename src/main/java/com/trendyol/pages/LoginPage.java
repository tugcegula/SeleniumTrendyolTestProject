package com.trendyol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage{


    // Elementler
    private By loginButtonOnHomePage = By.cssSelector("p.link-text");// giriş butonu
    private By emailField = By.id("login-email");
    private By passwordField = By.id("login-password-input");
    private By loginButton = By.cssSelector("button.q-button.submit");
  //  private By errorMessage = By.cssSelector(".message"); // Hatalı giriş mesajı için
    //*[@id="login-register"]/div[3]/div[1]/form/button
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public void openLoginPage() {
        driver.findElement(loginButtonOnHomePage).click();
    }

    public void enterEmail(String email) {
        elementUtil.doSendKeys(emailField, email);
    }

    public void enterPassword(String password) {
        elementUtil.doSendKeys(passwordField, password);
    }

    public void clickLogin() {
        elementUtil.waitForElementToBeVisible(loginButton, 15);
        elementUtil.doClick(loginButton);
    }

    public HomePage login(String email, String password) {
        openLoginPage();
        enterEmail(email);
        enterPassword(password);
        clickLogin();
        return new HomePage(driver);
    }
    /*public void openLoginPage() {
        driver.findElement(loginButtonOnHomePage).click();
    }

    public boolean login(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        submit.click();

        try {
            WebElement accountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("p.link-text")));
            return accountElement.getText().equalsIgnoreCase("Hesabım");
        } catch (Exception e) {
            return false;
        }
    }
*/

    /*public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }*/

}
