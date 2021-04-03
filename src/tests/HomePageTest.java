package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import pageobjects.HomePage;

public class HomePageTest {
	private WebDriver driver;
	private HomePage page;
	
	@BeforeAll
	static void setupOnce() {
		System.setProperty("webdriver.chrome.driver","/WebDriver/chromedriver.exe");
		System.setProperty("baseUrl", "https://www.google.com");
		System.out.println("BeforeAll");
		
	}
	
	@BeforeEach
	void setUp() throws InterruptedException {
		driver = new ChromeDriver();
		driver.get(System.getProperty("baseUrl"));
		page = new HomePage(driver);
		page.waitForLoad();
	}
	
	@AfterEach
	void tearDown() {driver.quit();}
	

	@Test
	@Disabled
	void checkTitleTest() {
		assertEquals("Google", checkTitle());
		
	}
	@Test
	void checkSearch() {
		WebElement searchBox = driver.findElement(By.className("gLFyf"));
		searchBox.sendKeys("Hello");
		searchBox.sendKeys(Keys.RETURN);
		page.waitForLoad();
		searchBox = driver.findElement(By.className("gLFyf"));
		
		// Check that text value in the next page is "Hello"
		assert searchBox.getAttribute("value").equals("Hello");
		
		// Check url redirect is correct
		assert driver.getCurrentUrl().substring(0, 37).equals("https://www.google.com/search?q=Hello");
		
		driver.navigate().back();
		
		page.waitForLoad();
		searchBox = driver.findElement(By.className("gLFyf"));
		searchBox.sendKeys("Goodbye");
		
		// Same test but click on the button this time
		List<WebElement> confirmSearchButton = driver.findElements(By.className("gNO89b"));
		System.out.println(confirmSearchButton.size());
		page.isDisplayed(By.className("gNO89b"),5);
		confirmSearchButton.get(0).click();
		
		page.waitForLoad();
		searchBox = driver.findElement(By.className("gLFyf"));
		// Check that text value in the next page is "Hello"
		assert searchBox.getAttribute("value").equals("Goodbye");
		
		// Check url redirect is correct
		assert driver.getCurrentUrl().substring(0, 39).equals("https://www.google.com/search?q=Goodbye");
		

	
	}
	
	
	@Test @Disabled
	void checkLuckyButtonTest() {
		assertTrue(page.isDisplayed(By.id("gbqfbb")));
	}
	
	public String checkTitle() {
		return driver.getTitle();
	}
}
