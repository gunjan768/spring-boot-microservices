package closure;

public class ThisReferenceExample {

    public void doProcess(int i, Process p) {
        System.out.println(this);
        p.process(i);
    }

    public void execute() {
        doProcess(10, i -> {
            // 'this' points to an instance on which execute() method is called (execute() is an instance method)
            System.out.println(this);
            System.out.println("Value of i is (inside the execute method) " + i);
        });
    }
    public static void main(String[] args) {

        ThisReferenceExample thisReferenceExample = new ThisReferenceExample();

        thisReferenceExample.doProcess(10, new Process() {
            @Override
            public void process(int i) {
                System.out.println("Value of i is " + i);
                System.out.println(this);
            }

            @Override
            public String toString() {
                return "This is the anonymous inner Process class";
            }
        });

        // Lambda doesn't overwrite the this reference. In anonymous class, this reference inside the anonymous class points to the instance
        // of that class but in Lambda, this reference inside the Lambda points to whatever 'this' refers outside the Lambda so we can't
        // 100% say that Lambda is the replacement of anonymous class.
        thisReferenceExample.doProcess(10, i -> {
            // Throws an error: 'closure.ThisReferenceExample.this' cannot be referenced from a static context. It is because, outside the Lambda,
            // this refers to nothing. Outside the Lambda, it has to refer to instance on which 'public static void main(String[] args)' has
            // been called but we can see that it is a static method (called on Class rather than on instance), hence gives an error.
            // System.out.println(this);
            System.out.println("Value of i is (Lambda) " + i);
        });

        thisReferenceExample.execute();
    }

    @Override
    public String toString() {
        return "ThisReferenceExample{}";
    }
}
