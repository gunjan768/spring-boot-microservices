package com.gunjan768;

/**
 * In your production code in the editor, place the caret at the class for which you want to create a test, press
 * Alt+Enter, and select Create Test.
 *
 * In the editor, place the caret at the test class or at the test subject in the source code and press Ctrl+Shift+T
 * (Navigate | Test Subject or Navigate | Test ).
 *
 * For more info: https://www.jetbrains.com/help/idea/create-tests.html#test-code-navigation
 */

public class MathUtils {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        return a / b;
    }

    public double computeCircleArea(double radius) {
        return Math.PI * radius * radius;
    }
}
