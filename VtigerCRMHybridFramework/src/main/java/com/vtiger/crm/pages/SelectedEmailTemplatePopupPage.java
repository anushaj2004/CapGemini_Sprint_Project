package com.vtiger.crm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.WebDriverListenerException;

public class SelectedEmailTemplatePopupPage {

	WebDriver driver;

	public SelectedEmailTemplatePopupPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements( driver,this);
	}
	
	@FindBy(tagName = "body")
    private WebElement pageBody;

    public WebElement getTemplateByName(String templateName) {
        return pageBody.findElement(
            By.xpath(".//a[normalize-space()='" + templateName + "']")
        );
    }
	
	
	
}
