package com.workforce.automation.configuration;


import java.io.IOException;
import java.util.Properties;

import com.workforce.automation.utilities.PathHelper;

public class PropertyFileReader implements ConfigurationReader{
	
	Properties properties=null;
	
	public PropertyFileReader() {
		properties=new Properties();
		try {
			//properties.load(PathHelper.getInputStreamResourcePath("C:\\Users\\user\\Desktop\\selenium\\Chrome driver"));
			properties.load(PathHelper.getInputStreamResourcePath("/src/main/resources/ConfigurationFile/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUrl() {
		return properties.getProperty("url");
	}

	public String getBrowser() {
		return properties.getProperty("browser");
	}

	public int getPageLoadTimeOut() {
		return Integer.parseInt(properties.getProperty("PageLoadTimeOut"));
	}
	
	public String getusername() {
		return properties.getProperty("username");
	}

	public String getpassword() {
		return properties.getProperty("password");
	}

	public String getredmineurl() {
		return properties.getProperty("redmine.url");
	}


}
