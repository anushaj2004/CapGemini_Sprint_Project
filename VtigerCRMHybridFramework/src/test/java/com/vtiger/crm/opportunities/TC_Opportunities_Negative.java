package com.vtiger.crm.opportunities;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.vtiger.crm.base.BaseClass;
import com.vtiger.crm.pages.HomePage;
import com.vtiger.crm.pages.OpportunitiesInformationPage;
import com.vtiger.crm.pages.OpportunitiesPage;

public class TC_Opportunities_Negative extends BaseClass{

	@Test(groups = {"functional","negative"})
	public void verifyOppName() {
		Reporter.log("Step 1: Navigate to the Opportunities module", true);
		HomePage h=new HomePage(driver);
		h.getOpplink().click();
		Reporter.log("Step 2: Click on Create Opportunity", true);
		OpportunitiesPage o=new OpportunitiesPage(driver);
		o.getCreateOpp().click();
		Reporter.log("Step 3: Initialize the Opportunity Information page", true);
		OpportunitiesInformationPage oi=new OpportunitiesInformationPage(driver);
		Reporter.log("Step 4: Click Save without entering the Opportunity Name", true);
		oi.getSavebt().click();
		Reporter.log("Step 5: Wait for the validation alert to appear", true);
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert al=wait.until(ExpectedConditions.alertIsPresent());
		String message=al.getText();
		Reporter.log("Validation alert message: " + message, true);
		Reporter.log("Step 6: Verify the Opportunity Name validation message", true);
		Assert.assertTrue(message.contains("Opportunity Name cannot be empty"), "alert message is not displayed");
		Reporter.log("Test case Passed: Opportunity Name validation message displayed successfully", true);
		Reporter.log("Step 7: Accept the validation alert", true);
		al.accept();
		Reporter.log("Validation alert accepted successfully", true);
	}
}
