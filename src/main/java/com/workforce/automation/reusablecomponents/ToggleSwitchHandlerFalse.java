package com.workforce.automation.reusablecomponents;

import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ToggleSwitchHandlerFalse {

	public static final String TOGGLE_STATE_FILE = "src/test/resources/toggle-state-false.properties";
    public static final String TOGGLE_STATE_KEY = "toggleState1";

    public void handleToggle1(WebDriver driver, WebElement toggleElement) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(TOGGLE_STATE_FILE));
            boolean previousState = Boolean.parseBoolean(properties.getProperty(TOGGLE_STATE_KEY, "true")); // Default to true

            // Toggle only if the current state matches the previous state from the file
            if (toggleElement.isSelected() == previousState) {
                toggleElement.click();
            }

            // Always invert the state for the next run
            properties.setProperty(TOGGLE_STATE_KEY, String.valueOf(!previousState));
            properties.store(new FileOutputStream(TOGGLE_STATE_FILE), "");
        } catch (IOException e) {
            throw new RuntimeException("Failed to handle toggle state", e);
        }
    }
}
