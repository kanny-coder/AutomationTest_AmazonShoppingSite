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

	public By getLocator(String locatorType, String locatorPath) {
		By locator;
		locator = locatorValue(locatorType, locatorPath);

		return locator;
	}

	public String getPageTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			throw new TestException("Current page title is: " + driver.getTitle());
		}
	}

	public String getCurrentURL() {
		try {
			return driver.getCurrentUrl();
		} catch (Exception e) {
			throw new TestException("Current URL is: " + driver.getCurrentUrl());
		}
	}

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

	public void refreshWindow() {
		driver.navigate().refresh();
	}

	public void clickIf(String locatorType, String locatorPath) {
		By locator;
		locator = locatorValue(locatorType, locatorPath);
		try {
			List<WebElement> elements = driver.findElements(locator);
			if (!elements.isEmpty()) {
				element = driver.findElement(locator);
				element.click();
				passMessage = "Clicked Successfully";
				report.pass(passMessage);
			}
		} catch (Exception e) {
			failMessage = "Element not found";
			report.fail(failMessage);
			throw new TestException(failMessage);
		}
	}
}
