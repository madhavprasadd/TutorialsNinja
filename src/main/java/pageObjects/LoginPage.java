package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	@FindBy(id="input-email")
	private WebElement emailid;
	@FindBy(id="input-password")
	private WebElement passswordfeild;
	@FindBy(xpath="//input[@type='submit']")
	private WebElement loginButton;
	@FindBy(className="alert")
	private WebElement invalidemailIdPasswordWarning;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmail(String email) {
		emailid.sendKeys(email);
	}
	public void enterPassword(String password) {
		passswordfeild.sendKeys(password);
	}
	
	public void clickLogin() {
		loginButton.click();
	}
	public String getWarningMessage() {
		return invalidemailIdPasswordWarning.getText();
	}
	
}

