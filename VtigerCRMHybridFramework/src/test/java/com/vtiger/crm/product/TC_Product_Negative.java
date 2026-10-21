package com.vtiger.crm.product;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.vtiger.crm.base.BaseClass;
import com.vtiger.crm.pages.HomePage;
import com.vtiger.crm.pages.ProductInformationPage;
import com.vtiger.crm.pages.ProductPage;
import com.vtiger.crm.utilities.ScreenshotUtility;

public class TC_Product_Negative extends BaseClass{

	@Test(groups = {"functional","negative"})
	public void verifyProductNameblank() throws IOException
	{
		Reporter.log("Step 1: Navigate to the Products module", true);
		HomePage h=new HomePage(driver);
		h.getProductlink().click();
		Reporter.log("Step 2: Click on Create Product", true);
		ProductPage p=new ProductPage(driver);
		p.getCreateProduct().click();
		Reporter.log("Step 3: Initialize the Product Information page", true);
		ProductInformationPage pi=new ProductInformationPage(driver);
		Reporter.log("Step 4: Click Save without entering the Product Name", true);
		pi.getSavebt().click();
		Reporter.log("Step 5: Wait for the validation alert to appear", true);
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert al=wait.until(ExpectedConditions.alertIsPresent());
		Reporter.log("Step 6: Retrieve the validation alert message", true);
		String message=al.getText();
		Reporter.log("Validation alert message: " + message, true);
		Reporter.log("Step 7: Verify the Product Name validation message", true);
		Assert.assertTrue(message.contains("Product Name cannot be empty"),"No alert message displayed");
		Reporter.log("Test case Passed: Product Name validation message displayed successfully", true);
		Reporter.log("Step 8: Accept the validation alert", true);
		al.accept();
		Reporter.log("Validation alert accepted successfully", true);
		
	}
}
