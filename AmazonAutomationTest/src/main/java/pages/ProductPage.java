package pages;

import org.testng.Assert;

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
	
	public void verifyProductPageDisplayed() {
		report.info("Verifying Product Page is Displayed");
		keyWord.waitUntilElementIsDisplayedOnScreen("xpath", AmazonObjRepo.productPageContainer);
	}

	public void selectColour(String phoneColour) throws Exception {
		report.info("Selecting colour of the product");
		System.out.println("Picking Colour");
		keyWord.scrollToViewThenClick("xpath", "//img[@alt='"+phoneColour+"']/../../..//input");
		keyWord.verifyElementExists("xpath", "//span[@id='inline-twister-expanded-dimension-text-color_name'][contains(text(),'"+phoneColour+"')]");
	}

	public void clickOnAddToCartButton() throws Exception{
		report.info("Click on Add to Cart Button");
		keyWord.scrollToViewThenClick("xpath", AmazonObjRepo.addToCarButton);
	}

	public CheckoutPage proceedToCheckout() throws Exception {
		report.info("Proceeding to Checkout Page");
		keyWord.click("xpath", AmazonObjRepo.proceedToCheckoutButton);
		return new CheckoutPage();
	}

	public void getPageDetails() {
		System.out.println("Current URL is :" + keyWord.getCurrentURL());
	}

	public int getProductPrice() {
		keyWord.scrollToView("xpath", AmazonObjRepo.productPrice);
		String prodPrice = keyWord.getElementText("xpath", AmazonObjRepo.productPrice);
		return Integer.parseInt(prodPrice.split(",")[0]+prodPrice.split(",")[1]);
	}

	public void verifyProductAddedToCart() throws Exception {
		report.info("Verifying Product Added to Cart");
		keyWord.scrollToView("xpath", AmazonObjRepo.addedToCartLabel);
		keyWord.verifyElementExists("xpath", AmazonObjRepo.addedToCartLabel);
	}
	
	
}
