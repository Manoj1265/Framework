package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccCreatedDeletedPage;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.SignUpPage;
import testBase.BaseClass;

public class LoginTests extends BaseClass {

	@Test(groups= {"Sanity", "Master"})
	public void TC01RegisterUser() {
		logger.info("*** Starting TC001 Account Registration ***");
		try {
			LandingPage landingPage = new LandingPage(BaseClass.driver);
			logger.info("Verifying the Logo of WebSite...");
			landingPage.verifyWebsiteLogo();
			landingPage.signupLoginButton();
			LoginPage loginPage = new LoginPage(BaseClass.driver);
			loginPage.verifyHeading();

			String name = randomString();
			String email = randomString() + "@gmail.com";

			loginPage.signUp(name, email);

			SignUpPage signUpPage = new SignUpPage(BaseClass.driver);
			logger.info("Verifying Account details");
			signUpPage.verifyAccInfo();
			signUpPage.fillSignUpForm(name, email, "manoj123", "12", "September", "1998");
			signUpPage.fillAddressInfo("Manoj", "Prajapati", "QK", "Kalwa market", "Thane", "India", "Maharashtra",
					"Thane", "400605", "9876512340");

			AccCreatedDeletedPage accCreatedDelPage = new AccCreatedDeletedPage(BaseClass.driver);

			accCreatedDelPage.verifyAccCreated();
			accCreatedDelPage.clickContinueBtn();
			landingPage.verifyLoggedInAs();
			landingPage.deleteAccount();
			accCreatedDelPage.verifyAccDeleted();
			accCreatedDelPage.clickContinueBtn();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception occurred: " + e.getMessage());
		}
		logger.info("*** Finished TC001 Account Registration ***");
	}
}
