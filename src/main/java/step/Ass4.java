package step;

import core.KeyWordAndroid;
import core.PropertiesFile;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.net.MalformedURLException;
import java.util.List;

import static core.KeyWordAndroid.Direction.UP;

public class Ass4 {
    public Ass4() {

    }
    KeyWordAndroid mobile = new KeyWordAndroid();

    @Given("^Mobile browser is on the google page$")
    public void mobileBrowserIsOnTheGooglePage() throws MalformedURLException, InterruptedException {
        PropertiesFile.setPropertiesFile();
        mobile.openMobileWebApp(PropertiesFile.getPropValue("APPIUM_URL"),
                PropertiesFile.getPropValue("PLATFORM_NAME"),
                PropertiesFile.getPropValue("PLATFORM_VERSION"),
                PropertiesFile.getPropValue("UDID"));
        mobile.navigateToUrl("https://www.google.com/");
        Thread.sleep(5000);

    }

    @When("Enter {string} in to search box")
    public void enterInToSearchBox(String arg0) throws InterruptedException {
        mobile.inputText(PropertiesFile.getPropValue("CLICK_LINK_URL"),arg0);
        mobile.pressEnter();
        Thread.sleep(5000);
    }

    @Then("Result {string} is shown")
    public void resultIsShown(String arg0) throws InterruptedException {
        Thread.sleep(5000);
        mobile.pasteShould(arg0);
        
    }

    @And("Following result is shown")
    public void followingResultIsShown(DataTable table) throws InterruptedException {
        List<String> data = table.asList();
        Thread.sleep(5000);
        mobile.pasteShould(data.get(1));
        Thread.sleep(5000);
        mobile.pasteShould(data.get(2));

        Thread.sleep(5000);
        mobile.closeApp();
    }


}
