package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page.LinkedinBasePage;

/**
 * PageObject of LinkedinHomePage with methods and variables
 */
public class LinkedinHomePage extends LinkedinBasePage {

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileMenu;

    @FindBy(xpath = "//input[@role and @placeholder='Search']")
    private WebElement searchField;

    /**
     * Constructor of LinkedinHomePage class)
     * @param webDriver - current webDriver object
     */
    public LinkedinHomePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    /**
     * method that will check, is homepage loaded or not by presence of profile menu on page
     */
    @Override
    public boolean isPageLoaded() {
        return profileMenu.isDisplayed();
    }


    /**
     * method that allows to make a search query in search tab and get search results
     * @param searchTerm
     * @return search page with results
     */
    public LinkedinSearchPage search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.RETURN);
        //return new LinkedinSearchPage(webDriver);
        return PageFactory.initElements(webDriver, LinkedinSearchPage.class);
    }

}
