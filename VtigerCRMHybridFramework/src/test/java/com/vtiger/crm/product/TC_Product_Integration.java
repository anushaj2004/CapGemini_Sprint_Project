package com.vtiger.crm.product;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vtiger.crm.base.BaseClass;
import com.vtiger.crm.pages.CreateVendorPage;
import com.vtiger.crm.pages.HomePage;
import com.vtiger.crm.pages.MoreOptionPage;
import com.vtiger.crm.pages.ProductInformationPage;
import com.vtiger.crm.pages.ProductPage;
import com.vtiger.crm.pages.VendorMoreInformationPage;
import com.vtiger.crm.pages.VendorPage;
import com.vtiger.crm.utilities.ExcelUtility;
import com.vtiger.crm.utilities.ExplicitWait;

public class TC_Product_Integration extends BaseClass{

	@DataProvider(name = "verifyProductVendor")
	  public Object[][] verifyProductVendor() throws IOException {
	      ExcelUtility e=new ExcelUtility();
	      return e.getData("Sheet4");
	}
	@Test(dataProvider = "verifyProductVendor",groups = {"integration","positive"})
	public void verifyProdcutLinkedVendor(String vendorName,String productName) throws InterruptedException {
		Reporter.log("Step 1: Navigate to the More Options menu", true);
		HomePage h=new HomePage(driver);
		h.getMoreoption().click();
		Reporter.log("Step 2: Open the Vendors module", true);
		MoreOptionPage mp=new MoreOptionPage(driver);
		mp.getVendorslink().click();
		Reporter.log("Step 3: Click on Create Vendor", true);
		VendorPage v=new VendorPage(driver);
		v.getCreatevendor().click();
		Reporter.log("Step 4: Enter the vendor name: " + vendorName, true);
		CreateVendorPage cv=new CreateVendorPage(driver);
		cv.getVendorName().sendKeys(vendorName);
		Reporter.log("Step 5: Save the vendor", true);
		cv.getSavebt().click();
		Reporter.log("Step 6: Open More Information for the vendor", true);
		cv.getMoreinfo().click();
		Reporter.log("Step 7: Display the vendor products section", true);
		VendorMoreInformationPage vm=new VendorMoreInformationPage(driver);
		vm.getShowVendorProduct().click();
		Reporter.log("Step 8: Click on Add Product", true);
		vm.getAddProduct().click();
		Reporter.log("Step 9: Enter the product name: " + productName, true);
		ProductInformationPage p=new ProductInformationPage(driver);
		p.getProductname().sendKeys(productName);
		Reporter.log("Step 10: Save the product", true);
		p.getSavebt().click();
		Reporter.log("Step 11: Wait for the Products link to become visible", true);
		ExplicitWait.waitForElementToVisible(driver, h.getProductlink());
		Reporter.log("Step 12: Navigate to the Products module", true);
		h.getProductlink().click();
		Reporter.log("Step 13: Select Vendor Name as the search filter", true);
		ProductPage pp=new ProductPage(driver);
		WebElement filter=pp.getFilter();
		Select sf=new Select(filter);
		sf.selectByContainsVisibleText("Vendor Name");
		Reporter.log("Step 14: Wait for the filter selection to apply", true);
		Thread.sleep(2000);
		Reporter.log("Step 15: Enter the vendor name in the search field: " + vendorName, true);
		pp.getProductnamesearch().sendKeys(vendorName);
		Reporter.log("Step 16: Select the product type filter", true);
		WebElement prodType = pp.getProducttype();
		Select s=new Select(prodType);
		s.selectByIndex(2);
		Reporter.log("Step 17: Click the Search button", true);
		pp.getSearchbt().click();
		Reporter.log("Step 18: Wait for the search results to load", true);
		Thread.sleep(2000);
		Reporter.log("Step 19: Retrieve the vendor product search results", true);
		String vendorProduct=pp.getProductlist().getText();
		Reporter.log("Vendor product search results: " + vendorProduct, true);
		Reporter.log("Step 20: Verify that the product is associated with the vendor", true);
		Assert.assertTrue(vendorProduct.contains(productName),"FAIL: Expected product was not found in the product list.");
		Reporter.log("Test case Passed: Product associated with the vendor and verified successfully", true);
	}
}
