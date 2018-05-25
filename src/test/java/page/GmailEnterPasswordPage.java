package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class GmailEnterPasswordPage extends LinkedinBasePage {

    private WebElement enterPasswordFromGmail;
    private WebElement NextButtonAfterEnteredPassword;


    public GmailEnterPasswordPage(WebDriver webDriver) {
        super(webDriver);
        initElements();
    }

    @Override
    public boolean isPageLoaded() {
        return enterPasswordFromGmail.isDisplayed();
    }

    public void initElements() {
        enterPasswordFromGmail = webDriver.findElement(By.xpath("//input[@type='password']"));
        NextButtonAfterEnteredPassword = webDriver.findElement(By.xpath("//div[@id='passwordNext']"));
    }

    public void enterPasswordAndClickNext(String passwordFromGmail) {
        enterPasswordFromGmail.sendKeys(passwordFromGmail);
        NextButtonAfterEnteredPassword.click();
    }
}
