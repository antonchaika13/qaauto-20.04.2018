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
                { "MIR2ASRT1@GMAIL.COM", "June0619!" }

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

    @Test
    public void emailInUpperCasePasswordValid() throws InterruptedException {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");
        linkedinLoginPage.login("MIR2ASRT1@GMAIL.COM", "June0619!" );

        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(webDriver);
        Assert.assertTrue(linkedinHomePage.isProfileMenuDisplayed(),"Profile menu is not displayed after login");

         //Fixme: use inheritance
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn",
                "Page title is wrong");
    }

    @Test
    public void validEmailStartedWithSpace() throws InterruptedException {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");
        linkedinLoginPage.login("\tmir2asrt1@gmail.com", "June0619!" );

        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(webDriver);
        Assert.assertTrue(linkedinHomePage.isProfileMenuDisplayed(),"Profile menu is not displayed after login");

        //Fixme: use inheritance
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn",
                "Page title is wrong");
    }

    //Negative tests

    @Test
    public void verifyLoginWithEmptyUsernameAndPassword()  {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        linkedinLoginPage.login("", "" );
        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(), "Sign in button is missing");
    }

    @Test
    public void verifyLoginWithValidEmailAndEmptyPassword()  {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        linkedinLoginPage.login("mir2asrt1@gmail.com", "" );
        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(), "Sign in button is missing");
    }

    @Test
    public void verifyLoginWithEmptyEmailAndValidPassword()  {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        linkedinLoginPage.login("", "June0619!" );
        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(), "Sign in button is missing");
    }

    @Test
    public void verifyLoginWithValidEmailAndInvalidPassword()  {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");
        linkedinLoginPage.login("mir2asrt1@gmail.com", "June0619" );

        LinkedinIncorrectLoginAndPasswordPage linkedinIncorrectLoginAndPasswordPage = new LinkedinIncorrectLoginAndPasswordPage(webDriver);
        Assert.assertEquals(linkedinIncorrectLoginAndPasswordPage.isErrorMessageDisplayed(),
                "Hmm, that's not the right password. Please try again or request a new one.",
                "Error message for invalid password is missing or incorrect");
    }

    @Test
    public void verifyLoginWithInvalidEmailAndValidPassword()  {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");
        linkedinLoginPage.login("jgdjgdkg@gmail.com", "June0619" );

        LinkedinIncorrectLoginAndPasswordPage linkedinIncorrectLoginAndPasswordPage = new LinkedinIncorrectLoginAndPasswordPage(webDriver);
        Assert.assertEquals(linkedinIncorrectLoginAndPasswordPage.isErrorForInvalidEmailDisplayed(),
                "Hmm, we don't recognize that email. Please try again.",
                "Error message for non-recognized email is missing or incorrect");
    }

    @Test
    public void verifyLoginWithIncorrectFormatOfEmail()  {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");
        linkedinLoginPage.login("mir2asrt1", "June0619!" );

        LinkedinIncorrectLoginAndPasswordPage linkedinIncorrectLoginAndPasswordPage = new LinkedinIncorrectLoginAndPasswordPage(webDriver);
        Assert.assertEquals(linkedinIncorrectLoginAndPasswordPage.isErrorForInvalidEmailDisplayed(),
                "Please enter a valid email address.",
                "Error message for invalid email format is missing or incorrect");
    }

    @Test
    public void verifyLoginWithMissedAddressInEmailAddress()  {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");
        linkedinLoginPage.login("@gmail.com", "June0619!" );

        LinkedinIncorrectLoginAndPasswordPage linkedinIncorrectLoginAndPasswordPage = new LinkedinIncorrectLoginAndPasswordPage(webDriver);
        Assert.assertEquals(linkedinIncorrectLoginAndPasswordPage.isErrorForInvalidEmailDisplayed(),
                "Please enter a valid email address.",
                "Error message for invalid email format is missing or incorrect");
    }

    @Test
    public void verifyLoginWhenSpecialSymbolsPresentInEmail()  {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");
        linkedinLoginPage.login("#@%^%#$@#$@#.com", "June0619!" );

        LinkedinIncorrectLoginAndPasswordPage linkedinIncorrectLoginAndPasswordPage = new LinkedinIncorrectLoginAndPasswordPage(webDriver);
        Assert.assertEquals(linkedinIncorrectLoginAndPasswordPage.isErrorForInvalidEmailDisplayed(),
                "Please enter a valid email address.",
                "Error message for invalid email format is missing or incorrect");
    }

    @Test
    public void verifyLoginWhenValidPasswordIsWrittenWithUppercase() {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");
        linkedinLoginPage.login("mir2asrt@gmail.com", "JUNE0619!" );

        LinkedinIncorrectLoginAndPasswordPage linkedinIncorrectLoginAndPasswordPage = new LinkedinIncorrectLoginAndPasswordPage(webDriver);
        Assert.assertEquals(linkedinIncorrectLoginAndPasswordPage.isErrorForInvalidPasswordDisplayed(),
                "Hmm, that's not the right password. Please try again or request a new one.",
                "Error message for invalid password is missing or incorrect");
    }

    @Test
    public void verifyLoginWhenValidEmailEnteredinPasswordAndValidPasswordEnteredinEmail()  {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");
        linkedinLoginPage.login("June0619!", "mir2asrt1@gmail.com" );

        LinkedinIncorrectLoginAndPasswordPage linkedinIncorrectLoginAndPasswordPage = new LinkedinIncorrectLoginAndPasswordPage(webDriver);
        Assert.assertEquals(linkedinIncorrectLoginAndPasswordPage.isErrorForInvalidEmailDisplayed(),
                "Please enter a valid email address.",
                "Error message for invalid email format is missing or incorrect");

    }

    @Test
    public void verifyJSInjectionInEmailAddressField()  {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");
        linkedinLoginPage.login("<script>alert(mir2asrt1@gmail.com)</script>", "June0619!" );

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

