package hooks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
	public static WebDriver driver;

	@Before
	public void setup() {
		PageFactory.initElements(driver, this);
		if (driver == null) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}
}
