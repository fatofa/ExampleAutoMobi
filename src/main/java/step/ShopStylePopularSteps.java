package step;

import core.KeyWordAndroid;
import core.LogHelper;
import core.PropertiesFile;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ShopStylePopularSteps {

    public ShopStylePopularSteps() {
    }
    public static final Logger logger = LogHelper.getLogger();
    KeyWordAndroid mobile = new KeyWordAndroid();
    protected String productNameInList = new String("");
    protected String productPriceInList = new String("");
    protected String productNameInDetail = new String("");
    protected String productPriceInDetail = new String("");

    @When("User chooses the first lowest price product in the Shoes")
    public void userChoosesTheFirstLowestPriceProductInTheShoes() throws InterruptedException {
        logger.info("Go to tab POPULAR then go to the SHOES");
        mobile.clickElement(PropertiesFile.getPropValue("BTN_POPULAR"));
        verifyPresentTabPopular();
        mobile.clickElement(PropertiesFile.getPropValue("BTN_SHOES"));
        verifyPresentScreenShopMenShoes();
        logger.info("Order the list by lowest price");
        Thread.sleep(5000);
        mobile.clickElement(PropertiesFile.getPropValue("BTN_LOWEST_PRICE"));
        verifyShopMenShoesWithOrderByLowestPrice();

        productNameInList = mobile.getAttribute(PropertiesFile.getPropValue("TXT_PRODUCT_1_NAME_XPATH"),
                "text");
        logger.info("productNameInList: " + productNameInList );
        productPriceInList = mobile.getAttribute(PropertiesFile.getPropValue("TXT_PRODUCT_1_PRICE_XPATH"),
                "text");
        logger.info("productPriceInList: " + productPriceInList );
        logger.info("Chooses the first lowest price product in the Shoes");
        mobile.clickElement(PropertiesFile.getPropValue("TXT_PRODUCT_1_NAME_XPATH"));

    }

    public void verifyPresentTabPopular() {
        logger.info("verify Present tab POPULAR");
        mobile.waitUntilPageContainerElement(PropertiesFile.getPropValue("BTN_SHOES"), 60);
//        assertTrue(mobile.pageShouldContainElement(PropertiesFile.getPropValue("BTN_") , 60));
    }

    public void verifyPresentScreenShopMenShoes() {
        logger.info("Verify present screen Shop Men Shoes");
        mobile.waitUntilPageContainerElement(PropertiesFile.getPropValue("BTN_LOWEST_PRICE") , 60);
        assertTrue(mobile.pageShouldContainElement(PropertiesFile.getPropValue("BTN_LOWEST_PRICE")));
    }

    public void verifyShopMenShoesWithOrderByLowestPrice() throws InterruptedException {
        logger.info("Verify product name with order lowest price");

        logger.info("Verify product price with order lowest price");
        Thread.sleep(5000);
    }

    @Then("User sees products name, product price and Buy now button")
    public void userSeesProductsNameProductPriceAndBuyNowButton() throws InterruptedException {

        mobile.waitUntilPageContainerElement(PropertiesFile.getPropValue("BTN_BUY_NOW") , 60);
        productNameInDetail = mobile.getAttribute(PropertiesFile.getPropValue("TXT_DETAILS_PRODUCT_NAME"),
                "text");
        productPriceInDetail = mobile.getAttribute(PropertiesFile.getPropValue("TXT_DETAILS_PRODUCT_PRICE"),
                "text");
        logger.info("productNameInList: " + productPriceInList );
        logger.info("productPriceInList: " + productPriceInList );
        assertEquals(productNameInList, productNameInDetail);
        assertEquals(productPriceInList, productPriceInDetail);
        Thread.sleep(5000);

        while (true) {
            Thread.sleep(2000);
            if (mobile.pageShouldContainElement(PropertiesFile.getPropValue("BTN_BACK_PRODUCT"))) {
                logger.info("Click button Back as it still exists");
                mobile.clickElement(PropertiesFile.getPropValue("BTN_BACK_PRODUCT"));
                Thread.sleep(2000);
            } else {
                logger.info("NOT CLICK");
                break;
            }
        }

         while (true) {
            Thread.sleep(2000);
            if (mobile.pageShouldContainElement(PropertiesFile.getPropValue("BTN_BACK"))) {
                logger.info("Click button Back as it still exists");
                mobile.clickElement(PropertiesFile.getPropValue("BTN_BACK"));
                Thread.sleep(2000);
            } else {
                logger.info("NOT CLICK");
                break;
            }
        }
    }



    @When("User clicks on the Categories")
    public void userClicksOnTheCategories() {
    }

    @Then("User sees the items of the Categories")
    public void userSeesTheItemsOfTheCategories() {
    }




}
