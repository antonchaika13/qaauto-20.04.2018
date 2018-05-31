package page;

import org.openqa.selenium.WebDriver;

public abstract class LinkedinBasePage {
    protected WebDriver webDriver;

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

}
