package com.vtiger.crm.base;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.module.Browser;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.vtiger.crm.pages.HomePage;
import com.vtiger.crm.pages.LoginPage;
import com.vtiger.crm.utilities.PropertyUtility;

public class BaseClass {
	protected WebDriver driver=null;
    PropertyUtility p=new PropertyUtility();
    @Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void lanuchBrowser(@Optional("chrome") String browser) throws Exception {
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.setExperimentalOption("prefs", new HashMap<String, Object>() {{
		    put("credentials_enable_service", false);
		    put("profile.password_manager_enabled", false);
		    put("profile.password_manager_leak_detection", false);
		}});
		if(browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver(options);
		else if(browser.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if(browser.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();
		//maximise the window browser
		driver.manage().window().maximize();
		//add implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		//navigate to the application
		driver.get(p.readDatafromPropertiesFile("url"));
		LoginPage l=new LoginPage(driver);
		//enter the login details
		l.loginDetails(p.readDatafromPropertiesFile("username"), p.readDatafromPropertiesFile("password"));
		Reporter.log("Login successfull",true);

	}
	@AfterMethod(alwaysRun = true)
	public void closeBrowser()
	{
		HomePage h=new HomePage(driver);
    	h.getShowsignout().click();
    	h.getSignout().click();
    	Reporter.log("logout executed",true);
		driver.quit();
		Reporter.log("Close Browser",true);
	}
}
