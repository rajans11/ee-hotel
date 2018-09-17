package com.ee.cucumber.steps;

import cucumber.api.Scenario;
import cucumber.api.java8.En;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.openqa.selenium.OutputType.BYTES;

public class Hooks implements En {

    @Autowired
    protected WebDriver driver;

    public Hooks(){
        After((Scenario result) -> {
            if (result.isFailed()) {
                try {
                    byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(BYTES);
                    SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss");
                    String timestamp = ft.format(new Date(System.currentTimeMillis()));
                    if (screenshotBytes != null) {
                        result.write(timestamp + " Screenshot taken for URL: " + driver.getCurrentUrl());
                        result.embed(screenshotBytes, "image/png");
                    }

                } catch (WebDriverException exception) {
                    throw new WebDriverException(exception);
                }
            }
        });
    }
}
