package com.vtiger.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectedOrganizationPopupPage {

	WebDriver driver;

	public SelectedOrganizationPopupPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id = "search_txt")
	private WebElement searchOrg;
	
	@FindBy(name = "search_field")
	private WebElement searchOrgType;
	
	@FindBy(name = "search")
	private WebElement searchbt;
	
	 // Example: first organization link in the results
    @FindBy(xpath = "//a[@href='javascript:window.close();']")
    private WebElement organizationSelect;

	public WebElement getSearchOrg() {
		return searchOrg;
	}

	public WebElement getSearchOrgType() {
		return searchOrgType;
	}

	public WebElement getSearchbt() {
		return searchbt;
	}

	public WebElement getOrganizationSelect() {
		return organizationSelect;
	}
	
}
