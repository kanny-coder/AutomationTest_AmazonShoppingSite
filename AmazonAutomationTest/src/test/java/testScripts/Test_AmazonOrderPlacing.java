package testScripts;


import org.testng.ITestResult;
import org.testng.annotations.*;

import base.TestBase;
import engine.KeyWord;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultPage;
import pages.SignInPage;
import utils.Reporting;

public class Test_AmazonOrderPlacing extends TestBase {
	static HomePage homePage;
	static KeyWord keyWord;
	static SignInPage signInPage;
	static SearchResultPage searchResultPage;
	static ProductPage productPage;
	static CheckoutPage checkoutPage;
	static Reporting report = new Reporting();
	
	public Test_AmazonOrderPlacing() {
		super();
	}
	
	@BeforeSuite
	public void setUpExtentReport() {
		report.setUpReport();
	}
	
	@BeforeTest
	public void setUp() {	
		setupBrowser();
		homePage = new HomePage();
	}
	
	@Test(priority = 1)
	public void signingInToAmazon() throws Exception{
		String expHomePageTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String expSignInPageTitle = "Amazon Sign In";
		String emailId = prop.getProperty("emailId");
		String passWord = prop.getProperty("passWord");
		String userName = prop.getProperty("userName");
		
	
		report.testCase("Signing in to Amazon", "Navigating to Sign In page of Amazon and signing in with the user credentials.");
		
		Thread.sleep(2000);
		System.out.println("Verify and Validate HomePage.");
		homePage.verifyAndValidateHomePage(expHomePageTitle);
	
		Thread.sleep(2000);
		System.out.println("Navigating to Sign In page from HomePage");
		signInPage = homePage.goToSignInPage();
		
		Thread.sleep(2000);
		System.out.println("Verify and Validate Sign In Page");
		report.info("Verify and Validate Sign In Page");
		signInPage.verifyAndValidateSignInPage(expSignInPageTitle);
		
		Thread.sleep(2000);
		System.out.println("Signing into Amazon.in");
		homePage = signInPage.enterSignInCredentialsAndLogin(emailId, passWord);
		homePage.getPageDetails();
		
		Thread.sleep(2000);
		System.out.println("Verify user logged into Amazon.in");
		homePage.verfiyLoggedInUser(userName);
		homePage.deleteCartIfNotEmpty();
		homePage.deleteOldAddressIfAvailable();	
	}
	
	@Test(priority = 2)
	public void  searchingForProduct() throws Exception{
		String productToSearch = "OnePlus Nord CE 3 Lite 5G";
		
		report.testCase("Searching for a product", "Search for a specific product and verifying correct search results are displayed.");
		
		Thread.sleep(2000);
		System.out.println("Searching for a product");
		searchResultPage = homePage.searchForProduct(productToSearch);
		searchResultPage.getPageDetails();
		
		Thread.sleep(2000);
		System.out.println("Verifying Search Result");
		searchResultPage.verifySearchResults(productToSearch);
		
	}
	
	@Test(priority = 3)
	public void filteringSearchResults() throws Exception{
		String minPrice = "10000";
		String maxPrice = "25000";
		
		report.testCase("Filtering the Search Results", "Filtering the search results by price range and verify that the filtered results are displayed correctly.");
		Thread.sleep(2000);
		System.out.println("Filtering Price Range");
		searchResultPage.filterPriceRange(minPrice, maxPrice);
		
		Thread.sleep(2000);
		System.out.println("Validating Price filter results");
		searchResultPage.validatePriceFilterResult(minPrice, maxPrice);
	}
	
	@Test(priority = 4)
	public void addingProductToShoppingCart() throws Exception{
		String productToSearch = "OnePlus Nord CE 3 Lite 5G";
		String productColour = "Chromatic Gray";
		
		report.testCase("Adding a product to the shopping cart", "Navigating to the selected product page, selecting the color of product, and adding the product to the shopping cart.");
		
		Thread.sleep(2000);
		System.out.println("Selecting a product");
		productPage = searchResultPage.selectProductFromSearchResults(productToSearch);
		productPage.getPageDetails();
		
		Thread.sleep(2000);
		System.out.println("Verifying Product page displayed");
		productPage.verifyProductPageDisplayed();;		
		
		Thread.sleep(2000);
		System.out.println("Selecting colour and memory size of product");
		productPage.selectColour(productColour);
		
		Thread.sleep(2000);
		System.out.println("Adding product to Cart");
		productPage.clickOnAddToCartButton();
	}
	
	@Test(priority = 5)
	public void proceedingToCheckout() throws Exception{
		report.testCase("Proceeding to checkout", "Proceeding to the checkout page and verify that the correct product and options are displayed in the shopping cart.");
		
		Thread.sleep(2000);
		System.out.println("Verifying and Validating Product Price");
		productPage.verifyProductAddedToCart();
		
		Thread.sleep(2000);
		System.out.println("Proceeding to Checkout");
		checkoutPage = productPage.proceedToCheckout();
		
		Thread.sleep(2000);
		System.out.println("Verifying Checkout Page");
		checkoutPage.verifyCheckoutPage();
	}
	
	@Test(priority = 6)
	public void fillingOutCheckoutForm() throws Exception{
		String country = "India";
		String fullName = "Automation User";
		String pinCode = "110015";
		String mobileNo = "8796475821";
		String houseNo = "F 72";
		String area = "Sudarshan Park, Moti Nagar"; 
		String city = "Delhi";
		String state = "Delhi";
		
		report.testCase("Filling out the checkout form", "Filling the checkout form with the user personal information.");	
		Thread.sleep(2000);
		System.out.println("Filling the address details");
		checkoutPage.fillAddressDetails(country, fullName, mobileNo, pinCode, houseNo, area, city, state);
		checkoutPage.verifyGrandTotalOrder();	
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException {
		driver.close();
		driver.quit();
	}
	
	@AfterMethod
    public void getResult(ITestResult result) {
        report.getResult(result);
    }
	
	@AfterSuite
	public void flushReport() {
		report.flushReport();
	}
}
