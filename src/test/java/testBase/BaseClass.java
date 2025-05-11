package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	private boolean False;
	public Logger logger;
	public Properties prop;

	@Parameters({ "os", "browser" })
	@BeforeClass(groups = { "Sanity", "Regression", "Master", "Datadriven" })
	public void setup(@Optional("Windows") String os, @Optional("chrome") String browser) throws IOException {
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");

		FileReader file = new FileReader(".//src//test//resources//config.properties");
//		FileInputStream fis = new FileInputStream(".//src//test//resources//config.properties");
		prop = new Properties();
		prop.load(file);

//		String url = prop.getProperty("url");
//		System.out.println(url);
		logger = LogManager.getLogger(this.getClass());

		if (prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			logger.info("Setting up remote environment for execution via Selenium Grid...");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			// os
			if (os.equalsIgnoreCase("Windows")) {
				capabilities.setPlatform(Platform.WIN10);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else if (os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			} else {
				System.out.println("No Matching OS");
				return;
			}

			// browser
			switch (browser.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("No Matching Browser... ");
				return;
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}

		if (prop.getProperty("execution_env").equalsIgnoreCase("local")) {
			logger.info("Setting up local environment for execution...");
			logger.info("Initializing the driver..");
			switch (browser.toLowerCase()) {
			case "chrome":
//			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid Browser name... ");
				return;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.get("https://demo.opencart.com/");
		driver.get(prop.getProperty("url"));
//		Thread.sleep(10); //To handle the CAPTCHA - verify you are human
		driver.manage().window().maximize();
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master", "Datadriven" })
	public void teardown() {
		logger.info("Terminating the browser..");
		driver.quit();
	}

	public String randomString() {
		String randomString = RandomStringUtils.randomAlphabetic(5);
		return randomString;
	}

	public String randomNumber() {
		String randomNumber = RandomStringUtils.randomNumeric(10);
		return randomNumber;
	}

	public String randomAlphaNumberic() {
		String randomString = RandomStringUtils.randomAlphabetic(5);
		String randomNumber = RandomStringUtils.randomNumeric(10);
		return (randomString + "@" + randomNumber);
	}

	public void dropDownSelect(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	public void scrollPage(String pixelToScroll) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + pixelToScroll + ")", "");
	}

	public void checkCheckBoxes(WebElement element) {
		boolean checkBox = element.isSelected();
		if (checkBox == False) {
			element.click();
		} else {
			System.out.println("CheckBox is already Checked");
		}
	}

	public String captureScreen(String screenshotName) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + screenshotName + "_" + timeStamp
				+ ".png";
		System.out.println(targetFilePath);
		File targetFile = new File(targetFilePath);
//		targetFile.renameTo(sourceFile);
		FileUtils.copyFile(sourceFile, targetFile);

		return targetFilePath;
	}
}
