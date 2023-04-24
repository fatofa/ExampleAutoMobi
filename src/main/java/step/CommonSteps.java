package step;

import core.KeyWordAndroid;
import core.LogHelper;
import core.PropertiesFile;
import io.cucumber.java.en.Given;
import org.slf4j.Logger;

public class CommonSteps {

    public CommonSteps() {

    }
    public static final Logger logger = LogHelper.getLogger();
    KeyWordAndroid mobile = new KeyWordAndroid();
    @Given("User goes to the Shop Men")
    public void userGoesToTheShopMen() {
        mobile.clickElement(PropertiesFile.getPropValue("BTN_NOT_NOW"));
        mobile.waitUntilPageContainerElement(PropertiesFile.getPropValue("BUTTON_SHOP_MEN"),60);
        mobile.clickElement(PropertiesFile.getPropValue("BUTTON_SHOP_MEN"));
        verifyPresentScreenShopMenHome();
    }
    @Given("User is in the home screen of Shop Men")
    public void userIsInTheHomeScreenOfShopMen() throws InterruptedException {
        Thread.sleep(5000);
        //mobile.clickElement(PropertiesFile.getPropValue("BTN_SHOES"));
        while (true) {
            if (mobile.pageShouldContainElement(PropertiesFile.getPropValue("BTN_BACK"))) {
                logger.info("Click button Back as it still exists");
                mobile.clickElement(PropertiesFile.getPropValue("BTN_BACK"));
                Thread.sleep(2000);
            } else {
                logger.info("NOT CLICK");
                break;
            }

        }
//        if (mobile.pageShouldContainElement(PropertiesFile.getPropValue("BTN_HOME"))) {
//            logger.info("Click button Back as it still exists");
//            mobile.clickElement(PropertiesFile.getPropValue("BTN_HOME"));
//            Thread.sleep(2000);
//        }
    }

    public void verifyPresentScreenShopMenHome() {
        mobile.waitUntilPageContainerElement(PropertiesFile.getPropValue("BTN_SHOES"), 60);
        mobile.waitUntilPageContainerElement(PropertiesFile.getPropValue("BTN_HOME"), 60);
        mobile.waitUntilPageContainerElement(PropertiesFile.getPropValue("BTN_CATEGORIES"), 60);
        mobile.waitUntilPageContainerElement(PropertiesFile.getPropValue("BTN_BRANDS"), 60);
    }

}
