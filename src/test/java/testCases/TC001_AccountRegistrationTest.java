package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

//	public WebDriver driver;

	@Test
	public void verifyAccountRegistration() {
		logger.info("****** Starting Test case TC001 Verify Account Registration ******");
		try {
			HomePage homePage = new HomePage(BaseClass.driver);
			homePage.waitForMyAccount();
			homePage.clickMyAccount();
			homePage.clickRegister();

			RegistrationPage regPage = new RegistrationPage(BaseClass.driver);
			regPage.setFirstname(randomString().toUpperCase());
			regPage.setLastname(randomString().toUpperCase());
			regPage.setEmail(randomString() + "@gmail.com");
			regPage.setPassowrd(randomAlphaNumberic());
			regPage.checkPolicy();
			regPage.clickContinue();
			String msgConfirmation = regPage.getMsgConfirmation();
			Assert.assertEquals(msgConfirmation, "Your Account Has Been Created!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("*** Test Case TC001 Ended ***");
	}
}
