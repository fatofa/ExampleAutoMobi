package test;

import core.BaseTest;
import org.testng.annotations.Test;
import page.HomePage;

public class PIN extends BaseTest {

    @Test
    public void Pinnn() throws InterruptedException {
        keyword.openUrl("https://padlet.com/ngocquyengvth/x6ad0vs5w7qtpi9x");
        keyword.waitForElementPresent("//i[@class='oricon text-grape-500']",60);
        keyword.clickElement("//i[@class='oricon text-grape-500']");
        Thread.sleep(5000);
    }


}
