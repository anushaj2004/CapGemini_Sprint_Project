package com.vtiger.crm.email;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

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
import com.vtiger.crm.pages.SelectedEmailTemplatePopupPage;
import com.vtiger.crm.pages.SelectedEmailToPopupPage;
import com.vtiger.crm.utilities.ExcelUtility;
import com.vtiger.crm.utilities.ExplicitWait;

public class TC_Email_Functional extends BaseClass{
	
	@DataProvider(name="saveEmailDetails")
	public Object[][] saveEmailDetails() throws IOException {
		ExcelUtility e=new ExcelUtility();
		return e.getMultipleData("Sheet6");
	}
	@Test(dataProvider = "saveEmailDetails",groups = {"functional","positive"})
	public void verifyEmailCompose(String emailname,String subjectname) throws InterruptedException {
		Reporter.log("Step 1: Navigate to the home page and click on Email", true);
		HomePage h=new HomePage(driver);
		h.getEmaillink().click();
		Reporter.log("Step 2: Click on Compose email", true);
		EmailPage e=new EmailPage(driver);
		String emailWindow = driver.getWindowHandle();
		e.getCompose().click();
		Reporter.log("Step 3: Wait for the Compose window to open", true);
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
		Reporter.log("Step 6: Select the contact", true);
		sto.getContactByName(emailname).click();
		// Return to the compose window
		driver.switchTo().window(composeWindow);
		Reporter.log("Step 7: Open the Email Templates popup", true);
		Set<String> oldWindows2 = driver.getWindowHandles();
		sc.getEmailtemplate().click();
		for (String window : driver.getWindowHandles()) {
		    driver.switchTo().window(window);

		    if (driver.getPageSource().contains("Email Templates")) {
		        break;
		    }
		}
		Reporter.log("Step 8: Select email template: " + subjectname, true);
		SelectedEmailTemplatePopupPage st =new SelectedEmailTemplatePopupPage(driver);
		st.getTemplateByName(subjectname).click();
		// Return to Compose window
		driver.switchTo().window(composeWindow);
		Reporter.log("Step 9: Save the composed email", true);
		sc.getSavebt().click();
		Reporter.log("Step 10: Search for the saved email by subject", true);
		driver.switchTo().window(emailWindow);
		e.getSearchtext().sendKeys(subjectname);
		Select s3=new Select(e.getSearchfield());
		s3.selectByContainsVisibleText("in Subject");
		e.getFindbt().click();
		Thread.sleep(2000);
		Reporter.log("Step 11: Verify the saved email subject", true);
		String savedSubject=e.getSavedSubject().getText();
		Reporter.log("Expected Subject: " + subjectname, true);
		Reporter.log("Actual Subject: " + savedSubject, true);
		Assert.assertTrue(savedSubject.contains(subjectname),"Subject name is not correct");
		Reporter.log("Test case Passed: Email was saved and the subject was verified successfully", true);
	}
}
