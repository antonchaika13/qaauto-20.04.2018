package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page.LinkedinBasePage;

public class LinkedinRequestPasswordResetPage extends LinkedinBasePage {
    @FindBy(xpath ="//div/input[@ name='userName']")
    private WebElement userEmailField;

    @FindBy(xpath ="//div/button[@ class='form__submit']")
    private WebElement submitButton;

    public LinkedinRequestPasswordResetPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return userEmailField.isDisplayed();
    }

    public LinkedinRequestPasswordResetSubmitPage submitUserEmail (String userEmail){
        userEmailField.sendKeys(userEmail);
        submitButton.click();
        return new LinkedinRequestPasswordResetSubmitPage(webDriver);
    }
}
