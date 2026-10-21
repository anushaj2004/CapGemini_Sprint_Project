package com.vtiger.crm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmailPage {

	WebDriver driver;

	public EmailPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//a[text()='Compose']")
	private WebElement compose;
	
	@FindBy(id = "search_text")
	private WebElement searchtext;

	@FindBy(id = "search_field")
	private WebElement searchfield;
	
	@FindBy(css = "[name='find']")
	private WebElement findbt;
	
	@FindBy(xpath = "//td[@style='cursor:pointer;']/child::b")
	private WebElement SavedSubject;
	
	@FindBy(xpath = "//a[text()='To Organizations']")
	private WebElement searchOrg;
	
	@FindBy(css = "[name='selected_id']")
	private WebElement selectsubject;

	@FindBy(css = "[value=' Delete']")
	private WebElement deletesubject;
	
	@FindBy(xpath = "//b[text()='No Records Found']")
	private WebElement norecordfound;
	
	public WebElement getSearchtext() {
		return searchtext;
	}

	public WebElement getSearchfield() {
		return searchfield;
	}

	public WebElement getFindbt() {
		return findbt;
	}

	public WebElement getCompose() {
		return compose;
	}

	public WebElement getSavedSubject() {
		return SavedSubject;
	}

	public WebElement getSearchOrg() {
		return searchOrg;
	}
	
	public WebElement getSelectsubject() {
		return selectsubject;
	}

	public WebElement getDeletesubject() {
		return deletesubject;
	}

	public WebElement getNorecordfound() {
		return norecordfound;
	}
	
	
	
	
	
}
