package com.vtiger.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorMoreInformationPage {

	WebDriver driver;

	public VendorMoreInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id = "show_Vendors_Products")
	private WebElement showVendorProduct;
	
	@FindBy(css = "[title='Add Product']")
	private WebElement addProduct;

	public WebElement getShowVendorProduct() {
		return showVendorProduct;
	}

	public WebElement getAddProduct() {
		return addProduct;
	}
	
	
}
