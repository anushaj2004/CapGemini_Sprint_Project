package com.vtiger.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorPage {
	WebDriver driver;

	public VendorPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements( driver,this);
	}
	
	@FindBy(css = "[title='Create Vendor...']")
	private WebElement createvendor;

	public WebElement getCreatevendor() {
		return createvendor;
	}

	
	
	
	
}
