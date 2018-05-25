import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class GmailEnterEmailPage extends LinkedinBasePage {

    private WebElement enterEmailFromGmail;
    private WebElement NextButton;


    public GmailEnterEmailPage(WebDriver webDriver) {
        super(webDriver);
        initElements();
    }

    @Override
    boolean isPageLoaded() {
        return enterEmailFromGmail.isDisplayed();
    }

    public void initElements() {
        enterEmailFromGmail = webDriver.findElement(By.xpath("//div//input[@type='email']"));
        NextButton = webDriver.findElement(By.xpath("//div[@id='identifierNext']"));
    }

    public void enterEmailAndClickNext(String emailFromGmail) {
        enterEmailFromGmail.sendKeys(emailFromGmail);
        NextButton.click();
    }

    public void getResetPasswordLinkFromEmail(){}
}
