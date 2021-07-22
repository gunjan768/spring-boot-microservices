package com.gunjan768.powermock;

import java.util.ArrayList;
import java.util.List;

// In static method, we need to give the class, which contains the static method but for constructor, we need to
// give class which is overwriting the constructor or making use of constructor.

public class SystemUnderTest {
	private final Dependency dependency;

	SystemUnderTest(Dependency dependency) {
		this.dependency = dependency;
	}

	public int methodUsingAnArrayListConstructor() {
		var list = new ArrayList();
		return list.size();
	}

	public int methodCallingAStaticMethod() {
		//privateMethodUnderTest calls static method SomeClass.staticMethod
		List<Integer> stats = dependency.retrieveAllStats();
		int sum = 0;
		for (int stat : stats) {
			sum += stat;
		}

		return UtilityClass.staticMethod(sum);
	}

	private long privateMethodUnderTest() {
		List<Integer> stats = dependency.retrieveAllStats();
		long sum = 0;
		for (int stat : stats)
			sum += stat;
		return sum;
	}
}
