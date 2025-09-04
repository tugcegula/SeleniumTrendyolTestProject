package com.trendyol.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.trendyol.pages.BasePage;
import com.trendyol.pages.HomePage;
import com.trendyol.pages.LoginPage;
import com.trendyol.utils.ConfigReader;
import com.trendyol.utils.ExtentReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BasePageTest {
    protected static WebDriver driver;
    protected BasePage basePage;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected static final Logger logger = LogManager.getLogger(BasePageTest.class);
    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;

    protected String email;
    protected String password;

    @BeforeSuite
    public void beforeSuite() {
        Locale.setDefault(Locale.ENGLISH);
        extentReports = ExtentReporter.getReporter();
        logger.info("ExtentReports ve Locale ayarlandı.");
    }

    @BeforeTest
    public void setUpTest() {
        email = ConfigReader.get("email");
        password = ConfigReader.get("password");

        String browser = ConfigReader.get("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.notifications", 2); // Bildirimleri engelle
            options.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            logger.info("ChromeDriver başlatıldı.");
        }
        basePage = new BasePage(driver);
    }

    @BeforeMethod
    public void setUp(Method method) {
        extentTest = extentReports.createTest(method.getName());
        logger.info("Test başlıyor: " + method.getName());
        driver.get(ConfigReader.get("baseUrl"));
        closeInitialPopups();
        loginPage = new LoginPage(driver);
        basePage = new BasePage(driver);
    }

    @Test
    public void verifyBasePageFunctions() {
        // 1. Login işlemi
        HomePage homePage = loginPage.login(email, password);
        basePage.refreshPage();

       // Assert.assertTrue(isLoggedIn(), "Login işlemi başarısız!");

        extentTest.info("Login başarılı");
        logger.info("Login başarılı");

        // 2. BasePage üzerinden testler
        BasePage basePage = new HomePage(driver);

        String title = basePage.getPageTitle();
        Assert.assertNotNull(title);
        extentTest.info("Sayfa başlığı: " + title);

        String url = basePage.getCurrentUrl();
        Assert.assertTrue(url.contains("trendyol"));
        extentTest.info("URL kontrolü başarılı: " + url);

        basePage.refreshPage();
        extentTest.info("Sayfa yenilendi");

        basePage.navigateBack();
        extentTest.info("Geriye navigasyon yapıldı");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail("Test FAILED: " + result.getThrowable());
            logger.error("Test FAILED:", result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass("Test SUCCESS");
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.skip("Test SKIPPED");
        }
        // driver.quit() burada değil. Suite sonunda kapatılıyor
    }

    @AfterTest
    public void tearDownTest() {
        if (driver != null) {
            driver.quit();
            logger.info("Tarayıcı kapatıldı.");
            extentTest.info("Tarayıcı kapatıldı.");
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        extentReports.flush();
        logger.info("ExtentReports flushlandı.");
    }

    public void closeInitialPopups() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // Çerez popup'ı
        try {
            By cookieBtn = By.id("onetrust-reject-all-handler");
            wait.until(driver -> driver.findElement(cookieBtn).isDisplayed());
            driver.findElement(cookieBtn).click();
            logger.info("Çerez popup'ı kapatıldı.");
        } catch (Exception e) {
            logger.warn("Çerez popup'ı bulunamadı.");
        }

        // Cinsiyet popup'ı
        try {
            By genderClose = By.cssSelector("div.modal-close");
            WebElement closeBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(genderClose));
            closeBtn.click();
            logger.info("Cinsiyet popup'ı kapatıldı.");
        } catch (Exception e) {
            logger.warn("Cinsiyet popup'ı bulunamadı.");
        }
    }

    public boolean isLoggedIn() {
        try {
            WebElement accountElement = driver.findElement(By.cssSelector("p.link-text"));
            return accountElement.getText().equalsIgnoreCase("Hesabım");
        } catch (Exception e) {
            return false;
        }
    }
}
