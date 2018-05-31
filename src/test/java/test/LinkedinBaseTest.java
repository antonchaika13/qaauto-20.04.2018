package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.LinkedinLoginPage;

/**
 * Base class for all 'test' classes
 */
public class LinkedinBaseTest {

    WebDriver webDriver;
    LinkedinLoginPage linkedinLoginPage;

    /**
     * method that will make a pre-conditions for every test: will open a browser and go to linkedin.com
     */
    @BeforeMethod
    public void before() {
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");
        linkedinLoginPage = new LinkedinLoginPage(webDriver);
    }

    /**
     * method that will make a post-conditions for every test: will close a browser
     */
    @AfterMethod
    public void after() {
        webDriver.close();
    }
}
