package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	@FindBy(linkText = "HP LP3065")
	WebElement validProduct;
	@FindBy(xpath = "(//p)[2+1]")
	WebElement searchWarningMsg;
  WebDriver driver;
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
  public boolean productDiaplayed() {
	 return validProduct.isDisplayed();
  }
  public String getSearchWarningMessage() {
	  return searchWarningMsg.getText();
  }
}
