package com.workforce.automation.reusablecomponents;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.opera.OperaDriver;

import com.workforce.automation.base.Base;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class Browser {
	public static Logger log = Logger.getLogger(Browser.class);

	public static WebDriver startBrowser() {
		String browser = Base.reader.getBrowser().toLowerCase();
		log.info("Selected Browser is: "+browser);
		switch (browser) {



		case "chrome":
			WebDriverManager.chromedriver().clearDriverCache().setup();
			ChromeOptions options1 = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver", "D:\\Chromedriver\\chromedriver.exe");
			Base.setDriver(new ChromeDriver(options1));
			log.info("Chrome Browser is Started" + Base.getDriver().hashCode());
			return Base.getDriver();

		case "ie":
			WebDriverManager.iedriver().setup();
			Base.setDriver(new InternetExplorerDriver());
			log.info("Internet Explorer Browser is Started" + Base.getDriver().hashCode());
			return Base.getDriver();

//		case "opera":
//			WebDriverManager.operadriver().setup();
//			Base.setDriver(new OperaDriver());
//			log.info("Opera Browser is Started" + Base.getDriver().hashCode());
//			return Base.getDriver();
//
//		case "htmlunit":
//			Base.setDriver(new WebDriver());
//			log.info("HtmlUnit Browser is Started" + Base.getDriver().hashCode());
//			return Base.getDriver();

		default:
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\raghu.jain\\Downloads\\geckodriver-v0.30.0-win64\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();		
			 GeckoDriverService service = new GeckoDriverService.Builder()
					 // Optional: Redirect GeckoDriver logs to a file
			            .withLogOutput(System.out)
			            .build();
			Base.setDriver(new FirefoxDriver(service));
			log.info("Firefox Browser is Started" + Base.getDriver().hashCode());
			return Base.getDriver();
			 
			
		
		case "chromebrowser":
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\mohana.krishna\\Downloads\\chromedriver_win32-8\\chromedriver.exe");
			Base.setDriver(new ChromeDriver());
			log.info("Chrome browser is started" + Base.getDriver().hashCode());
			return Base.getDriver();


			case "browser" :
				WebDriverManager.chromedriver().clearDriverCache().setup();
				ChromeOptions options2 = new ChromeOptions();
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\mohana.krishna\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
				Base.setDriver(new ChromeDriver(options2));
				log.info("Chrome Browser is Started" + Base.getDriver().hashCode());
				return Base.getDriver();
		}

	}

	public static void maximize() {
		Base.getDriver().manage().window().maximize();
	}

	public static byte[] takeScreenshot() {
		try {
			return ((TakesScreenshot)Base.getDriver()).getScreenshotAs(OutputType.BYTES);
		}
		catch(Exception e){
			log.info("Exception has Occured while taking screenshot");
			return null;
		}

	}
}
