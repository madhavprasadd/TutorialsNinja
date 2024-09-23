//package com.tutorilasninja.qa.testcases;
package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
//import dev.failsafe.internal.util.Assert;
import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utils.Utilities;

public class LoginTest extends Base {

	public LoginTest() throws IOException {
		super();
	}

	public WebDriver driver;

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	LoginPage loginpage;

	@BeforeMethod()
	public void setup() throws Throwable {
		// loadProperties();

		driver = intializeBrowser(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccount();
		loginpage = homepage.clickOnLogin();
	}

	@Test(priority = 1, dataProvider = "supplyTestData")
	public void verifyLoginValidCred(String email, String password) {

		// LoginPage loginpage=new LoginPage(driver);
		loginpage.enterEmail(email);
		loginpage.enterPassword(password);
		loginpage.clickLogin();
		AccountPage accountpage = new AccountPage(driver);
//Assert.fail("failing the test");
		Assert.assertTrue(accountpage.getStatusOFEditInfo());

	}

	@Test(priority = 2)
	public void loginInvaildCred() {

		// appending the date to mailid,bcz the app wont allow the 5 consective
		// unscueesfull login attempts with same email-id
		// LoginPage loginpage=new LoginPage(driver);

		loginpage.enterEmail(("asdf12" + Utilities.timeStamp() + "@gmail.com"));
		loginpage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginpage.clickLogin();

		Assert.assertTrue(loginpage.getWarningMessage().contains(dataProp.getProperty("warningMessage")));

	}

	@Test(priority = 3)
	public void verifyLoginvalidemailInvalidPassword() {
		// LoginPage loginpage=new LoginPage(driver);
		loginpage.enterEmail(prop.getProperty("validemail"));
		loginpage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginpage.clickLogin();

		Assert.assertTrue(loginpage.getWarningMessage().contains(dataProp.getProperty("warningMessage")));

	}

	@Test(priority = 4)
	public void verifyLoginInvalidemailvalidPassword() {

//LoginPage loginpage=new LoginPage(driver);
		loginpage.enterEmail(dataProp.getProperty("invalidemail"));
		loginpage.enterPassword(prop.getProperty("vaildpass"));
		loginpage.clickLogin();

		Assert.assertTrue(loginpage.getWarningMessage().contains(dataProp.getProperty("warningMessage")));

	}

	@Test(priority = 5)
	public void verifyLoginwithNoEmailandPass() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.clickLogin();

		Assert.assertTrue(loginpage.getWarningMessage().contains(dataProp.getProperty("warningMessage")));

	}

	@DataProvider
	public Object[][] supplyTestData() throws IOException {
		Object[][] data = Utilities.getTestDataFromExcel("TestData");
		return data;
	}

}