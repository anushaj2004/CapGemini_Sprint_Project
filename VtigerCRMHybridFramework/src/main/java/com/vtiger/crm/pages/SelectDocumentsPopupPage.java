package com.vtiger.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectDocumentsPopupPage {
	
	WebDriver driver;

	public SelectDocumentsPopupPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(id = "search_txt")
	private WebElement searchtext;
	
	@FindBy(xpath = "//select[@class='txtBox']")
	private WebElement searchDoc;
	
	@FindBy(css = "[name='search']")
	private WebElement searchbt;

	@FindBy(xpath = "//span[text()='No Documents Found !']")
	private WebElement nodocpresent;
	
	public WebElement getSearchtext() {
		return searchtext;
	}

	public WebElement getSearchDoc() {
		return searchDoc;
	}

	public WebElement getSearchbt() {
		return searchbt;
	}

	public WebElement getNodocpresent() {
		return nodocpresent;
	}
	
	
}
