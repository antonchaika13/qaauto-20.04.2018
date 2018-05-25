package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.LinkedinBasePage;

public class LinkedinResetPasswordPage extends LinkedinBasePage {
    WebElement enterEmailForResetPassword;
    WebElement submitButtonForResetPassword;



    public LinkedinResetPasswordPage(WebDriver webDriver) {
            super(webDriver);
            initElements();
        }

    private void initElements(){
        enterEmailForResetPassword = webDriver.findElement(By.xpath("//div/input[@ name='userName']"));
        submitButtonForResetPassword = webDriver.findElement(By.xpath("//div/button[@ class='form__submit']"));
    }

        @Override
        public boolean isPageLoaded() {
        return true;
    }

    public void submitEmailForResetPassword(String enterEmail) {
        enterEmailForResetPassword.sendKeys(enterEmail);
        submitButtonForResetPassword.click();
    }
}
