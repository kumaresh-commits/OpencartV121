package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	// constructor
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	// locators
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement First_Name;
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement Last_Name;
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement E_Mail;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement Password;
	@FindBy(xpath = "//button[normalize-space()='Continue']")
	WebElement Continue_btn;
	@FindBy(xpath = "//input[@name='agree']")
	WebElement policy_btn;
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	// Action
	public void setFirstName(String fname) {
		First_Name.sendKeys(fname);
	}

	public void setLastName(String Lname) {
		Last_Name.sendKeys(Lname);
	}

	public void setEmail(String mail) {
		E_Mail.sendKeys(mail);
	}

	public void setpassword(String pwd) {
		Password.sendKeys(pwd);
	}

	public void conButton() {
		//Continue_btn.click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", Continue_btn);
	}

	public void Policybutton() {
		//policy_btn.click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", policy_btn);
	}

	public String confirmMsg() {
		try {
			return msgConfirmation.getText();

		} catch (Exception e) {
			return (e.getMessage());

		}

	}

}
