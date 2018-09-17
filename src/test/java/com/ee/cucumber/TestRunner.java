package com.ee.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
/**
 * Adding a json output for cucumber reports which can be found in the target folder.
 * Also the more tests that created the more tagging will be necessary for different type of tests like regression, smoke, production
 * as well as identifying failing, pending tests which is all visible in reporting.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features"},
		tags = {"@P1,~@Failing"},
		plugin = {"pretty","html:build/cucumber","json:build/cucumber.json"})
public class TestRunner {}