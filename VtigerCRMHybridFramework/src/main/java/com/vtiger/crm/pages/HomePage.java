package com.vtiger.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.crm.utilities.ExplicitWait;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath  = "//a[text()='Documents']")
	private WebElement documentlink;

	@FindBy(xpath  = "//a[text()='Products']")
	private WebElement productlink;
	
	@FindBy(xpath = "//a[text()='Opportunities']")
	private WebElement opplink;
	
	@FindBy(linkText = "Organizations")
	private WebElement organizationlink;
	
	@FindBy(xpath = "//a[text()='Email']")
	private WebElement emaillink;
	
	@FindBy(xpath = "//a[text()='More']")
	private WebElement moreoption;
	
	@FindBy(xpath = "(//td[@class='small'])[2]")
	private WebElement showsignout;
	
	@FindBy(xpath = "//a[text()='Sign Out']")
	private WebElement signout;
	
	public WebElement getDocumentlink() {
		return documentlink;
	}
	
	public WebElement getProductlink() {
		return ExplicitWait.waitForElementToVisible(driver, productlink);
	}

	public WebElement getOpplink() {
		return opplink;
	}

	public WebElement getOrganizationlink() {
		return organizationlink;
	}

	public WebElement getEmaillink() {
		return emaillink;
	}

	public WebElement getMoreoption() {
		return moreoption;
	}

	public WebElement getShowsignout() {
		return showsignout;
	}

	public WebElement getSignout() {
		return signout;
	}
	
}
