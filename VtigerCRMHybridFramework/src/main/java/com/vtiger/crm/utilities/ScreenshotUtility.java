package com.vtiger.crm.utilities;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ScreenshotUtility {
	public static void takeScreenshot(WebDriver driver,String fileName) throws IOException {
		TakesScreenshot ts= (TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File des=new File("./Screenshots/"+fileName+".png");
		FileHandler.copy(src, des);
	}

//    // New method for capturing the alert pop-up
//    public static void takeAlertScreenshot(String fileName)
//            throws Exception {
//        Robot robot = new Robot();
//
//        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//
//        BufferedImage screenshot = robot.createScreenCapture(screenRect);
//
//        File destination =
//            new File("./Screenshots/" + fileName + ".png");
//
//        destination.getParentFile().mkdirs();
//
//        ImageIO.write(screenshot, "png", destination);
//    }


}
