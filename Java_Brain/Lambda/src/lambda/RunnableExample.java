package lambda;

public class RunnableExample {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class");
            }
        });

        // thread.run(): run() is replaced by start();
        thread.start();

        // Since Runnable has single abstract method so we can Lambda
        Thread thread1 = new Thread(() -> System.out.println("Inside Lambda"));
        thread1.start();
    }
}
