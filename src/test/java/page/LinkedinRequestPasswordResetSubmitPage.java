package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

public class LinkedinRequestPasswordResetSubmitPage extends LinkedinBasePage {

    @FindBy(xpath = "//button[@class='resend__link']")
    private WebElement resendLinkButton;
    public boolean isPageLoaded() {
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
        String messageSubject = "enter email subj here";
        String messageTo = "sst.tau@gmail.com";
        String messageFrom = "SST TAU <sst.tau@gmail.com>";

        GMailService gMailService = new GMailService();
        gMailService.connect();
        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 10);
        System.out.println("Content: " + message);

        return new LinkedinSetNewPasswordPage(webDriver);
    }
}
