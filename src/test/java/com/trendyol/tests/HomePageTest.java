package com.trendyol.tests;

import com.trendyol.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BasePageTest {

    @Test
    public void trendyolTitleTest() {
        HomePage homePage = new HomePage(driver);
        extentTest.info("Trendyol home page is opened.");
        String actualTitle = driver.getTitle();
        extentTest.info("Page title retrieved: " + actualTitle);
        Assert.assertTrue(actualTitle.contains("Trend"), "Başlık 'Trend' içermiyor!");
        extentTest.pass("The page title is correct.");
    }
}
