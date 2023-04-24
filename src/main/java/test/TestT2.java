package test;

import core.BaseTest;
import org.testng.annotations.Test;
import page.HomePage;

public class TestT2 extends BaseTest {

    private HomePage objHome;
    @Test
    public void test002() throws Exception {

        System.out.println("Exercise 1:");
        System.out.println("Test case 1:");
        objHome = new HomePage(this.keyword);

        objHome.goToBrowserEx1();
//        objHome.active();

        objHome.helloVN();

    }

    @Test
    public void testCVV() {
        keyword.openUrl("https://lbbjp.sse.codesandbox.io/");
        keyword.waitForElementPresent("//option[@value='pm_card_chargeCustomerFail']",60);
        keyword.clickElement("//option[@value='pm_card_chargeCustomerFail']");
        keyword.clickElement("//span[normalize-space()='Charge']");
    }


    @Test
    public void Pinnn() throws InterruptedException {
        keyword.openUrl("https://padlet.com/ngocquyengvth/x6ad0vs5w7qtpi9x");
        keyword.waitForElementPresent("//i[@class='oricon text-grape-500']",60);
        keyword.clickElement("//i[@class='oricon text-grape-500']");
        Thread.sleep(5000);
    }


}
