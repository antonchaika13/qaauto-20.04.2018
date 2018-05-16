import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinIncorrectLoginAndPasswordPage {
    private WebDriver webDriver;

    private WebElement errorMessageInvalidPassword;
    private WebElement errorInvalidEmailFormat;
    private WebElement errorInvalidPassword;


    public LinkedinIncorrectLoginAndPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    public void initElements(){
        errorMessageInvalidPassword = webDriver.findElement(By.xpath("//span[@id='session_password-login-error']"));
        errorInvalidEmailFormat = webDriver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        errorInvalidPassword = webDriver.findElement(By.xpath("//span[@id='session_password-login-error']"));
    }

    public String isErrorMessageDisplayed(){return errorMessageInvalidPassword.getText(); }
    public String isErrorForInvalidEmailDisplayed(){return errorInvalidEmailFormat.getText(); }
    public String isErrorForInvalidPasswordDisplayed(){return errorInvalidPassword.getText(); }
}
