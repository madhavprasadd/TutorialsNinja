package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSucessPage {
	
	WebDriver driver;
	@FindBy(xpath = "(//div/h1)[2]")
	WebElement SucssMsgg;
	
	public AccountSucessPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getSucessMeassage() {
		return SucssMsgg.getText();
	}
	
	

}
