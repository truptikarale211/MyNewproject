package com.base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.utility.PropertiesUtils;

public class BaseClass {

	public static WebDriver driver=null;
	 public static ExtentReports report=null;
	 public static ExtentSparkReporter spark;
	 public static ExtentTest test=null;
	
	
	
	public void initialization() {
		String browser=PropertiesUtils.readproperty("browser");
		
		if(browser.equalsIgnoreCase("chrome"));
		System.setProperty("webdriver.chrome.driver","chromedriver2.exe");
		driver=new ChromeDriver();
		
	
	driver.manage().window().maximize();	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	driver.get(PropertiesUtils.readproperty("url"));
	}
   public void reportInIt() {
	   report =new ExtentReports();
	   spark =new ExtentSparkReporter(System.getProperty("user.dir")+"/target/ExtentReport.html");
	   report.attachReporter(spark);
	   
   }
    public String CaptureScreenshot(String name) {
    	TakesScreenshot ts=(TakesScreenshot)driver;
    	File src=ts.getScreenshotAs(OutputType.FILE);
    	String path=System.getProperty("user.dir")+"/screenshots/"+name+".jpg";
    	File dest=new File(path);
    	try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
    return path;
    }
    
}



