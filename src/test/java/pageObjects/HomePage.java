package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	// constructor
	public HomePage(WebDriver driver) 
	{
		super(driver);
	}

	// Locators
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(xpath="//a[@class='dropdown-item'][normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(linkText="Login") WebElement loginlink;
	
	//Actions
	public void clickMyaccount()
	{
		lnkMyaccount.click();
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	}
	
	public void loginbtn()
	{
		loginlink.click();
	}

}
