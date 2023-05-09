package pages;

import org.testng.Assert;
import base.TestBase;
import engine.KeyWord;
import utils.AmazonObjRepo;
import utils.Reporting;

public class CheckoutPage extends TestBase {
	KeyWord keyWord;
	Reporting report = new Reporting();
	String checkoutPageTitle = "Select a delivery address";

	public CheckoutPage() {
		keyWord = new KeyWord();
	}

	/*
	 * This Method verifies the CheckoutPage
	 */
	public void verifyCheckoutPage() {
		report.info("Verifying Checkout Page");
		keyWord.waitUntilElementIsDisplayedOnScreen("xpath", AmazonObjRepo.checkOutPage_Header);
		Assert.assertEquals(checkoutPageTitle, keyWord.getPageTitle());
		keyWord.waitUntilElementIsDisplayedOnScreen("xpath", AmazonObjRepo.checkOutPage_Form);
	}

	/*
	 * This Method fills the Delivery Address Details
	 */
	public void fillAddressDetails(String country, String fullName, String mobileNo, String pinCode, String houseNo,
			String area, String city, String state) throws Exception {
		report.info("Filling Address Details");
		
		report.info("Selecting Country");
		System.out.println("Selecting Country");
		keyWord.click("xpath", AmazonObjRepo.countryDropdown);
		keyWord.click("xpath", "//li[@role='option']//a[normalize-space()='" + country + "']");

		report.info("Entering Full Name");
		System.out.println("Entering Full Name");
		keyWord.enterText("id", AmazonObjRepo.fullName_Addr, fullName);

		report.info("Entering Mobile Number");
		System.out.println("Entering Mobile Number");
		keyWord.enterText("id", AmazonObjRepo.phoneNumber_Addr, mobileNo);

		report.info("Entering Pin Code");
		System.out.println("Entering Pin Code");
		keyWord.enterText("id", AmazonObjRepo.pinCode_Addr, pinCode);

		report.info("Filling Home Address");
		System.out.println("Filling Home Address");
		keyWord.enterText("id", AmazonObjRepo.houseNo_Addr, houseNo);
		keyWord.enterText("id", AmazonObjRepo.area_Addr, area);

		report.info("Entering City");
		System.out.println("Entering City");
		keyWord.enterText("id", AmazonObjRepo.city_Addr, city.toUpperCase());

		report.info("Selecting State");
		System.out.println("Selecting State");
		keyWord.click("xpath", AmazonObjRepo.stateDropdown);
		keyWord.click("xpath", "//li[@role='option']//a[normalize-space()='" + state.toUpperCase() + "']");

		report.info("Clicking - Use This Address Button");
		System.out.println("Clicking Use This Address Button");
		clickUseThisAddressButton();
	}

	/*
	 * This Method verifies the Grand Total of the Order
	 */
	public void verifyGrandTotalOrder() throws Exception {
		keyWord.verifyElementExists("xpath", AmazonObjRepo.grandTotalPrice);
		String grandTotal = keyWord.getElementText("xpath", AmazonObjRepo.grandTotalPrice);
		report.info("Total Bill : " + grandTotal);
	}

	/*
	 * This Method clicks on "Use This Address Button"
	 */
	public void clickUseThisAddressButton() throws Exception {
		keyWord.click("xpath", AmazonObjRepo.useThisAddressButton);
	}
}
