package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends BasePage{

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement inputFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement inputLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement inputEmail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement inputPassword;
	
	@FindBy(xpath="//button[text()='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPolicy;
	
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstname(String fname) {
		inputFirstname.sendKeys(fname);
	}
	
	public void setLastname(String lname) {
		inputLastname.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		inputEmail.sendKeys(email);
	}
	
	public void setPassowrd(String password) {
		inputPassword.sendKeys(password);
	}
	
	public void clickContinue() {
		btnContinue.click();
	}
	
	public void checkPolicy() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(chkPolicy));
		chkPolicy.click();
	}
	
	public String getMsgConfirmation() {
		return msgConfirmation.getText();
	}
}
