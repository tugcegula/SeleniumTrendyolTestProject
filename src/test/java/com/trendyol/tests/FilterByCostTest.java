package com.trendyol.tests;

import com.trendyol.pages.FilterByCost;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FilterByCostTest extends BasePageTest {

    private FilterByCost filterByCost;


    @BeforeClass
    public void setUp() {
        // Initialize AddToCart page before the test starts
        filterByCost= new FilterByCost(driver);
        logger.info("AddToCart page has been initialized.");
    }

    @Test
    public void testAddProductToCart() {
        String productName = "Ayakkabı";  // The product to search for

        // 1. Perform product search
        filterByCost.searchProduct(productName);
        logger.info("Product search performed: " + productName);
    }


}