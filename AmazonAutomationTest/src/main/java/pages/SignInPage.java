package pages;

import org.testng.Assert;

import base.TestBase;
import engine.KeyWord;
import utils.AmazonObjRepo;
import utils.Reporting;

public class SignInPage extends TestBase {

	KeyWord keyWord;
	Reporting report = new Reporting();

	public SignInPage() {
		keyWord = new KeyWord();
	}

	public String getSignInPageTile() {
		return keyWord.getPageTitle();
	}

	public void verifySignInPageVisibility() {
		report.info(String.format("Verfying Sign In Page i.e [%s] Visibility", keyWord.getCurrentURL()));
		System.out.println("Current URL is: " + keyWord.getCurrentURL());
		keyWord.waitForElementToBeVisible("xpath", AmazonObjRepo.signInHeader_SignInPage);
		keyWord.waitForElementToBeVisible("id", AmazonObjRepo.emailIdField_signIn);
	}

	public void enterUserEmailToLogin(String emailId) {
		keyWord.enterText("id", AmazonObjRepo.emailIdField_signIn, emailId);
	}

	public void clickOnContinueButton() throws Exception {
		keyWord.click("id", AmazonObjRepo.continueBtn);
	}

	public void enterPasswordToLogin(String passWord) {
		keyWord.enterText("id", AmazonObjRepo.passWordField_signIn, passWord);
	}

	public void clickOnSignInButton() throws Exception {
		keyWord.click("id", AmazonObjRepo.signInBtn);
	}

	public void verifyAndValidateSignInPage(String expSignInPageTitle) {
		verifySignInPageVisibility();
		report.info("Verifying Sign In Page tile");
		Assert.assertEquals(expSignInPageTitle, getSignInPageTile());
	}

	public HomePage enterSignInCredentialsAndLogin(String emailId, String passWord) throws Exception {
		report.info("Entering email id / phone number to Sign In and clicking Continue button");
		System.out.println("Entering email id / phone number to Sign In and clicking Continue button");
		enterUserEmailToLogin(emailId);
		clickOnContinueButton();

		report.info("Enter Password to to Sign In and Hitting Sign In button");
		System.out.println("Enter Password to to Sign In and Hitting Sign In button");
		enterPasswordToLogin(passWord);
		clickOnSignInButton();
		return new HomePage();
	}
}
