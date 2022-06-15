package devsavantautomationtest;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class GoogleSearchStepDefinitions {
	private static final String URL = "https://www.google.com";
	private static final By SEARCH_INPUT = By.name("q");
	private static final By I_FEEL_LUCK_SEARCH_BUTTON = By.xpath("(//*[@name='btnI'])[2]");
	private static final By DEVSAVANT_FIRST_TITLE = By.xpath("(//*[contains(@class,'heading-text')])[1]");
	private WebDriver driver;


	@Given("User is on the Google search page")
	public void userIsOnTheGoogleSearchPage() {
		WebDriverManager.chromedriver().setup();
		if (driver == null) {
			driver = new ChromeDriver();
		}
		driver.get(URL);
	}

	@When("searches the word {string} with the feeling lucky button")
	public void searchesTheWordWithTheFeelingLuckyButton(String word) {
		var searchInput = driver.findElement(SEARCH_INPUT);
		var iFeelLuckySearchButton = driver.findElement(I_FEEL_LUCK_SEARCH_BUTTON);
		searchInput.sendKeys(word);
		iFeelLuckySearchButton.click();
	}

	@Then("should see the main page of Devsavant")
	public void shouldSeeTheMainPageOfDevsavant() {
		new Actions(driver).pause(3000).perform();
		var devsavantFirstTitle = driver.findElement(DEVSAVANT_FIRST_TITLE);
		assertTrue(devsavantFirstTitle.getText().contains("BIENVENIDO A DEVSAVANT"));
		assertEquals("BIENVENIDO A DEVSAVANT", devsavantFirstTitle.getText());
		var ss = (TakesScreenshot) driver;
		try {
			FileUtils.copyFile(
				ss.getScreenshotAs(OutputType.FILE),
				new File("./target/googlesearch/resultpage.png")
			);
		} catch (IOException e) {
			fail("Failed to take screenshot");
		}
	}

	@After
	public void closeDriver() {
		if (driver != null) driver.quit();
	}
}
