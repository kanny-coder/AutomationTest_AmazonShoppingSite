package pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;

import base.TestBase;
import engine.KeyWord;
import utils.AmazonObjRepo;
import utils.Reporting;

public class SearchResultPage extends TestBase {

	KeyWord keyWord;
	Reporting report = new Reporting();

	public SearchResultPage() {
		keyWord = new KeyWord();
	}

	public void verifySearchResults(String productToSearch) throws Exception {
		report.info("Verifying Search Results");
		System.out.println("Verifying Search result Info Bar on Top");
		keyWord.verifyElementExists("xpath", AmazonObjRepo.searchResultInfoBarTop);
		System.out.println("Verifying searched Product name");
		keyWord.verifyElementExists("xpath",
				"//span[@data-component-type='s-result-info-bar']//span[contains(text(),'" + productToSearch + "')]");
	}

	public void filterPriceRange(String minPrice, String maxPrice) throws Exception {
		report.info("Filtering by Price Range");
		keyWord.scrollToView("id", AmazonObjRepo.priceFilterColumn);
		System.out.println("Entering Minimum price to filer");
		keyWord.enterText("xpath", AmazonObjRepo.minPriceFilter, minPrice);
		System.out.println("Entering Maximum price to filter");
		keyWord.enterText("xpath", AmazonObjRepo.maxPriceFilter, maxPrice);
		System.out.println("Submitting Price Filter");
		keyWord.click("xpath", AmazonObjRepo.priceFilterSubmitBtn);
	}

	public void validatePriceFilterResult(String minPrice, String maxPrice) {
		report.info("Validating Price Filter Results");
		List<String> prices = keyWord.getAllTextVals("xpath", AmazonObjRepo.priceTagsSearchResults);
		boolean flag = true;
		ArrayList<Integer> val = new ArrayList<>();
		for (int i = 0; i < prices.size(); i++) {
			if (Integer.parseInt(prices.get(i).split(",")[0] + prices.get(i).split(",")[1]) < Integer
					.parseInt(minPrice)) {
				flag = false;
				val.add(Integer.parseInt(prices.get(i).split(",")[0] + prices.get(i).split(",")[1]));
			}
			if (Integer.parseInt(prices.get(i).split(",")[0] + prices.get(i).split(",")[1]) > Integer
					.parseInt(maxPrice)) {
				val.add(Integer.parseInt(prices.get(i).split(",")[0] + prices.get(i).split(",")[1]));
				flag = false;
			}
		}
		if (!flag) {
			report.fail("Products are not filtered between the price range specified : Rs." + minPrice + "- Rs."
					+ maxPrice);
			report.info("Incorrect Prices are : ");
			System.err.println(
					"Products are not filtered according to specified price range, following are the incorrect prices : ");
			ArrayList<Integer> incPrices = new ArrayList<>();
			for (Iterator iterator = val.iterator(); iterator.hasNext();) {
				Integer integer = (Integer) iterator.next();
				incPrices.add(integer);
				System.err.println("Price :" + integer);
			}
			report.info(incPrices.toString());

		} else {
			report.pass("Products are filtered Successfully between the price range specified : Rs." + minPrice
					+ "- Rs." + maxPrice);
			System.out.println("Products are filtered correctly");
		}
	}

	public ProductPage selectProductFromSearchResults(String productToSearch) throws Exception {
		report.info("Selecting a Product");
		System.out.println("Selecting a Product");
		keyWord.scrollToViewThenClick("xpath",
				"//a[@target='_blank']//span[contains(text(),'" + productToSearch + "')]");	
		report.info("Switching Product page tab");
		keyWord.tabSwitch(1);
		return new ProductPage();
	}

	public void getPageDetails() {
		System.out.println("Current URL is :" + keyWord.getCurrentURL());
	}
}
