package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC03LoginPositiveNegative extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups= {"Datadriven"})
	public void loginWithCorrectUserAndPassword(String username, String password, String expectedResult) {
		logger.info("*** Starting TC003 LoginPositiveNegative with Data Driven Testing ***");
		try {
			LandingPage landingPage = new LandingPage(BaseClass.driver);
			landingPage.verifyWebsiteLogo();
			landingPage.signupLoginButton();

			LoginPage loginPage = new LoginPage(BaseClass.driver);
			loginPage.verifyLoginHeading();
			loginPage.loginToAcc(username, password);
			boolean isUsernameDisplayed = landingPage.verifyLoggedInAs();

			logger.info("Validating the invalid and valid login");
			if (expectedResult.equalsIgnoreCase("Valid")) {
				if (isUsernameDisplayed == true) {
					loginPage.logoutUser();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}
			if (expectedResult.equalsIgnoreCase("Invalid")) {
				if (isUsernameDisplayed == true) {
					loginPage.logoutUser();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception occurred: " + e.getMessage());
		}
		logger.info("*** Finished TC003 LoginPositiveNegative with Data Driven Testing ***");
	}
}
