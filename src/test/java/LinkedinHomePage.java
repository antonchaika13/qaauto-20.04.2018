import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinHomePage extends LinkedinBasePage {

    private WebElement profileMenu;
    private WebElement searchField;

    public LinkedinHomePage(WebDriver webDriver) {
        super(webDriver);
        initElements();
    }

    @Override
    boolean isPageLoaded() {
        return profileMenu.isDisplayed();
    }

    public void initElements(){
        profileMenu = webDriver.findElement(By.xpath("//li[@id='profile-nav-item']"));
        searchField = webDriver.findElement(By.xpath("//input[@role and @placeholder='Search']"));

    }

    public void search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.RETURN);
    }

}
