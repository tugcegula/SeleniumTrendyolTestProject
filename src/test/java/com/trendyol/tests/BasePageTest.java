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
import org.openqa.selenium.JavascriptExecutor;
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
import java.util.NoSuchElementException;

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

    /* -------------------- BEFORE SUITE -------------------- */

    @BeforeSuite
    public void beforeSuite() {
        Locale.setDefault(Locale.ENGLISH);
        extentReports = ExtentReporter.getReporter();
        logger.info("ExtentReports ve Locale ayarlandı.");
    }

    /* -------------------- BEFORE TEST -------------------- */

    @BeforeTest
    public void setUpTest() {



        String browser = ConfigReader.get("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();


            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            options.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            logger.info("ChromeDriver başlatıldı.");
        }
    }

    /* -------------------- BEFORE CLASS -------------------- */

    @BeforeClass
    public void setUpClass() {
        email = ConfigReader.get("email");
        password = ConfigReader.get("password");
        driver.get(ConfigReader.get("baseUrl"));

        ((JavascriptExecutor) driver).executeScript(
                "localStorage.setItem('gender_modal_show_count', '2');"
        );

        driver.navigate().refresh();

        closeInitialPopups();

        loginPage = new LoginPage(driver);
        basePage = new BasePage(driver);



        //logger.info("BeforeClass tamamlandı: popup kapandı, login yapıldı.");
    }

    /* -------------------- BEFORE METHOD -------------------- */

    @BeforeMethod
    public void setUp(Method method) {
        extentTest = extentReports.createTest(method.getName());
        logger.info("Test başlıyor: " + method.getName());

        checkLogin();
    }

    /* -------------------- TEST -------------------- */

    @Test
    public void verifyBasePageFunctions() {

        String title = basePage.getPageTitle();
        Assert.assertNotNull(title);
        extentTest.info("Sayfa başlığı: " + title);

        String url = basePage.getCurrentUrl();
        Assert.assertTrue(url.contains("trendyol"));
        extentTest.info("URL kontrolü başarılı: " + url);
    }

    /* -------------------- AFTER METHOD -------------------- */

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
    }

    /* -------------------- AFTER TEST -------------------- */

    @AfterTest
    public void tearDownTest() {

        if (driver != null) {
            driver.quit();
            logger.info("Tarayıcı kapatıldı.");
        }
    }

    /* -------------------- AFTER SUITE -------------------- */

    @AfterSuite
    public void tearDownSuite() {
        extentReports.flush();
        logger.info("ExtentReports flushlandı.");
    }

    /* -------------------- HELPER METHODS -------------------- */

    public void checkLogin() {
        if (!isUserLoggedIn()) {
            loginPage.login(email, password);
            logger.info("Login işlemi gerçekleştirildi.");
        }
    }

    public boolean isUserLoggedIn() {
        try {
            WebElement accountElement = driver.findElement(
                    By.xpath("//p[contains(@class,'navigation-text') and contains(text(),'Hesabım')]")
            );
            return accountElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void closeInitialPopups() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        try {
            By cookieBtn = By.id("onetrust-reject-all-handler");
            wait.until(d -> d.findElement(cookieBtn).isDisplayed());
            driver.findElement(cookieBtn).click();
            logger.info("Çerez popup'ı kapatıldı.");
        } catch (Exception e) {
            logger.warn("Çerez popup'ı bulunamadı.");
        }
    }
}

