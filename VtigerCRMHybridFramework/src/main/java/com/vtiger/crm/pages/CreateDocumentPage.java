package com.vtiger.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.crm.utilities.ExplicitWait;

public class CreateDocumentPage {
WebDriver driver;

public CreateDocumentPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements( driver,this);
	
}

@FindBy(css = "[name='notes_title']")
private WebElement title;

@FindBy(xpath = "(//input[@class='crmbutton small save'])[1]")
private WebElement savebt;

@FindBy(css = "[name='filename']")
private WebElement filepath;

@FindBy(css = "[name='assigned_user_id']")
private WebElement assignusers;

public WebElement getTitle() {
	return title;
}

public WebElement getSavebt() {
	return  savebt;
}

public WebElement getFilepath() {
	return filepath;
}

public WebElement getAssignusers() {
	return assignusers;
}





}
