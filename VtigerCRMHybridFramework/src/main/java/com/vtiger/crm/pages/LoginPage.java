package com.vtiger.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
 //Declare WebDriver reference to control the browser
  WebDriver driver;
 //Constructor to initialize the WebDriver and page elements
  public LoginPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver,this);
  }
  //Locate the username field
  @FindBy(css = "[name='user_name']")
  private WebElement username;
  
  //Locate the password field
  
  @FindBy(css = "[name='user_password']")
  private WebElement password;
  
  //Locate the Login Button 
  
  @FindBy(id = "submitButton")
  private WebElement loginbt;
  
  @FindBy(css = "[class='errorMessage']")
  private WebElement errormessage;
  
  //Method to enter LoginDetails
  public void loginDetails(String un,String pw) {
	  username.sendKeys(un);
	  password.sendKeys(pw);
	  loginbt.click();
  }

  public WebElement getErrormessage() {
	return errormessage;
  }
  
  
  
}
