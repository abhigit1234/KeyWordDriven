package com.qa.KeyWord.Engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.BaseClass.BaseClassKey;

public class KeyWord_Engine{

	public WebDriver driver;
	public Properties p;
	
	public  XSSFWorkbook wb;
	public  XSSFSheet sheet;
	public BaseClassKey base;
	public WebElement ele;
	public String locator;

	public void startExecution(String sheetName) {
	
		FileInputStream fis=null;
		try {
			fis = new FileInputStream("C:\\Users\\abhil\\OneDrive\\Desktop\\key.xlsx");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			
		}
		try {
			wb = new XSSFWorkbook(fis);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			
		}
		sheet = wb.getSheet(sheetName);
		int row = sheet.getRow(0).getLastCellNum();
		int j = 0;
		for (int i = 0; i < row; i++) {
			try {
				String locatorName = null;
				String locatorVal=null;
				
			locator = sheet.getRow(i + 1).getCell(j + 1).toString();
			if (!locator.equalsIgnoreCase("NA")) {
				locatorName = locator.split("=")[0];
				locatorVal = locator.split("=")[1];
			}
			String Action = sheet.getRow(i + 1).getCell(j + 2).toString().trim();
			String Value = sheet.getRow(i + 1).getCell(j + 3).toString().trim();

			switch (Action) {

			case "open browser":
				

				base = new BaseClassKey();
				p = base.initProp();
								
				if (Value.isEmpty() || Value.equals("NA")) {
					driver =	base.setup(p.getProperty("browser"));
				} else {
					driver = base.setup(Value);
				}
				break;
			case "enter url":driver.get(Value);break;
			case "close":driver.close();default:
				
			}
			switch (locatorName ) {
			case "name":
				ele = driver.findElement(By.name(locatorVal));
				ele.clear();
				ele.sendKeys(Value);
				if (Action.equals("sendkeys")) {
					ele.clear();
					ele.sendKeys(Value);
				} else if (Action.equals("click")) {
					Thread.sleep(2000);
					ele.click();
				}
				break;
			case "xpath":
				ele = driver.findElement(By.xpath(locatorVal));
				ele.click();
				break;
			default:break;
			}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			}

		
	}
		
}
