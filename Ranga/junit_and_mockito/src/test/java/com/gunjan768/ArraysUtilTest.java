package com.gunjan768;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.Duration;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArraysUtilTest {

    @Test
    @Order(1)
    public void arraySort() {
        int[] a = {12, 3, 4, 1};
        int[] expected = {1, 3, 4, 12};
        Arrays.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    @Order(3)
    public void arraySortNullException() {
        int[] a = null;

        assertThrows(
                NullPointerException.class,
                () -> {
                    Arrays.sort(a);
                },
                "Sorting null array"
        );
    }

    @Test
    @Order(2)
    public void arraySortPerformance() {
        assertTimeout(Duration.ofMinutes(2), () -> {
            // Perform task that takes less than 2 minutes.
            int[] a = {21, 12, 17, 4};

            for (int i = 1; i <= 100000; ++i) {
                a[0] = i;
                Arrays.sort(a);
            }
        });
    }

    @Test
    @Order(4)
    void timeoutExceeded()
    {
        // The following assertion fails with an error message similar to:
        // execution exceeded timeout of 10 ms by 91 ms
        assertTimeout(Duration.ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            int[] a = {21, 12, 17, 4};

            for (int i = 1; i <= 100000; ++i) {
                a[0] = i;
                Arrays.sort(a);
            }
        });
    }
}
