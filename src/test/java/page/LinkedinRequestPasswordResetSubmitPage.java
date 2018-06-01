package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

/**
 * PageObject of LinkedinRequestPasswordResetSubmitPage with methods and variables
 */
public class LinkedinRequestPasswordResetSubmitPage extends LinkedinBasePage {

    @FindBy(xpath = "//button[@class='resend__link']")
    private WebElement resendLinkButton;
    public boolean isPageLoaded() {
        waitUntilElementIsClickable(resendLinkButton,150);
        return resendLinkButton.isDisplayed();
    }

    /**
     * Constructor of LinkedinRequestPasswordResetSubmitPage class)
     * @param webDriver - current webDriver object
     */
    public LinkedinRequestPasswordResetSubmitPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }


    /**
     * method that allows to navigate to 'reset password' message received in email from Linkedin
     */
    public LinkedinSetNewPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "Nick, here's the link to reset your password";
        String messageTo = "mir2asrt1@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";



        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 60);
        System.out.println("Content: " + message);

        return new LinkedinSetNewPasswordPage(webDriver);
    }
}
