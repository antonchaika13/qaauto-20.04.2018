package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

/**
 * class LinkedinResetPasswordTest which consists of test case that checks end to end flow with reset user password
 */
public class LinkedinResetPasswordTest extends LinkedinBaseTest {


    @Test
    public void successfulPasswordReset() {
        String userEmail = "mir2asrt1@gmail.com";
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Linkedin login page is not loaded.");

        LinkedinRequestPasswordResetPage linkedinRequestPasswordResetPage = linkedinLoginPage.clickOnForgotPasswordLink();
        Assert.assertTrue(linkedinRequestPasswordResetPage.isPageLoaded(),
                "Linkedin login page is not loaded.");

        LinkedinRequestPasswordResetSubmitPage linkedinRequestPasswordResetSubmitPage=linkedinRequestPasswordResetPage.submitUserEmail(userEmail);
        Assert.assertTrue(linkedinRequestPasswordResetSubmitPage.isPageLoaded(),
                "RequestPasswordResetSubmitPage page is not loaded.");

        LinkedinSetNewPasswordPage linkedinSetNewPasswordPage = linkedinRequestPasswordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(linkedinSetNewPasswordPage.isPageLoaded(),
                "NewPasswordPage page is not loaded.");

        LinkedinPasswordChangeConfirmedPage linkedinPasswordChangeConfirmedPage = linkedinSetNewPasswordPage.setNewUserPassword();
        Assert.assertTrue(linkedinPasswordChangeConfirmedPage.isPageLoaded(),
                "NewPasswordPage page is not loaded.");

        LinkedinHomePage linkedinHomePage = linkedinPasswordChangeConfirmedPage.clickOnGoToHomeButton();
        Assert.assertTrue(linkedinHomePage.isPageLoaded(),
                "Homepage is not loaded.");







    }
}
