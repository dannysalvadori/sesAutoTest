package com.fdmgroup.sesAutoTest.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage {

	public final String URL = "http://localhost:3702/register";
	private WebDriver driver = null;
	
	// Singleton
	private static RegistrationPage instance = null;
	private RegistrationPage() {}
	
	public static RegistrationPage getInstance(WebDriver driver) {
		if (instance == null) {
			instance = new RegistrationPage();
			instance.driver = driver;
		}
		return instance;
	}
	
	public final WebElement getEmailInput() {
		return driver.findElement(By.id("email"));
	}
	
	public final WebElement getPasswordInput() {
    	return driver.findElement(By.id("password"));
	}
	
	public final WebElement getPasswordConfirmInput() {
    	return driver.findElement(By.id("confirmPW"));
	}
	
	public final WebElement getFirstNameInput() {
		return driver.findElement(By.id("name"));
	}
	
	public final WebElement getLastNameInput() {
		return driver.findElement(By.id("lastName"));
	}
	
	public final WebElement getBirthDateInput() {
		return driver.findElement(By.id("birthDate"));
	}
	
	public final WebElement getSubmitButton() {
		return driver.findElement(By.id("submit"));
	}
		
}
