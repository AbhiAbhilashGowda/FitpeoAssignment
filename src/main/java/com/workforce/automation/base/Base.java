package com.workforce.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.workforce.automation.configuration.ConfigurationReader;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class Base {
    
    public static Logger log=Logger.getLogger(Base.class);
    
    public static WebDriver driver ;
    public static ConfigurationReader reader ;
    
    public static WebDriver getDriver() {
        return Base.driver;
    }

    public static void setDriver(WebDriver driver) {
        Base.driver = driver;
        // Set implicit wait
        Base.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    
   
    
    
    
    }