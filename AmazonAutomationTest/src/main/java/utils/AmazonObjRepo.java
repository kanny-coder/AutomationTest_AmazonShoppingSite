package utils;

public class AmazonObjRepo {
	// XPATHS

	// HomePage
	public static String signInBtnHomePage = "//div[@id='nav-flyout-ya-signin']//span[@class='nav-action-inner'][normalize-space()='Sign in']";
	public static String loggedInUser = "//a[@id='nav-link-accountList']//div//span[@id='nav-link-accountList-nav-line-1']";
	public static String cartIcon = "//span[@id='nav-cart-count']";
	public static String getCartCount = "//span[@id='nav-cart-count']";
	public static String yourAccount = "//span[@class='nav-text'][normalize-space()='Your Account']";
	public static String yourAddressesButton = "//div[@data-card-identifier='AddressesAnd1Click']//div//h2[contains(text(),'Your Addresses')]";
	public static String removeAddressButton = "//div[@id='ya-myab-edit-address-desktop-row-0']//span[@class='a-declarative']//a[contains(text(),'Remove')]";
	public static String deleteAddrConfirmationYes = "//span[@id='deleteAddressModal-0-submit-btn']//input[@type='submit']";

	// Cart
	public static String deleteCartItem = "//input[@value='Delete']";

	// SignIn Page
	public static String signInHeader_SignInPage = "//h1[normalize-space()='Sign in']";

	// Search Results Page
	public static String searchResultInfoBarTop = "//div[@id='search']//span[@data-component-type='s-result-info-bar']";
	public static String minPriceFilter = "//div[@id='priceRefinements']//ul//input[@id='low-price']";
	public static String maxPriceFilter = "//div[@id='priceRefinements']//ul//input[@id='high-price']";
	public static String priceFilterSubmitBtn = "//div[@id='priceRefinements']//span//form//span[@id='a-autoid-1']//input[(@type='submit')]";
	public static String priceTagsSearchResults = "//span[@class='a-price-whole']";

	// Product Page
	public static String addToCarButton = "//input[@id='add-to-cart-button']";
	public static String productPrice = "//div[@id='corePriceDisplay_desktop_feature_div']//div//span[@class='a-price-whole']";
	public static String productPageContainer = "//div[@id='dp-container']";
	public static String addedToCartLabel = "//div[@id='attach-added-to-cart-message']//div[@id='attachDisplayAddBaseAlert']//span[contains(text(),'Added to Cart')]";
	public static String cartSubtotal = "//b[normalize-space()='Cart subtotal']/../../following-sibling::span//span";
	public static String proceedToCheckoutButton = "//input[@aria-labelledby='attach-sidesheet-checkout-button-announce']";

	// Checkout Page
	public static String checkOutPage_Header = "//h1[normalize-space()='Checkout']";
	public static String checkOutPage_Form = "//form[@id='address-ui-checkout-form']";
	public static String countryDropdown = "//span[@id='address-ui-widgets-countryCode']//span[@role='button']";
	public static String stateDropdown = "//span[@id='address-ui-widgets-enterAddressStateOrRegion']//span[@role='button']";
	public static String useThisAddressButton = "//input[@aria-labelledby='address-ui-widgets-form-submit-button-announce']";
	public static String selectPayWithCardRadioBtn = "//input[@value='SelectableAddCreditCard']";
	public static String enterCardDetailsLink = "//span[@id='apx-add-credit-card-action-test-id']//div//a[@id='pp-48d0Yo-95']";
	public static String grandTotalPrice = "//div[@id='subtotals-marketplace-table']//table[@class='a-normal small-line-height']//tbody//tr//td[contains(@class,'grand-total-price')]";

//	*****************************************************************************************************************

	// ID's

	// HomePage
	public static String homePageAmazonLogo = "nav-logo-sprites";
	public static String accountListDropDown = "nav-link-accountList";
	public static String productSearchBar = "twotabsearchtextbox";
	public static String productSearchButton = "nav-search-submit-button";

	// SignIn Page
	public static String emailIdField_signIn = "ap_email";
	public static String continueBtn = "continue";
	public static String passWordField_signIn = "ap_password";
	public static String signInBtn = "signInSubmit";

	// Search Results Page
	public static String priceFilterColumn = "priceRefinements";

	// Checkout Page
	public static String fullName_Addr = "address-ui-widgets-enterAddressFullName";
	public static String phoneNumber_Addr = "address-ui-widgets-enterAddressPhoneNumber";
	public static String pinCode_Addr = "address-ui-widgets-enterAddressPostalCode";
	public static String houseNo_Addr = "address-ui-widgets-enterAddressLine1";
	public static String area_Addr = "address-ui-widgets-enterAddressLine2";
	public static String city_Addr = "address-ui-widgets-enterAddressCity";
}
