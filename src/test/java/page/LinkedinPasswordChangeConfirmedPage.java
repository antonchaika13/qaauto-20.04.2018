package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LinkedinPasswordChangeConfirmedPage extends LinkedinBasePage {


    public LinkedinPasswordChangeConfirmedPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver,this);
    }
    public boolean isPageLoaded() {
        return false;
    }

    public LinkedinHomePage clickOnGoToHomeButton() {
        return new LinkedinHomePage(webDriver);
    }
}
