package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AccCreatedDeletedPage extends BasePage {

	public WebDriver driver;

	public AccCreatedDeletedPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(xpath = "//h2/b")
	WebElement AccDeleted;
	@FindBy(xpath = "//b[text()='Account Created!']")
	WebElement accCreated;
	@FindBy(xpath = "//div[@class='pull-right']/a[text()='Continue']")
	WebElement continueButton;

	public void verifyAccCreated() {
		boolean accountCreated = accCreated.isDisplayed();
		Assert.assertTrue(accountCreated);
	}
	
	public void verifyAccDeleted() {
		boolean accDeleted = AccDeleted.isDisplayed();
		Assert.assertTrue(accDeleted);
	}
	
	public void clickContinueBtn() {
		continueButton.click();
	}
}
