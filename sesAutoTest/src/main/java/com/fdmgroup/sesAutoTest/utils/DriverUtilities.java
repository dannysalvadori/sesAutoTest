package com.fdmgroup.sesAutoTest.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtilities {

        private static final String CONFIG_FILE_NAME = "config.properties";
        private static DriverUtilities instanceOfDriverUtilities;
        private WebDriver driver;

        // Private constructor ensures singleton pattern
        private DriverUtilities() {}

        /**
         * Singleton pattern getInstance()
         */
        public static DriverUtilities getInstance() {
                if (instanceOfDriverUtilities == null) {
                        instanceOfDriverUtilities = new DriverUtilities();
                }
                return instanceOfDriverUtilities;
        }

        /**
         * Gets a WebDriver instance as specified in config file
         */
        public WebDriver getDriver() {
                if (driver == null) {
                        createDriver();
                }
                return driver;
        }

        /**
         * Initialises WebDriver implementation to Chrome/Firefox, whichever specified in config file
         */
    private void createDriver() {

        // Read config file for specific driver
        String driverName = getDriverName();

        switch (driverName) {
                case "Google Chrome":
                        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                        this.driver = new ChromeDriver();
                        break;

                case "Firefox":
                        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
                        this.driver = new FirefoxDriver();
                        break;

                default:
                        break;
        }
    }

        /**
         * Scans config file for driver name
         */
        private String getDriverName() {
                Properties config = new Properties();
                String driverName = "";
            try {
                config.load(new FileInputStream(CONFIG_FILE_NAME));

            } catch (FileNotFoundException e) {
                        System.out.println("Config file '" + CONFIG_FILE_NAME + "' not found.");
                        e.printStackTrace();

            } catch (IOException e) {
                        System.out.println("Error when loading config file");
                        e.printStackTrace();
                }

        for (String key : config.stringPropertyNames()) {
                if (key.equals("browser")) {
                        driverName = config.getProperty(key);
                }
        }
        return driverName;
    }

}