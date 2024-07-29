package com.qa.KeyWord.Engine;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.BaseClass.BaseClassKey;

public class KeyWord_Engine {

	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	
	public Properties p;
	public BaseClassKey b;
	public WebDriver driver;
	public WebElement ele;
	public String LocatorName;
	public String LocatorValue;
	public String Action;
	public String Value;

	public void startExecution(String sheetName) {
		 FileInputStream fis=null;
		try {
			fis = new FileInputStream(".//src/test/resources/key.xlsx");
		} catch (Exception e) {
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

			LocatorName = sheet.getRow(i + 1).getCell(j + 1).toString().trim();
			LocatorValue = sheet.getRow(i + 1).getCell(j + 2).toString().trim();
			Action = sheet.getRow(i + 1).getCell(j + 3).toString().trim();
			Value = sheet.getRow(i + 1).getCell(j + 4).toString().trim();

			switch (Action) {
			case "open browser":
				b = new BaseClassKey();
				b.initProp();
				if (Value.isEmpty() || Value.equalsIgnoreCase("NA")) {
					driver = b.setup(p.getProperty("browser"));
				} else {
					driver = b.setup(Value);
				}
				break;

			case "enter url":
				if (Value.isEmpty() || Value.equalsIgnoreCase("NA")) {
					driver.get(p.getProperty("url"));
				} else {
					driver.get(Value);
				}
				break;
			case "close":
				if (Value.equals("NA")) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}
					driver.close();
					break;
				}
			default:
				break;
			}
			switch (LocatorName) {
			case "name":
				ele = driver.findElement(By.name(LocatorValue));

				if (Action.equals("sendkeys")) {
					ele.sendKeys(Value);
				} else if (Action.equals("click")) {
					ele.click();
				}

				break;
			case "xpath":
				ele = driver.findElement(By.xpath(LocatorValue));
				ele.click();

				break;
			
			default:
				break;
			}

		}

	}

}
