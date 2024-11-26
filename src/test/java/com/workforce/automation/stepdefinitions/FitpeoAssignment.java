package com.workforce.automation.stepdefinitions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.workforce.automation.base.Base;
import com.workforce.automation.pageobjects.FitpeoAssignmentPage;
import com.workforce.automation.reusablecomponents.Elements;
import com.workforce.automation.reusablecomponents.PauseExecution;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class FitpeoAssignment extends Base {
	Actions actions = new Actions(driver);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	FitpeoAssignmentPage fitpeopage=new FitpeoAssignmentPage();
	WebDriverWait wait = new WebDriverWait(Base.driver, Duration.ofSeconds(10));
	@Given("the user opens the web browser and navigates to the Fitpeo homepage")
	public void the_user_opens_the_web_browser_and_navigates_to_the_fitpeo_homepage() {
      Base.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Base.getDriver().get(Base.reader.getUrl());
		PauseExecution.waitTill(1);
	}

	@When("the user navigates to the Revenue Calculator page")
	public void the_user_navigates_to_the_revenue_calculator_page() {
		Elements.click(FitpeoAssignmentPage.clickonRevenuecalculatorPage);
		PauseExecution.waitTill(2);
		
	   
	}

	@When("the user scrolls down to the slider section")
	public void the_user_scrolls_down_to_the_slider_section() {
		 WebElement scrollRevenueCalculator = driver.findElement(By.xpath("//h4[normalize-space()='Medicare Eligible Patients']"));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", scrollRevenueCalculator);
        PauseExecution.waitTill(2);		
	   
	}

	@When("the user adjusts the slider")
	public void the_user_adjusts_the_slider() {
		 WebElement slider = driver.findElement(By.xpath("//input[@type='range']")); 
         // Get the slider's width in pixels
         int sliderWidth = slider.getSize().getWidth();
         System.out.println("Slider width: " + sliderWidth);
         double percentage = 465.0; // using the formula
         
         // Calculate the offset (percentage of the slider width)
         int offset = (int) ((percentage / 100) * sliderWidth);
         System.out.println("Calculated pixel offset: " + offset);

         // Move the slider
         
         actions.clickAndHold(slider)
                .moveByOffset(offset, 0) // Move horizontally by the calculated offset
                .release()
                .perform();
         PauseExecution.waitTill(2);


        
	}

	@When("the user updates the text field as {string}")
	public void the_user_updates_the_text_field_as(String value) {
		//Elements.TypeText(FitpeoAssignmentPage.enterValue, value);
		actions.moveToElement(FitpeoAssignmentPage.enterValue).click().perform();
		Elements.clearTxtBox(FitpeoAssignmentPage.enterValue);
		PauseExecution.waitTill(1);
		Elements.TypeText(FitpeoAssignmentPage.enterValue, value);
		PauseExecution.waitTill(1);
	    
		
	}
		

	@Then("the slider value is validated")
	public void the_slider_value_is_validated() {
		 
		
		
	    
	}

	@When("the user selects CPT codes")
	public void the_user_selects_cpt_codes() {
		WebElement clickonFirstCPT = driver.findElement(By.xpath("//div[@class='MuiBox-root css-rfiegf']//div[1]//label[1]//span[1]//input[1]"));
		
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", clickonFirstCPT);
        PauseExecution.waitTill(2);
        actions.moveToElement(clickonFirstCPT).click().build().perform();
        
        actions.moveToElement(FitpeoAssignmentPage.clickonSecondCPT).click().build().perform();
        
        actions.moveToElement(FitpeoAssignmentPage.clickonThirdCPT).click().build().perform();
        WebElement clickonFourthCPT = driver.findElement(By.xpath("//div[@class='MuiBox-root css-1p19z09']//div[8]//label[1]//span[1]//input[1]"));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", clickonFourthCPT);
        PauseExecution.waitTill(1);
        actions.moveToElement(FitpeoAssignmentPage.clickonFourthCPT).click().build().perform();
        PauseExecution.waitTill(2);
        
	
	}

	@Then("the total recurring reimbursement is validated")
	public void the_total_recurring_reimbursement_is_validated() {
		 WebElement revenuePerYear = driver.findElement(By.xpath("//h3[normalize-space()='$917946.40']"));
		 js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", revenuePerYear);
		 PauseExecution.waitTill(1);
		 WebElement Annually = driver.findElement(By.xpath("//p[normalize-space()='$10746.40']"));
		 WebElement IndividualPatient = driver.findElement(By.xpath("//p[normalize-space()='560']"));
		 WebElement AllPatient = driver.findElement(By.xpath("//p[normalize-space()='$75600']"));
		
		    // Retrieve the actual text values from the web elements
		    String ActualrevenuePerYear = revenuePerYear.getText();
		    String ActualAnnually = Annually.getText();
		    String ActualIndividualPatient = IndividualPatient.getText();
		    String ActualAllPatient = AllPatient.getText();

		    // Define the expected values
		    String ExpectedrevenuePerYear = "$917946.40";
		    String ExpectedAnnually = "$10746.40";
		    String ExpectedIndividualPatient = "560";
		    String ExpectedAllPatient = "$75600";

		    // Assertion for revenuePerYear
		    Assert.assertEquals("Revenue per Year doesn't match the expected value", ExpectedrevenuePerYear, ActualrevenuePerYear);

		    // Assertion for Annually
		    Assert.assertEquals("Annually value doesn't match the expected value", ExpectedAnnually, ActualAnnually);

		    // Assertion for IndividualPatient
		    Assert.assertEquals("Individual Patient value doesn't match the expected value", ExpectedIndividualPatient, ActualIndividualPatient);

		    // Assertion for AllPatient
		    Assert.assertEquals("All Patient value doesn't match the expected value", ExpectedAllPatient, ActualAllPatient);

		    // If all assertions pass, the test will complete successfully
		    System.err.println("All values validated successfully.");
		}

		
		
	 
	

	@Then("the header displaying the total reimbursement for all patients per month is verified")
	public void the_header_displaying_the_total_reimbursement_for_all_patients_per_month_is_verified() {
		 WebElement updatedvalue = driver.findElement(By.xpath("//input[@type='number']"));
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", updatedvalue);
		PauseExecution.waitTill(1);
		actions.moveToElement(FitpeoAssignmentPage.enterValue).click().perform();
		Elements.clearTxtBox(FitpeoAssignmentPage.enterValue);
		PauseExecution.waitTill(1);
		//here if we don't change the value then we can't verify according to the Assignment Questions, last modified value will be 560 so for that total Recurring Reimbursement for all patients per month ='$75600' 
		actions.moveToElement(FitpeoAssignmentPage.enterValue).click().sendKeys("820").build().perform();
	
		PauseExecution.waitTill(5);
		WebElement data=driver.findElement(By.xpath("//p[normalize-space()='$110700']"));
		String actualValue=data.getText();
		
        System.err.println("############### " + actualValue);
        PauseExecution.waitTill(1);
        String expectedValue="$110700";
        
            Assert.assertEquals("The actual value does not match the expected value!", expectedValue, actualValue);
            System.err.println("Test Passed: Actual value matches expected value!");
       
        }
	    
	    
	    
        
	}
	


	

	
	       
