package step;

import core.KeyWordAndroid;
import core.PropertiesFile;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.AssertJUnit.assertEquals;

public class ShopStyleBrandsStep {

    public ShopStyleBrandsStep() {

    }

    KeyWordAndroid mobile = new KeyWordAndroid();

    @When("User search the a ([^\"]*)$")
    public void userSearchTheABrand(String brand) throws InterruptedException {
        mobile.clickElement(PropertiesFile.getPropValue("BTN_BRANDS"));
        mobile.waitUntilPageContainerElement(PropertiesFile.getPropValue("TAB_BRANDS_SELECTED") , 60);
        mobile.inputText(PropertiesFile.getPropValue("TEXT_BOX_SEARCH"), brand);
    }

    @Then("User sees the ([^\"]*)$")
    public void userSeesTheBrand_name(String brand_name) {
        mobile.waitUntilPageContainerElement(PropertiesFile.getPropValue("TXT_BRANDS_NAME_1_XPATH") , 60);
        String strBrandName = mobile.getAttribute(PropertiesFile.getPropValue("TXT_BRANDS_NAME_1_XPATH") , "text");
        assertEquals(strBrandName, brand_name);

    }

}
