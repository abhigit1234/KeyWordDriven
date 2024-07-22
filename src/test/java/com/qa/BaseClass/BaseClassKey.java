package com.qa.BaseClass;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;

public class BaseClassKey {

	public  WebDriver driver;
	public Properties p;

	public  WebDriver setup(String browser) {
		if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public Properties initProp() {
		try {
			FileInputStream fis = new FileInputStream(".//src/test/resources/config.properties");
			p = new Properties();
			p.load(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return p;
	}
}
