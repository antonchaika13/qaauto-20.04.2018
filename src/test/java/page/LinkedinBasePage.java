package page;

import org.openqa.selenium.WebDriver;
import util.GMailService;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class LinkedinBasePage {
    protected WebDriver webDriver;
    protected static GMailService gMailService = new GMailService();

    /**
     * constructor of LinkedinBasePage class
     * @param webDriver - current WebDriver object
     */
    public LinkedinBasePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    /**
     * method that allows to get current page title
     * @return page title
     */
    public String getCurrentTitle(){
        return webDriver.getTitle();
    }

    /**
     * global method that will check, is current page loaded or not
     */
    abstract boolean isPageLoaded();

    public WebElement waitUntilElementIsClickable (WebElement webElement, int timeOutInSeconds){
               WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSeconds);
               wait.until(ExpectedConditions.elementToBeClickable(webElement));
               return webElement;
            }

}
