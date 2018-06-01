package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page.LinkedinBasePage;
import util.GMailService;

/**
 * PageObject of LinkedinRequestPasswordResetPage with methods and variables
 */
public class LinkedinRequestPasswordResetPage extends LinkedinBasePage {
    @FindBy(xpath ="//div/input[@ name='userName']")
    private WebElement userEmailField;

    @FindBy(xpath ="//div/button[@ class='form__submit']")
    private WebElement submitButton;


    /**
     * Constructor of LinkedinRequestPasswordResetPage class)
     * @param webDriver - current webDriver object
     */
    public LinkedinRequestPasswordResetPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    /**
     * method that checks, is current page loaded by presence of user email input field
     */
    public boolean isPageLoaded() {
        return userEmailField.isDisplayed();
    }

    /**
     * method that allows to make a request for reset password by enter email and submit
     * @param userEmail
     * @return LinkedinRequestPasswordResetSubmitPage should open
     */
    public LinkedinRequestPasswordResetSubmitPage submitUserEmail (String userEmail){
        //gMailService = new GMailService();
        gMailService.connect();
        userEmailField.sendKeys(userEmail);
        submitButton.click();
        return new LinkedinRequestPasswordResetSubmitPage(webDriver);
    }
}
