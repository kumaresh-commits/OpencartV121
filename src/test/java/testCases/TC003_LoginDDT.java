package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="DataProvider") // getting data from another class
	public void verify_LoginDDT(String email, String pwd, String exp) {

		logger.info("********* Started TC003_LoginDDT ***********");

		try {
		// HomePage
		HomePage hp = new HomePage(driver); // HomePage Object
		hp.clickMyaccount();
		logger.info("");
		hp.loginbtn();

		LoginPage lp = new LoginPage(driver); // LoginPage Object
		lp.email_id(email);
		lp.pwd_crd(pwd);
		lp.signIn();

		MyAccountPage myc = new MyAccountPage(driver);
		boolean targetPage = myc.isMyAccountPageExists();

		// Valid data --> Login success ---> Test Pass
		// --> Login Failed ---> Test Fail

		// Invalid data --> Login success ---> Test Failed
		// --> Login Failed ----> Test Passs

		if (exp.equalsIgnoreCase("Valid")) 
		{
			if (targetPage == true) 
			{
				myc.logout();
				Assert.assertTrue(true);
			} 
			else 
			{
				Assert.assertTrue(false);
			}
		}
		
		if (exp.equalsIgnoreCase("Invalid")) {
			if (targetPage == true) {
				myc.logout();
				Assert.assertTrue(false);
			} 
			else 
			{
				Assert.assertTrue(true);
			}

		}
		
		}catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("********* Finished TC003_LoginDDT ***********");

	}

}
