package pages;

import org.testng.Assert;
import base.TestBase;
import engine.KeyWord;
import utils.AmazonObjRepo;
import utils.Reporting;

public class HomePage extends TestBase {
	
	KeyWord keyWord;
	Reporting report = new Reporting();
	
	public HomePage() {
		keyWord = new KeyWord();
		navigateToHomePage();
	}
	
	/*
	 * This Method returns the WebPage Title
	 */
	public String getHomePageTitle() {
		return keyWord.getPageTitle();
	}
	
	/*
	 * This Method Navigates to Amazon.in's Home page
	 */
	public void navigateToHomePage() {
		String URL = prop.getProperty("url");
		keyWord.navigateToURL(URL);
		System.out.println("Navigating to Amazon.in: " + keyWord.getCurrentURL());
		System.out.println("Current URL is : "+keyWord.getCurrentURL());
	}
	
	/*
	 * This Method returns "True" or "False" boolean value if Amazon's Home page logo is visible.
	 */
	public boolean getHomePageAmazonLogo() throws Exception {
		return keyWord.getElement("id", AmazonObjRepo.homePageAmazonLogo).isDisplayed();
	}
	
	/*
	 * This Method Navigates to Amazon.in's Sign In page
	 */
	public SignInPage goToSignInPage() throws Exception{
		report.info("Navigating to Amazon Sign In page");
		keyWord.mouseHover("id", AmazonObjRepo.accountListDropDown);
		keyWord.click("xpath", AmazonObjRepo.signInBtnHomePage);	
		return new SignInPage();
	}

	/*
	 * This Method verifies and Validates HomePage of Amazon.in
	 */
	public void verifyAndValidateHomePage(String expHomePageTitle) throws Exception {
		report.info("Verifying Amazon Page Title and Logo");
		Assert.assertEquals(expHomePageTitle, getHomePageTitle());
		Assert.assertTrue(getHomePageAmazonLogo());
	}

	/*
	 * This Method verified the Username of user logged in.
	 */
	public void verfiyLoggedInUser(String userName) throws Exception {
		report.info("Verifying Logged In User");
		if(keyWord.getElementText("xpath", AmazonObjRepo.loggedInUser).contains(userName)) {
			System.out.println("Logged in User is : "+userName);
			Assert.assertTrue(true);
		}else {
			System.out.println(keyWord.getElementText("xpath", AmazonObjRepo.loggedInUser));
			Assert.assertTrue(false);
		}
	}
	
	/*
	 * This Method searches for the specified Product
	 */
	public SearchResultPage searchForProduct(String productName) throws Exception {
		report.info("Search for a product");
		keyWord.verifyElementExists("id", AmazonObjRepo.productSearchBar);
		keyWord.enterText("id", AmazonObjRepo.productSearchBar, productName);
		keyWord.click("id", AmazonObjRepo.productSearchButton);		
		return new SearchResultPage();
	}
	
	/*
	 * This Method delete the items of cart if it is not empty
	 */
	public void deleteCartIfNotEmpty() throws Exception {
		int cartCount = Integer.parseInt(keyWord.getElementText("xpath", AmazonObjRepo.getCartCount));	
		if(cartCount>0) {
			clearCart();
		}
	}

	/*
	 * This Method clears the cart
	 */
	public void clearCart() throws Exception {
		report.info("Clearing Shopping Cart");
		keyWord.click("xpath", AmazonObjRepo.cartIcon);
		int itemCount = keyWord.getCount("xpath", AmazonObjRepo.deleteCartItem);
		boolean flag = false;
		while(itemCount!=0) {
			keyWord.click("xpath", AmazonObjRepo.deleteCartItem);
			itemCount--;
			flag = true;
		}
		
		if(flag && itemCount==0) {
			report.pass("Cart Emptied");
			System.out.println("Cart Emptied");
		}
		
	}

	/*
	 * This Method displays the current page details
	 */
	public void getPageDetails() {
		report.info("Current URL is :" + keyWord.getCurrentURL());
		System.out.println("Current URL is :" + keyWord.getCurrentURL());
	}

	/*
	 * This Method deletes the old addresses (If Available)
	 */
	public void deleteOldAddressIfAvailable() throws Exception {
		keyWord.mouseHover("id", AmazonObjRepo.accountListDropDown);
		keyWord.click("xpath", AmazonObjRepo.yourAccount);
		keyWord.click("xpath", AmazonObjRepo.yourAddressesButton);
		int addressCount = keyWord.getCount("xpath", AmazonObjRepo.removeAddressButton);
		boolean flag = false;
		if(addressCount>0) {
			report.info("Deleting old Addresses");
			while(addressCount!=0) {
				keyWord.click("xpath", AmazonObjRepo.removeAddressButton);
				keyWord.click("xpath", AmazonObjRepo.deleteAddrConfirmationYes);
				keyWord.click("xpath", AmazonObjRepo.deleteAddrConfirmationYes);
				addressCount--;
				flag = true;
			}
		}
		else {
			System.out.println("No Old Addresses Found.");
		}
		if(flag && addressCount==0) {
			report.pass("Old Addresses Deleted");
			System.out.println("Old Addresses Deleted");
			keyWord.refreshWindow();
		}
	}
}
