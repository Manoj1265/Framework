package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import testBase.BaseClass;

public class SignUpPage extends BasePage {

	public WebDriver driver;
	public BaseClass baseClass = new BaseClass();

	public SignUpPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(xpath = "//b[text()='Enter Account Information']")
	WebElement accInfo;
	@FindBy(xpath = "//div[@class='radio']/span/input[@value='Mr']")
	WebElement title;
	@FindBy(xpath = "//input[@name='name']")
	WebElement name;
	@FindBy(xpath = "//input[@name='email']")
	WebElement email;
	@FindBy(name = "password")
	WebElement pass;
	@FindBy(xpath = "//select[@name='days']")
	WebElement day;
	@FindBy(name = "months")
	WebElement months;
	@FindBy(name = "years")
	WebElement years;
	@FindBy(name = "newsletter")
	WebElement newsletterCheckbox;
	@FindBy(id = "optin")
	WebElement specialOfferCheckbox;
	@FindBy(name = "first_name")
	WebElement fname;
	@FindBy(name = "last_name")
	WebElement lname;
	@FindBy(name = "company")
	WebElement company;
	@FindBy(name = "address1")
	WebElement address1;
	@FindBy(name = "address2")
	WebElement address2;
	@FindBy(name = "country")
	WebElement country;
	@FindBy(name = "state")
	WebElement state;
	@FindBy(name = "city")
	WebElement city;
	@FindBy(id = "zipcode")
	WebElement zipcode;
	@FindBy(id = "mobile_number")
	WebElement MobileNumber;
	@FindBy(xpath = "//button[text()='Create Account']")
	WebElement createButton;
	
	public void verifyAccInfo() {
		boolean enterAccInfoHead = accInfo.isDisplayed();
		Assert.assertTrue(enterAccInfoHead);
	}

	public void fillSignUpForm(String actualName, String actualEmail, String Password, String Date, String Month,
			String Year) {
		title.click();
		String nameValue = name.getAttribute("value");
		Assert.assertEquals(nameValue, actualName);
		String emailValue = email.getAttribute("value");
		Assert.assertEquals(emailValue, actualEmail);
		pass.sendKeys(Password); 
		
		baseClass.scrollPage("400");
		baseClass.dropDownSelect(day, Date);
		baseClass.dropDownSelect(months, Month);
		baseClass.dropDownSelect(years, Year);
		baseClass.checkCheckBoxes(newsletterCheckbox);
		baseClass.checkCheckBoxes(specialOfferCheckbox);
	}

	public void fillAddressInfo(String fName, String lName, String ComapanyName, String Add1, String Add2,
			String Country, String State, String City, String ZipCode, String MobileNum) {
		fname.sendKeys(fName);
		lname.sendKeys(lName);
		company.sendKeys(ComapanyName);
		address1.sendKeys(Add1);
		address2.sendKeys(Add2);
		baseClass.dropDownSelect(country, Country);
		state.sendKeys(State);
		city.sendKeys(City);
		zipcode.sendKeys(ZipCode);
		MobileNumber.sendKeys(MobileNum);
		createButton.click();
	}


}