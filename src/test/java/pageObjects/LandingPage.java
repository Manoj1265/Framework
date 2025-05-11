package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LandingPage extends BasePage {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(xpath = "//div[@class='logo pull-left']")
	WebElement websiteLogo;
	@FindBy(xpath = "//a[text()=' Signup / Login']")
	WebElement loginButton;
	@FindBy(xpath = "//ul[@class='nav navbar-nav']/li/a[text()=' Logged in as ']")
	WebElement LoggedIn;
	@FindBy(xpath = "//ul[@class='nav navbar-nav']/li/a[text()=' Delete Account']")
	WebElement delAccount;
	@FindBy(xpath = "//a[text()=' Logged in as ']/b")
	WebElement usrName;
	@FindBy(xpath = "//p[text()='Your email or password is incorrect!']")
	WebElement incorrectMsg;
	@FindBy(xpath = "//a[text()=' Contact us']")
	WebElement contactUsButton;

	public void verifyWebsiteLogo() {
		boolean logo = websiteLogo.isDisplayed();
		Assert.assertTrue(logo);
	}

	public void signupLoginButton() {
		loginButton.click();
	}

	public boolean verifyLoggedInAs() {
		try {
			boolean loggedInAs = LoggedIn.isDisplayed();
//		Assert.assertTrue(loggedInAs);
			return loggedInAs;
		} catch (Exception e) {
			System.out.println("User not logged In.");
		}
		return false;
	}

	public void deleteAccount() {
		delAccount.click();
	}

	public void verifyUsernameDisplayed() {
		String loggedInAs = LoggedIn.getText();
//		String userName = usrName.getText();
//		String dispalyedUsername = loggedInAs + userName;
		Assert.assertEquals(loggedInAs, "Logged in as Manoj");
	}

	public void verifyUserNamePassIncorrectMsg() {
		boolean errorMsg = incorrectMsg.isDisplayed();
		Assert.assertTrue(errorMsg);
	}

	public void contactUs() {
		contactUsButton.click();
	}
}
