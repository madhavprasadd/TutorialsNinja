package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccount;
	@FindBy(linkText="Login")
	private WebElement login;
	@FindBy(linkText="Register")
	WebElement register;
	@FindBy(name="search")
	WebElement enterProduct;
	@FindBy(css=".btn.btn-default.btn-lg")
	WebElement search;
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	 
	public void clickOnMyAccount() {
		myAccount.click();
		
	}
	public LoginPage clickOnLogin() {
		login.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage clickOnRegister() {
		
		register.click();
		return new RegisterPage(driver);
	}
	public void enterProduct(String prod) {
		enterProduct.sendKeys(prod);;
	}
	public SearchPage search() {
		search.click();
		return new SearchPage(driver);
	}

}
