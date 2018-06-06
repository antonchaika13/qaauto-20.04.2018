package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * PageObject of LinkedinSearchPage with methods and variables
 */
public class LinkedinSearchPage extends LinkedinBasePage {
    WebElement resultsCounter;

    @FindBy(xpath ="//li[contains(@class, 'search-result__occluded-item')]")
    List<WebElement> searchResults;

    /**
     * Constructor of LinkedinSearchPage class)
     * @param webDriver - current webDriver object
     */
    public LinkedinSearchPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        //initElements();
    }



    /**
     * method that checks, is search page loaded
     */
    @Override
    public boolean isPageLoaded() {
        waitUntilElementIsClickable(resultsCounter,60);
        return resultsCounter.isDisplayed();
    }


    /**
     * method that will record integer value of search results count
     * @return search results count
     */
    public int getResultsCount() {
        return searchResults.size();
    }

    public List<String> getResultsList() {
        List<String> searchResultsList = new ArrayList();
        for (WebElement searchResult:searchResults){
            ((JavascriptExecutor)webDriver).executeScript(
                    "arguments[0].scrollIntoView();", searchResult);

            String searchResultText = searchResult.getText();
            searchResultsList.add(searchResultText);
        }
        return searchResultsList;
    }

}








