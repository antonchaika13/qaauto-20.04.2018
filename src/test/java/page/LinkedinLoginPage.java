package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinLoginPage extends LinkedinBasePage {

    @FindBy(xpath ="//input[@id='login-email']")
    private WebElement userEmailField;

    @FindBy(xpath ="//input[@id='login-password']")
    private WebElement userPasswordField;

    @FindBy(xpath ="//input[@id='login-submit']")
    private WebElement signInButton;

    @FindBy(xpath ="//a[@ class='link-forgot-password']")
    private WebElement forgotPasswordButton;

    public LinkedinLoginPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @Override
    public boolean isPageLoaded() {
        return signInButton.isDisplayed();
    }


    public LinkedinHomePage login(String userEmail, String userPassword) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();
        //return new LinkedinHomePage(webDriver);
        return PageFactory.initElements(webDriver, LinkedinHomePage.class);

    }

    public LinkedinLoginSubmitPage loginWithInvalidData(String userEmail, String userPassword) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();
        return new LinkedinLoginSubmitPage(webDriver);

    }

    public void clickOnForgotPasswordLink() {
        //userEmailField.sendKeys(userEmail);
        //userPasswordField.sendKeys(userPassword);
        //signInButton.click();
        forgotPasswordButton.click();

    }

}
