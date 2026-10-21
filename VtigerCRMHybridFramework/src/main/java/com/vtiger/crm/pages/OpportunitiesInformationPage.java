package com.vtiger.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.crm.utilities.ExplicitWait;

public class OpportunitiesInformationPage {

	WebDriver driver;

	public OpportunitiesInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name = "potentialname")
	private WebElement oppName;
	
	@FindBy(name = "sales_stage")
	private WebElement salesstage;
	
	@FindBy(name = "closingdate")
	private WebElement closingdate;
	
	@FindBy(xpath = "(//input[@type='submit'])[2]")
	private WebElement savebt;

	@FindBy(xpath = "//input[@name='related_to']")
	private WebElement organizationField;

	@FindBy(xpath = "//img[@alt='Select']")
	private WebElement organizationLookup;
	
	@FindBy(linkText = "More Information")
	private WebElement  moreinfo;
	
	public WebElement getOppName() {
		return ExplicitWait.waitForElementToVisible(driver, oppName);
	}

	public WebElement getSalesstage() {
		return salesstage;
	}

	public WebElement getClosingdate() {
		return ExplicitWait.waitForElementToVisible(driver, closingdate);
	}

	public WebElement getSavebt() {
		return ExplicitWait.waitForElementToVisible(driver, savebt );
	}
	
	public WebElement getOrganizationField() {
	    return organizationField;
	}

	public WebElement getOrganizationLookup() {
	    return organizationLookup;
	}

	public WebElement getMoreinfo() {
		return moreinfo;
	}
	
	
}
