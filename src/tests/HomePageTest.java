package tests;

import pageobjects.HomePage;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
	void checkTitleTest() {
		assertEquals("Google", checkTitle());
		
	}
	@Test
	void checkLuckyButtonTest() {
		assertTrue(page.isDisplayed(By.id("gbqfbb")));
	}
	
	@Ignore
	public String checkTitle() {
		return driver.getTitle();
	}
}
