package com.vtiger.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectedEmailPopupCompose {

	WebDriver driver;

	public SelectedEmailPopupCompose(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements( driver,this);
	}
	
	@FindBy(id = "parent_name")
	private WebElement to;
	
	@FindBy(id = "subject")
	private WebElement subject;
	
	@FindBy(xpath = "(//input[@class='crmbutton small edit'])[1]")
	private WebElement emailtemplate;
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement savebt;

	@FindBy(css = "[title='Select']")
	private WebElement addto;
	
	@FindBy(css = "[name='parent_type']")
	private WebElement selectType;
	
	public WebElement getTo() {
		return to;
	}

	public WebElement getSubject() {
		return subject;
	}

	public WebElement getEmailtemplate() {
		return emailtemplate;
	}

	public WebElement getSavebt() {
		return savebt;
	}

	public WebElement getAddto() {
		return addto;
	}

	public WebElement getSelectType() {
		return selectType;
	}
	
	
	
	
	
}
