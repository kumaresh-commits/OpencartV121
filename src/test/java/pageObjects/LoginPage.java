package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	//Locators
	@FindBy(xpath="//input[@id='input-email']") WebElement email_fd;
	@FindBy(xpath="//input[@id='input-password']") WebElement pwd_fd;
	@FindBy(xpath="//button[normalize-space()='Login']") WebElement signInButton;
	
	
	//Actions
	
	public void email_id(String eid)
	{
		email_fd.sendKeys(eid);
	}
	
	public void pwd_crd(String pwd)
	{
		pwd_fd.sendKeys(pwd);
	}
	
	public void signIn()
	{
		signInButton.click();
	}

}
