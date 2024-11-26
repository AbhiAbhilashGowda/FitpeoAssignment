package com.workforce.automation.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {
		
		"classpath:FeatureFiles/FitpeoAssignment.feature",



},

		glue = { "classpath:com.workforce.automation.stepdefinitions",
				"classpath:com.workforce.automation.runner" }, monochrome = true, publish = true, plugin = { "pretty",
						"html:target/cucumber_html_report", "json:target/cucumber.json" })
		


public class RunnerTest {

}
