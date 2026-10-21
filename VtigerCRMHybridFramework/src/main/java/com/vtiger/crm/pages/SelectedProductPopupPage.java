package com.vtiger.crm.pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectedProductPopupPage {

	WebDriver driver;

	public SelectedProductPopupPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id = "search_txt")
	private WebElement productNameSearch;

	@FindBy(css = "[name='search_field']")
	private WebElement productType;
	
	@FindBy(css = "[name='search']")
	private WebElement searchbt;

	public WebElement getProductNameSearch() {
		return productNameSearch;
	}

	public WebElement getProductType() {
		return productType;
	}

	public WebElement getSearchbt() {
		return searchbt;
	}
	
	public void selectProductByName(String productName) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='" + productName + "']"))).click();
	}
	
	
}
