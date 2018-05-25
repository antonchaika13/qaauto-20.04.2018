import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinkedinResetPasswordTest {
    WebDriver webDriver;

    @BeforeMethod
    public void before() {
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");
    }

    @AfterMethod
    public void after() {
        webDriver.close();
    }

    @Test
    public void successfulResetPasswordTest() {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Linkedin login page is not loaded.");

        linkedinLoginPage.clickOnForgotPasswordLink();

        LinkedinResetPasswordPage linkedinResetPasswordPage = new LinkedinResetPasswordPage(webDriver);
        Assert.assertTrue(linkedinResetPasswordPage.isPageLoaded(),
                "Linkedin reset password page is not loaded.");
        linkedinResetPasswordPage.submitEmailForResetPassword("mir2asrt1@gmail.com");

        //webDriver = new FirefoxDriver();
        webDriver.get("https://www.gmail.com");
        GmailHomePage gmailHomePage = new GmailHomePage(webDriver);
        Assert.assertTrue(gmailHomePage.isPageLoaded(),
                "Gmail email login page is not loaded.");
        gmailHomePage.enterEmailAndClickNext("mir2asrt1@gmail.com");

        GmailEnterPasswordPage gmailEnterPasswordPage = new GmailEnterPasswordPage(webDriver);
        Assert.assertTrue(gmailEnterPasswordPage.isPageLoaded(),
                "Gmail password login page is not loaded.");
        gmailEnterPasswordPage.enterPasswordAndClickNext("June0619!");

        GmailEnterEmailPage gmailEnterEmailPage = new GmailEnterEmailPage(webDriver);
        Assert.assertTrue(gmailEnterEmailPage.isPageLoaded(),
                "Gmail home page is not loaded.");
        //gmailEnterEmailPage.getResetPasswordLinkFromEmail();


    }
}
