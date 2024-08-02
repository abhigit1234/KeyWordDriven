package com.qa.BaseClass;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.math3.geometry.spherical.twod.Edge;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseKey {

	public WebDriver driver;
	public FileInputStream fis;
	public Properties p;
	
	public WebDriver setupBrowser(String browser) {
		if(browser.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(browser.equals("edge")) {
			driver=new EdgeDriver();
		}else if(browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public Properties prop() {
		try {
			fis = new FileInputStream(".//src/test/resources/config.properties");
			p = new Properties();
			p.load(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	return p;
	}
	
	
}
