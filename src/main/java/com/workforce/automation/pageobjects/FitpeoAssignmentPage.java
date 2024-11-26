package com.workforce.automation.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.workforce.automation.base.Base;

public class FitpeoAssignmentPage {
	
	public FitpeoAssignmentPage()
	{
		PageFactory.initElements(Base.getDriver(),this);
	}

	@FindBy(xpath = "(//div[contains(text(),'Revenue Calculator')])[1]")
	public static WebElement clickonRevenuecalculatorPage;
	
	@FindBy(xpath ="//h4[normalize-space()='Medicare Eligible Patients']")
	public static WebElement scrollRevenueCalculator;
	
	@FindBy(xpath ="//input[@type='number']")
	public static WebElement enterValue;
	
	@FindBy(xpath="//div[@class='MuiBox-root css-rfiegf']//div[1]//label[1]//span[1]//input[1]")
	public static WebElement clickonFirstCPT;
	
	@FindBy(xpath="//div[@class='MuiBox-root css-1p19z09']//div[2]//label[1]//span[1]//input[1]")
	public static WebElement clickonSecondCPT;
	
	@FindBy(xpath="//div[@class='MuiBox-root css-1p19z09']//div[3]//label[1]//span[1]//input[1]")
	public static WebElement clickonThirdCPT;
	
	@FindBy(xpath="//div[@class='MuiBox-root css-1p19z09']//div[8]//label[1]//span[1]//input[1]")
	public static WebElement clickonFourthCPT;
	
	

	
	
	
}

