package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PIMPage {

	private WebDriver driver;
	private WebDriverWait wait;

	// Constructor
	public PIMPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	private By addEmployeeBtn = By.xpath("//div[@class='orangehrm-header-container']/button[@type='button']");
	private By firstNameField = By.name("firstName");
	private By middleNameField = By.name("middleName");
	private By lastNameField = By.name("lastName");
	private By employeeId = By
			.xpath("//label[contains(text(),'Employee Id')]/parent::div/following-sibling::div/input");
	private By saveButton = By.xpath("//button[@type='submit']");
	private By employeeFullName = By.xpath(
			"//h6[contains(text(),'Personal Details')]/following::div[contains(@class, 'orangehrm-edit-employee-name')]/h6");
	private By pageName = By
			.xpath("//div[@class='oxd-topbar-header-title']//h6[contains(@class,'header-breadcrumb-module')]");
	private By employeeListTab = By.xpath("//li/a[text()= 'Employee List']");
	private By mainNotification = By.xpath("//div[contains(@class,'toast-content--success')]/p[1]");
	// private By subNotification =
	// By.xpath("//div[contains(@class,'toast-content--success')]/p[2]");
	private By firstNameList = By.xpath("//div[contains(@class,'oxd-padding-cell')][@role='cell'][3]/div");
	private By lastNameList = By.xpath("//div[contains(@class,'oxd-padding-cell')][@role='cell'][4]/div");
	// private By nextPage = By.xpath("//button[contains(@class,'previous-next')]");

	public void addEmployee(String firstName, String middleName, String lastName, String empId) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
		driver.findElement(middleNameField).sendKeys(middleName);
		driver.findElement(lastNameField).sendKeys(lastName);

		// clearing the input box using Actions class
		WebElement employeeId = driver.findElement(this.employeeId);
		employeeId.click();
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).keyDown(Keys.BACK_SPACE).keyUp(Keys.BACK_SPACE)
				.perform();
		employeeId.sendKeys(empId);
		driver.findElement(saveButton).click();
	}

	public String getPageName() {
		String pagenm = driver.findElement(pageName).getText();
		return pagenm;
	}

	public String getNotification() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(mainNotification));
		String noti = driver.findElement(mainNotification).getText();
		return noti;
	}

	public String getPersonalFullName() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(employeeFullName));
		return driver.findElement(employeeFullName).getText().trim();
	}

	public WebElement getAddEmployeeButton() {
		return wait.until(ExpectedConditions.elementToBeClickable(addEmployeeBtn));
	}

	public WebElement getEmployeeListTab() {
		return wait.until(ExpectedConditions.elementToBeClickable(employeeListTab));
	}

	public List<String> getAllEmployeeFullNames() {
		List<String> fullNames = new ArrayList<>();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameList));

		List<WebElement> firstNames = driver.findElements(firstNameList);
		List<WebElement> lastNames = driver.findElements(lastNameList);

		for (int i = 0; i < firstNames.size(); i++) {
			js.executeScript("arguments[0].scrollIntoView();", firstNames.get(i));
			String fullName = firstNames.get(i).getText().trim() + " " + lastNames.get(i).getText().trim();
			fullNames.add(fullName);
		}
		return fullNames;
	}

}
