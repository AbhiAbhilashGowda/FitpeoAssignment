package com.workforce.automation.reusablecomponents;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class ToggleSwitchHandlerTrue {

	public static final String TOGGLE_STATE_FILE = "src/test/resources/toggle-state-true.properties"; // Adjust path as needed
    public static final String TOGGLE_STATE_KEY = "toggleState";

    public void handleToggle(WebDriver driver, WebElement toggleElement) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(TOGGLE_STATE_FILE));
            boolean previousState = Boolean.parseBoolean(properties.getProperty(TOGGLE_STATE_KEY, "false")); // Default to false

            if (!previousState) {
                toggleElement.click();
            }

            properties.setProperty(TOGGLE_STATE_KEY, String.valueOf(!previousState));
            properties.store(new FileOutputStream(TOGGLE_STATE_FILE), "");
        } catch (IOException e) {
            throw new RuntimeException("Failed to handle toggle state", e);
          }
       }
    }

