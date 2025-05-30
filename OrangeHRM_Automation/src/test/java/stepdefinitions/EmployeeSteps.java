package stepdefinitions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import pages.*;
import utilities.ConfigReader;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class EmployeeSteps {
	WebDriver driver = Hooks.getDriver();
	LoginPage loginPage = new LoginPage(driver);
	DashboardPage dashboardPage = new DashboardPage(driver);
	PIMPage pimPage = new PIMPage(driver);

	@Given("User launches the OrangeHRM application")
	public void userLaunchesTheOrangeHRMApplication() {
		driver.get(ConfigReader.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
	}

	@Given("User logs in with valid credentials")
	public void userLogsInWithValidCredentials() {
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
	}

	@When("User hovers over the PIM tab")
	public void userHoversOverThePIMTab() {
		Actions actions = new Actions(driver);
		actions.moveToElement(dashboardPage.pimTab()).perform();
	}

	@When("User clicks on the PIM tab")
	public void userClicksOnThePIMTab() {
		dashboardPage.pimTab().click();
	}

	@Then("PIM dashboard should be visible")
	public void pimDashboardShouldBeVisible() {
		String pageName = pimPage.getPageName();
		System.out.println("Page Title: " + pageName);
		Assert.assertEquals(pageName, "PIM");
	}

	@When("User navigates to Add Employee section")
	public void userNavigatesToAddEmployeeSection() {
		userClicksOnThePIMTab();
		pimPage.getAddEmployeeButton().click();
	}

	@When("User adds employee with first name {string}, middle name {string}, last name {string} and employee id {string}")
	public void userAddsEmployeeWithFirstNameMiddleNameLastNameAndEmployeeId(String fName, String mName, String lName,
			String empId) {
		pimPage.addEmployee(fName, mName, lName, empId);
	}

	@Then("Employee should be added successfully")
	public void employeeShouldBeAddedSuccessfully() {
		System.out.println("Employee Add: " + pimPage.getNotification());
		Assert.assertEquals(pimPage.getNotification(), "Success");
	}

	@When("User navigates to Employee List")
	public void userNavigatesToEmployeeList() {
		userClicksOnThePIMTab();
		pimPage.getEmployeeListTab().click();
	}

	@Then("The following employees should be present in the list:")
	public void theFollowingEmployeesShouldBePresentInTheList(io.cucumber.datatable.DataTable dataTable) {
		List<String> expectedNames = dataTable.asList(String.class);
		List<String> actualNames = pimPage.getAllEmployeeFullNames();

		for (String expected : expectedNames) {
			Assert.assertTrue("Employee name not found: " + expected, actualNames.contains(expected));
			System.out.println("Name Verified: " + expected);
		}
	}

}