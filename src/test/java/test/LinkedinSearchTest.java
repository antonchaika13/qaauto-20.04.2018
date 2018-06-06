package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.LinkedinHomePage;
import page.LinkedinSearchPage;


import java.util.List;

import static java.lang.Thread.sleep;


/**
 *  class LinkedinSearchTest which consists of test case of search functionality.
 */

public class LinkedinSearchTest extends LinkedinBaseTest {


    /**
     * Test case for search.  Verify that search count results equal 10. Verify that every search result will contain a search term.
     */
    @Test
    public void basicSearchTest() throws InterruptedException {
        String searchTerm = "HR";


        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Linkedin login page is not loaded.");

        LinkedinHomePage linkedinHomePage = linkedinLoginPage.login("mir2asrt1@gmail.com", "June0620!");
        Assert.assertTrue(linkedinHomePage.isPageLoaded(),
                "Linkedin Home page is not loaded");

        LinkedinSearchPage linkedinSearchPage = linkedinHomePage.search(searchTerm);


        Assert.assertTrue(linkedinSearchPage.isPageLoaded(),
                "Search page is not loaded");

        Assert.assertEquals(linkedinSearchPage.getResultsCount(), 10,
                "Search results count is wrong.");

        List<String> resultsList = linkedinSearchPage.getResultsList();

        for (String result : resultsList) {
            Assert.assertTrue(result.contains(searchTerm),
                    "Searchterm "+searchTerm+" is missing in following result: \n" +result);
        }
    }
}
