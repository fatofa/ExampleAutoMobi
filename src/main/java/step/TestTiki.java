package step;

import core.KeyWordAndroid;
import core.PropertiesFile;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.net.MalformedURLException;

import static core.KeywordWeb.logger;
import static org.testng.AssertJUnit.assertTrue;

public class TestTiki {
    public TestTiki() {
    }

    KeyWordAndroid mobile = new KeyWordAndroid();
    @Given("Nguoi dung mo app va di toi man hinh danh muc")
    public void nguoiDungMoAppVaDiToiManHinhDanhMuc() throws MalformedURLException {
        PropertiesFile.setPropertiesFile();
        mobile.openApplication(PropertiesFile.getPropValue("APPIUM_URL"),
                PropertiesFile.getPropValue("PLATFORM_NAME"),
                PropertiesFile.getPropValue("PLATFORM_VERSION"),
                PropertiesFile.getPropValue("UDID"),
                PropertiesFile.getPropValue("APP_PACKAGE"),
                PropertiesFile.getPropValue("APP_ACTIVITY"));
        assertTrue(mobile.waitUntilPageContainerElement(PropertiesFile.getPropValue("BTN_DANH_MUC"),60));
        mobile.clickElement(PropertiesFile.getPropValue("BTN_DANH_MUC"));
        assertTrue(mobile.waitUntilPageContainerElement(PropertiesFile.getPropValue("BTN_ME_VA_BE"),60));
    }

    @When("Nguoi dung tab vao ([^\"]*)$")
    public void nguoiDungTabVaoDanhMuc(String danhMuc) {
        logger.info("(//android.widget.TextView[@text='" + danhMuc + "'])[2]");
        mobile.clickElement("(//android.widget.TextView[@text='" + danhMuc + "'])[2]");
    }

    @Then("Nguoi dung se thay ([^\"]*)$")
    public void nguoiDungSeThayDanhMucCon(String danhMucCon) {
        logger.info("//android.widget.TextView[@text='" + danhMucCon + "']");
        assertTrue(mobile.waitUntilPageContainerElement("//android.widget.TextView[@text='" + danhMucCon + "']", 60));
//        mobile.closeApp();

    }
}
