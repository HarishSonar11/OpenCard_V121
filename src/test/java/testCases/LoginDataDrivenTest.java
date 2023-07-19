package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import utilities.XLUtility;

public class LoginDataDrivenTest extends BaseClass {


    @Test(dataProvider="Testdata",groups = {"regression"})
	public void testLogin(String email,String password,String exp) 
    {

		
			logger.info("Test case TC_002_Login Stsrt");
			try {
			driver.get(rb.getString("appURL"));
			logger.info("home page launched");

			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clicklogin();

			LoginPage lp = new LoginPage(driver);

			lp.setEmail(email);
			logger.info("Entered Email");
			lp.setPassword(password);
			logger.info("Entered password");
			lp.clickLogin();
			logger.info("Clicked on login");

			boolean mypage = lp.isMyAccountPageExists();

			 if (exp.equals("Valid")) {

				if (mypage == true) {

					logger.info("Login Sucess");
					MyAccount my = new MyAccount(driver);
					my.clicklogout();
					Assert.assertTrue(true);
				} else {
					logger.info("Login failed");
					Assert.assertTrue(false);
				}
			}

		if (exp.equals("Invalid")) {
				if (mypage == true) {
					logger.info("Login failed");
					MyAccount my = new MyAccount(driver);
					my.clicklogout();
					Assert.assertTrue(false);
				} else {
					logger.info("Login failed");
					Assert.assertTrue(true);
				}
			}
		}

		catch (Exception e) {
			logger.fatal("failed");
			Assert.fail();
		}
		logger.info("TCDDT finished");
	}

	
	@DataProvider(name = "Testdata")
	public String[][] getData() throws IOException {
		String path = ".\\testData\\Opencart_LoginData.xlsx";
		XLUtility xlutil = new XLUtility(path);

		int totalnumbeofrow = xlutil.getRowCount("Sheet1");
		int tptalnumberofcolumn = xlutil.getCellCount("Sheet1", 1);

		String logindata[][] = new String[totalnumbeofrow][tptalnumberofcolumn];

		for (int i = 1; i <= totalnumbeofrow; i++) {
			for (int j = 0; j < tptalnumberofcolumn; j++) {
				logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}
		return logindata;
	}
}
