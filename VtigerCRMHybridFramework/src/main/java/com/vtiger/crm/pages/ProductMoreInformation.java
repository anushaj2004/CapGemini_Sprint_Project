package com.vtiger.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductMoreInformation {
WebDriver driver;

public ProductMoreInformation(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver,this);
}

@FindBy(css = "[title='Add Document']")
private WebElement addDoc;

@FindBy(id = "show_Products_Documents")
private WebElement showproductdoc;

@FindBy(xpath = "//a[text()='del']")
private WebElement deldoc;

@FindBy(css = "[title='Select Documents']")
private WebElement seldoc;

public WebElement getAddDoc() {
	return addDoc;
}

public WebElement getShowproductdoc() {
	return showproductdoc;
}

public WebElement getDeldoc() {
	return deldoc;
}

public WebElement getSeldoc() {
	return seldoc;
}




}
