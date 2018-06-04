package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * PageObject of LinkedinSetNewPasswordPage with methods and variables
 */

public class LinkedinSetNewPasswordPage extends LinkedinBasePage{

    @FindBy(xpath =" //input[@name='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath =" //input[@name='confirmPassword']")
    private WebElement confirmPasswordField;

    @FindBy(xpath =" //button[@class='form__submit']")
    private WebElement submitNewPassword;



    public LinkedinSetNewPasswordPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        waitUntilElementIsClickable(submitNewPassword,30);
        return newPasswordField.isDisplayed();
    }

    public LinkedinPasswordChangeConfirmedPage setNewUserPassword(String newPassword, String confirmPassword){
        newPasswordField.sendKeys(newPassword);
        confirmPasswordField.sendKeys(confirmPassword);
        submitNewPassword.click();
        return new LinkedinPasswordChangeConfirmedPage(webDriver);
    }
}
