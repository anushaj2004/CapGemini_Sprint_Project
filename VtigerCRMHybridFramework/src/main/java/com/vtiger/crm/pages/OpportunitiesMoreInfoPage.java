package com.vtiger.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunitiesMoreInfoPage {

	WebDriver driver;

	public OpportunitiesMoreInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id = "show_Potentials_Products")
	private WebElement showProduct;
	
	@FindBy(css = "[title='Select Products']")
	private WebElement selectProduct;

	public WebElement getShowProduct() {
		return showProduct;
	}

	public WebElement getSelectProduct() {
		return selectProduct;
	}
	
	
	
	
}
