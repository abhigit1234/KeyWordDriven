package com.qa.TestMethod;

import org.testng.annotations.Test;

import com.qa.KeyWord.Engine.EngineKey;

public class TestMethodKey {

	@Test(priority = 1)
	public void startEngine1() {
		
		EngineKey ek = new EngineKey();
		ek.readExcel("TestCase1");
	}
	

	@Test(priority = 2)
	public void startEngine2() {
		
		EngineKey ek = new EngineKey();
		ek.readExcel("TestCase2");
	}
	
}
