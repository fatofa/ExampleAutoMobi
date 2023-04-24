package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class KeywordWeb {

    public static final Logger logger = LogHelper.getLogger();
    private static WebDriver driver;
    public KeywordWeb() {
    }

    // keyword action

    public void openBrowser(String browser) {
    logger.info("Opening browser");
        switch (browser.toUpperCase()) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "EDGE":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
        }
        logger.info("open browser successfully " + browser);
    }

    public void openUrl(String... url) {

        String rawUrl = url.length > 0 ? url[0]: "";
        if (rawUrl != null && !rawUrl.isEmpty()) {
            logger.info("Go to URL: " + rawUrl);
            logger.info("url: " + url);
            driver.get(rawUrl);
        }
    }

    public void closeBrowser() {
        logger.info("Close browser");
        driver.quit();
    }

    public void maximizeWindow() {
        logger.info("Maximizing window...");
        driver.manage().window().maximize();
    }

    public void waitForElementPresent(String element, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
    }

    //Ex1
    public void sendKeys(String element, String value) {
        logger.info("Send Keys: "+ value);
        driver.findElement(By.xpath(element)).sendKeys(value);
    }

    public void clickElement(String elemen) {
        logger.info("Click element: " + elemen);
        driver.findElement(By.xpath(elemen)).click();
    }

    public void checkElement(String element , String text) throws InterruptedException {
        logger.info("Check element: " + element);
        WebElement strElement = driver.findElement(By.xpath(element));
        assertEquals(text, strElement.getText());

    }

    public void executeJavaScript(String script) {
        logger.info("Executing JavaScript: " + script);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script);

    }


    public void clickElementByJavaScript(String element) {
        logger.info("Click Element by JavaScript: " + element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement click = driver.findElement(By.xpath(element));
        js.executeScript("arguments[0].click();" , click);
    }

    public void backByJavaScript() {
        logger.info("Back by JavaScript");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].back();");
    }

    public void verifyElementByJavaScript(String element) {
        logger.info("Verifying element by JavaScriptExecutor");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement display = driver.findElement(By.xpath(element));
        js.executeScript("arguments[0].scrollIntoView();" , display);
    }

    public void getCookies() {
        logger.info("Getting cookies");
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("cookies = " + cookies);
    }

    public boolean verifyElementVisible(String element) {
        logger.info("verifyElementVisible");
        boolean blnVerify = false;
        try {
            blnVerify =  driver.findElement(By.xpath(element)).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return blnVerify;
    }

}
