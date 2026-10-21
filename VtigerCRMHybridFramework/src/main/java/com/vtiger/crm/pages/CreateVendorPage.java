package com.vtiger.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateVendorPage {
	WebDriver driver;

	public CreateVendorPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css = "[name='vendorname']")
	private WebElement vendorName;
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement savebt;
	
	@FindBy(linkText = "More Information")
	private WebElement moreinfo;
	
	public WebElement getVendorName() {
		return vendorName;
	}

	public WebElement getSavebt() {
		return savebt;
	}

	public WebElement getMoreinfo() {
		return moreinfo;
	}
	
	
	
	
	

}
