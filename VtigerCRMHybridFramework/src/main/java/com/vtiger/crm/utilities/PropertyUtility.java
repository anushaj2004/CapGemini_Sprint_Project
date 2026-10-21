package com.vtiger.crm.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyUtility {
	//method to read Data from properties file
	public String readDatafromPropertiesFile(String key) throws Exception {
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData/Vtiger.properties");
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String readDatafromPropertiesFileForInvalid(String key) throws Exception {
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData/vtigerLogin.properties");
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}
}