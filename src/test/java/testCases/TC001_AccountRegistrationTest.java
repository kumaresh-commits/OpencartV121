package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups= {"Regression","Master"})
	public void verify_AccountRegistration() {

		logger.info("****** Started TC001_AccountRegistrationTest ******");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyaccount();
			logger.info("Clicked Myaccount...");
			hp.clickRegister();
			logger.info("Clicked MyRegister...");

			logger.info("Providing customer details...");
			AccountRegistrationPage acr = new AccountRegistrationPage(driver);

			acr.setFirstName(randomString().toUpperCase());
			acr.setLastName(randomString().toUpperCase());

			acr.setEmail(randomString() + "@gmail.com");
			acr.setpassword(randomAlphaNumaric());

			acr.Policybutton();
			acr.conButton();

			logger.info("Validating Expected message...");
			String confmsg = acr.confirmMsg();
			if (confmsg.equals("Your Account Has Been Created!")) 
			{
				Assert.assertTrue(true);
			} 
			else 
			{
				logger.error("Test failed...");
				logger.debug("debug logs...");
				Assert.assertTrue(false);
			}

			// Assert.assertEquals(confmsg, "Your Account Has Been Created!!!");
		} catch (Exception e) {

			Assert.fail();
		}

		logger.info("****** Fineshed TC001_AccountRegistrationTest ******");
	}

}
