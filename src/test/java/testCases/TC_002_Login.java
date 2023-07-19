package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class TC_002_Login extends BaseClass {

	@Test(groups = {"sanity"})
	public void testLogin() {

		logger.info("Test case TC_002_Login Stsrt");

		driver.get(rb.getString("appURL"));
		logger.info("home page launched");

		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clicklogin();

		LoginPage lp = new LoginPage(driver);
		lp.setEmail(rb.getString("email"));
		logger.info("Entered Email");
		lp.setPassword(rb.getString("password"));
		logger.info("Entered password");
		lp.clickLogin();
		logger.info("Clicked on login");

		boolean mypage = lp.isMyAccountPageExists();

		try
		{
		if (mypage) {
			logger.info("Login Sucess");
			Assert.assertTrue(true);

		} else {
			logger.info("Login failed");
			Assert.assertTrue(false);
		}
		}
		catch(Exception e)
		{
			logger.fatal("failed");
			Assert.fail();
		}

	}

}
