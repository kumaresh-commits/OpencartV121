package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.SearchResultsPage;
import testBase.BaseClass;

public class TC004_Search extends BaseClass {

	@Test(groups = { "Sanity", "Master" })
	public void verify_ProductSearch() {

		logger.info("****** Started TC004_Search - verify_ProductSearch ******");

		try {
			SearchResultsPage srp = new SearchResultsPage(driver);

			String productName = p.getProperty("SearchProductName");
			logger.info("Searching for product: " + productName);

			srp.enterSearchKeyword(productName);
			srp.clickSearch();

			logger.info("Validating search results page is displayed...");
			Assert.assertTrue(srp.isSearchPageDisplayed(), "Search results page not displayed");

			logger.info("Validating product is found in results...");
			Assert.assertTrue(srp.isProductFound(), "Product '" + productName + "' not found in search results");

		} catch (Exception e) {
			logger.error("TC004_Search failed: " + e.getMessage());
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}

		logger.info("****** Finished TC004_Search - verify_ProductSearch ******");
	}

	@Test(groups = { "Regression", "Master" })
	public void verify_InvalidProductSearch() {

		logger.info("****** Started TC004_Search - verify_InvalidProductSearch ******");

		try {
			SearchResultsPage srp = new SearchResultsPage(driver);

			String invalidProduct = "xyznonexistentproduct12345";
			logger.info("Searching for invalid product: " + invalidProduct);

			srp.enterSearchKeyword(invalidProduct);
			srp.clickSearch();

			Assert.assertTrue(srp.isSearchPageDisplayed(), "Search results page not displayed");

			logger.info("Validating no results message is shown...");
			Assert.assertTrue(srp.isNoResultsMessageDisplayed(),
					"Expected 'no product' message for invalid search");

		} catch (Exception e) {
			logger.error("TC004_Search invalid search failed: " + e.getMessage());
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}

		logger.info("****** Finished TC004_Search - verify_InvalidProductSearch ******");
	}
}
