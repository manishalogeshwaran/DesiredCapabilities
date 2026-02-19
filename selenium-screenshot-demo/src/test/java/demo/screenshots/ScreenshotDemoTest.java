package demo.screenshots;
 
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;  
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
 
public class ScreenshotDemoTest {
    private WebDriver driver;
    private WebDriverWait wait;
 
    @BeforeClass
    public void setUp() {
        // ChromeOptions is optional but good for stability in some environments
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // uncomment if you want headless run
        driver = new ChromeDriver(options); // Selenium Manager auto-handles driver in most machines
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test
 
    public void captureScreenshotsOnTheInternetApp() throws Exception {
 
        // 1) Open home page
        driver.get("https://the-internet.herokuapp.com/");
        takeViewportScreenshot("01-home-page");
        // 2) Go to Form Authentication (Login page)
        driver.findElement(By.linkText("Form Authentication")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        takeViewportScreenshot("02-login-page");
        // 3) Do a WRONG login to create an error message
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("wrongPassword");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        // Wait until error message appears
        WebElement flashMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        // Take screenshot of the page (shows the error message on page)
        takeViewportScreenshot("03-wrong-login-page");
        // Take screenshot of only the error message element
        takeElementScreenshot(flashMessage, "04-error-message-only");
        // 4) Now do CORRECT login (success)
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.urlContains("/secure"));
        takeViewportScreenshot("05-success-page");
    }
 
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    // ------------------- Screenshot Helpers -------------------
    // Takes screenshot of the current visible page (viewport)
    private void takeViewportScreenshot(String fileLabel) throws IOException {
        Path screenshotsDir = Paths.get(System.getProperty("user.dir"), "screenshots");
        Files.createDirectories(screenshotsDir);
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
        Path destination = screenshotsDir.resolve(fileLabel + "_" + timestamp + ".png");
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Saved screenshot: " + destination);
    }
    // Takes screenshot of a specific element only (like a message, button, logo, etc.)
    private void takeElementScreenshot(WebElement element, String fileLabel) throws IOException {
        Path screenshotsDir = Paths.get(System.getProperty("user.dir"), "screenshots");
        Files.createDirectories(screenshotsDir);
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
        Path destination = screenshotsDir.resolve(fileLabel + "_" + timestamp + ".png");
        File source = element.getScreenshotAs(OutputType.FILE);
        Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Saved element screenshot: " + destination);
    }
}