package exception;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class ExceptionHandlingExample {

    public static void main(String[] args) {

        int[] num = {1, 2, 0, 4, 5};
        int key = 2;

//        process(num, key, new BiConsumer<Integer, Integer>() {
//            @Override
//            public void accept(Integer i, Integer j) {
//                System.out.print(i + j + " ");
//            }
//        });

        // Handling exception using try and catch block but it will make our lambda from one liner to cumbersome.
//        process(num, key, (i, j) -> {
//            try {
//                System.out.print(j/i + " ");
//            } catch (ArithmeticException e) {
//                System.out.println("ArithmeticException : " + e);
//            }
//        });

        // We used wrapperLambda() method which will wrap the Lambda and let it be liner only.
        process(num, key, wrapperLambda((i, j) -> System.out.println(j/i + " ")));
    }

    private static void process(int[] num, int key, BiConsumer<Integer, Integer> consumer) {
        System.out.println("Inside the process method");

        for(int it: num) {
            consumer.accept(it, key);
        }
    }

    private static BiConsumer<Integer, Integer> wrapperLambda(BiConsumer<Integer, Integer> consumer) {
        System.out.println("Inside the wrapperLambda");
        return (i, j) -> {
            try {
                System.out.println("Value: " + i + ", Key: " + j);
                consumer.accept(i, j);
            } catch (ArithmeticException e) {
                System.out.println("Exception caught in the wrapper lambda : " + e);
            }
        };
    }
}