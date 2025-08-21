package com.trendyol.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void trendyolTitleTest() {

        extentTest.info("Trendyol home page is opened."); // Rapor için log
        String actualTitle = driver.getTitle();
        extentTest.info("Page title retrieved: " + actualTitle); // Rapor için log

        Assert.assertTrue(actualTitle.contains("Trend"), "Başlık 'Trend' içermiyor!");
        extentTest.pass("The page title is correct."); // Rapor için log
    }
}
