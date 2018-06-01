package test;

import org.testng.Assert;
import org.testng.annotations.*;
import page.LinkedinHomePage;
import page.LinkedinLoginSubmitPage;


/**
 * class LinkedinLoginTest that consists of both positive and negative test cases for verification of login to Linkedin
 */
public class LinkedinLoginTest extends LinkedinBaseTest {


    /**
     * data provider that contains a combinations of test data(email and password) for succesfulLoginTest
     */
    @DataProvider
    public Object[][] ValidDataProvider() {
        return new Object[][]{
                { "mir2asrt1@gmail.com", "June0619!" },
                //{ "MIR2ASRT1@GMAIL.COM", "June0619!" },
                //{ "\tmir2asrt1@gmail.com", "June0619!" },
                //{ "Mir2asrt1@gmail.com", "June0619!" },

        };
    }

    /**
     * successfulLoginTest
     * @param userEmail
     * @param userPassword
     */
    @Test(dataProvider = "ValidDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword ) {
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Login page is not loaded");

        LinkedinHomePage linkedinHomePage = linkedinLoginPage.login(userEmail, userPassword);
        Assert.assertTrue(linkedinHomePage.isPageLoaded(),
                "Home page is not loaded");

    }

    /**
     * data provider that contains a combinations of test data(empty email or/and empty password) for verifyLoginWithEmptyUsernameAndPassword
     */
    @DataProvider
    public Object[][] EmptyValuesDataProvider() {
        return new Object[][]{
                { "", "" },
                { "", "June0619!" },
                { "mir2asrt1@gmail.com", "" },
        };
    }

    /**
     * test: verify login with empty username and password
     * @param emptyEmail
     * @param emptyPassword
     */
    @Test(dataProvider = "EmptyValuesDataProvider")
    public void verifyLoginWithEmptyUsernameAndPassword(String emptyEmail, String emptyPassword)  {
        linkedinLoginPage.login(emptyEmail, emptyPassword);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
    }

    /**
     * data provider that contains a combinations of test data(correct email and incorrect password) for verifyLoginWithValidEmailAndInvalidPassword test
     */
    @DataProvider
    public Object[][] IncorrectPasswordDataProvider() {
        return new Object[][]{
                { "mir2asrt1@gmail.com", "June0619" },
                //{ "mir2asrt@gmail.com", "JUNE0619!" },
        };
    }

    /**
     * test: verify login with valid email and invalid password
     * @param correctEmail
     * @param incorrectPassword
     */
        @Test(dataProvider = "IncorrectPasswordDataProvider")
    public void verifyLoginWithValidEmailAndInvalidPassword(String correctEmail, String incorrectPassword)  {
        Assert.assertEquals(linkedinLoginPage.getCurrentTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");

        LinkedinLoginSubmitPage linkedinLoginSubmitPage = linkedinLoginPage.loginWithInvalidData(correctEmail, incorrectPassword);
        Assert.assertEquals(linkedinLoginSubmitPage.getErrorMessageDisplayed(),
                "Hmm, that's not the right password. Please try again or request a new one.",
                "Error message for invalid password is missing or incorrect");
    }


    /**
     * data provider that contains a combinations of test data(incorrect email format and correct password) for verifyLoginWithIncorrectFormatOfEmail test
     */
    @DataProvider
    public Object[][] IncorrectEmailDataProvider() {
        return new Object[][]{
                { "mir2asrt1", "June0619!" },
                //{ "@gmail.com", "June0619!" },
                //{ "#@%^%#$@#$@#.com", "June0619!" },
                //{ "June0619!", "mir2asrt1@gmail.com" },
                //{ "<script>alert(mir2asrt1@gmail.com)</script>", "June0619!" },
        };
    }

    /**
     * test: verify login with incorrect format of email
     * @param incorrectEmail
     * @param correctPassword
     */
    @Test(dataProvider = "IncorrectEmailDataProvider")
    public void verifyLoginWithIncorrectFormatOfEmail(String incorrectEmail, String correctPassword)  {
        Assert.assertEquals(linkedinLoginPage.getCurrentTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");

        LinkedinLoginSubmitPage linkedinLoginSubmitPage = linkedinLoginPage.loginWithIncorrectEmail(incorrectEmail, correctPassword);
        Assert.assertEquals(linkedinLoginSubmitPage.getErrorForInvalidEmailDisplayed(),
                "Please enter a valid email address.",
                "Error message for invalid email format is missing or incorrect");
    }


    /**
     * test: verify possibility to log in with correct email and incorrect password (too small)
     */
    @Test
    public void verifyLoginWhenPasswordIsTooSmall() {
        Assert.assertEquals(linkedinLoginPage.getCurrentTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");

        LinkedinLoginSubmitPage linkedinLoginSubmitPage = linkedinLoginPage.loginWithSmallPassword("mir2asrt1@gmail.com", "0" );
        Assert.assertEquals(linkedinLoginSubmitPage.getErrorForInvalidPasswordDisplayed(),
                "The password you provided must have at least 6 characters.",
                "Error message for invalid password length is missing or incorrect");
    }

}

