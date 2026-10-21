package com.vtiger.crm.login;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.vtiger.crm.base.BaseClass;
import com.vtiger.crm.base.BaseClassForLogin;
import com.vtiger.crm.pages.LoginPage;
import com.vtiger.crm.utilities.PropertyUtility;
import com.vtiger.crm.utilities.ScreenshotUtility;

public class TC_Login_Functional extends BaseClassForLogin{
	
@Test(groups = {"functional","positive"})
public void verifyLogin() throws Exception {
	Reporter.log("Step 1: Initialize the Login page", true);
	LoginPage l=new LoginPage(driver);
	Reporter.log("Step 2: Read username and password from the properties file", true);
	PropertyUtility p=new PropertyUtility();
	Reporter.log("Step 3: Enter login credentials and click Login", true);
	l.loginDetails(p.readDatafromPropertiesFile("username"),p.readDatafromPropertiesFile("password"));
	Reporter.log("Step 4: Verify successful login", true);
	Assert.assertTrue(driver.getCurrentUrl().contains("module=Home"),"Login failed" );
	Reporter.log("Test case Passed: Login successful and Home page URL verified", true);

}

@Test(groups = {"functional","negative"})
public void verifyInvalidLogin() throws Exception {
	Reporter.log("Step 1: Initialize the Login page", true);
	LoginPage l=new LoginPage(driver);
	Reporter.log("Step 2: Read invalid username and password from the properties file", true);
	PropertyUtility p=new PropertyUtility();
	Reporter.log("Step 3: Enter invalid login credentials", true);
	l.loginDetails(p.readDatafromPropertiesFileForInvalid("username"),p.readDatafromPropertiesFileForInvalid("password") );
	Reporter.log("Step 4: Capture the login error message", true);
	String errormessage=l.getErrormessage().getText();
	Reporter.log("Login error message: " + errormessage, true);
	Reporter.log("Step 5: Verify that the login error message is displayed", true);
	Assert.assertTrue(l.getErrormessage().isDisplayed(),"Expected login error was not displayed");
	Reporter.log("Test case Passed: Invalid login error message was displayed successfully", true);
	Reporter.log("Step 6: Capture screenshot for invalid login credentials", true);
	ScreenshotUtility.takeScreenshot(driver, "InvalidLoginCredentails");
	Reporter.log("Screenshot captured for invalid login credentials", true);
}
}
