package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pageObjects.HomePage;
import pageObjects.SearchPage;

public class SearchTest extends Base {
	public SearchTest() throws IOException   {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebDriver driver;
	@BeforeMethod
	public void setup() {
		driver=intializeBrowser("chrome");
		
	}
	
	SearchPage searchpage;
	@Test(priority = 1)
	public void verifySearchWithVaildProduct() {
		HomePage homepage=new HomePage(driver);
		homepage.enterProduct(dataProp.getProperty("product"));
		searchpage=homepage.search();
		//SearchPage searchpage=new SearchPage(driver);
		Assert.assertTrue(searchpage.productDiaplayed(),dataProp.getProperty("searchsucess"));
		
	}
	
	@Test(priority = 2)
	public void verifySearchWithNonExixtingProd() {
		HomePage homepage=new HomePage(driver);
		homepage.enterProduct(dataProp.getProperty("product2"));
		searchpage=homepage.search();
		//SearchPage searchpage=new SearchPage(driver);
		Assert.assertEquals(searchpage.getSearchWarningMessage(),dataProp.getProperty("searchwarning"));
	}
	
	@Test(priority = 3)
	public void verifySearchWithOutProduct() {
		HomePage homepage=new HomePage(driver);
		homepage.enterProduct("");
		searchpage=homepage.search();
		//SearchPage searchpage=new SearchPage(driver);
		Assert.assertEquals(searchpage.getSearchWarningMessage(),dataProp.getProperty("searchwarning"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
