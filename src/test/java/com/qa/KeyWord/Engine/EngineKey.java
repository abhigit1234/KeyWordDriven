package com.qa.KeyWord.Engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.BaseClass.BaseKey;

public class EngineKey {

	public FileInputStream fis;
	public Properties p;
	public WebDriver driver;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	public String LocatorName;
	public String LocatorValue;
	public String Action;
	public String Value;
	public BaseKey b;
	public WebElement element;
	public List<WebElement> elements;

	public void readExcel(String sheetName) {
		try {
			fis = new FileInputStream(".//src/test/resources/key.xlsx");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			wb = new XSSFWorkbook(fis);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		sheet = wb.getSheet(sheetName);
		int row = sheet.getLastRowNum();
		int j = 0;
		for (int i = 0; i < row; i++) {
			LocatorName = sheet.getRow(i + 1).getCell(j + 1).toString().trim();
			LocatorValue = sheet.getRow(i + 1).getCell(j + 2).toString().trim();
			Action = sheet.getRow(i + 1).getCell(j + 3).toString().trim();
			Value = sheet.getRow(i + 1).getCell(j + 4).toString().trim();

			switch (Action) {
			case "launch browser":
				b = new BaseKey();
				b.prop();
				if (Value.isEmpty() || Value.equals("NA")) {
					driver = b.setupBrowser(p.getProperty("browser"));
				} else if (Value.equals("chrome")) {
					driver = b.setupBrowser(Value);
				}
				break;
			case "enter url":
				if (Value.isEmpty() || Value.equals("NA")) {
					driver.get(p.getProperty("url"));
				} else {
					driver.get(Value);
				}				
				break;
			case "quit":
				 if (Action.equals("quit")) {	
				try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {}
					driver.quit();
				 }
			break;
			
			case "close":
			 if (Action.equals("close")) {
				 try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {}
				
				 driver.close();
			}
			case "getNumOfPages":
				if(Action.equals("getNumOfPages")) {
					String text =	element.getText();
				int num =	Integer.valueOf(text.substring(text.indexOf("(")+1, text.indexOf("Pages")-1));
				System.out.println(num);
				}
					
				break;
			default:
				break;
			}

			switch (LocatorName) {
			case "id":
				element = driver.findElement(By.id(LocatorValue));
				if (Action.equals("sendkeys")) {
					element.sendKeys(Value);
				} else if (Action.equals("click")) {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {}
					element.click();
				} else if (Action.equals("isDisplayed")) {
					System.out.println(element.isDisplayed());
				}else if (Action.equals("getText")) {
					System.out.println(element.getText());
				}else if (Action.equals("getTitle")) {
					System.out.println(driver.getTitle());
				}
				
					
				break;
				
			case "name":
				element = driver.findElement(By.name(LocatorValue));
				if (Action.equals("sendkeys")) {
					element.sendKeys(Value);
				} else if (Action.equals("click")) {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {}
					element.click();
				} else if (Action.equals("isDisplayed")) {
					System.out.println(element.isDisplayed());
				}else if (Action.equals("getText")) {
					System.out.println(element.getText());
				}else if (Action.equals("getTitle")) {
					System.out.println(driver.getTitle());
				}
				
				break;
			case "xpath":
				element = driver.findElement(By.xpath(LocatorValue));
				if (Action.equals("sendkeys")) {
					element.sendKeys(Value);
				} else if (Action.equals("click")) {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {}
					element.click();
				} else if (Action.equals("isDisplayed")) {
					System.out.println(element.isDisplayed());
				}else if (Action.equals("getText")) {
					System.out.println(element.getText());
				}else if (Action.equals("getTitle")) {
					System.out.println(driver.getTitle());
				}
			
				elements = driver.findElements(By.xpath(LocatorValue));
				for (WebElement list : elements) {
					System.out.println(list.getText());
					if (list.equals(Value)) {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {}
						list.click();
					}else if (Action.equals("getTitle")) {
						System.out.println(driver.getTitle());
					}
				}
				break;
			default:
				break;
			}

		}

	}

}
