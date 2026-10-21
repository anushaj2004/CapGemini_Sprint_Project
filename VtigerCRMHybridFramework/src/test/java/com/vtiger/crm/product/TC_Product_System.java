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
import com.vtiger.crm.utilities.ExplicitWait;

public class TC_Product_System extends BaseClass {

	@DataProvider(name = "createproduct")
	  public Object[][] createproduct() throws IOException {
	      ExcelUtility e=new ExcelUtility();
	      return e.getMultipleData("Sheet5");
	}
	
	@Test(dataProvider = "createproduct",groups = {"system","positive"})
	public void verifyProductSystemTest(String productName,String cartNo,String unitPrice) throws InterruptedException {
		Reporter.log("Step 1: Navigate to the Products module", true);
		HomePage h=new HomePage(driver);
		h.getProductlink().click();
		Reporter.log("Step 2: Click on Create Product", true);
		ProductPage p=new ProductPage(driver);
		p.getCreateProduct().click();
		Reporter.log("Step 3: Initialize the Product Information page", true);
		ProductInformationPage pi=new ProductInformationPage(driver);
		Reporter.log("Step 4: Enter the product name: " + productName, true);
		pi.getProductname().sendKeys(productName);
		Reporter.log("Step 5: Enter the cart number: " + cartNo, true);
		pi.getCartNo().sendKeys(cartNo);
		Reporter.log("Step 6: Enter the unit price: " + unitPrice, true);
		pi.getUnitPrice().sendKeys(unitPrice);
		Reporter.log("Step 7: Save the product details", true);
		pi.getSavebt().click();
		Reporter.log("Step 8: Navigate back to the Products module", true);
		h.getProductlink().click();
		Reporter.log("Step 9: Wait for the product search field to become visible", true);
		ExplicitWait.waitForElementToVisible(driver, p.getProductnamesearch());
		Reporter.log("Step 10: Search for the product: " + productName, true);
		p.getProductnamesearch().sendKeys(productName);
		Reporter.log("Step 11: Select Product Name as the search filter", true);
		WebElement prodType = p.getProducttype();
		Select s=new Select(prodType);
		s.selectByContainsVisibleText("Product Name");
		Reporter.log("Step 12: Click the Search button", true);
		p.getSearchbt().click();
		Thread.sleep(2000);
		Reporter.log("Step 14: Retrieve the product search results", true);
		String productFound=p.getProductlist().getText();
		Reporter.log("Product search results: " + productFound, true);
		Reporter.log("Step 15: Verify that the product was found successfully", true);
		Assert.assertTrue(productFound.contains(productName),"FAIL: Product was not found: " + productName);
		Reporter.log("PASS: Product found successfully: " + productName, true);

	}
	
	
}
