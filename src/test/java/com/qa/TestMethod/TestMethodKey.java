package com.qa.TestMethod;

import org.testng.annotations.Test;

import com.qa.KeyWord.Engine.EngineKey;

public class TestMethodKey {

	@Test
	public void startEngine() {
		
		EngineKey ek = new EngineKey();
		ek.readExcel("TestCase");
	}
	
}
