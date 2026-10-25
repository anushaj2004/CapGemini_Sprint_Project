package com.vtiger.crm.opportunities;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vtiger.crm.base.BaseClass;
import com.vtiger.crm.pages.HomePage;
import com.vtiger.crm.pages.OpportunitiesInformationPage;
import com.vtiger.crm.pages.OpportunitiesPage;
import com.vtiger.crm.pages.OrganizationPage;
import com.vtiger.crm.pages.SelectedOrganizationPopupPage;
import com.vtiger.crm.utilities.ExcelUtility;
import com.vtiger.crm.utilities.ScreenshotUtility;

public class TC_Opportunities_Functional extends BaseClass {
	
	@DataProvider(name="oppCreated")
	public Object[][] oppCreated() throws IOException {
		ExcelUtility e=new ExcelUtility();
		return e.getData("Sheet3");
	}
	
	@Test(dataProvider = "oppCreated",groups = {"functional","positive"})
	public void verifyOpportunitiesCreated(String oppname,String orgname,String productname) throws InterruptedException, IOException {
		Reporter.log("Step 1: Navigate to the Opportunities module", true);
		HomePage h=new HomePage(driver);
		h.getOpplink().click();
		Reporter.log("Step 2: Click on Create Opportunity", true);
		OpportunitiesPage op=new OpportunitiesPage(driver);
		op.getCreateOpp().click();
		Reporter.log("Step 3: Enter the opportunity name: " + oppname, true);
		OpportunitiesInformationPage op1=new OpportunitiesInformationPage(driver);
		op1.getOppName().sendKeys(oppname);
		String mainWindow = driver.getWindowHandle();
		Reporter.log("Step 4: Open the Organization lookup popup", true);
		op1.getOrganizationLookup().click();
		Thread.sleep(1500);
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        Reporter.log("Step 5: Search for organization: " + orgname, true);
        SelectedOrganizationPopupPage sop=new SelectedOrganizationPopupPage(driver);
        sop.getSearchOrg().sendKeys(orgname);
        WebElement orgType = sop.getSearchOrgType();
        Select s1=new Select(orgType);
        s1.selectByIndex(0);
        Reporter.log("Step 6: Click Search to find the organization", true);
        sop.getSearchbt().click();
        Reporter.log("Step 7: Select the organization: " + orgname, true);
        sop.getOrganizationSelect().click();
        driver.switchTo().window(mainWindow);
        Reporter.log("Step 8: Return to the Opportunity creation window", true);
        Reporter.log("Step 9: Select the sales stage", true);
		Select stage = new Select(op1.getSalesstage());
	    stage.selectByIndex(1);
	    Reporter.log("Step 10: Save the opportunity", true);
	    op1.getSavebt().click();
	    Thread.sleep(2000);
	    Reporter.log("Step 11: Navigate back to the Opportunities module", true);
	    h.getOpplink().click();
	    Reporter.log("Step 12: Search for the created opportunity: " + oppname, true);
	    op.getOpportunitynamesearch().sendKeys(oppname);
	    WebElement oppType = op.getOppType();
	    Select s2=new Select(oppType);
	    s2.selectByIndex(1);
	    Thread.sleep(2000);
	    Reporter.log("Step 13: Click Search to find the opportunity", true);
	    op.getSearchbt().click();
	    Thread.sleep(2000);
	    Reporter.log("Step 14: Verify that the opportunity was created successfully", true);
	    String opportunityFound = op.getOpportunitylist().getText();
	    Reporter.log("Expected Opportunity Name: " + oppname, true);
	    Reporter.log("Actual Opportunity Details: " + opportunityFound, true);
	    Assert.assertTrue(opportunityFound.contains(oppname),"Opportunity was not created successfully");
	    Reporter.log("Test case Passed: Opportunity was created and verified successfully", true);
	    Reporter.log("Step 15: Capture screenshot of the successful opportunity creation", true);
	    ScreenshotUtility.takeScreenshot(driver, "Opportunity_Functional_Positive");
	    Reporter.log("Screenshot captured: Opportunity_Functional_Positive", true);
	}
}
