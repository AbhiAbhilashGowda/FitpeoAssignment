package com.workforce.automation.reusablecomponents;

import java.awt.datatransfer.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.workforce.automation.base.Base;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Elements {

	// private static final WebDriver driver = null;
	public Logger log = Logger.getLogger(Elements.class);

	private static Set<String> usedCodes = new HashSet<>();
	private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static void TypeText(WebElement element, String data) {
		element.sendKeys(data);
	}

	public static String TypeTextreturn(WebElement element, String data) {
		element.sendKeys(data);
		return data;
	}

	public static void TypeTextIfElementPresent(WebElement element, String data) {
		if (element.isDisplayed()) {
			element.sendKeys(data);
		}
	}

	public static void clearTxtBox(WebElement element) {
		element.clear();
	}

	public static void typeRandomNumber(WebElement element, int data) {
		Random random = new Random();
		int rn = random.nextInt(100);
		String randomNumber = Integer.toString(rn);
		element.sendKeys(randomNumber);
	}

	public static String getTextBoxValue(WebElement element) {
		return element.getAttribute("value");
	}

	public static String getText(WebElement element) {
		return element.getText();
	}

	public static boolean VerifyTextEquals(WebElement element, String expected) {
		boolean flag = false;
		if (element.getText().equals(expected))
			return flag = true;
		return flag;
	}

	public static String getTitle() {
		return Base.getDriver().getTitle();
	}

	public static String getCurrentUrl() {
		return Base.getDriver().getCurrentUrl();
	}

	public static boolean isSelected(WebElement element) {
		if (element.isSelected())
			return true;
		return false;
	}

	public static void selectCheckBox(WebElement element) {
		if (!isSelected(element))
			element.click();
	}

	public static void deSelectCheckbox(WebElement element) {
		if (isSelected(element))
			element.click();
	}

	public static void selectRadioButton(WebElement element) {
		if (!isSelected(element))
			element.click();
	}

	public static void deSelectRadioButton(WebElement element) {
		if (isSelected(element))
			element.click();
	}

	public static boolean isEnabled(WebElement element) {
		if (element.isEnabled())
			return true;
		return false;
	}

	public static boolean isDisplayed(WebElement element) {
		if (element.isDisplayed())
			return true;
		return false;
	}

	public static void selectByText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public static void selectByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public static void selectByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public static String getFirstSelectedOption(WebElement element) {
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public static List<WebElement> getAllSelectedOptions(WebElement element) {
		Select select = new Select(element);
		return select.getAllSelectedOptions();
	}

	public static List<WebElement> getAllOptions(WebElement element) {
		Select select = new Select(element);
		return select.getOptions();
	}

	public static void deSelectByText(WebElement element, String text) {
		Select select = new Select(element);
		select.deselectByVisibleText(text);
	}

	public static void deSelectByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.deselectByIndex(index);
	}

	public static void deSelectByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.deselectByValue(value);
	}

	public static void click(final WebElement element) {
		Waits.waitUntil(new BooleanSupplier() {
			@Override
			public boolean getAsBoolean() {
				return isDisplayed(element);
			}
		});
		element.click();
	}

	public static void doubleClick(WebElement element) {

		Actions actions = new Actions(Base.driver);
		actions.doubleClick(element).build().perform();
	}

	public static void clickOnlyIfElementPresent(WebElement element) {
		if (isDisplayed(element))
			element.click();
	}

	public static Alert getAlert() {
		return Base.getDriver().switchTo().alert();
	}

	public static void AcceptAlert() {
		getAlert().accept();
	}

	public static void DismissAlert() {
		getAlert().dismiss();
	}

	public static String getAlertText() {
		String text = getAlert().getText();
		return text;
	}

	public static boolean isAlertPresent() {
		try {
			Base.getDriver().switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public static void AcceptAlertIfPresent() {
		if (!isAlertPresent())
			return;
		AcceptAlert();
	}

	public static void DismissAlertIfPresent() {

		if (!isAlertPresent())
			return;
		DismissAlert();
	}

	public static void AcceptPrompt(String text) {

		if (!isAlertPresent())
			return;

		Alert alert = getAlert();
		alert.sendKeys(text);
		alert.accept();
	}

	public static void scrollToElemet(WebElement element) {
		Waits.tryJavascript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x,
				element.getLocation().y);
	}

	public static void scrollToEnd(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void scrollDownByPixels(WebDriver driver, int pixels) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, arguments[0]);", pixels);
	}

	public static void scrollToElemetAndClick(WebElement element) {
		scrollToElemet(element);
		element.click();
	}

	public static void scrollIntoView(WebElement element) {
		Waits.tryJavascript("arguments[0].scrollIntoView()", element);
	}

	public static void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
	}

	public static void scrollDownVertically() {
		Waits.tryJavascript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void scrollUpVertically() {
		Waits.tryJavascript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	public static void scrollDownByPixel() {
		Waits.tryJavascript("window.scrollBy(0,1500)");
	}

	public static void scrollUpByPixel() {
		Waits.tryJavascript("window.scrollBy(0,-1500)");
	}

	public static void ZoomInBypercentage() {
		Waits.tryJavascript("document.body.style.zoom='40%'");
	}

	public static void ZoomBy100percentage() {
		Waits.tryJavascript("document.body.style.zoom='100%'");
	}

	public static void ActionsClass(WebElement element) {

		Actions a = new Actions(Base.driver);
		a.moveToElement(element).click().build().perform();
	}

	public static void ActionsEnterText(WebElement element, String data) {
		Actions ob = new Actions(Base.driver);
		ob.moveToElement(element).sendKeys(data).build().perform();
	}

	public static String enterrandomalphabet(WebElement element, int i) {
		String s = RandomStringUtils.randomAlphabetic(i);
		return s;
	}

	public static String enterrandomNumeric(WebElement element, int i) {
		String s = RandomStringUtils.randomNumeric(i);
		return s;
	}

	public static String RandomGenerator(String x, int i) {
		String randomstring = null;
		switch (x) {
			case "Alpha":
				randomstring = RandomStringUtils.randomAlphabetic(i);
				return randomstring;

			case "Numeric":
				randomstring = RandomStringUtils.randomNumeric(i);
				return randomstring;

			case "AlphaNumeric":
				randomstring = RandomStringUtils.randomAlphanumeric(i);
				return randomstring;

			case "NumericString":
				String someText = "Channel - ";
				randomstring = RandomStringUtils.randomNumeric(i);
				return someText + randomstring;

		}
		return randomstring;
	}

	public static String generateUniqueAlphanumericCode() {

		Random random = new Random();
		String code;

		do {
			StringBuilder codeBuilder = new StringBuilder(3);
			for (int i = 0; i < 3; i++) {
				int index = random.nextInt(ALPHANUMERIC.length());
				codeBuilder.append(ALPHANUMERIC.charAt(index)); // Generate a random character from ALPHANUMERIC
			}
			code = codeBuilder.toString();
		} while (usedCodes.contains(code)); // Ensure the code hasn't been used before

		usedCodes.add(code); // Store the used code to avoid repetition
		return code;
	}

	public static void clearWebField(WebElement element) {
		while (!element.getAttribute("value").equals("")) {
			element.sendKeys(Keys.BACK_SPACE);
		}
	}

	public static void KeyboardEnter(WebElement element1) {
		Actions a = new Actions(Base.driver);
		a.sendKeys(Keys.ENTER).build().perform();
	}

	public static void selectAll(WebElement element) {
		Actions action = new Actions(Base.driver);
		action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
	}

	public static void copyAll(WebElement element) {
		Actions action = new Actions(Base.driver);
		action.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).build().perform();
	}

	public static void pasteAll(WebElement element) {
		Actions action = new Actions(Base.driver);
		action.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
	}

	public static void backSpace(WebElement element) {
		Actions action = new Actions(Base.driver);
		action.keyDown(Keys.CONTROL).sendKeys("delete").keyUp(Keys.CONTROL).build().perform();
	}

	public static void pressTab(WebElement element) {
		Actions action = new Actions(Base.driver);
		action.sendKeys(element, Keys.TAB).build().perform();
	}

	public static void pressTabAndClick(WebElement elementToTab, WebElement elementToClick) {
		// Create an Actions object
		Actions action = new Actions(Base.driver);

		// Perform the TAB key press on the given element
		action.sendKeys(elementToTab, Keys.TAB).build().perform();

		// Perform a click on the next desired element after pressing TAB
		action.moveToElement(elementToClick).click().build().perform();
	}

	public static void switchTab(String ID) throws Throwable {

		((JavascriptExecutor) Base.driver).executeScript("window.open()");
		Set<String> windows = Base.driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		String ID1 = itr.next();
		String ID2 = itr.next();
		PauseExecution.waitTill(1);
		if (ID == "ID1") {
			Base.driver.switchTo().window(ID2);
			// Base.driver.get("https://mail7.io/");
			// Base.driver.get("https://mail.yahoo.com/d/folders/1");
			PauseExecution.waitTill(1);
		} else if (ID == "ID2") {
			Base.driver.switchTo().window(ID1);
		}

	}

	public static void switchToFrame() {
		WebElement iframe = Base.driver.findElement(By.xpath("//div[@class='message']//iframe"));
		Base.driver.switchTo().frame(iframe);

	}

	public static void selectDateByJavaScriptExecutor(WebDriver driver, WebElement element, String dateValue) {

		JavascriptExecutor jse = (JavascriptExecutor) Base.driver;
		jse.executeScript("arguments[0].setAttribute('value','" + dateValue + "');", element);
	}

	public static boolean clickIfElementPresent(WebElement element) {
		if (isDisplayed(element)) {
			element.click();
			return true;
		} else {
			return false;
		}
	}

	public void sleepTillElementIsTheir() throws InterruptedException {
		Thread.sleep(100);
	}

	public static void waitTillElementAvailable() {
		Base.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static void avoidNullPointerException(WebElement element) {

		if (element != null) {
			// Perform actions on the element
			element.click();
		} else {
			System.out.println("Element not found!");
		}

	}

	// Window Handle

	public static void handleDemo() {

		// It will return the parent window name as a String
		String parent = Base.driver.getWindowHandle();

		Set<String> s = Base.driver.getWindowHandles();

		// Now iterate using Iterator
		Iterator<String> I1 = s.iterator();
		{

			while (I1.hasNext()) {

				String child_window = I1.next();

				if (!parent.equals(child_window)) {
					Base.driver.switchTo().window(child_window);

					System.out.println(Base.driver.switchTo().window(child_window).getTitle());

				}
			}
		}
	}
	// Notification Sound

	public static void playNotificationSound() throws InterruptedException {

		try {
			// Load the sound file (replace with the path to your sound file)
			File soundFile = new File("src/main/resources/File to Upload/mixkit-classic-alarm-995.txt");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

			// Create a clip to play the sound
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);

			// Play the sound
			clip.start();

			// Sleep to allow the sound to play (adjust as needed)
			PauseExecution.waitTill(1); // 2 seconds

			// Close the clip
			clip.close();

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {

			e.printStackTrace();
		}
	}

	// Read the current DIN value from the file

	public static String readCurrentDIN() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("din.txt"));
			String currentDIN = reader.readLine();
			reader.close();
			return currentDIN;
		} catch (IOException e) {
			e.printStackTrace();
			return "DIN_048";
		}
	}

	// Extract the numeric part, increment it, and return the next value
	public static int getNextDINValue(String currentDIN) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(currentDIN);
		if (matcher.find()) {
			int currentValue = Integer.parseInt(matcher.group());
			return currentValue + 1;
		}
		return 49; // Default if no numeric part found
	}

	// Save the updated DIN value back to the file
	public static void saveCurrentDIN(int nextValue) {
		String nextDIN = "DIN_" + String.format("%03d", nextValue);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("din.txt"));
			writer.write(nextDIN);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Generate File Path

	public static List<String> generateFilePaths() {

		List<String> filePaths = new ArrayList<>();
		filePaths.add(
				"D:\\Table Management\\workspace\\WorkforceAutomation2.0\\src\\test\\resources\\Files to Upload\\1.pdf");
		filePaths.add(
				"D:\\Table Management\\workspace\\WorkforceAutomation2.0\\src\\test\\resources\\Files to Upload\\2.pdf");
		filePaths.add(
				"D:\\Table Management\\workspace\\WorkforceAutomation2.0\\src\\test\\resources\\Files to Upload\\3.pdf");
		filePaths.add(
				"D:\\Table Management\\workspace\\WorkforceAutomation2.0\\src\\test\\resources\\Files to Upload\\4.pdf");
		filePaths.add(
				"D:\\Table Management\\workspace\\WorkforceAutomation2.0\\src\\test\\resources\\Files to Upload\\5.pdf");
		return filePaths;

	}
	// Drag and drop

	public static void dragandDrop(WebDriver driver, WebElement source, WebElement target) {
		Actions actions = new Actions(Base.driver);
		// Initiate drag
		actions.clickAndHold(source);

		// Move to the target
		actions.moveToElement(target);

		// Release to drop
		actions.release().build().perform();
	}

	// Toggle switch based on condition

	public static void toggleSwitch(WebElement switchElement) {
		// Check the current state of the toggle switch
		boolean isToggled = switchElement.isSelected();

		// Toggle the switch
		switchElement.click();

		if (isToggled) {
			System.out.println("Toggled off.");
		} else {
			System.out.println("Toggled on.");
		}
	}

	// Generate Random URL

	public static String generateRandomURL() {
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder randomURL = new StringBuilder();

		Random rand = new Random();
		int length = 10; // Adjust the length of your URL as needed

		for (int i = 0; i < length; i++) {
			randomURL.append(characters.charAt(rand.nextInt(characters.length())));
		}

		return "http://example.com/" + randomURL.toString();
	}

	// Generate new EmployeeID

	public static String generateEmployeeID() {
		return UUID.randomUUID().toString();
	}

	// Copy Contents to clip board

	public static String getClipboardContents() {
		try {
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			return (String) clipboard.getData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	// Select Check boxes

	public static void selectCheckboxesForAssessmentQuestion(WebDriver driver, int numberOfCheckboxesToSelect,
			int startIndex) {
		// Find all check boxes on the web page
		List<WebElement> checkboxes = Base.driver
				.findElements(By.xpath("//div[@class='custom-library-questions']//input[@type='checkbox']"));

		// Validate startIndex
		if (startIndex < 0 || startIndex >= checkboxes.size()) {
			throw new IllegalArgumentException("Invalid startIndex");
		}

		// Select check boxes starting from the specified index
		for (int i = startIndex; i < Math.min(startIndex + numberOfCheckboxesToSelect, checkboxes.size()); i++) {
			// Get the check box at the current index
			WebElement checkbox = checkboxes.get(i);

			// Check the check box if it's not already selected
			if (!checkbox.isSelected()) {
				checkbox.click();
			}
		}
		WebElement selectQuestions = Base.driver.findElement(By.xpath("//button[contains(.,'selected  questions')]"));
		selectQuestions.click();
		PauseExecution.waitTill(1);
	}
	// Select Status

	public static void clickRandomStatus(List<WebElement> statusOptions) {
		// Generate a random index to select a status
		int randomIndex = new Random().nextInt(statusOptions.size());

		// Click on the randomly chosen status option
		statusOptions.get(randomIndex).click();
	}

	// Upload resumes

	public static List<String> getResumePaths() {
		List<String> resumePaths = new ArrayList<>();

		// Specify the directory containing the resumes
		String resumeDirectory = "D:\\Table Management\\workspace\\WorkforceAutomation2.0\\src\\test\\resources\\Files to Upload";

		File directory = new File(resumeDirectory);
		File[] files = directory.listFiles();

		if (files != null) {
			for (File file : files) {
				if (file.isFile() && file.getName().endsWith("m.pdf")) {
					resumePaths.add(file.getPath());
				}
			}
		}

		return resumePaths;
	}

	// Select Checkboxes

	public static void selectCheckboxes(WebDriver driver, int numberOfCheckboxesToSelect, int startIndex) {
		// Find all check boxes on the web page
		List<WebElement> checkboxes = Base.driver.findElements(By.xpath("//input[@class='ant-checkbox-input']"));

		// Validate startIndex
		if (startIndex < 0 || startIndex >= checkboxes.size()) {
			throw new IllegalArgumentException("Invalid startIndex");
		}

		// Select check boxes starting from the specified index
		for (int i = startIndex; i < Math.min(startIndex + numberOfCheckboxesToSelect, checkboxes.size()); i++) {
			// Get the check box at the current index
			WebElement checkbox = checkboxes.get(i);

			// Check the check box if it's not already selected
			if (!checkbox.isSelected()) {
				checkbox.click();
			}
		}
	}

	public static String getClipboardContents1() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);

		if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			try {
				return (String) contents.getTransferData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException | IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void openNewTabAndPasteLink(WebDriver driver, String link) {
		// Open a new tab using JavaScript
		((JavascriptExecutor) Base.driver).executeScript("window.open();");

		// Switch to the new tab
		for (String windowHandle : Base.driver.getWindowHandles()) {
			Base.driver.switchTo().window(windowHandle);
		}
		Base.driver.get("about:blank");

		// // Focus on an input field (e.g., a search bar) using JavaScript
		// ((JavascriptExecutor) Base.driver).executeScript("document.body.innerHTML =
		// '<input id=\"searchInput\">';");
		// WebElement inputField = Base.driver.findElement(By.id("searchInput"));
		// inputField.sendKeys(link);
		//
		// // Simulate the Enter key press to submit the search (or paste) action
		// Actions actions = new Actions(Base.driver);
		// actions.sendKeys(Keys.ENTER).perform();
		// Navigate to a website where user input is accepted (e.g., Google)
		((JavascriptExecutor) Base.driver).executeScript("window.location.href = arguments[0];", link);

	}

	public static boolean isMandatoryFieldPresent() {
		// Find all input fields on the page
		List<WebElement> inputFields = Base.driver.findElements(By.tagName("input"));

		// Iterate through each input field
		for (WebElement inputField : inputFields) {
			// Check if the field is mandatory (contains the 'required' attribute)
			if (inputField.getAttribute("required") != null || inputField.getAttribute("aria-invalid") != null) {
				return true; // Found at least one mandatory field
			}
		}

		return false; // No mandatory field found
	}

	public static void enterDataInFirstEmptyDynamicField(String data) {
		// Find all input fields on the page
		List<WebElement> inputFields = Base.driver.findElements(By.tagName("input"));

		// Iterate through each input field
		for (WebElement inputField : inputFields) {
			// Check if the field is visible, enabled, and empty
			if (inputField.isDisplayed() && inputField.isEnabled() && inputField.getAttribute("value").isEmpty()) {
				// Enter data in the first empty field found
				inputField.sendKeys(data);
				System.out.println("Entered data in a dynamic/custom mandatory field.");
				return; // Exit the loop after the first empty field is found and data is entered
			}
		}

		// If no empty field is found
		System.out.println("No empty dynamic/custom mandatory field found.");
	}

	// Select a toggle random switch

	public static void toggleRandomSwitch(List<WebElement> toggleSwitches) {
		// Select a random toggle switch
		int randomIndex = new Random().nextInt(toggleSwitches.size());
		WebElement switchElement = toggleSwitches.get(randomIndex);

		// Check the current state of the toggle switch
		boolean isToggled = switchElement.isSelected();

		// Toggle the switch
		switchElement.click();

		if (isToggled) {
			System.out.println("Toggled off.");
		} else {
			System.out.println("Toggled on.");
		}
	}

	public static boolean isToggled = false;

	public static void toggleSwitch(List<WebElement> toggleSwitches) {

		int randomIndex = new Random().nextInt(toggleSwitches.size());
		WebElement switchElement = toggleSwitches.get(randomIndex);

		// Toggle the switch
		switchElement.click();

		// Update the state
		isToggled = !isToggled;

		if (isToggled) {
			System.out.println("Toggled on.");
		} else {
			System.out.println("Toggled off.");
		}
	}

	// Select Random radio button

	public static void selectRandomRadioButton(List<WebElement> radioButtons) {
		// Select a random radio button
		int randomIndex = new Random().nextInt(radioButtons.size());
		radioButtons.get(randomIndex).click();
	}

	public static void selectRandomColor(List<WebElement> randomColor) {
		// Select a random color
		int pickRandomColor = new Random().nextInt(randomColor.size());
		randomColor.get(pickRandomColor).click();
	}

	// Select Random checkbox
	public static void selectRandomCheckbox(List<WebElement> checkBox) {
		// Select a random checkbox
		int randomCheckbox = new Random().nextInt(checkBox.size());
		checkBox.get(randomCheckbox).click();
	}

	// Select Random Dropdown Values

	public static void selectRandomDropdownValues(List<WebElement> dropdownValues) {
		// Select a random dropdown Values
		int randomIndex = new Random().nextInt(dropdownValues.size());
		dropdownValues.get(randomIndex).click();
	}

	// Select Random Values

	public static void selectRandomValues(List<WebElement> randomValues) {
		int randomIndex = new Random().nextInt(randomValues.size());
		randomValues.get(randomIndex).click();
	}

	// Select Random Dropdown values

	public static void selectRandomDropdownValue(WebDriver driver, By dropdownLocator) {
		WebElement dropdown = Base.driver.findElement(dropdownLocator);
		dropdown.click();

		List<WebElement> options = dropdown.findElements(By.tagName("option"));
		int randomIndex = new Random().nextInt(options.size());

		options.get(randomIndex).click();
	}

	// Leave Type

	public static void validateAccrualDetails(WebDriver driver, boolean expectedValue) {

		// Validate "Accrual", "Accrual Prorate", "Reset" is true/false

		WebElement accrualElement = Base.driver
				.findElement(By.xpath("(//button[@class='ant-switch ant-switch-checked'])[2]"));
		WebElement accrualProrateElement = Base.driver
				.findElement(By.xpath("(//button[@class='ant-switch ant-switch-checked'])[3]"));
		WebElement resetElement = Base.driver
				.findElement(By.xpath("(//button[@class='ant-switch ant-switch-checked'])[4]"));

		// conditions for true values

		boolean isAccrualTrue = Boolean.parseBoolean(accrualElement.getAttribute("ant-switch ant-switch-checked"));
		boolean isAccrualProrateTrue = Boolean
				.parseBoolean(accrualProrateElement.getAttribute("ant-switch ant-switch-checked"));
		boolean isResetTrue = Boolean.parseBoolean(resetElement.getAttribute("ant-switch ant-switch-checked"));

		if (isAccrualTrue == expectedValue && isAccrualProrateTrue == expectedValue && isResetTrue == expectedValue) {

			System.out.println("Validation Passed!");

		} else {

			System.out.println("Validation Failed!");
		}
	}

	// Select Random Values from Drop-down

	public static void dropdwonSelect(List<WebElement> element) {

		List<WebElement> accrualinEntitlement = Base.driver.findElements(By.xpath("//ul[@role='listbox']//li"));
		Random random = new Random();
		int randomIndex = random.nextInt(accrualinEntitlement.size());
		accrualinEntitlement.get(randomIndex).click();
	}

	// Select Campus - Job Type from Drop-down

	public static void selectCampusJobType(List<WebElement> element) {

		List<WebElement> campusJobType = Base.driver.findElements(By.xpath("//ul[@role='listbox']//li"));
		Random random = new Random();
		int randomIndex = random.nextInt(campusJobType.size());
		campusJobType.get(randomIndex).click();
	}

	// Extract particular number from the URL

	public static String extractUserId(String url) {
		String[] parts = url.split("/");
		return parts[parts.length - 1];
	}

	// Get the Dynamic IDs

	public static String getDynamicTicketId() {

		return "3426";
	}

	public static void copyToClipboard(String text) {

		StringSelection stringSelection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}
	// Toggle switch

	public static String generateRandomString(int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}

	public static String getRandomValidMessage() {
		// Array of valid messages
		String[] validMessages = {
				"Hello, how are you?",
				"Meeting at 3 PM in the conference room.",
				"Don't forget to submit your reports by EOD.",
				"Happy birthday! 🎉",
				"Let's catch up for lunch tomorrow.",
				"The project deadline has been extended.",
				"Thank you for your hard work and dedication.",
				"Please review the attached document.",
				"Wishing you a great weekend!",
				"Reminder: Team-building event on Friday.",
				"Please Fill up the time sheet for this week",
				"Deploy the changes to production ETA : 5 minitues",
				"Your leave has been rejected due to workforce event",
				"Important: Emergency meeting at 2 PM today.",
				"New policy updates - please read and acknowledge.",
				"Congratulations on your work anniversary!",
				"Project status update: Milestone achieved!",
				"Training session on new tools tomorrow.",
				"Don't forget to update your contact information.",
				"Welcome to the team! 🎊",
				"Reminder: Quarterly review meeting next week.",
				"hey",
				"hello",
				"hi",
				"hello there",
				"moin",
				"hey there",
				"let's go",
				"hey dude",
				"Greetings",
				"Howdy",
				"Hy",
				"hlw",
				"hey stella",
				"hy stella",
				"hi stella",
				"hellow stella",
				"hello stella",
				"hellow",
				"greetings stella",
				"cu",
				"good by",
				"cee you later",
				"bye",
				"goodbye",
				"have a nice day",
				"see you around",
				"bye bye",
				"see you later",
				"tata",
				"take care",
				"see you soon",
				"see you again",
				"you are not responding to my answers properly",
				"you are not responding correctly",
				"I am not happy with your answers",
				"You are not meeting my expectations.",
				"Give me correct answer.",
				"Not satisfied with your answer",
				"Not giving answers perfectly",
				"not good",
				"Please provide correct answers",
				"I am not happy with you",
				"My question is not what you are understanding.",
				"This is not what I asked.",
				"This is not that I asked.",
				"This is not that I am looking for.",
				"I am happy",
				"I feel great",
				"I'm fine",
				"I had a great day",
				"Me, wonderful!",
				"I am amazed",
				"You are too cool",
				"I am loving it",
				"I am starting to like this",
				"I am feeling great",
				"Doing fine",
				"Never better",
				"Feeling lucky",
				"All great here",
				"Fantastic day",
				"What a great day",
				"I'm inspired",
				"It's perfect",
				"amazing",
				"I feel wonderful",
				"I am feeling very good",
				"I am great",
				"I'm good",
				"I'm feeling awesome",
				"I'm feeling happy",
				"Today is an awesome day!",
				"I'm so glad",
				"I'm just really happy right now",
		};

		Random random = new Random();

		// Select a random message from the array
		int randomIndex = random.nextInt(validMessages.length);
		return validMessages[randomIndex];
	}

	public static String getRandomAIMessage() {

		String[] chatBoxMessage = {

				"My available leave balance",
				"My available leave",
				"Show my available leave.",
				"What is my available leave balance?",
				"How many leave days do I have left?",
				"Check my remaining leave days",
				"What is my remaining leave balance?",
				"Check my available leave days",
				"How many remaining leave days do I have left?",
				"How many available leave days do I have left?",
				"How many leave days do I have left?",
				"Check my remaining leave days",
				"Check number of leave still I have",
				"Check number of leaves in my hand",
				"Give number of leave still I have",
				"Give number of leave in my hand",
				"Show number of leave still I have",
				"Show number of leave in my hand",
				"How many leave days do I have left?",
				"Check my remaining leave days",
				"What is my leave balance?",
				"How many leave days do I have left?",
				"Check my remaining leave days",
				"Give my remaining leave balance",
				"Give my available leave balance",
				"Give my available leaves",
				"Give my remaining leaves",
				"Give how many remaining leaves I have",
				"Give how many available leaves I have",
				"Give how many remaining leaves I have left",
				"Give how many available leaves I have left",
				"I want to check my remaining leave balance",
				"I want to check my available leave balance",
				"I want leave balance left",
				"I want to check available leave balance I have",
				"I want to check remaining leave balance I have",
				"Show my remaining leave balance",
				"Show my available leave balance",
				"Show leave balance left",
				"Show available leave balance I have",
				"Show remaining leave balance I have",
				"Tell my remaining leave balance",
				"Tell my available leave balance",
				"Tell leave balance left",
				"tell available leave balance I have",
				"tell remaining leave balance I have",
				"print my remaining leave balance",
				"Print my available leave balance",
				"print leave balance left",
				"Print available leave balance I have",
				"print remaining leave balance I have",
				"Total available leaves",
				"Total available leave balance",
				"Total remaining leaves",
				"Total remaining leave balance",
				"Where is the headquarter of e2eworkforce?",
				"Where is the main office of e2eworkforce?",
				"Where is the main office of e2eworkforce and e2ehiring?",
				"Where is e2eworkforce headquartered?",
				"Where is the headquarter of e2ehiring?",
				"Where is the main office of e2ehiring?",
				"Where is e2ehiring headquartered?",
				"Headquarter",
				"Headkuater",
				"Headcuater",
				"Headquarter of e2ehiring",
				"Headkuater of e2ehiring",
				"Headcuater of e2ehiring",
				"Headquarter of e2eworkforce",
				"Headkuater of e2eworkforce",
				"Headcuater of e2eworkforce",
				"main office",
				"main headquarter",
				"main headquarter of e2eworkforce",
				"main office of e2ehiring",
				"In which city e2ehiring is located?",
				"In which state e2ehiring is located?",
				"In which city e2eworkforce is located?",
				"In which state e2eworkforce is located?",
		};

		Random random = new Random();

		// Select a random message from the array
		int randomIndex = random.nextInt(chatBoxMessage.length);
		return chatBoxMessage[randomIndex];

	}

	public static void setShiftTimings(int hour, int minute, boolean isAM) {
		JavascriptExecutor js = (JavascriptExecutor) Base.driver;
		String script = "arguments[0].value = '" + hour + "'; arguments[1].value = '" + minute
				+ "'; arguments[2].value = '" + (isAM ? "AM" : "PM") + "' ;";
		WebElement hoursInput = Base.driver
				.findElement(By.xpath("(//ul[@class='ant-picker-time-panel-column'])[1]//li[5]"));
		WebElement mintuesInput = Base.driver
				.findElement(By.xpath("(//ul[@class='ant-picker-time-panel-column'])[2]//li[30]"));
		WebElement ampmInput = Base.driver
				.findElement(By.xpath("(//ul[@class='ant-picker-time-panel-column'])[3]//li[1]"));
		js.executeScript(script, hoursInput, mintuesInput, ampmInput);

	}

	public static void sendKeysAndPressControlEnter(WebElement element, String keysToSend) {

		// TODO Auto-generated method stub
		Actions actions = new Actions(Base.driver);
		actions.moveToElement(element).sendKeys(keysToSend);

		// Press and hold Control (or Ctrl) key
		actions.keyDown(Keys.CONTROL);

		// Press Enter key
		actions.sendKeys(Keys.ENTER);

		// Release Control key
		actions.keyUp(Keys.CONTROL);

		// Perform the action on the given element
		actions.moveToElement(element).perform();
	}

	public static void sendCodeLinesAndPressCtrlEnter(List<String> codeLines, WebElement enterCodeElement) {
		for (String line : codeLines) {
			Elements.sendKeysAndPressControlEnter(enterCodeElement, line);
		}
	}

	public static void randomizeElementsAndClick(WebDriver driver, By locator) {

		List<WebElement> randomElements = Base.driver.findElements(locator);
		int numberOfCheckboxes = randomElements.size();
		Random random = new Random();
		int randomIndex = random.nextInt(numberOfCheckboxes);
		randomElements.get(randomIndex).click();
	}

	// Method to generate a random description
	public static String generateRandomDescription() {
		String[] descriptions = {
				"Used for creating, running, and analyzing microbenchmarks written in Java.",
				"A highly capable load testing tool for web applications, providing a domain-specific language for writing performance tests.",
				"A widely-used open-source tool for load testing and performance measurement of web applications.",
				"An open-source load testing tool written in Python, allowing you to define user behavior using Python code.",
				"A distributed load testing tool written in Erlang, suitable for stress testing of web servers, databases, and messaging systems.",
				"A modern, powerful load testing toolkit for Node.js applications, supporting both HTTP and WebSocket protocols.",
				"A powerful .NET library for benchmarking, allowing you to write benchmarks in C# and analyze performance metrics.",
				"An open-source tool for measuring and analyzing the performance of web pages, providing insights into load times and rendering performance.",
				"A visualization tool for profiling software performance, allowing you to generate flame graphs from profiling data.",
				"A monitoring and alerting toolkit designed for reliability and scalability, often used for gathering performance metrics from applications.",
				"A popular open-source analytics and monitoring platform that integrates with various data sources, including Prometheus, for visualizing performance metrics.",
				"A cloud-based observability platform that provides application performance monitoring (APM) and infrastructure monitoring capabilities.",
				"A SaaS-based monitoring and analytics platform that offers APM, infrastructure monitoring, and log management features.",
				"An application performance monitoring solution offering real-time visibility into the performance of applications and their underlying infrastructure.",
				"A versatile platform for collecting, searching, and analyzing machine-generated data, including performance metrics and logs.",
				"An open-source stack for centralized logging and monitoring, often used for analyzing performance-related logs and metrics.",
				"A distributed tracing system that helps troubleshoot latency issues in microservices architectures by providing insights into request flows.",
				"Another distributed tracing system, often used in conjunction with Zipkin or independently, to monitor and profile the performance of distributed systems.",
				"A performance instrumentation tool and tracing backend developed by Google, primarily used for analyzing performance on Android devices.",
				"A monitoring and security platform that provides container visibility, allowing you to monitor the performance of containerized applications.",
				"A set of APIs, libraries, agents, and instrumentation to collect traces and metrics from applications, supporting multiple programming languages and platforms.",
				"A lightweight load testing tool written in Python, designed for testing WebSocket-based applications.",
				"A versatile HTTP load testing tool written in Go, supporting rate limiting, distributed testing, and custom report generation.",
				"A load testing tool from SmartBear, offering a visual interface for creating and running performance tests against web services and APIs.",
				"An open-source load testing tool written in Go, providing a scripting API for defining complex test scenarios.",
				"An automation-friendly framework for continuous testing, supporting various testing tools (e.g., JMeter, Gatling) and providing integration with CI/CD pipelines.",
				"A performance testing tool from Micro Focus, offering a comprehensive suite of features for load testing, stress testing, and performance monitoring.",
				"A full-stack monitoring platform that combines application performance monitoring, infrastructure monitoring, and digital experience monitoring capabilities.",
				"A monitoring and error tracking platform that helps identify and diagnose performance issues in web and mobile applications.",
				"An application performance monitoring solution with automatic discovery of application components and dependencies for real-time performance insights.",
				"An open-source monitoring and graphing tool for storing and visualizing time-series data, often used for monitoring system performance metrics.",
				"A time-series database designed for storing and querying time-stamped data, commonly used in conjunction with Grafana for visualizing performance metrics.",
				"A simple network daemon for collecting and aggregating custom metrics, often used as a data source for monitoring systems like Graphite and InfluxDB.",
				"An application performance management solution for large-scale distributed systems, offering distributed tracing, real-time monitoring, and root cause analysis.",
				"A distributed monitoring system that aggregates events from various sources and provides real-time analytics and alerting capabilities.",
				"An open-source project for standardizing distributed tracing instrumentation and APIs across different programming languages and platforms.",
				"A popular open-source implementation of the OpenTracing standard, offering distributed tracing capabilities for microservices architectures.",
				"A distributed tracing service from AWS, providing insights into request flows and performance bottlenecks in cloud-based applications.",
				"A distributed tracing and monitoring platform designed for serverless architectures, offering insights into performance and cost optimization.",
				"A performance profiling and testing tool for PHP applications, providing insights into code execution times and performance bottlenecks.",
				"A Java profiler for CPU, memory, and thread profiling, offering deep insights into Java application performance.",
				"A visual tool for monitoring and troubleshooting Java applications, providing real-time performance data and profiling capabilities.",
				"A memory profiler for Java applications, offering insights into memory usage and identifying memory leaks and inefficiencies.",
				"A historical debugger and performance profiler for .NET applications, providing insights into code execution and performance bottlenecks.",
				"An application monitoring solution for .NET applications, offering automatic discovery of dependencies and performance insights.",
				"Microsoft's cloud-based application lifecycle management platform, offering performance testing and monitoring capabilities.",
				"A performance management tool for PHP applications, offering profiling and performance testing features.",
				"A performance and memory profiler for .NET applications, providing insights into code execution and memory allocation.",
				"An APM (Application Performance Management) tool designed for microservices architectures, offering distributed tracing and performance monitoring.",
				"A monitoring and analytics service from Microsoft Azure, providing performance monitoring and application diagnostics for cloud-based applications.",
		};

		// Generate a random index to select a description from the array
		Random random = new Random();
		int randomIndex = random.nextInt(descriptions.length);

		return descriptions[randomIndex];
	}

	public static String todoComments() {

		String[] todoComments = {

				"Great job, let's tackle this task next!",
				"Looking forward to seeing this crossed off the list!",
				"You've got this one in the bag!",
				"One step closer to success!",
				"This task is on our radar!",
				"Let's make progress on this together!",
				"Keep up the awesome work!",
				"I'm excited to see what we accomplish!",
				"Let's knock this one out!",
				"Your efforts on this are appreciated!"

		};

		Random random = new Random();

		// Select a random message from the array
		int randomIndex = random.nextInt(todoComments.length);
		return todoComments[randomIndex];
	}

	// Method to generate a random folder name
	public static String generateRandomFolderName() {
		String[] words = { "Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Zeta", "Eta", "Theta", "Iota", "Kappa" };
		Random random = new Random();
		int index1 = random.nextInt(words.length);
		int index2 = random.nextInt(words.length);
		int number = random.nextInt(1000); // Add a random number for uniqueness
		return words[index1] + words[index2] + number;

	}

	public static void scrollDownPixelByPixel(WebDriver driver, int scrollStep, int totalPixels) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i < totalPixels; i += scrollStep) {
			js.executeScript("window.scrollBy(0," + scrollStep + ");");
			try {
				Thread.sleep(100); // Sleep to simulate natural scrolling and allow content to load
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Helpdesk Response and Resolution Escalation Minutes

	public static int getRandomMinutes() {
		Random random = new Random();
		return random.nextInt(59) + 1; // Generate a random number between 1 and 59
	}

	public static void typeTextAndPressEnter(WebElement element, String data) {
		element.sendKeys(data); // Type the text
		new Actions(Base.driver) // Create a new Actions object
				.sendKeys(Keys.ENTER) // Simulate pressing Enter
				.perform(); // Execute the action
	}

	// Method to generate random subject with more than 7 random words
	public static String generateRandomSubject() {
		Random random = new Random();
		StringBuilder subjectBuilder = new StringBuilder("Redmine - "); // Start with "Redmine - "

		int wordCount = random.nextInt(4) + 8; // Ensure at least 8 words (random between 8 and 11)

		// Generate each word and append to the subject
		for (int i = 0; i < wordCount; i++) {
			subjectBuilder.append(generateRandomWord()).append(" ");
		}

		return subjectBuilder.toString().trim(); // Return the generated subject
	}

	// Helper method to generate a random word of length between 3 and 8 characters
	public static String generateRandomWord() {
		Random random = new Random();
		int wordLength = random.nextInt(6) + 3; // Random word length between 3 and 8
		StringBuilder wordBuilder = new StringBuilder();

		// Generate a word with random letters
		for (int i = 0; i < wordLength; i++) {
			char randomChar = (char) ('a' + random.nextInt(26)); // Generate a random lowercase letter
			wordBuilder.append(randomChar);
		}

		return wordBuilder.toString(); // Return the generated word
	}

}
