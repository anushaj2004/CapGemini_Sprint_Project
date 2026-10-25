package com.vtiger.crm.opportunities;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vtiger.crm.base.BaseClass;
import com.vtiger.crm.pages.HomePage;
import com.vtiger.crm.pages.OpportunitiesInformationPage;
import com.vtiger.crm.pages.OpportunitiesMoreInfoPage;
import com.vtiger.crm.pages.OpportunitiesPage;
import com.vtiger.crm.pages.ProductPage;
import com.vtiger.crm.pages.SelectedOrganizationPopupPage;
import com.vtiger.crm.pages.SelectedProductPopupPage;
import com.vtiger.crm.utilities.ExcelUtility;

public class TC_Opportunities_Integration extends BaseClass{

	@DataProvider(name="oppProductIntegration")
	public Object[][] oppProductIntegration() throws IOException {
		ExcelUtility e=new ExcelUtility();
		return e.getData("Sheet3");
	}
	
	@Test(dataProvider = "oppProductIntegration",groups = {"integration","positive"})
	public void verifyOpportunityIntegrationWithProduct(String oppname,String orgname,String productname) throws InterruptedException {
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
//		Thread.sleep(1500);
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
        Reporter.log("Step 6: Search and select the organization", true);
        sop.getSearchbt().click();
        sop.getOrganizationSelect().click();
        driver.switchTo().window(mainWindow);
        Reporter.log("Step 7: Return to the Opportunity creation window", true);
        Reporter.log("Step 8: Select the sales stage", true);
		Select stage = new Select(op1.getSalesstage());
	    stage.selectByIndex(1);
	    Reporter.log("Step 9: Save the opportunity", true);
	    op1.getSavebt().click();
	    Reporter.log("Step 10: Open More Information for the opportunity", true);
		op1.getMoreinfo().click();
		OpportunitiesMoreInfoPage om=new OpportunitiesMoreInfoPage(driver);
		Reporter.log("Step 11: Display the product selection section", true);
		om.getShowProduct().click();
		String oppMoreinfoWindow = driver.getWindowHandle();
		Reporter.log("Step 12: Open the product selection popup", true);
		om.getSelectProduct().click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		// Switch to the product popup
		Set<String> productWindows = driver.getWindowHandles();

		for (String window : productWindows) {
		    if (!window.equals(oppMoreinfoWindow)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}
		Reporter.log("Step 13: Search for product: " + productname, true);
		SelectedProductPopupPage sp=new SelectedProductPopupPage(driver);
		sp.getProductNameSearch().sendKeys(productname);
		Select s=new Select(sp.getProductType());
		s.selectByContainsVisibleText("Product Name");
		Reporter.log("Step 14: Click Search to find the product", true);
		sp.getSearchbt().click();
		Thread.sleep(2000);
		Reporter.log("Step 15: Select the product: " + productname, true);
		sp.selectProductByName(productname);
		driver.switchTo().window(oppMoreinfoWindow);
		Reporter.log("Step 16: Return to the Opportunity More Information window", true);
		Reporter.log("Step 17: Navigate to the Products module", true);
		h.getProductlink().click();
		ProductPage p=new ProductPage(driver);
		Reporter.log("Step 18: Search for the product: " + productname, true);
		p.getProductnamesearch().sendKeys(productname);
		WebElement prodType1 = p.getProducttype();
		Select sel= new Select(prodType1);
	    sel.selectByContainsVisibleText("Product Name");
	    Reporter.log("Step 19: Click Search to find the product", true);
		p.getSearchbt().click();
		Thread.sleep(2000);
		Reporter.log("Step 20: Select the product from the product list", true);
		p.getProductlist().click();
		Reporter.log("Test case Passed: Opportunity product selection workflow completed", true);
	}
}
