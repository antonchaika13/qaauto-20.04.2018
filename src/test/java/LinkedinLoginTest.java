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

    @Test
    public void successfulLoginTest() {

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");
        linkedinLoginPage.login("mir2asrt1@gmail.com", "June0619!" );

        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(webDriver);
        Assert.assertTrue(linkedinHomePage.isProfileMenuDisplayed(),"Profile menu is not displayed after login");


        //Fixme: use inheritance
        Assert.assertEquals(linkedinLoginPage.getCurrentPageTitle(), "LinkedIn",
                "Home page title is wrong.");
    }

    @Test
    //Email in upper case, password valid
    public void successfulLoginTest2() throws InterruptedException {

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is missing");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        userEmailField.sendKeys("MIR2ASRT1@GMAIL.COM");

        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        userPasswordField.sendKeys("June0619!");

        signInButton.click();
        sleep(3000);

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn", "Page title is wrong");
    }

    @Test
    //valid email started with space, valid password
    public void successfulLoginTest3() throws InterruptedException {

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is missing");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        userEmailField.sendKeys("\tmir2asrt1@gmail.com");

        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        userPasswordField.sendKeys("June0619!");

        signInButton.click();
        sleep(3000);

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn", "Page title is wrong");

    }

    @Test
    public void verifyLoginWithEmptyUsernameAndPassword()  {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        linkedinLoginPage.login("", "" );
        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(), "Sign in button is missing");
    }

    @Test
    //Valid email and empty password
    public void negativeLoginTest2() throws InterruptedException {

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is missing");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        userEmailField.sendKeys("mir2asrt1@gmail.com");

        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        userPasswordField.sendKeys("");

        signInButton.click();
        sleep(3000);
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is missing");
    }

    @Test
    //Empty email and valid password
    public void negativeLoginTest3() throws InterruptedException {

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is missing");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        userEmailField.sendKeys("");

        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        userPasswordField.sendKeys("June0619!");

        signInButton.click();
        sleep(3000);
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is missing");
    }

    @Test
    //Valid email and invalid password
    public void negativeLoginTest4() throws InterruptedException {

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is missing");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        userEmailField.sendKeys("mir2asrt1@gmail.com");

        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        userPasswordField.sendKeys("June0619");

        signInButton.click();
        sleep(3000);

        WebElement errorInvalidPassword = webDriver.findElement(By.xpath("//span[@id='session_password-login-error']"));
        Assert.assertEquals(errorInvalidPassword.getText(),
                "Hmm, that's not the right password. Please try again or request a new one.",
                "Error message for invalid password is missing or incorrect");
    }

    @Test
    //Invalid email and valid password
    public void negativeLoginTest5() throws InterruptedException {

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is missing");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        userEmailField.sendKeys("jgdjgdkg@gmail.com");

        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        userPasswordField.sendKeys("June0619!");

        signInButton.click();
        sleep(3000);

        WebElement errorInvalidEmail = webDriver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        Assert.assertEquals(errorInvalidEmail.getText(), "Hmm, we don't recognize that email. Please try again.",
                "Error message for invalid email is missing or incorrect");
    }

    @Test
    //No @ or domain in email address
    public void negativeLoginTest6() throws InterruptedException {

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is missing");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        userEmailField.sendKeys("mir2asrt1");

        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        userPasswordField.sendKeys("June0619!");

        signInButton.click();
        sleep(3000);

        WebElement errorInvalidFormat = webDriver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        Assert.assertEquals(errorInvalidFormat.getText(), "Please enter a valid email address.",
                "Error message for invalid email format is missing or incorrect");
    }

    @Test
    //Missed address in email address
    public void negativeLoginTest7() throws InterruptedException {

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is missing");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        userEmailField.sendKeys("@gmail.com");

        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        userPasswordField.sendKeys("June0619!");

        signInButton.click();
        sleep(3000);

        WebElement errorInvalidFormat = webDriver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        Assert.assertEquals(errorInvalidFormat.getText(), "Please enter a valid email address.",
                "Error message for invalid email format is missing or incorrect");


    }

    @Test
    //Special symbols present in email address
    public void negativeLoginTest8() throws InterruptedException {

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is missing");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        userEmailField.sendKeys("#@%^%#$@#$@#.com");

        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        userPasswordField.sendKeys("June0619!");

        signInButton.click();
        sleep(3000);

        WebElement errorInvalidFormat = webDriver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        Assert.assertEquals(errorInvalidFormat.getText(), "Please enter a valid email address.",
                "Error message for invalid email format is missing or incorrect");


    }

    @Test
    //email and password valid, but password written with uppercase
    public void negativeLoginTest9() throws InterruptedException {

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is missing");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        userEmailField.sendKeys("mir2asrt@gmail.com");

        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        userPasswordField.sendKeys("JUNE0619!");

        signInButton.click();
        sleep(3000);

        WebElement errorInvalidPassword = webDriver.findElement(By.xpath("//span[@id='session_password-login-error']"));
        Assert.assertEquals(errorInvalidPassword.getText(),
                "Hmm, that's not the right password. Please try again or request a new one.",
                "Error message for invalid password is missing or incorrect");

    }

    @Test
    //valid email entered in password field, valid password entered in email field
    public void negativeLoginTest10() throws InterruptedException {

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is missing");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        userEmailField.sendKeys("June0619!");

        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        userPasswordField.sendKeys("mir2asrt1@gmail.com");

        signInButton.click();
        sleep(3000);

        WebElement errorInvalidFormat = webDriver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        Assert.assertEquals(errorInvalidFormat.getText(), "Please enter a valid email address.",
                "Error message for invalid email format is missing or incorrect");

    }

    @Test
    //JS injection in email address field
    public void negativeLoginTest11() throws InterruptedException {

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is missing");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        userEmailField.sendKeys("<script>alert(mir2asrt1@gmail.com)</script>");

        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        userPasswordField.sendKeys("June0619!");

        signInButton.click();
        sleep(3000);

        WebElement errorInvalidFormat = webDriver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        Assert.assertEquals(errorInvalidFormat.getText(), "Please enter a valid email address.",
                "Error message for invalid email format is missing or incorrect");


    }

    @Test
    //email address valid, password is too small
    public void negativeLoginTest12() throws InterruptedException {

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is missing");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        userEmailField.sendKeys("mir2asrt1@gmail.com");

        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        userPasswordField.sendKeys("0");

        signInButton.click();
        sleep(3000);

        WebElement errorPasswordLength = webDriver.findElement(By.xpath("//span[@id='session_password-login-error']"));
        Assert.assertEquals(errorPasswordLength.getText(), "The password you provided must have at least 6 characters.",
                "Error message for invalid password length is missing or incorrect");


    }

    @Test
    //email address value=0, password is valid
    public void negativeLoginTest13() throws InterruptedException {

        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in button is missing");

        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        userEmailField.sendKeys("0");

        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        userPasswordField.sendKeys("June0619!");

        signInButton.click();
        sleep(3000);

        WebElement errorInvalidFormat = webDriver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        Assert.assertEquals(errorInvalidFormat.getText(), "Be sure to include + and your country code.",
                "Error message for invalid email format is missing or incorrect");
    }

}

