package com.vtiger.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.crm.utilities.ExplicitWait;

public class OpportunitiesPage {

	WebDriver driver;

	public OpportunitiesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css = "[title='Create Opportunity...']")
	private WebElement createOpp;
	
	 @FindBy(name = "search_text")
	 private WebElement opportunitynamesearch;
	 
	 @FindBy(id = "bas_searchfield")
	 private WebElement oppType;

	 @FindBy(xpath =  "(//input[@value=' Search Now '])[1]")
	 private WebElement searchbt;

	 @FindBy(xpath = "//a[@title='Opportunities']")
	 private WebElement opportunitylist;

	 public WebElement getCreateOpp() {
		 return createOpp;
		}
	    
	 public WebElement getOpportunitynamesearch() {
	     return opportunitynamesearch;
	    }

	 public WebElement getOppType() {
		return oppType;
	}

	 public WebElement getSearchbt() {
	     return ExplicitWait.waitForElementToVisible(driver, searchbt);
	    }

	 public WebElement getOpportunitylist() {
	     return opportunitylist;
	    }
	
	
}
