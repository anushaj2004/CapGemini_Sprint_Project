package com.vtiger.crm.document;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.vtiger.crm.base.BaseClass;
import com.vtiger.crm.pages.HomePage;

public class TC_Document_Smoke extends BaseClass{
	@Test(groups = {"smoke","positive"})
	public void verifyDocumentPage() throws InterruptedException {
		Reporter.log("Step 1: Navigate to the home page and click on Documents", true);
		HomePage h=new HomePage(driver);
		h.getDocumentlink().click();
		Reporter.log("Step 2: Verify the Documents page is displayed from the home page", true);
		Assert.assertTrue(driver.getCurrentUrl().contains("Documents&action=index"), "Documents page did not displayed");
		Reporter.log("Test case Passed: Verified Documents page is displayed", true);
		
	}
	
	
	
}
