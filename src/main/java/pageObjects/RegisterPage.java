package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	@FindBy(id="input-firstname")
	WebElement firstname;
	@FindBy(id="input-lastname")
	WebElement lastName;
	@FindBy(id="input-email")
	WebElement NewEmail;
	@FindBy(id="input-telephone")
	WebElement tellephone;
	@FindBy(id="input-password")
	WebElement Password;
	@FindBy(name="agree")
	WebElement terms_conds;
	@FindBy(className="btn-primary")
	WebElement registerButton;
	@FindBy(xpath="(//input[@type='radio'])[2]")
	WebElement registerNewsLetter;
	@FindBy(css=".alert-dismissible")
	WebElement warningmsg;
	@FindBy(className="alert-dismissible")
	WebElement policyWarning;
	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	WebElement firstNameWarning;
	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	WebElement LastNmaewrng;
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	WebElement emailWrng;
	@FindBy(xpath = "//div[contains(text(),'Telephone')]")
	WebElement telPhoneWrng;
	@FindBy(xpath="//div[text()='Password must be between 4 and 20 characters!']")
	WebElement telPasswoerdWrng;
	WebDriver driver;
	@FindBy(id="input-confirm")
	WebElement confrmPassowrd;
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String name) {
		firstname.sendKeys(name);
	}
	public void enterlastName(String name) {
		lastName.sendKeys(name);
	}
	public void enterEmail(String email) {
		NewEmail.sendKeys(email);
	}
public void enterTellphone(String phn) {
	tellephone.sendKeys(phn);
}
public void enterPassword(String password)
{
	Password.sendKeys(password);
}
public void enterConformPassword(String password)
{
	confrmPassowrd.sendKeys(password);
}

public void selectTerm_Condition() {
	terms_conds.click();
}

public AccountSucessPage clickSubmit() {
	registerButton.click();
	return new AccountSucessPage(driver);
}
public void registerNewsLetter() {
	registerNewsLetter.click();
}
public String duplicateEmailWarning() {
  return	warningmsg.getText();
}
public String getpolicyWarningMsg() {
	return policyWarning.getText();
}
public String getFirstNmaeWrng() {
	return firstNameWarning.getText();
}
public String getLastNameWrng() {
	
	return LastNmaewrng.getText();
}
public String getEmailWrng() {
	
	return emailWrng.getText();
}
public String getTElWrng() {
	
	return telPhoneWrng.getText();
}

public String getPasswordWrng() {
	
	
	return telPasswoerdWrng.getText();
}
}
