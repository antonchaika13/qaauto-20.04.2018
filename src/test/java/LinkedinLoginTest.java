import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {

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

    @DataProvider
    public Object[][] ValidDataProvider() {
        return new Object[][]{
                { "mir2asrt1@gmail.com", "June0619!" },
                { "MIR2ASRT1@GMAIL.COM", "June0619!" },
                { "\tmir2asrt1@gmail.com", "June0619!" },
                { "Mir2asrt1@gmail.com", "June0619!" },

        };
    }

    @Test(dataProvider = "ValidDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword ) {

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");
        linkedinLoginPage.login(userEmail, userPassword);

        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(webDriver);
        Assert.assertTrue(linkedinHomePage.isProfileMenuDisplayed(),"Profile menu is not displayed after login");


        //Fixme: use inheritance
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn",
                "Home page title is wrong.");
    }



    //Negative tests

    @DataProvider
    public Object[][] EmptyValuesDataProvider() {
        return new Object[][]{
                { "", "" },
                { "", "June0619!" },
                { "mir2asrt1@gmail.com", "" },
        };
    }

    @Test(dataProvider = "EmptyValuesDataProvider")
    public void verifyLoginWithEmptyUsernameAndPassword(String emptyEmail, String emptyPassword)  {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        linkedinLoginPage.login(emptyEmail, emptyPassword);
        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(), "Sign in button is missing");
    }

    @DataProvider
    public Object[][] IncorrectPasswordDataProvider() {
        return new Object[][]{
                { "mir2asrt1@gmail.com", "June0619" },
                { "mir2asrt@gmail.com", "JUNE0619!" },
        };
    }

        @Test(dataProvider = "IncorrectPasswordDataProvider")
    public void verifyLoginWithValidEmailAndInvalidPassword(String correctEmail, String incorrectPassword)  {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");
        linkedinLoginPage.login(correctEmail, incorrectPassword);

        LinkedinIncorrectLoginAndPasswordPage linkedinIncorrectLoginAndPasswordPage = new LinkedinIncorrectLoginAndPasswordPage(webDriver);
        Assert.assertEquals(linkedinIncorrectLoginAndPasswordPage.isErrorMessageDisplayed(),
                "Hmm, that's not the right password. Please try again or <a class=\"password-reminder-link\" href=\"/uas/request-password-reset\">request a new one.</a>",
                "Error message for invalid password is missing or incorrect");
    }


    @DataProvider
    public Object[][] IncorrectEmailDataProvider() {
        return new Object[][]{
                { "mir2asrt1", "June0619!" },
                { "@gmail.com", "June0619!" },
                { "#@%^%#$@#$@#.com", "June0619!" },
                { "June0619!", "mir2asrt1@gmail.com" },
                { "<script>alert(mir2asrt1@gmail.com)</script>", "June0619!" },
        };
    }

    @Test(dataProvider = "IncorrectEmailDataProvider")
    public void verifyLoginWithIncorrectFormatOfEmail(String incorrectEmail, String correctPassword)  {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");
        linkedinLoginPage.login(incorrectEmail, correctPassword);

        LinkedinIncorrectLoginAndPasswordPage linkedinIncorrectLoginAndPasswordPage = new LinkedinIncorrectLoginAndPasswordPage(webDriver);
        Assert.assertEquals(linkedinIncorrectLoginAndPasswordPage.isErrorForInvalidEmailDisplayed(),
                "Please enter a valid email address.",
                "Error message for invalid email format is missing or incorrect");
    }


    @Test
    public void verifyLoginWhenPasswordIsTooSmall() {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");
        linkedinLoginPage.login("mir2asrt1@gmail.com", "0" );

        LinkedinIncorrectLoginAndPasswordPage linkedinIncorrectLoginAndPasswordPage = new LinkedinIncorrectLoginAndPasswordPage(webDriver);
        Assert.assertEquals(linkedinIncorrectLoginAndPasswordPage.isErrorForInvalidPasswordDisplayed(),
                "The password you provided must have at least 6 characters.",
                "Error message for invalid password length is missing or incorrect");
    }

    @Test
    public void verifyLoginWhenEmailAddressValueEqualZero()  {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");
        linkedinLoginPage.login("0", "June0619!" );

        LinkedinIncorrectLoginAndPasswordPage linkedinIncorrectLoginAndPasswordPage = new LinkedinIncorrectLoginAndPasswordPage(webDriver);
        Assert.assertEquals(linkedinIncorrectLoginAndPasswordPage.isErrorForInvalidEmailDisplayed(),
                "Be sure to include \"+\" and your country code.",
                "Error message for invalid password is missing or incorrect");
    }

}

