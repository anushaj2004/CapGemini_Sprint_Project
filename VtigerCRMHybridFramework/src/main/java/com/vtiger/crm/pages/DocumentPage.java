package com.vtiger.crm.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentPage {
WebDriver driver;

public DocumentPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver,this);
}

@FindBy(css = "[title='Create Document...']")
private WebElement createdoc;

@FindBy(id = "bas_searchfield")
private WebElement searchdoc;

@FindBy(css = "[name='search_text']")
private WebElement searchtext;

@FindBy(xpath = "(//input[@value=\" Search Now \"])[1]")
private WebElement searchbt;

@FindBy(xpath = "//a[@title='Documents']")
private WebElement title;

@FindBy(xpath = "//a[@title='Download file']")
private WebElement file;

@FindBy(xpath = "//span[contains(text(),'No Document Found')]")
private WebElement nodocfound;

public WebElement getCreatedoc() {
	return createdoc;
}

public WebElement getSearchdoc() {
	return searchdoc;
}

public WebElement getSearchtext() {
	return searchtext;
}

public WebElement getSearchbt() {
	return searchbt;
}

public WebElement getTitle() {
	return title;
}

public WebElement getFile() {
	return file;
}

public WebElement getNodocfound() {
	return nodocfound;
}






}
