package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends BasePage {

	public SearchResultsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@name='search'][@class='form-control']")
	WebElement searchInput;

	@FindBy(xpath = "//button[contains(@class,'btn-light')]")
	WebElement searchButton;

	@FindBy(xpath = "//h1[contains(text(),'Search')]")
	WebElement searchPageHeading;

	@FindBy(xpath = "//div[@id='content']//div[contains(@class,'product-thumb')]")
	WebElement firstProduct;

	@FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criteria')]")
	WebElement noResultsMessage;

	public void enterSearchKeyword(String keyword) {
		searchInput.clear();
		searchInput.sendKeys(keyword);
	}

	public void clickSearch() {
		searchButton.click();
	}

	public boolean isSearchPageDisplayed() {
		try {
			return searchPageHeading.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isProductFound() {
		try {
			return firstProduct.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isNoResultsMessageDisplayed() {
		try {
			return noResultsMessage.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
