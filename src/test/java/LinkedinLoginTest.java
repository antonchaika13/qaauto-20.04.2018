import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LinkedinLoginTest {

    @Test
    public void successfulLoginTest() {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");



        WebElement userEmailField = webDriver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = webDriver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        userEmailField.sendKeys("mir2asrt1@gmail.com");
        userPasswordField.sendKeys("June0619!");
        signInButton.click();

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn", "Page title is wrong");
    }
    }

