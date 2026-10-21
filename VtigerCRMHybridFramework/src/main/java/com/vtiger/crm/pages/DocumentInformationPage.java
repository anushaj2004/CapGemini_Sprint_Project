package com.vtiger.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentInformationPage {
WebDriver driver;

public DocumentInformationPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver,this);
}

@FindBy(id = "dtlview_Title")
private WebElement title;

@FindBy(xpath = "(//input[@name='Delete'])[1]")
private WebElement deletedoc;

public WebElement getTitle() {
	return title;
}

public WebElement getDeletedoc() {
	return deletedoc;
}





}
