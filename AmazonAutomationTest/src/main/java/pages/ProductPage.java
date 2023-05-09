package pages;

import base.TestBase;
import engine.KeyWord;
import utils.AmazonObjRepo;
import utils.Reporting;

public class ProductPage extends TestBase{
	
	KeyWord keyWord;
	Reporting report = new Reporting();

	public ProductPage() {
		keyWord = new KeyWord();
	}
	
	/*
	 * This Method Verifies if Product Page is Displayed
	 */
	public void verifyProductPageDisplayed() {
		report.info("Verifying Product Page is Displayed");
		keyWord.waitUntilElementIsDisplayedOnScreen("xpath", AmazonObjRepo.productPageContainer);
	}

	/*
	 * This Method select the colour of the product
	 */
	public void selectColour(String phoneColour) throws Exception {
		report.info("Selecting colour of the product");
		System.out.println("Picking Colour");
		keyWord.scrollToViewThenClick("xpath", "//img[@alt='"+phoneColour+"']/../../..//input");
		keyWord.verifyElementExists("xpath", "//span[@id='inline-twister-expanded-dimension-text-color_name'][contains(text(),'"+phoneColour+"')]");
	}

	/*
	 * This Method clicks on "Add To Cart" Button
	 */
	public void clickOnAddToCartButton() throws Exception{
		report.info("Click on Add to Cart Button");
		keyWord.scrollToViewThenClick("xpath", AmazonObjRepo.addToCarButton);
	}

	/*
	 * This Method clicks on Proceed to Checkout button to open checkout page
	 */
	public CheckoutPage proceedToCheckout() throws Exception {
		report.info("Proceeding to Checkout Page");
		keyWord.click("xpath", AmazonObjRepo.proceedToCheckoutButton);
		return new CheckoutPage();
	}

	/*
	 * This Method gets the current Page details
	 */
	public void getPageDetails() {
		System.out.println("Current URL is :" + keyWord.getCurrentURL());
	}

	/*
	 * This Method gets the Product Price
	 */
	public int getProductPrice() {
		keyWord.scrollToView("xpath", AmazonObjRepo.productPrice);
		String prodPrice = keyWord.getElementText("xpath", AmazonObjRepo.productPrice);
		return Integer.parseInt(prodPrice.split(",")[0]+prodPrice.split(",")[1]);
	}

	/*
	 * This Method verifies if the product is added to Cart
	 */
	public void verifyProductAddedToCart() throws Exception {
		report.info("Verifying Product Added to Cart");
		keyWord.scrollToView("xpath", AmazonObjRepo.addedToCartLabel);
		keyWord.verifyElementExists("xpath", AmazonObjRepo.addedToCartLabel);
	}
}
