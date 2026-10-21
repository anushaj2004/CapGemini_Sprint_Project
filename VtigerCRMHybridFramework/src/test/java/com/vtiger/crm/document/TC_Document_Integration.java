package com.vtiger.crm.document;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
import com.vtiger.crm.pages.ProductInformationPage;
import com.vtiger.crm.pages.ProductMoreInformation;
import com.vtiger.crm.pages.ProductPage;
import com.vtiger.crm.pages.SelectDocumentsPopupPage;
import com.vtiger.crm.utilities.ExcelUtility;

public class TC_Document_Integration extends BaseClass{
	
	@DataProvider(name = "createDoc")
	  public Object[][] createDoc() throws IOException {
	      ExcelUtility e=new ExcelUtility();
	      return e.getMultipleData("Sheet2");
	}
	
@Test(dataProvider = "createDoc",groups = {"integration","positive"})
public void verifyproductdocIntegration(String doctitle,String productname) throws InterruptedException, IOException {
	Reporter.log("Step 1:Navigate to the home page and click on documents",true);
	HomePage h=new HomePage(driver);
	h.getProductlink().click();
	Reporter.log("Step 2: Click on Create Product", true);
	ProductPage p=new ProductPage(driver);
	p.getCreateProduct().click();
	Reporter.log("Step 3: Enter product name and save the product", true);
	ProductInformationPage p1=new ProductInformationPage(driver);
	ExcelUtility e=new ExcelUtility();
	e.openExcel();
	p1.getProductname().sendKeys(productname);
	p1.getSavebt().click();
	Reporter.log("Step 4: Open product More Information", true);
	p1.getMoreinfo().click();
	Reporter.log("Step 5: Open product Documents and click Add Document", true);
	ProductMoreInformation p2=new ProductMoreInformation(driver);
	p2.getShowproductdoc().click();
	p2.getAddDoc().click();
	Reporter.log("Step 6: Enter document title and save the document", true);
	CreateDocumentPage cd=new CreateDocumentPage(driver);
	cd.getTitle().sendKeys(doctitle);
	String expectedTitle=cd.getTitle().getAttribute("value");;
	cd.getSavebt().click();
	Reporter.log("Step 7: Navigate to Documents module and search for the document", true);
	h.getDocumentlink().click();
	DocumentPage d=new DocumentPage(driver);
	WebElement search = d.getSearchdoc();
	Select s=new Select(search);
	s.selectByIndex(1);
	d.getSearchtext().sendKeys(doctitle);
	d.getSearchbt().click();
	Thread.sleep(2000);
	Reporter.log("Step 8: Verify the document title", true);
	String actualTitle=d.getTitle().getText();
	Reporter.log("Expected Document Title: " + expectedTitle, true);
	Reporter.log("Actual Document Title: " + actualTitle, true);
	Assert.assertEquals(actualTitle, expectedTitle,"Document title does not match!");
	Reporter.log("Step 9: Product-document integration test passed", true);
}

@Test(dataProvider = "createDoc",groups = {"integration","negative"})
public void verifyProductDocDelete(String productName,String DocTitle) throws InterruptedException {
	Reporter.log("Step 1: Navigate to Documents module", true);
	HomePage h=new HomePage(driver);
	h.getDocumentlink().click();
	Reporter.log("Step 2: Click on Create Document", true);
	DocumentPage d=new DocumentPage(driver);
	d.getCreatedoc().click();
	Reporter.log("Step 3: Enter document title and save the document", true);
	CreateDocumentPage cd=new CreateDocumentPage(driver);
	cd.getTitle().sendKeys(DocTitle);
	cd.getSavebt().click();
	Reporter.log("Step 4: Click on Delete Document", true);
	DocumentInformationPage d1=new DocumentInformationPage(driver);
	d1.getDeletedoc().click();
	Reporter.log("Step 5: Wait for delete confirmation alert", true);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	Alert al = wait.until(ExpectedConditions.alertIsPresent());
	String message = al.getText();
	Reporter.log("Delete confirmation alert: " + message, true);
	Thread.sleep(2000);
	al.accept();
	Thread.sleep(2000);
	Reporter.log("Step 6: Navigate to Products module", true);
	h.getProductlink().click();
	Reporter.log("Step 7: Create a product", true);
	ProductPage p=new ProductPage(driver);
	p.getCreateProduct().click();
	ProductInformationPage p1=new ProductInformationPage(driver);
	p1.getProductname().sendKeys(productName);
	p1.getSavebt().click();
	p1.getMoreinfo().click();
	Reporter.log("Step 8: Open Product Documents", true);
	ProductMoreInformation p2=new ProductMoreInformation(driver);
	p2.getShowproductdoc().click();
	Reporter.log("Step 9: Open Select Documents popup", true);
	String parentWindow = driver.getWindowHandle();
	p2.getSeldoc().click();
	// Wait until the popup window opens
	WebDriverWait wait1 = new WebDriverWait(
	        driver, Duration.ofSeconds(10));

	wait1.until(ExpectedConditions.numberOfWindowsToBe(2));
	// Switch to the popup window
	for (String window : driver.getWindowHandles()) {
	    if (!window.equals(parentWindow)) {
	        driver.switchTo().window(window);
	        break;
	    }
	}
	// Now Selenium is in the Documents popup window
	Reporter.log("Popup Title: " + driver.getTitle(), true);
	Reporter.log("Popup URL: " + driver.getCurrentUrl(), true);
	Reporter.log("Step 10: Search for the deleted document", true);
	SelectDocumentsPopupPage s=new SelectDocumentsPopupPage(driver);
	s.getSearchtext().sendKeys(DocTitle);
	WebElement doctype = s.getSearchDoc();
	Select s1=new Select(doctype);
	s1.selectByIndex(0);
	s.getSearchbt().click();
	Reporter.log("Step 11: Verify the document search result", true);
	String message1=s.getNodocpresent().getText();
	Reporter.log("Search result: " + message1, true);
	Assert.assertTrue(message1.toLowerCase().contains("no document"),"Deleted document is still present or the no-document message was not displayed");
	driver.switchTo().window(parentWindow);
}

}
