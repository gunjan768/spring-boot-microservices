package com.gunjan768;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

/**
 * Everytime Junit runs a test, it creates a new instance of the class i.e. Junit creates new instance for
 * every method it runs. It does so to keep every test independent of each other. This is the default behaviour
 * (TestInstance.Lifecycle.PER_METHOD). We can have one instance only for all methods by changing the default
 * behaviour of Junit5 (TestInstance.Lifecycle.PER_CLASS).
 */

/**
 * Some annotations of Junit5: https://devqa.io/junit-5-annotations/
 */

/**
 * Conditional Execution annotations
 * . @EnabledOnOs(), @EnabledOnJre(), @EnabledIf, @EnabledIfSystemProperty, @EnabledIfEnvironmentVariable
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Running MathUtilsTest")
//@Tag("Top class") // Can declare tag on the top most level class
class MathUtilsTest {
    private MathUtils mathUtils;
    private TestInfo testInfo;
    private TestReporter testReporter;

    /**
     * @BeforeAll annotated method should be static as it will run even before the instance creation (static is
     * mandatory for @TestInstance(TestInstance.Lifecycle.PER_METHOD)). But if we change this default behaviour to
     * TestInstance.Lifecycle.PER_CLASS, then static becomes optional because now there will be only one instance
     * and Junit says that it can run the @BeforeAll annotated method in the starting.
     */
    @BeforeAll
    static void beforeAllInit() {
        System.out.println("Runs before all (at the very start)");
    }

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter) {
        this.testInfo = testInfo;
        this.testReporter = testReporter;

        testReporter.publishEntry(
                "Before Each .... !!!! " +
                "MethodName: " + testInfo.getDisplayName() +
                " Class : " + testInfo.getClass() +
                " Tags : " + testInfo.getTags()
        );

        mathUtils = new MathUtils();
    }

    @AfterEach
    void cleanup() {
        System.out.println("Cleaning up...");
    }

    @Test
    @DisplayName("Testing add method")
    @Tag("Math")
    void add() {
        System.out.println("MethodName: " + testInfo.getDisplayName() + " " + testInfo.getTags());
        int expected = 2;
        int actual = mathUtils.add(1, 1);

        assertEquals(expected, actual, "Add methods should add two numbers") ;
    }

    @Nested     // Used for grouping similar test cases
    @DisplayName("Subtract test class")
    @Tag("Math")    // Will allow to run the specified test according to the Tag (need some configuration, see docs)
    class SubtractTest {
        @Test
        @DisplayName("Subtract Positive")
        @Tag("Baby")
        void subtractPositive() {
            System.out.println(
                    "MethodName: " + testInfo.getDisplayName() +
                     " Class : " + testInfo.getClass() +
                     " Tags : " + testInfo.getTags()
            );

            assertEquals(0, mathUtils.subtract(2, 2), "assuming 0");
        }

        @Tag("Math")
        @Test
        @DisplayName("Subtract Negative")
        void subtractNegative() {
            assertEquals(4, mathUtils.subtract(6, 2), "assuming 4");
        }

        @Tag("Math")
        @Test
        @DisplayName("Subtract Negative")
        void subtractAnyType() {
            // Using Lambda as third argument. Junit will run the lambda only when test fails.
            assertEquals(8, mathUtils.subtract(24, 16), () -> "assuming 4");
        }
    }

    @Tag("Math")
    @Test
    void multiply() {
//        assertEquals(4, mathUtils.multiply(2, 2), "Add methods should add two numbers");

        // You will get the timestamp also in the log part
        testReporter.publishEntry(
                "MethodName: " + testInfo.getDisplayName() +
                " Class : " + testInfo.getClass() +
                " Tags : " + testInfo.getTags()
        );

        // To write multiple assertion cases
        assertAll(
                () -> assertEquals(4, mathUtils.multiply(2, 2), "assuming 4"),
                () -> assertEquals(0, mathUtils.multiply(0, 2), "assuming 0"),
                () -> assertEquals(2, mathUtils.multiply(1, 2), "assuming 1")
        );
    }

    @Test
    void divide() {
        final var assumption = !(Math.random() <= 0.5);
        /**
         * If assumeTrue() accepts false, then this test will not run
         */
        assumeTrue(assumption);
        assertThrows(
                ArithmeticException.class,
                () -> mathUtils.divide(1, 0),
                "Divide by zero should throw an exception"
        );
    }

    // WIll repeat the test 3 times. Using @RepeatedTest(x), you can accept 'RepetitionInfo' instance as a parameter
    // which will be passed by Junit5 (DI)
    @RepeatedTest(3)
    @Tag("Circle")
    void computeCircleArea(RepetitionInfo repetitionInfo) {
        //System.out.println(repetitionInfo.getCurrentRepetition() + " " + repetitionInfo.getTotalRepetitions());
        if (repetitionInfo.getCurrentRepetition() <= 2) {
            assertEquals(
                    Math.PI * 100,
                    mathUtils.computeCircleArea(10),
                    "Should return area of the circle"
            );
        }
    }

    @Test
    @Disabled   // Will skip this test
    @DisplayName("Seeing the functionality of @Disabled annotation")
    void testDisabled() {
        fail("This test will fail for sure");
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    @DisplayName("@EnabledOnOs annotation")
    void testEnabledOnlyForLinux() {
        fail("This test will fail for sure");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    @DisplayName("@EnabledOnJre annotation")
    void testEnabledOnlyForCertainJRE() {
        fail("This test will fail for sure");
    }
}