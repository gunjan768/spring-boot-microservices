package com.java.method_reference;

public class MethodReferenceExample {

    public static void printMessage() {
        System.out.println("Hello");
    }

    public static void main(String[] args) {


//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                printMessage();
//            }
//        });

        // Thread thread = new Thread(() -> printMessage());

        Thread thread = new Thread(MethodReferenceExample::printMessage);
        thread.start();
    }
}
