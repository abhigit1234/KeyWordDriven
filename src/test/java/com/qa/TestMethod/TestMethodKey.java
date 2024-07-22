package com.qa.TestMethod;

import java.io.IOException;

import org.testng.annotations.Test;

import com.qa.KeyWord.Engine.KeyWord_Engine;

public class TestMethodKey {
 public KeyWord_Engine key;
	
	@Test
	public  void test(){
		key = new KeyWord_Engine();
		key.startExecution("Sheet");
	}
}
