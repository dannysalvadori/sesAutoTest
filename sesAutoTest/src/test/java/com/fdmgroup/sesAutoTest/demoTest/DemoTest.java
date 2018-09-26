package com.fdmgroup.sesAutoTest.demoTest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fdmgroup.sesAutoTest.pageObjectModel.NavigationPanel;
import com.fdmgroup.sesAutoTest.pageObjectModel.RegistrationPage;
import com.fdmgroup.sesAutoTest.testData.UserData;
import com.fdmgroup.sesAutoTest.utils.DriverUtilities;

public class DemoTest {
        
        private static final String SCREENSHOT_DIRECTORY
                = "C:\\Users\\Danny.Salvadori\\workspace-sts-3.8.4.RELEASE\\sesAutoTest\\screenshots\\";
        
        /******************
         * Handy dandy stuff to know
         * 
         * driver.close()                        Closes the active window
         * driver.quit()                 Closes all controlled windows and kills session
         * driver.navigate()             Gets navigation object...
         * navigate().refresh()          Refresh the page
         * navigate().back()             Navigate back a page
         * navigate().forward()          Navigate Forward (assuming you've gone "back")
         * 
         * driver.getCurrentUrl()        
         * driver.manage()                       Gets Options object for many things...
         * driver.manage().window()...   Various window modifying methods
         * 
         */
        
        private WebDriver driver;
        
        @Before
        public void setup() {
			DriverUtilities driverUtils = DriverUtilities.getInstance();
			driver = driverUtils.getDriver();
        }
        
        @AfterClass
        public static void cleanup() {
			DriverUtilities driverUtils = DriverUtilities.getInstance();
			WebDriver driver = driverUtils.getDriver();
			driver.quit(); // Closes all controlled windows and driver connection 
        }

        @Test
        public void goToGoogleTest() {
        	final String GOOGLE_URL = "https://www.google.co.uk/search?q=FDM+Group"; 
        	driver.get(GOOGLE_URL);
        	String currentUrl = driver.getCurrentUrl();
        	assertTrue("Google URL was wrong", currentUrl.contains(GOOGLE_URL));
        }
        
        @Test
        public void screenShotTest() throws InterruptedException, IOException {
        	driver.get("https://www.google.co.uk/search?q=FDM+Group");

        	// Save screen shot
        	File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        	FileUtils.copyFile(scrFile, new File(SCREENSHOT_DIRECTORY + "screenshot.png"));
        }
        
        @Test
        public void registrationFormTest() throws IOException {
        	driver.get("http://localhost:3702/");
        	takeScreenshot("localHost");
        	
        	// Index should contain navigation
        	NavigationPanel navBar = NavigationPanel.getInstance(driver); 
        	navBar.getRegisterLink().click();
        	
        	
        	// Should now be on registration page
        	takeScreenshot("registrationForm");
        	RegistrationPage regPage = RegistrationPage.getInstance(driver);
        	assertEquals("Wrong URL", driver.getCurrentUrl(), regPage.URL);
        	
        	regPage.getEmailInput().sendKeys(UserData.VALID_EMAIL);
        	regPage.getPasswordInput().sendKeys(UserData.VALID_PASSWORD);
        	regPage.getPasswordConfirmInput().sendKeys(UserData.MISMATCHED_PASSWORD);
        	regPage.getFirstNameInput().sendKeys(UserData.VALID_FIRST_NAME);
        	regPage.getLastNameInput().sendKeys(UserData.VALID_LAST_NAME);
        	
        	// Just for testsies...
        	takeScreenshot("beforeSubmit");
        	
        	driver.findElement(By.id("submit")).click();
        	takeScreenshot("onSubmit");
        	
        	List<WebElement> errors = driver.findElements(By.className("alert-danger"));
        	assertEquals("Wrong number of error messages", 1, errors.size());
        	assertEquals("Wrong error message", "Passwords do not match.", errors.get(0).getText());
        }
        
        /**
         * @param Syntax candy for taking screen shots
         */
        private void takeScreenshot(String fileName) throws IOException {
        	File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        	FileUtils.copyFile(scrFile, new File(SCREENSHOT_DIRECTORY + fileName + ".png"));
        }
        
}