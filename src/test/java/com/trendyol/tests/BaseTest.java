package com.trendyol.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.trendyol.utils.ConfigReader;
import com.trendyol.utils.ExtentReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Locale;

public class BaseTest {

    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected  static ExtentReports extentReports;
    protected static ExtentTest extentTest;

    @BeforeSuite
    public void extentSetup(){
        //raporu başlatır.
        Locale.setDefault(Locale.ENGLISH);
        extentReports = ExtentReporter.getReporter();
    }
    @BeforeMethod
    public void setUp(Method method) {
        logger.info("Starting the test...");
        //Bu satırın çalışması için Java'nın Method sınıfını extentTest metotlarına parametre olarak iletmek gerekiyor
        extentTest = extentReports.createTest(method.getName());

        String browser = ConfigReader.get("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            logger.info("ChromeDriver is initialized.");
        }

        driver.manage().window().maximize();
        driver.get(ConfigReader.get("baseUrl"));

        closeInitialPopups(); // 🧩 burada popup'ları kapatıyoruz
        logger.info("The site is open: " + ConfigReader.get("baseUrl"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail("Test failed: " + result.getThrowable());
            logger.error("Test failed:", result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass("Test successful.");
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.skip("Test successful.");
        }

        if (driver != null) {
            driver.quit();
            logger.info("The browser was closed.");
            extentTest.info("The browser was closed.");
        }
    }

    @AfterSuite
    public void flushReport() {
        // Raporu yazar
        extentReports.flush();
    }

    public void closeInitialPopups() {
        try {
            // Çerez popup
            By cookieCloseBtn =By.id("onetrust-reject-all-handler");
            driver.findElement(cookieCloseBtn).click();
            logger.info("Cookie popup closed.");
        } catch (Exception e) {
            logger.warn("Cookie popup closed.");
        }

        try {
            // Cinsiyet seçimi popup
            By genderPopupClose = By.cssSelector("div[title='Kapat']"); // Veya uygun bir locator
            driver.findElement(genderPopupClose).click();
            logger.info("Gender popup closed.");
        } catch (Exception e) {
            logger.warn("Gender popup closed.");
        }
    }
}
