package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class LinkedinResetPasswordTest extends LinkedinBaseTest {


    @Test
    public void successfulPasswordReset() {
        String userEmail = "mir2asrt1@gmail.com";
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "Linkedin login page is not loaded.");

        LinkedinRequestPasswordResetPage linkedinRequestPasswordResetPage = linkedinLoginPage.clickOnForgotPasswordLink();
        Assert.assertTrue(linkedinRequestPasswordResetPage.isPageLoaded(),
                "Linkedin login page is not loaded.");

        LinkedinRequestPasswordResetSubmitPage linkedinRequestPasswordResetSubmitPage=linkedinRequestPasswordResetPage.submitUserEmail();
        Assert.assertTrue(linkedinRequestPasswordResetSubmitPage.isPageLoaded(),
                "RequestPasswordResetSubmitPage page is not loaded.");

        LinkedinSetNewPasswordPage linkedinSetNewPasswordPage = linkedinRequestPasswordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(linkedinSetNewPasswordPage.isPageLoaded(),
                "NewPasswordPage page is not loaded.");

        LinkedinPasswordChangeConfirmedPage linkedinPasswordChangeConfirmedPage = setNewPasswordPage.setNewUserPassword();
        Assert.assertTrue(linkedinPasswordChangeConfirmedPage.isPageLoaded(),
                "NewPasswordPage page is not loaded.");

        LinkedinHomePage linkedinHomePage = passwordChangeConfirmedPage.clickOnGoToHomeButton();
        Assert.assertTrue(linkedinHomePage.isPageLoaded(),
                "Homepage is not loaded.");







    }
}
