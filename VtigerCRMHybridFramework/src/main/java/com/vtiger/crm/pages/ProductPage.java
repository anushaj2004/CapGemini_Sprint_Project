package com.vtiger.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.crm.utilities.ExplicitWait;

public class ProductPage {
WebDriver driver;

public ProductPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements( driver,this);
}

@FindBy(css = "[title='Create Product...']")
private WebElement createProduct;

@FindBy(css = "[name='search_text']")
private WebElement productnamesearch;

@FindBy(id = "bas_searchfield")
private WebElement producttype;

@FindBy(xpath = "(//input[@value=\" Search Now \"])[1]")
private WebElement searchbt;

@FindBy(xpath = "(//a[@title='Products'])[1]")
private WebElement productlist;

@FindBy(id = "viewname")
private WebElement filter;

public WebElement getCreateProduct() {
	return createProduct;
}

public WebElement getProductnamesearch() {
	return productnamesearch;
}

public WebElement getProducttype() {
	return ExplicitWait.waitForElementToVisible(driver, producttype) ;
}

public WebElement getSearchbt() {
	return searchbt;
}

public WebElement getProductlist() {
	return productlist;
}

public WebElement getFilter() {
	return ExplicitWait.waitForElementToVisible(driver, filter) ;
}





}
