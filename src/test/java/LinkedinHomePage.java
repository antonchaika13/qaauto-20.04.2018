import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinHomePage extends LinkedinBasePage {

    private WebElement profileMenu;
    private WebElement searchTab;

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
        searchTab = webDriver.findElement(By.xpath("//div[@class='nav-search-bar']//input[@role='combobox']"));

    }
    public void setSearchTab  (String searchQuery){
        searchTab.sendKeys(searchQuery);
        searchTab.sendKeys(Keys.RETURN);
    };

}
