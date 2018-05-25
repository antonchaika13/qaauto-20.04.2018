package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.LinkedinBasePage;

public class LinkedinLoginSubmitPage extends LinkedinBasePage {

    private WebElement errorMessageInvalidPassword;
    private WebElement errorInvalidEmailFormat;
    private WebElement errorInvalidPassword;


    public LinkedinLoginSubmitPage(WebDriver webDriver) {
        super(webDriver);
        initElements();
    }

    @Override
    boolean isPageLoaded() {
        return errorMessageInvalidPassword.isDisplayed();
    }

    public void initElements(){
        errorMessageInvalidPassword = webDriver.findElement(By.xpath("//span[@id='session_password-login-error']"));
        errorInvalidEmailFormat = webDriver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        errorInvalidPassword = webDriver.findElement(By.xpath("//span[@id='session_password-login-error']"));
    }

    public String getErrorMessageDisplayed(){return errorMessageInvalidPassword.getText(); }
    public String getErrorForInvalidEmailDisplayed(){return errorInvalidEmailFormat.getText(); }
    public String getErrorForInvalidPasswordDisplayed(){return errorInvalidPassword.getText(); }
}
