package com.workforce.automation.reusablecomponents;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class PauseExecution {

	public static Logger log = Logger.getLogger(PauseExecution.class);
	
	
	// This is for seconds
	
	public static void waitTill(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // Handle the exception if needed
        }
    }
	
	// This is for milliseconds
	
	public static void pauseTill(long milliseconds) {
	    try {
	        TimeUnit.MILLISECONDS.sleep(milliseconds);
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	        // Handle the exception if needed
	    }
	}
}
