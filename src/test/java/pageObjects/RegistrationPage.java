package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

	WebDriver driver;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-firstname")
	WebElement txtFirstname;

	@FindBy(id = "input-lastname")
	WebElement txtLastname;

	@FindBy(id = "input-email")
	WebElement txtEmail;
	
	@FindBy(id="input-telephone")
	WebElement txtTelephone;

	@FindBy(id = "input-password")
	WebElement txtPassword;
	
	@FindBy(id="input-confirm")
	WebElement txtconfPassword;
	
	@FindBy(xpath = "//label[normalize-space()='Yes']")
	WebElement newsletteryes;

	@FindBy(id = "//input[@value='0']")
	WebElement newsletterno;

	@FindBy(xpath="//input[@name='agree']")
	WebElement chkboxPrivacy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement bntContinue;

	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	WebElement sucessmessage;

	public void setFirstName(String fname) {
		txtFirstname.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLastname.sendKeys(lname);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	public void setTelephone(String tel) {
		txtTelephone.sendKeys(tel);
	}

	public void setPassword(String pass) {
		txtPassword.sendKeys(pass);
	}
	
	public void setconfirmPassword(String cnpass) {
		txtconfPassword.sendKeys(cnpass);
	}

	public void newsletteryes() {
		newsletteryes.click();
	}

	public void newsletterno() {
		newsletterno.click();
	}

	public void privacypolicy() {
		chkboxPrivacy.click();
	}

	public void clickcontinue() {

		bntContinue.click();
	}

	public String getconfirmationmessage() {
		try {
			return sucessmessage.getText();
		} catch (Exception e) {
			return (e.getMessage());
		}

	}

}
