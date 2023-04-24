package core;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class KeyWordAndroid {

    public KeyWordAndroid() {

    }
    private static AndroidDriver<?> mobile;
    public static final Logger logger = LogHelper.getLogger();


    /**
     * Android native web app action keywords
     */

    public void openMobileWebApp(String androidURL, String platformName, String platformVersion, String udid) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
        caps.setCapability("chromedriverExecutable", "F:\\DocumentJava\\chromedriver_win32\\chromedriver.exe");
        caps.setCapability("platformName", platformName);
        caps.setCapability("platformVersion", platformVersion);
        caps.setCapability("udid", udid);

        mobile = new AndroidDriver(new URL(androidURL), caps);
        mobile.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void openApplication(String androidURL, String platformName, String platformVersion, String udid, String appPackage, String appActivity) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", platformName);
        caps.setCapability("platformVersion", platformVersion);
        caps.setCapability("udid", udid);
        caps.setCapability("appPackage", appPackage);
        caps.setCapability("appActivity", appActivity);

        mobile = new AndroidDriver(new URL(androidURL), caps);
        mobile.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void tapElement(String xpath) {
        WebElement element = mobile.findElement(By.xpath(xpath));
        TouchAction action = new TouchAction(mobile);
        action.tap(TapOptions.tapOptions().withElement(ElementOption.element(element))).perform();
    }

    public void swipeScreen(Direction dir) {
        System.out.println("swipeScreen(): dir: '" + dir + "'");

        final int ANIMATION_TIME = 200; // ms
        final int PRESS_TIME = 200; // ms
        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Dimension dims = mobile.manage().window().getSize();

        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(mobile)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    /**
     * Mobile web app action keywords
     */

    public void clickElement(String xpath) {
        logger.info("Click element: " + xpath);
        WebElement element = mobile.findElement(By.xpath(xpath));
        element.click();
    }

    public void inputText(String xpath, String text) throws InterruptedException {
        WebElement element = mobile.findElement(By.xpath(xpath));
        element.clear();
        element.sendKeys(text);
        //pressEnter();
        Thread.sleep(2000);
       }

    public void pressEnter() {
        mobile.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public void navigateToUrl(String url) {
        mobile.navigate().to(url);
    }

    public void pasteShould(String expected) {
        String bodyText = mobile.findElement(By.cssSelector("body")).getText();
        Assert.assertTrue(bodyText.contains(expected), "Text not found");
    }

    public void closeApp() {
        mobile.quit();
    }

    public boolean waitUntilPageContainerElement(String xpath, long timeout) {
        logger.info("waitUntilPageContainerElement");
        try {
            WebDriverWait wait = new WebDriverWait(mobile , timeout);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            if (element != null) {
                logger.info("Element is display");
                return true;
            }
            logger.error("Element is not display");
        } catch (TimeoutException e) {
            logger.error("TimeoutException");
        }
        return false;
    }

    public boolean pageShouldContainElement(String xpath) {
        logger.info("pageShouldContainElement");
        boolean result = false;
        if(waitUntilPageContainerElement(xpath , 5)) {
            logger.info("true");
            result =  true;
        } else {
            logger.info("false");
            result = false;
        }
        logger.info("result: " + result);
        return result;

    }

    public String getAttribute(String xpath, String attribute) {
        WebElement element = mobile.findElement(By.xpath(xpath));
        return element.getAttribute(attribute);
    }

}
