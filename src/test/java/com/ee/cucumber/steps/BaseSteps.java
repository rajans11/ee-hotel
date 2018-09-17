package com.ee.cucumber.steps;

import com.ee.cucumber.cucumber.ScenarioSession;
import com.ee.cucumber.cucumber.ScenarioWrapper;
import com.ee.cucumber.cucumber.reports.ReportGenerator;
import com.ee.cucumber.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes={ScenarioWrapper.class, DriverFactory.class, ReportGenerator.class, ScenarioSession.class})
public class BaseSteps {

	@Autowired
	protected WebDriver driver;

	@Autowired
	private WebDriverWait wait;

	@Autowired
	ScenarioSession scenarioSession;
}
