package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage {

	private WebDriver driver;

	// Constructor
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}

	// Locator for PIM tab in the main menu
	private By pimTab = By.xpath("//a[contains(@href,'pim/viewPimModule')]");

	// Method to return PIM tab element
	public WebElement pimTab() {
		return driver.findElement(pimTab);
	}
}
