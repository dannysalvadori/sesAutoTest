package com.fdmgroup.sesAutoTest.demoTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fdmgroup.sesAutoTest.pageObjectModel.NavigationPanel;
import com.fdmgroup.sesAutoTest.pageObjectModel.RegistrationPage;
import com.fdmgroup.sesAutoTest.testData.ApplicationData;
import com.fdmgroup.sesAutoTest.testData.RegistrationData;
import com.fdmgroup.sesAutoTest.utils.DriverUtilities;

public class RegistrationTest {
        
        private WebDriver driver;
        
        private String inputEmail = null;
        private String inputPassword = null;
        private String inputConfirmPassword = null;
        private String inputFirstName = null;
        private String inputLastName = null;
        private String inputBirthDate = null;
        
        @Before
        public void setup() {
			DriverUtilities driverUtils = DriverUtilities.getInstance();
			driver = driverUtils.getDriver();
			driver.get(ApplicationData.INDEX_URL);
        }
        
//        @After
        public void clearInput() {
        	clearInputValues();
        }
        
        @AfterClass
        public static void cleanup() {
			DriverUtilities driverUtils = DriverUtilities.getInstance();
			WebDriver driver = driverUtils.getDriver();
			driver.quit(); 
        }

        @Test
        public void registrationFormPasswordMismatchTest() throws IOException {
        	
        	RegistrationPage regPage = navigateToRegistrationPage();
        	
        	regPage.getEmailInput().sendKeys(RegistrationData.VALID_EMAIL);
        	regPage.getPasswordInput().sendKeys(RegistrationData.VALID_PASSWORD);
        	regPage.getPasswordConfirmInput().sendKeys(RegistrationData.MISMATCHED_PASSWORD);
        	regPage.getFirstNameInput().sendKeys(RegistrationData.VALID_FIRST_NAME);
        	regPage.getLastNameInput().sendKeys(RegistrationData.VALID_LAST_NAME);
        	regPage.getSubmitButton().click();
        	
        	List<WebElement> errors = driver.findElements(By.className("alert-danger"));
        	assertEquals("Wrong number of error messages", 1, errors.size());
        	assertEquals("Wrong error message", "Passwords do not match.", errors.get(0).getText());
        }

        public void runTest() throws IOException {
        	
        	// Setup fail conditions
        	inputConfirmPassword = RegistrationData.MISMATCHED_PASSWORD;
        	
        	RegistrationPage regPage = navigateToRegistrationPage();
        	initInputValues();
        	clearInputs(regPage);
        	populateInput(regPage);

        	// Submit form
        	regPage.getSubmitButton().click();

        	List<WebElement> errors = driver.findElements(By.className("alert-danger"));
        	assertEquals("Wrong number of error messages", 1, errors.size());
        	assertEquals("Wrong error message", "Passwords do not match.", errors.get(0).getText());
        }
        
        /**
         * Navigate to the registration page
         */
        private RegistrationPage navigateToRegistrationPage() {
        	// Note: registration link is only visible if not logged in 
        	NavigationPanel navBar = NavigationPanel.getInstance(driver); 
        	navBar.getRegisterLink().click();
        	
        	// Confirm landing on registration page
        	RegistrationPage regPage = RegistrationPage.getInstance(driver);
        	assertEquals("Wrong URL", driver.getCurrentUrl(), regPage.URL);
        	
        	return regPage;
        }
        
        /**
         * Sets input values to default, unless specified otherwise
         */
        private void initInputValues() {
        	inputEmail = inputEmail == null ? RegistrationData.VALID_EMAIL : inputEmail;
        	inputPassword = inputPassword == null ? RegistrationData.VALID_PASSWORD : inputPassword;
        	inputConfirmPassword = inputConfirmPassword == null ? RegistrationData.VALID_PASSWORD : inputConfirmPassword;
        	inputFirstName = inputFirstName== null ? RegistrationData.VALID_FIRST_NAME : inputFirstName;
        	inputLastName = inputLastName== null ? RegistrationData.VALID_LAST_NAME : inputLastName;
        	inputBirthDate = inputBirthDate == null ? RegistrationData.VALID_BIRTHDATE : inputBirthDate;
        }
        
        private void clearInputs(RegistrationPage regPage) {
        	regPage.getEmailInput().clear();
        	regPage.getPasswordInput().clear();
        	regPage.getPasswordConfirmInput().clear();
        	regPage.getFirstNameInput().clear();
        	regPage.getLastNameInput().clear();
        	regPage.getBirthDateInput().clear();
        }
        
        private void populateInput(RegistrationPage regPage) {
        	regPage.getEmailInput().sendKeys(inputEmail);
        	regPage.getPasswordInput().sendKeys(inputPassword);
        	regPage.getPasswordConfirmInput().sendKeys(inputConfirmPassword);
        	regPage.getFirstNameInput().sendKeys(inputFirstName);
        	regPage.getLastNameInput().sendKeys(inputLastName);
        	regPage.getLastNameInput().sendKeys(inputBirthDate);
        }
        
        /**
         * Sets input values back to null, for next test run
         */
        private void clearInputValues() {
        	inputEmail = null;
        	inputPassword = null;
        	inputConfirmPassword = null;
        	inputFirstName = null;
        	inputLastName = null;
        	inputBirthDate = null;
        }
        
}