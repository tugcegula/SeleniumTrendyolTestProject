package com.trendyol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage{


    // Elementler
    private By loginButtonOnHomePage = By.cssSelector("div.navigation-menu-user");
            //By.xpath("//p[@class='user-text navigation-text' and text()='Giriş Yap']");// giriş butonu
    private By loginWrapper = By.cssSelector("login-register-wrapper");
    private By emailField = By.id("email-input");
    private By devamButton = By.cssSelector("button.email-check-button");
            //By.id("login-email");
    private By passwordField = By.id("login-password-input");
   // private By loginButton = By.cssSelector("button.q-button.submit");
    private By loginButton = By.cssSelector("button.login-button");
    private By profileIcon = By.cssSelector("user-menu");
    private By hesabimText = By.xpath("//p[contains(@class,'navigation-text') and contains(text(),'Hesabım')]");
    //  private By errorMessage = By.cssSelector(".message"); // Hatalı giriş mesajı için
    //*[@id="login-register"]/div[3]/div[1]/form/button
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public void openLoginPage() {
        WebElement loginButtonOnHomePage2 = driver.findElement(By.cssSelector("div.navigation-menu-user"));
        //elementUtil.waitForLoaderToDisappear(15);
        /*elementUtil.waitForElementToBeClickable(loginButtonOnHomePage, 15);
        elementUtil.waitForElementToBeVisible(loginWrapper, 15);*/
       /* Actions actions = new Actions(driver);
        actions.moveToElement(loginButtonOnHomePage2).click();*/
      //  elementUtil.doClick(loginButtonOnHomePage);
        elementUtil.jsClick(
                By.cssSelector("div.navigation-menu-user")
        );
    }

    public void enterEmail(String email) {
        elementUtil.waitForElementToBeVisible(emailField,10);
        elementUtil.doSendKeys(emailField, email);
        elementUtil.waitForElementToBeClickable(devamButton,10);
        elementUtil.doClick(devamButton);
    }

    public void enterPassword(String password) {
        elementUtil.waitForElementToBeVisible(passwordField,10);
        elementUtil.doSendKeys(passwordField, password);
    }

    public void clickLogin() {
        elementUtil.doClick(loginButton);
    }

    public boolean isLoggedIn() {
        return elementUtil.waitForElementToBeVisible(profileIcon, 5);
    }

    public void waitUntilReady() {
        elementUtil.waitForElementToBeVisible(profileIcon, 15);
    }
    public String getHesabimText() {
        elementUtil.waitForElementToBeVisible(hesabimText, 10);
        return driver.findElement(hesabimText).getText();

    }
    public HomePage login(String email, String password) {
        openLoginPage();

        //if (!isLoggedIn()) {

            enterEmail(email);
            enterPassword(password);
            clickLogin();
            waitUntilReady();
           // getHesabimText();
       // }
       /// elementUtil.waitForUrlContains("/home", 15);
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
