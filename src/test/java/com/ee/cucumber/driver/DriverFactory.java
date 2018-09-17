package com.ee.cucumber.driver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class DriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    private static WebDriver webdriver;

    @Value("${webbrowser}")
    private String webbrowser;

    @Value("${baseurl}")
    private String baseUrl;

    @Bean(name="webdriver", destroyMethod="close")
    public WebDriver getDriver() {
        System.out.println("hello: "+webbrowser);
		WebDriver webdriver = null;
		switch (webbrowser) {
			case "firefox":
				webdriver = createFirefoxDriver();
				break;

			case "chrome":
				webdriver = createChromeDriver();
				break;
		}
        if (baseUrl != null) {
            webdriver.get(baseUrl);
        }
		return webdriver;
	}

    /**
     *
     * @return specified Driver.
     * Ability to specify driver capabilites
     */
    private FirefoxDriver createFirefoxDriver() {
        logger.debug("Creating FirefoxDriver");
        FirefoxDriver driver;
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        logger.info("FirefoxDriver created with settings: " + driver.getCapabilities().toString());
        return driver;
    }

    private ChromeDriver createChromeDriver() {
        ChromeDriver driver;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        return driver;
    }

	@Bean("wait")
	public WebDriverWait getWebDriverWait() {
		return new WebDriverWait(getDriver(), 5);
	}
}
