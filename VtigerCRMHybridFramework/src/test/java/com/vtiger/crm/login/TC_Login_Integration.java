package com.vtiger.crm.login;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.vtiger.crm.base.BaseClassForLogin;
import com.vtiger.crm.pages.HomePage;
import com.vtiger.crm.pages.LoginPage;
import com.vtiger.crm.utilities.PropertyUtility;

public class TC_Login_Integration extends BaseClassForLogin {
@Test(groups = {"integration","positive"})
public void verifyLoginIntegrationDocument() throws Exception {
	Reporter.log("Step 1: Initialize the Login page", true);
	LoginPage l=new LoginPage(driver);
	Reporter.log("Step 2: Read username and password from the properties file", true);
	PropertyUtility p=new PropertyUtility();
	Reporter.log("Step 3: Enter valid login credentials", true);
	l.loginDetails(p.readDatafromPropertiesFile("username"),p.readDatafromPropertiesFile("password"));
	Reporter.log("Step 4: Initialize the Home page", true);
	HomePage h=new HomePage(driver);
	Reporter.log("Step 5: Click on the Documents link", true);
	h.getDocumentlink().click();
	Reporter.log("Step 6: Verify that the Documents module is opened", true);
	Assert.assertTrue(driver.getCurrentUrl().contains("module=Documents"),"Document module was not opened after login");
	Reporter.log("Test case Passed: Documents module opened successfully after login", true);
}
}
