package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


    public class GmailHomePage extends LinkedinBasePage {


        private WebElement SearchTab;


        public GmailHomePage(WebDriver webDriver) {
            super(webDriver);
            initElements();
        }

        @Override
        public boolean isPageLoaded() {
            return SearchTab.isDisplayed();
        }

        public void initElements() {
            SearchTab = webDriver.findElement(By.xpath("//input[@aria-label='Search']"));

        }

            public void enterEmailAndClickNext(String emailFromGmail) {


        }

    }
