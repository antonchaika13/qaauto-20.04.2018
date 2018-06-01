package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * PageObject of LinkedinSetNewPasswordPage with methods and variables
 */

public class LinkedinSetNewPasswordPage extends LinkedinBasePage{

    public LinkedinSetNewPasswordPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return false;
    }

    public LinkedinPasswordChangeConfirmedPage setNewUserPassword(){
        return new LinkedinPasswordChangeConfirmedPage(webDriver);
    }
}
