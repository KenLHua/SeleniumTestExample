package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
	public WebDriver driver;
	
	public Base(WebDriver driver) {this.driver = driver;}
	
	public WebElement find(By locator) {
		return driver.findElement(locator);
	}
	
	public Boolean isDisplayed(By locator) {
		try {
			return find(locator).isDisplayed();
		}catch(org.openqa.selenium.NoSuchElementException exception) {
			return false;
		}
	}
	
	public Boolean isDisplayed(By locator, int maxWaitTime) {
		try {
			waitFor(ExpectedConditions.visibilityOfElementLocated(locator), maxWaitTime);
		} catch (org.openqa.selenium.TimeoutException exception) {
			return false;
		}
		return true;
	}
	
	
	public void waitFor(ExpectedCondition<WebElement> condition, Integer timeout) {
		timeout = timeout != null ? timeout : 5;
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		wait.until(condition);
	}

	public void waitForLoad() {
		ExpectedCondition<Boolean> pageLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver arg0) {
				return (Boolean)((JavascriptExecutor)driver)
						.executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(pageLoad);
	}

}
