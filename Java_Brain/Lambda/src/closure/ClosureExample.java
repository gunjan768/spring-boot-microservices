package closure;

interface Process {
    void process(int i);
}

public class ClosureExample {
    public static void main(String[] args) {

        int a = 10, b = 20;

        doProcess(a, new Process() {
            @Override
            public void process(int i) {

                // You can't change the value of the variable defined outside process() scope. Here 'b' can't be changed. Before Java 8 'b' needs
                // to be final but since Java 8 it is like Java trusts us that we will not change the value so need to be final, but if we try
                // to change the value of 'b', Java compiler will yell.
                System.out.println(i + b);
            }
        });

        // Error: java: local variables referenced from an inner class must be final or effectively final. You can't change the value of 'b'.
        // b = 5;

        // Lambda will take the frozen value of 'b' using the concept of closure property.
        doProcess(a, i -> System.out.println(i + b));
    }

    public static void doProcess(int i, Process p) {
        p.process(i);
    }
}
