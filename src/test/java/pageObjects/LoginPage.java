package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends BasePage{

	WebDriver driver;
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
	}
	
	@FindBy(xpath="//h2[text()='New User Signup!']") WebElement signUpHeading;
	@FindBy(name="name") WebElement nameField;
	@FindBy(xpath="//input[@data-qa='signup-email']") WebElement emailField;
	@FindBy(xpath="//button[text()='Signup']") WebElement signUpButton;
	@FindBy(xpath="//h2[text()='Login to your account']") WebElement loginToAccHeading;
	@FindBy(xpath="//input[@data-qa='login-email']") WebElement loginEmail;
	@FindBy(xpath="//input[@data-qa='login-password']") WebElement loginPass;
	@FindBy(xpath="//button[text()='Login']") WebElement loginButton;
	@FindBy(xpath="//a[text()=' Logout']") WebElement logoutButton;
	@FindBy(xpath="//p[text()='Email Address already exist!']") WebElement emailExist;
	
	public void verifyHeading() {
		boolean signupHead = signUpHeading.isDisplayed();
		Assert.assertTrue(signupHead);
	}
	
	public void signUp(String name, String Email) {
		nameField.sendKeys(name);
		emailField.sendKeys(Email);
		signUpButton.click();
	}
	
	public void verifyLoginHeading() {
		boolean loginHead = loginToAccHeading.isDisplayed();
		Assert.assertTrue(loginHead);
	}
	
	public void loginToAcc(String email, String pass) {
		loginEmail.sendKeys(email);
		loginPass.sendKeys(pass);
		loginButton.click();
	}
	
	public void logoutUser() {
		logoutButton.click();
	}
	
	public void verifyEmailAlreadyExist() {
		boolean alreadyExist = emailExist.isDisplayed();
		Assert.assertTrue(alreadyExist);
	}
}
