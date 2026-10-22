package com.vtiger.crm.email;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vtiger.crm.base.BaseClass;
import com.vtiger.crm.pages.EmailPage;
import com.vtiger.crm.pages.HomePage;
import com.vtiger.crm.pages.SelectedEmailPopupCompose;
import com.vtiger.crm.pages.SelectedEmailToPopupPage;
import com.vtiger.crm.utilities.ExcelUtility;
import com.vtiger.crm.utilities.ExplicitWait;

public class TC_Email_Negative extends BaseClass{

	@DataProvider(name="saveEmailDetails")
	public Object[][] saveEmailDetails() throws IOException {
		ExcelUtility e=new ExcelUtility();
		return e.getMultipleData("Sheet8");
	}
	@Test(dataProvider = "saveEmailDetails",groups = {"functional","negative"})
	public void verifyDeleteEmail(String emailname,String subjectname) throws InterruptedException {
		Reporter.log("Step 1: Navigate to the home page and click on Email", true);
		HomePage h=new HomePage(driver);
		h.getEmaillink().click();
		EmailPage e=new EmailPage(driver);
		String emailWindow = driver.getWindowHandle();
		Reporter.log("Step 2: Click on Compose email", true);
		e.getCompose().click();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		Reporter.log("Step 3: Wait for the Compose window to open", true);
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String window:driver.getWindowHandles())
		{
			if(!window.equals(emailWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
		String composeWindow = driver.getWindowHandle();
		SelectedEmailPopupCompose sc=new SelectedEmailPopupCompose(driver);
		Reporter.log("Step 4: Click Add To to select an email recipient", true);
		Set<String> oldWindows = driver.getWindowHandles();

		sc.getAddto().click();

		wait.until(ExpectedConditions.numberOfWindowsToBe(3));

		for (String window : driver.getWindowHandles()) {
		    if (!oldWindows.contains(window)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}
		// Now the driver is in the contact popup
		Reporter.log("Step 5: Search for contact: " + emailname, true);
		SelectedEmailToPopupPage sto = new SelectedEmailToPopupPage(driver);
		sto.getSearchField().sendKeys(emailname);
		Select sel = new Select(sto.getSearchType());
		sel.selectByVisibleText("Last Name");
		sto.getSearchButton().click();
		Reporter.log("Step 6: Select the contact: " + emailname, true);
		sto.getContactByName(emailname).click();
		// Return to the compose window
		driver.switchTo().window(composeWindow);
		Reporter.log("Step 7: Enter the email subject: " + subjectname, true);
		sc.getSubject().sendKeys(subjectname);
		Reporter.log("Step 8: Save the composed email", true);
		sc.getSavebt().click();
		Reporter.log("Step 9: Return to the Email window and search by subject", true);
		driver.switchTo().window(emailWindow);
		e.getSearchtext().sendKeys(subjectname);
		Select s3=new Select(e.getSearchfield());
		s3.selectByContainsVisibleText("in Subject");
		e.getFindbt().click();
		Thread.sleep(2000);
		Reporter.log("Step 10: Verify the saved email subject", true);
		String savedSubject=e.getSavedSubject().getText();
		Reporter.log("Expected Subject: " + subjectname, true);
		Reporter.log("Actual Subject: " + savedSubject, true);
		Assert.assertTrue(savedSubject.contains(subjectname),"Subject name is not correct");
		Reporter.log("Test case Passed: Email was saved and subject verified successfully", true);
		Reporter.log("Step 11: Select the saved email", true);
		e.getSelectsubject().click();
		Reporter.log("Step 12: Click Delete to delete the saved email", true);
		e.getDeletesubject().click();
		Alert al=wait.until(ExpectedConditions.alertIsPresent());
		String deletemessage =al.getText();
		Reporter.log("Delete confirmation message: " + deletemessage, true);
		al.accept();
		Reporter.log("Delete confirmation alert accepted", true);
		Reporter.log("Step 13: Search again to verify email deletion", true);
		e.getSearchtext().sendKeys(subjectname);
		Select s4=new Select(e.getSearchfield());
		s4.selectByContainsVisibleText("in Subject");
		e.getFindbt().click();
		ExplicitWait.waitForElementToVisible(driver, e.getNorecordfound());
		String recordNotfound=e.getNorecordfound().getText();
		Reporter.log("Search result after deletion: " + recordNotfound, true);
		Reporter.log("Test case Passed: Email deletion verification completed", true);
	}
}
