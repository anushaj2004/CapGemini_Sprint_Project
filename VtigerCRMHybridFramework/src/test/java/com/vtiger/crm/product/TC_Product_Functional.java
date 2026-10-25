package com.vtiger.crm.product;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vtiger.crm.base.BaseClass;
import com.vtiger.crm.pages.HomePage;
import com.vtiger.crm.pages.ProductInformationPage;
import com.vtiger.crm.pages.ProductPage;
import com.vtiger.crm.utilities.ExcelUtility;

public class TC_Product_Functional extends BaseClass{
	@DataProvider(name = "createProdcut")
	  public Object[][] createProduct() throws IOException {
	      ExcelUtility e=new ExcelUtility();
	      return e.getData("Sheet2");
	}
@Test(dataProvider = "createProdcut",groups = {"functional","positive"})
public void verifyCreateProduct(String productname,String docname) throws InterruptedException {
	Reporter.log("Step 1: Navigate to the Products module", true);
	HomePage h=new HomePage(driver);
	h.getProductlink().click();
	Reporter.log("Step 2: Click on Create Product", true);
	ProductPage p=new ProductPage(driver);
	p.getCreateProduct().click();
	Reporter.log("Step 3: Initialize the Product Information page", true);
	ProductInformationPage p1=new ProductInformationPage(driver);
	Reporter.log("Step 4: Enter the product name", true);
	p1.getProductname().sendKeys(productname);
	Reporter.log("Step 5: Save the product", true);
	p1.getSavebt();
	Reporter.log("Step 6: Navigate back to the Products module", true);
	h.getProductlink().click();
	Reporter.log("Step 7: Enter the product name in the search field", true);
	p.getProductnamesearch().sendKeys(productname);
	Reporter.log("Step 8: Select the product type", true);
	WebElement productType = p.getProducttype();
	Select s=new Select(productType);
	s.selectByIndex(1);
	Reporter.log("Step 9: Wait for the search criteria to be applied", true);
	Thread.sleep(2000);
	Reporter.log("Step 10: Click the Search button", true);
	p.getSearchbt().click();
	Reporter.log("Step 11: Wait for the search results to load", true);
	Thread.sleep(2000);
	Reporter.log("Step 12: Retrieve the product search results", true);
	String productFound=p.getProductlist().getText();
	Reporter.log("Product search results: " + productFound, true);
	Reporter.log("Step 13: Verify that the product was created successfully", true);
	Assert.assertTrue(productFound.contains(productname),"Product was not created successfully: " + productname);
	Reporter.log("Test case Passed: Product created and verified successfully", true);
}
}
