package com.vtiger.crm.document;

import java.io.File;
import java.io.IOException;
import java.nio.channels.SelectableChannel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vtiger.crm.base.BaseClass;
import com.vtiger.crm.pages.CreateDocumentPage;
import com.vtiger.crm.pages.DocumentPage;
import com.vtiger.crm.pages.HomePage;
import com.vtiger.crm.utilities.ExcelUtility;


public class TC_Document_System extends BaseClass {
	@DataProvider(name = "createDoc")
	  public Object[][] createDoc() throws IOException {
	      ExcelUtility e=new ExcelUtility();
	      return e.getData("Sheet1");
	  }
@Test(dataProvider = "createDoc",groups = {"system","positive"})
public void createDocAndVerifyDownload(String title,String productname,String filepath,String au,String largefile) throws InterruptedException {
	Reporter.log("Step 1: Navigate to the home page and click on Documents", true);
	HomePage h=new HomePage(driver);
	h.getDocumentlink().click();
	Reporter.log("Step 2: Click on Create Document", true);
	DocumentPage d=new DocumentPage(driver);
	d.getCreatedoc().click();
	Reporter.log("Step 3: Enter document title and file path", true);
	CreateDocumentPage cd=new CreateDocumentPage(driver);
    cd.getTitle().sendKeys(title);
    cd.getFilepath().sendKeys(filepath);
    Reporter.log("Step 4: Select the assigned user", true);
    WebElement assignuser = cd.getAssignusers();
    Select s=new Select(assignuser);
    s.selectByVisibleText(au);
    Reporter.log("Step 5: Click on Save button to create the document", true);
	cd.getSavebt().click();
	Reporter.log("Step 6: Navigate back to Documents module", true);
	h.getDocumentlink().click();
	Reporter.log("Step 7: Select the document search criteria", true);
	WebElement search = d.getSearchdoc();
	Select s1=new Select(search);
	s1.selectByIndex(1);
	Reporter.log("Step 8: Search for the created document: " + title, true);
	d.getSearchtext().sendKeys(title);
	d.getSearchbt().click();
	Reporter.log("Step 9: Verify the document is displayed in the document list", true);
	Assert.assertTrue(driver.getPageSource().contains(title), "Document was not displayed in the document list");
	Reporter.log("Test case Passed: Document was created and displayed successfully", true);
	
}
}
