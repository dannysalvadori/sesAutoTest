package com.fdmgroup.sesAutoTest.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationPanel {

	private WebDriver driver = null;
	
	// Singleton
	private static NavigationPanel instance = null;
	private NavigationPanel() {}
	
	public static NavigationPanel getInstance(WebDriver driver) {
		if (instance == null) {
			instance = new NavigationPanel();
			instance.driver = driver;
		}
		return instance;
	}
	
	/***********************************
	*          Always Visible          *
	***********************************/
	
	public final WebElement getHomeLink() {
		return driver.findElement(By.id("navLogo"));
	}
	
	/***********************************
	*    Visible when NOT logged in    *
	***********************************/
	
	public final WebElement getLoginLink() {
		return driver.findElement(By.id("navLogin"));
	}
	
	public final WebElement getRegisterLink() {
		return driver.findElement(By.id("navRegister"));
	}
	
	/***********************************
	*   Visible only when logged in    *
	***********************************/

	public final WebElement getNameLink() {
		return driver.findElement(By.id("navName"));
	}
	
	public final WebElement getAccountLink() {
		return driver.findElement(By.id("navAccount"));
	}
	
	public final WebElement getStockExchangeLink() {
		return driver.findElement(By.id("navStockExchange"));
	}
	
	public final WebElement getReportsLink() {
		return driver.findElement(By.id("navReports"));
	}
	
	public final WebElement getLogoutLink() {
		return driver.findElement(By.id("navLogout"));
	}	
	
	/***********************************
	*      Visible only to Admin       *
	***********************************/

	public final WebElement getAdminHomeLink() {
		return driver.findElement(By.id("navAdmin"));
	}

}
