package com.vtiger.crm.document;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vtiger.crm.base.BaseClass;
import com.vtiger.crm.pages.CreateDocumentPage;
import com.vtiger.crm.pages.DocumentInformationPage;
import com.vtiger.crm.pages.DocumentPage;
import com.vtiger.crm.pages.HomePage;
import com.vtiger.crm.utilities.ExcelUtility;
import com.vtiger.crm.utilities.ScreenshotUtility;

public class TC_Document_Functional extends BaseClass{
	@DataProvider(name = "createDoc")
	  public Object[][] createDoc() throws IOException {
	      ExcelUtility e=new ExcelUtility();
	      return e.getData("Sheet1");
	  }
	@Test(dataProvider = "createDoc",groups = {"functional","negative"})
	public void verifyCreateDocument(String title,String productname,String filepath,String assignuser,String largefile) throws IOException {
		Reporter.log("Navigate to the home page and click on documents");
		HomePage h=new HomePage(driver);
		h.getDocumentlink().click();
		Reporter.log("Step 2: Click on Create Document button", true);
		DocumentPage d=new DocumentPage(driver);
		d.getCreatedoc().click();
		Reporter.log("Step 3: Enter the document title", true);
		CreateDocumentPage cd=new CreateDocumentPage(driver);
	    cd.getTitle().sendKeys(title);
	    Reporter.log("Step 4: Click on Save button to save the document", true);
		cd.getSavebt().click();
		Reporter.log("Step 5: Verify the created document title", true);
		DocumentInformationPage di=new DocumentInformationPage(driver);
		String actualTitle=di.getTitle().getText();
		Reporter.log("Expected Document Title: " + title, true);
		Reporter.log("Actual Document Title: " + actualTitle, true);
		Assert.assertEquals(actualTitle,title, "Document title is not matching");
		Reporter.log("Step 6: Document title is matching. Test passed", true);
	}
	
	@Test(groups = {"functional","negative"})
	public void verifyLargeSizedFile() throws Exception {
		Reporter.log("Navigate to the home page and click on documents");
		HomePage h=new HomePage(driver);
		h.getDocumentlink().click();
		Reporter.log("Step 2: Click on Create Document button", true);
		DocumentPage d=new DocumentPage(driver);
		d.getCreatedoc().click();
		Reporter.log("Step 3: Open Excel file and read document test data", true);
		CreateDocumentPage cd=new CreateDocumentPage(driver);
		ExcelUtility e=new ExcelUtility();
		e.openExcel();
		Reporter.log("Step 4: Enter document title from Excel", true);
		cd.getTitle().sendKeys(e.getData("Sheet1", 1, 0));
		Reporter.log("Step 5: Upload a file larger than 3MB", true);
		cd.getFilepath().sendKeys(e.getData("Sheet1", 1, 4));
		Reporter.log("Step 6: Wait for the file size alert", true);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert al = wait.until(ExpectedConditions.alertIsPresent());
		Reporter.log("Step 7: Capture and verify the alert message", true);
		String message = al.getText();
		Reporter.log("Expected Alert Message: File size should not be greater than 3MB", true);
		Reporter.log("Actual Alert Message: " + message, true);
		Assert.assertEquals(message, "File size should not be greater than 3MB");
		Reporter.log("Step 8: Accept the alert", true);
		al.accept();
		Reporter.log("Step 9: Capture screenshot of the large file validation", true);
		ScreenshotUtility.takeScreenshot(driver, "TC_DOCUMENT_LARGESIZEFILE");
		Reporter.log("Step 10: Large file validation test completed successfully", true);
	}
}
