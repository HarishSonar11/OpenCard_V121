package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;

public class AccountRegistrationTest extends BaseClass {

	@Test(groups = {"master"})
	public void testRegistration() throws IOException {
  try {
		
		logger.info("Testcase AccountRegistrationTest Start");

		driver.get(rb.getString("appURL"));

		logger.info("Home page displayed");
		driver.manage().window().maximize();

		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on myAccount");
		hp.clickRegister();
		logger.info("Clicked on Register");

		RegistrationPage rp = new RegistrationPage(driver);
		rp.setFirstName("Harry");
		logger.info("First Name Entered");
		rp.setLastName("Soni");
		logger.info("Last Name Entered");
		rp.setEmail(randomestring() + "@gmail.com");
		logger.info("Email id Entered");
		rp.setTelephone("9028685714");
		logger.info("Telephone number Entered");
		rp.setPassword("Harish");
		logger.info("Password Entered");
		rp.setconfirmPassword("Harish");
		logger.info("Confirm Password Entered");
		rp.newsletteryes();
		logger.info("newsletteryes Selected");
		rp.privacypolicy();
		logger.info("Policy check box Selected");
		rp.clickcontinue();
		logger.info("Clicked on continue button");

		String rgdone = rp.getconfirmationmessage();

		if (rgdone.equals("Your Account Has Been Created!")) {
			logger.info("Your Account Has Been Created!");
						Assert.assertTrue(true);

		} else {
			logger.info("Account Registartion failed");
			captureScreen(driver,"testRegistration");
			Assert.assertTrue(false);
		}
  }
  catch(Exception e){
	  
	  logger.info(e.getMessage());
	  captureScreen(driver,"testRegistration");
   }

	}

}
