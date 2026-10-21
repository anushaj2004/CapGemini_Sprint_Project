package com.vtiger.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInformationPage {
WebDriver driver;

public ProductInformationPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver,this);
}

@FindBy(css = "[name='productname']")
private WebElement productname;

@FindBy(xpath = "(//input[@type='submit'])[1]")
private WebElement savebt;

@FindBy(linkText = "More Information")
private WebElement moreinfo;

@FindBy(id = "productcode")
private WebElement cartNo;

@FindBy(id = "unit_price")
private WebElement unitPrice;

public WebElement getProductname() {
	return productname;
}

public WebElement getSavebt() {
	return savebt;
}

public WebElement getMoreinfo() {
	return moreinfo;
}

public WebElement getCartNo() {
	return cartNo;
}

public WebElement getUnitPrice() {
	unitPrice.clear();
	return unitPrice;
}





}
