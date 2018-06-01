package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * PageObject of LinkedinLoginSubmitPage with methods and variables
 */
public class LinkedinLoginSubmitPage extends LinkedinBasePage {

    private WebElement errorMessageInvalidPassword;
    private WebElement errorInvalidEmailFormat;
    private WebElement errorInvalidPassword;

    /**
     * Constructor of LinkedinLoginSubmitPage class)
     * @param webDriver - current webDriver object
     */
    public LinkedinLoginSubmitPage(WebDriver webDriver) {
        super(webDriver);
        initElements();
    }

    /**
     * method that will check, is LoginSumbit page loaded or not by presence of error message for invalid password
     * @return
     */
    @Override
    boolean isPageLoaded() {
        return errorMessageInvalidPassword.isDisplayed();
    }

    /**
     * method that initialize weblements
     */
    public void initElements(){
        errorMessageInvalidPassword = webDriver.findElement(By.xpath("//span[@id='session_password-login-error']"));
        errorInvalidEmailFormat = webDriver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        errorInvalidPassword = webDriver.findElement(By.xpath("//span[@id='session_password-login-error']"));
    }

    /**
     * a group of 3 similar methods that will get the text of error messages (depends of entered test data)
     * @return text
     */
    public String getErrorMessageDisplayed(){return errorMessageInvalidPassword.getText(); }
    public String getErrorForInvalidEmailDisplayed(){return errorInvalidEmailFormat.getText(); }
    public String getErrorForInvalidPasswordDisplayed(){return errorInvalidPassword.getText(); }
}
