package runner;

import core.KeyWordAndroid;
import core.PropertiesFile;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.junit.runner.RunWith;
import org.testng.annotations.*;

import java.net.MalformedURLException;

//@RunWith(Cucumber.class)
@CucumberOptions (
        features = {"src/main/resources/feature/ShopStyleBrands.feature"},
        glue = {"step"}
//        tags = {"@TC_001" , "@TC_003"}
)

public class MobileTestNG {
    protected KeyWordAndroid mobile;

    public MobileTestNG() {
        mobile = new KeyWordAndroid();
    }
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        PropertiesFile.setPropertiesFile();
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @BeforeTest
    public void beforeTest() throws MalformedURLException {
        mobile.openApplication(PropertiesFile.getPropValue("APPIUM_URL"),
                PropertiesFile.getPropValue("PLATFORM_NAME"),
                PropertiesFile.getPropValue("PLATFORM_VERSION"),
                PropertiesFile.getPropValue("UDID"),
                PropertiesFile.getPropValue("APP_PACKAGE"),
                PropertiesFile.getPropValue("APP_ACTIVITY"));
    }

    @Test(dataProvider = "scenarios")
    public void testRunner(PickleWrapper pickle, FeatureWrapper cucumber) {
        testNGCucumberRunner.runScenario(pickle.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        mobile.closeApp();
        testNGCucumberRunner.finish();
    }

}
