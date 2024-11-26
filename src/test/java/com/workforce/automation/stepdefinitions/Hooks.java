
package com.workforce.automation.stepdefinitions;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.workforce.automation.base.Base;
import com.workforce.automation.configuration.PropertyFileReader;
import com.workforce.automation.reusablecomponents.Browser;
import com.workforce.automation.utilities.PathHelper;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.FileOutputStream;
import java.util.Date;

public class Hooks {
	WebDriver driver;
	private static Logger log = Logger.getLogger(Hooks.class);

	@Before
	public void setUp(Scenario scenario) throws InterruptedException {
		PropertyConfigurator
				.configure(PathHelper.getResourcePath("/src/main/resources/ConfigurationFile/log4j.properties"));
		log.info("Scenario Started: " + scenario.getName());
		Base.reader = new PropertyFileReader();
		Browser.startBrowser();
		Browser.maximize();
	}

	@After // Use "order" to control the execution order of hooks
	public void afterScenario(Scenario scenario) throws InterruptedException {

		
		    Document pdfDocument = new Document();
			
			try {
			
			String pdfFileName = scenario.getName() + "_report.pdf";
			String pdfPath = "target/Reports_" + pdfFileName;
			PdfWriter.getInstance(pdfDocument, new FileOutputStream(pdfPath));
			pdfDocument.open();

			Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
			Font magentaFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.MAGENTA);
			Font pinkFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.PINK);

			pdfDocument.add(new Paragraph("Scenario Name: " + scenario.getName(), redFont));
			pdfDocument.add(new Paragraph("Scenario Status: " + scenario.getStatus(), magentaFont));
			pdfDocument.add(new Paragraph("Time: " + new Date(), pinkFont));
			pdfDocument.add(new Paragraph("Scenario :" + scenario.isFailed(), redFont));

			if (scenario.getStatus() == scenario.getStatus().FAILED) {
				// Take a screenshot if the scenario is failed
				byte[] screenshot = ((TakesScreenshot) Base.driver).getScreenshotAs(OutputType.BYTES);
				// scenario.attach(screenshot, "image/png", "FailedScreenshot");
				Image image = Image.getInstance(screenshot);
				image.scaleToFit(500, 500);
				// pdfDocument.add(new Paragraph("Scenario Failed Screenshot", redFont));
				pdfDocument.add(image);
			} else if (scenario.getStatus() == scenario.getStatus().PASSED) {

				// Take a screenshot if the scenario is passed
				byte[] screenshot = ((TakesScreenshot) Base.driver).getScreenshotAs(OutputType.BYTES);
				// scenario.attach(screenshot, "image/png", "PassedScreenshot");
				Image image = Image.getInstance(screenshot);
				image.scaleToFit(500, 500);
				// pdfDocument.add(new Paragraph("Scenario Passed Screenshot", magentaFont));
				pdfDocument.add(image);
			}

			pdfDocument.close();

			System.out.println("PDF report generated: " + pdfPath);

			String pdfLink = pdfPath;
			System.out.println("PDF Link: " + pdfLink);


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} if(Base.driver != null ) {
					Base.driver.quit();
				}
			}
	}
	

