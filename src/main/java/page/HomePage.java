package page;

import core.*;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static core.PropertiesFile.setPropValue;

public class HomePage extends BasePage {

    public HomePage() {
        super();
    }

   public HomePage(KeywordWeb keyword) {
        super(keyword);
   }
   public static final Logger logger = LogHelper.getLogger();

    public void goToBrowserEx1() {
        keyword.maximizeWindow();
        keyword.openUrl(PropertiesFile.getPropValue("URL_EX1"));
    }

    public void active() throws InterruptedException {
        logger.info("Active");
        Set<String> account = new HashSet<String>();
        //11
        String temp;
        while(true) {
            temp = randomString(1);
            if(account.contains(temp) == true) {
                temp = randomString(1);
            } else {
                account.add(temp);
                login(temp);
                Thread.sleep(2000);
            }
            boolean check = verifyLoginOk(PropertiesFile.getPropValue("LOGIN_OK"));
            if (check == true) {
                setPropValue("PASSWORD_DONE" , temp);
                break;
            }
        }



    }

    public boolean verifyLoginFailed(String element) {
        logger.info("Verifying login failed");
        return  keyword.verifyElementVisible(PropertiesFile.getPropValue("LOGIN_FAIL"));
    }

    public boolean verifyLoginOk(String element) {
        logger.info("Verifying login failed");
        return  keyword.verifyElementVisible(PropertiesFile.getPropValue("LOGIN_OK"));
    }

    public String randomString(int i) {
        logger.info("Random string");
        String temp = RandomStringUtils.randomAscii(i);
        return temp;
    }

    public void login(String pass) {
        logger.info("Login");
        keyword.waitForElementPresent(PropertiesFile.getPropValue("TEXT_BOX_USER"), 60);
        keyword.sendKeys(PropertiesFile.getPropValue("TEXT_BOX_USER"),
                PropertiesFile.getPropValue("USER_NAME_TEMP"));
        keyword.sendKeys(PropertiesFile.getPropValue("TEXT_BOX_PASS"),
                pass);
        keyword.clickElement(PropertiesFile.getPropValue("BTN_LOGIN"));
    }


    public void helloVN() {
        System.out.println(JsonFile.getJSONValue("name"));
    }

}
