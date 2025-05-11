package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC02LoginWithCorrectUserAndPassword extends BaseClass {

	@Test(groups= {"Regression", "Master"})
	public void loginWithCorrectUserAndPassword() {
		logger.info("*** Starting TC002 Login with Correct Credentials ***");
		try {
			LandingPage landingPage = new LandingPage(BaseClass.driver);
			landingPage.verifyWebsiteLogo();
			landingPage.signupLoginButton();

			LoginPage loginPage = new LoginPage(BaseClass.driver);
			loginPage.verifyLoginHeading();
			loginPage.loginToAcc("manoj@prajapati.com", "manoj123");
			landingPage.verifyLoggedInAs();
			landingPage.verifyUsernameDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception occurred: " + e.getMessage());
//			Assert.assertTrue(false);
		}
		logger.info("*** Finished TC002 Login with Correct Credentials ***");
	}
}
