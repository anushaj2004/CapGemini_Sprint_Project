package com.vtiger.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MoreOptionPage {

	WebDriver driver;

	public MoreOptionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText = "Vendors")
	private WebElement vendorslink;

	public WebElement getVendorslink() {
		return vendorslink;
	}
	
	
}
