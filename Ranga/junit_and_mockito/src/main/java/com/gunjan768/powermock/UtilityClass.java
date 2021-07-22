package com.gunjan768.powermock;

public class UtilityClass {
	static int staticMethod(int value) {
		// Some complex logic is done here...
		throw new RuntimeException("I dont want to be executed. I will anyway be mocked out.");
	}
}
