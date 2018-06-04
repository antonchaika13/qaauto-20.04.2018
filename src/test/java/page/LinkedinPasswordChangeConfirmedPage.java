package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinPasswordChangeConfirmedPage extends LinkedinBasePage {

    @FindBy(xpath =" //button[@class='form__cancel']")
    private WebElement goToHomePageButton;


    public LinkedinPasswordChangeConfirmedPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver,this);
    }
    public boolean isPageLoaded() {
        return goToHomePageButton.isDisplayed();
    }

    public LinkedinHomePage clickOnGoToHomeButton() {
        goToHomePageButton.click();
        return new LinkedinHomePage(webDriver);
    }
}
