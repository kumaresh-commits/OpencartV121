package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups= {"Sanity","Master"})
	public void loginTest() {
		logger.info("******* Started TC002_LoginTest ********");

		try {
		HomePage hp = new HomePage(driver); // HomePage Object
		hp.clickMyaccount();
		logger.info("Clicked Myaccount...");
		hp.loginbtn();
		logger.info("Clicked login button...");

		logger.info("Providing login details...");
		LoginPage lp = new LoginPage(driver); // LoginPage Object

		lp.email_id(p.getProperty("email"));
		lp.pwd_crd(p.getProperty("password"));
		lp.signIn();
		logger.info("Account signIn");

		logger.info("Validated Myaccount page");
		MyAccountPage myc = new MyAccountPage(driver); // MyAccount Page Object
		boolean valid_txt = myc.isMyAccountPageExists();
		Assert.assertEquals(valid_txt, true);
		}catch(Exception e)
		{
			Assert.fail();
		}

		logger.info("******* Ended TC002_LoginTest ********");

	}

}
