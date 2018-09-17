package com.ee.cucumber.pageobjects;

import com.ee.cucumber.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractPage {

	@Autowired
	protected DriverFactory driverFactory;

	protected WebDriver driver;

	public AbstractPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 Some convience methods to help with writing method on page object level. Ideally move this out into a util class.
	 */
	public void type(WebElement arg0, String text) {
		clear(arg0);
		typeKeys(arg0, text);
	}

	public void typeKeys(WebElement arg0, String text) {
		arg0.sendKeys(text);
	}

	public void clear(WebElement arg0) {
		arg0.clear();
	}

	public void select(WebElement arg0, String text){
		new Select(arg0).selectByVisibleText(String.valueOf(text));
	}

	public void waitForElementToDisplay(WebElement element, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void clickAndWait(WebElement locator, long waitTime) {
		locator.click();
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
