package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pageObjects.AccountSucessPage;
import pageObjects.HomePage;
import pageObjects.RegisterPage;
import utils.Utilities;

public class RegisterTest extends Base {
	public WebDriver driver;
      public RegisterTest() throws IOException {
    	  super();
      }
     RegisterPage registerpage;
     AccountSucessPage asp;
	@BeforeMethod
	public void setup() throws InterruptedException {
		driver=intializeBrowser("chrome");
		HomePage homepage=new HomePage(driver);
		homepage.clickOnMyAccount();
		registerpage=homepage.clickOnRegister();
	}
	@Test(priority = 1)
	public void verifyRegisteringAccount() throws InterruptedException {
		//RegisterPage registerpage=new RegisterPage(driver);
		//AccountSucessPage asp=new AccountSucessPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstname"));
		registerpage.enterlastName(dataProp.getProperty("lastname"));
		registerpage.enterEmail("asdfzxcv" + Utilities.timeStamp() + "@gmail.com");
		registerpage.enterTellphone(dataProp.getProperty("telphone"));
		registerpage.enterPassword(dataProp.getProperty("inputpassword"));
		registerpage.enterConformPassword(dataProp.getProperty("inputpassword"));
		registerpage.selectTerm_Condition();
		asp=registerpage.clickSubmit();
		Assert.assertEquals(asp.getSucessMeassage(),dataProp.getProperty("sucessmessage"));
	}

	@Test(priority = 2)
	public void verifyRegisterallFeilds() throws InterruptedException {
		//RegisterPage  registerpage=new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstname"));
		registerpage.enterlastName(dataProp.getProperty("lastname"));
		registerpage.enterEmail("asdfzxcv" + Utilities.timeStamp() + "@gmail.com");
		registerpage.enterTellphone(dataProp.getProperty("telphone"));
		registerpage.enterPassword(dataProp.getProperty("inputpassword"));
		registerpage.enterConformPassword(dataProp.getProperty("inputpassword"));
		registerpage.registerNewsLetter();
		registerpage.selectTerm_Condition();
		asp=registerpage.clickSubmit();
		// Assert.assertTrue(driver.findElement(By.xpath("(//input[@type='radio'])[2]")).isSelected());
		//AccountSucessPage asp=new AccountSucessPage(driver);
		//Thread.sleep(3000);
		Assert.assertEquals(asp.getSucessMeassage(),dataProp.getProperty("sucessmessage"));
	}

	@Test(priority = 3)
	public void registeringWithExsistingEmail() {
		//RegisterPage registerpage=new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstname"));
		registerpage.enterlastName(dataProp.getProperty("lastname"));
		registerpage.enterEmail(prop.getProperty("validemail"));
		registerpage.enterTellphone(dataProp.getProperty("telphone"));
		registerpage.enterPassword(dataProp.getProperty("inputpassword"));
		registerpage.enterPassword(dataProp.getProperty("inputpassword"));
		registerpage.selectTerm_Condition();
		registerpage.clickSubmit();
		// Assert.assertTrue(driver.findElement(By.xpath("(//input[@type='radio'])[2]")).isSelected());
		Assert.assertEquals(registerpage.duplicateEmailWarning(),
				dataProp.getProperty("warningmessageRegister"));
	}

	@Test(priority = 4)
	public void registerWithNoDetails() {
		//RegisterPage registerpage=new RegisterPage(driver);
		registerpage.clickSubmit();
		Assert.assertTrue(registerpage.getpolicyWarningMsg()
				.contains("Warning: You must agree to the Privacy Policy!"));
		Assert.assertTrue(registerpage.getFirstNmaeWrng().contains("First Name must be between 1 and 32 characters!"));
		Assert.assertTrue(registerpage.getLastNameWrng().contains("Last Name must be between 1 and 32 characters!"));
		Assert.assertTrue(registerpage.getEmailWrng()
				.contains("E-Mail Address does not appear to be valid!"));
		Assert.assertTrue(registerpage.getTElWrng()
				.contains("Telephone must be between 3 and 32 characters!"));
		Assert.assertTrue(registerpage.getPasswordWrng().contains("Password must be between 4 and 20 characters!"));
	}

	@AfterMethod
	public void teardown() {
			driver.quit();
	}

}
