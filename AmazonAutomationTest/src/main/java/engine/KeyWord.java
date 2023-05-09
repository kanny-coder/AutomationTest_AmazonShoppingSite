package engine;

import java.time.Duration;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;
import base.TestBase;
import utils.Reporting;
import utils.StaticVals;

public class KeyWord extends TestBase {

	static WebDriverWait wait;
	private static WebElement element = null;
	private static String passMessage = "", failMessage = "";
	static Actions action = null;
	static Reporting report = new Reporting();

	/*
	 * This method will navigate to the specified URL
	 */
	public void navigateToURL(String URL) {
		try {
			driver.navigate().to(URL);
			passMessage = "Successfully navigated to : " + URL;
			System.out.println(passMessage);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			failMessage = "Navigation to " + URL + " failed";
			System.err.println(failMessage);
		}
	}

	/*
	 * This method will return the "By" locator
	 */
	public By locatorValue(String locatorType, String locatorPath) {
		By by;

		switch (locatorType) {
		case "id":
			by = By.id(locatorPath);
			break;
		case "name":
			by = By.name(locatorPath);
			break;
		case "xpath":
			by = By.xpath(locatorPath);
			break;
		case "css":
			by = By.cssSelector(locatorPath);
			break;
		case "linkText":
			by = By.linkText(locatorPath);
			break;
		case "partialLinkText":
			by = By.partialLinkText(locatorPath);
			break;
		default:
			by = null;
			break;
		}
		return by;
	}

	/*
	 * This method will click on the WebElement
	 */
	public void click(String locatorType, String locatorPath) throws Exception {
		By locator;
		locator = locatorValue(locatorType, locatorPath);
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(StaticVals.TIMEOUT));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			element = driver.findElement(locator);
			element.click();
			passMessage = "The following element is clickable: " + locator;
			report.pass(passMessage);
		} catch (Exception e) {
			failMessage = "The following element is not clickable: " + locator;
			report.fail(failMessage);
			throw new TestException(failMessage);
		}
	}
	
	/*
	 * This method verifies if the particular WebElement exists on the Web page or not
	 */
	public void verifyElementExists(String locatorType, String loctorPath) throws Exception {
		By locator;
		locator = locatorValue(locatorType, loctorPath);

		try {
			List<WebElement> element = driver.findElements(locator);
			int size = element.size();
			if (!(size > 0)) {
				failMessage = "Element " + locator + " does not Exist";
				System.err.println(failMessage);
				report.fail(failMessage);
			} else {
				passMessage = "Element " + locator + " Exists";
				System.out.println(passMessage);
				report.pass(passMessage);
			}
		} catch (Exception e) {
			throw new TestException(failMessage);
		}
	}
	
	/*
	 * This method is used to Hover over the particular WebElement
	 */
	public void mouseHover(String locatorType, String locatorPath) {
		By locator;
		locator = locatorValue(locatorType, locatorPath);
		action = new Actions(driver);
		try {
			element = driver.findElement(locator);
			action.moveToElement(element).perform();
			passMessage = "Mouse Hover element " + locator + " successful";
			System.out.println(passMessage);
			report.pass(passMessage);
		} catch (Exception e) {
			failMessage = "Element " + locator + " not found";
			report.fail(failMessage);
			System.out.println(failMessage);
		}
	}

	/*
	 * This method waits till WebElement's presence on the DOM of a page. This does not necessarily mean that the element is visible.
	 */
	public void waitForElementToBeVisible(String locatorType, String locatorPath) {
		By locator;
		locator = locatorValue(locatorType, locatorPath);
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(StaticVals.TIMEOUT));
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			passMessage = "The following element is visible: " + locator;
			System.out.println(passMessage);
			report.pass(passMessage);
		} catch (Exception e) {
			failMessage = "The following element is not visible: " + locator;
			report.fail(failMessage);
			throw new NoSuchElementException(failMessage);
		}
	}

	/*
	 * This method waits until the visibility of WebElement on screen
	 */
	public void waitUntilElementIsDisplayedOnScreen(String locatorType, String locatorPath) {
		By locator;
		locator = locatorValue(locatorType, locatorPath);
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(StaticVals.TIMEOUT));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			passMessage = "The following element is visible: " + locator;
			report.pass(passMessage);
		} catch (Exception e) {
			failMessage = "The following element is not visible: " + locator;
			;
			report.fail(failMessage);
			throw new NoSuchElementException(failMessage);
		}
	}

	/*
	 * This Method enters the provided text in arguments to the WebElement location specified
	 */
	public void enterText(String locatorType, String locatorPath, String value) {
		By locator;
		locator = locatorValue(locatorType, locatorPath);
		try {
			element = driver.findElement(locator);
			element.click();
			clearTextFied(locatorType, locatorPath);
			element.sendKeys(value);
			passMessage = String.format("[%s] successfully sent to the following element: [%s]", value,
					locator.toString());
			report.pass(passMessage);
		} catch (Exception e) {
			failMessage = String.format("Error in sending [%s] to the following element: [%s]", value,
					locator.toString());
			report.fail(failMessage);
			throw new TestException(failMessage);
		}
	}

	/*
	 * This Method clears the text field of particular WebElement
	 */
	public void clearTextFied(String locatorType, String locatorPath) {
		By locator;
		locator = locatorValue(locatorType, locatorPath);

		try {
			element = driver.findElement(locator);
			element.clear();
			passMessage = "Element cleared";
			report.pass(passMessage);
		} catch (Exception e) {
			failMessage = "The following element could not be cleared: " + element.getText();
			report.fail(failMessage);
			throw new TestException(failMessage);
		}
	}

	/*
	 * This Method returns the WebElement for specified locator
	 */
	public WebElement getElement(String locatorType, String locatorPath) {
		By locator;
		locator = locatorValue(locatorType, locatorPath);

		try {
			element = driver.findElement(locator);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	/*
	 * This Method returns "By" locator
	 */
	public By getLocator(String locatorType, String locatorPath) {
		By locator;
		locator = locatorValue(locatorType, locatorPath);

		return locator;
	}

	/*
	 * This Method returns the Title of Current webpage
	 */
	public String getPageTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			throw new TestException("Current page title is: " + driver.getTitle());
		}
	}

	/*
	 * This Method returns the current of Webpage.
	 */
	public String getCurrentURL() {
		try {
			return driver.getCurrentUrl();
		} catch (Exception e) {
			throw new TestException("Current URL is: " + driver.getCurrentUrl());
		}
	}

	/*
	 * This method returns the enclosed Text by specified WebElement
	 */
	public String getElementText(String locatorType, String locatorPath) {
		By locator = getLocator(locatorType, locatorPath);
		waitUntilElementIsDisplayedOnScreen(locator);
		try {
			String Text = driver.findElement(locator).getText();
			return Text;
		} catch (Exception e) {
			System.out.println(String.format("Element %s does not exist", locator));
		}
		return null;
	}

	/*
	 * This Method waits until the WebElement is displayed on screen
	 */
	public void waitUntilElementIsDisplayedOnScreen(By locator) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(StaticVals.TIMEOUT));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			passMessage = "The following element is visible: " + locator;
			report.pass(passMessage);
		} catch (Exception e) {
			failMessage = "The following element is not visible: " + locator;
			report.fail(failMessage);
			throw new NoSuchElementException(failMessage);
		}
	}

	/*
	 * This method returns the count of specified WebElement
	 */
	public int getCount(String locatorType, String locatorPath) {

		By locator = getLocator(locatorType, locatorPath);
		int count = 0;
		try {
			List<WebElement> element = driver.findElements(locator);
			count = element.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	/*
	 * This method will scroll to view the specified WebElement
	 */
	public void scrollToView(String locatorType, String locatorPath) {
		element = getElement(locatorType, locatorPath);
		action = new Actions(driver);
		waitForElementToBeVisible(locatorType, locatorPath);
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			passMessage = "The following element is available to view: " + element.toString();
			report.pass(passMessage);
		} catch (Exception e) {
			failMessage = "The following element is not available to view: " + element.toString();
			report.fail(failMessage);
			throw new TestException(failMessage);
		}
	}

	/*
	 * This method will scroll to the specified WebElement and then it will click on it
	 */
	public void scrollToViewThenClick(String locatorType, String locatorPath) {
		element = getElement(locatorType, locatorPath);
		action = new Actions(driver);
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			action.moveToElement(element).perform();
			action.click(element).perform();
			passMessage = "The following element is clickable: " + element.toString();
			report.pass(passMessage);
		} catch (Exception e) {
			failMessage = "The following element is not clickable: " + element.toString();
			report.fail(failMessage);
			throw new TestException(String.format("The following element is not clickable: [%s]", element.toString()));
		}
	}

	/*
	 * The method returns the List of all Text Values of all the specified WebElements
	 */
	public List<String> getAllTextVals(String locatorType, String locatorPath) {
		By locator;
		locator = locatorValue(locatorType, locatorPath);
		List<WebElement> elements = driver.findElements(locator);
		ArrayList<String> values = new ArrayList<>();
		for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
			element = (WebElement) iterator.next();
			values.add(element.getText());
		}
		return values;
	}

	/*
	 * This Method switches to specified tab number.
	 */
	public void tabSwitch(int n) throws Exception {
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		if (n >= 0 && n < tabs.size()) {
			// Switch to the opened tab
			driver.switchTo().window(tabs.get(n));
			passMessage = "Switch to tab " + (n + 1) + " is successful";
			report.pass(passMessage);
			System.out.println(getPageTitle());
		} else {
			failMessage = "Switch to tab " + (n + 1) + " is unsuccessful. Tab not found";
			report.fail(failMessage);
			System.err.println(failMessage);
			throw new TestException(failMessage);
		}
	}

	/*
	 * This Method refresh the current window
	 */
	public void refreshWindow() {
		driver.navigate().refresh();
	}
}
