package com.vtiger.crm.pages;

import java.awt.print.PageFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SelectedEmailToPopupPage {

	WebDriver driver;

	public SelectedEmailToPopupPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	    @FindBy(xpath = "//input[@name='search_text']")
	    private WebElement searchField;

	    @FindBy(css = "[name='search_field']")
	    private WebElement searchType;

	    @FindBy(xpath = "//input[@type='button']")
	    private WebElement searchButton;

	    public WebElement getSearchField() {
	        return searchField;
	    }

	    public WebElement getSearchType() {
	        return searchType;
	    }

	    public WebElement getSearchButton() {
	        return searchButton;
	    }

	    public WebElement getContactByName(String contactName) {
	        return driver.findElement(By.xpath("//a[normalize-space()='" + contactName + "']"));
	    }
	  
	
}
