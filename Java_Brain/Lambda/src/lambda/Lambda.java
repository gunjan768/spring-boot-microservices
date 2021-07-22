package lambda;

import java.io.IOException;

// Q) Why Lambdas ?
// . Enables functional programming
// . Readable and concise code
// . Easier to use apis and libraries
// . Enable support for parallel processing

public class Lambda {

    public void greet(GreetingInterface greetingInterface) {
        greetingInterface.perform();
    }

    public static void main(String[] args) throws IOException {

        Lambda g = new Lambda();
        GreetingInterfaceImp helloWorldGreeting = new GreetingInterfaceImp();

        // Basically we have passed the thing (an instance of HelloWorldGreetingInterface) which contains the function (perform()) instead of
        // passing the function itself. We can pass the function (against of OOP) without creating the object using the concept of Lambda.
        g.greet(helloWorldGreeting);

        GreetingInterface innerClassGreeting = new GreetingInterface() {
            @Override
            public void perform() {
                System.out.println("Hello world from inner class");
            }
        };

        // Lambda doesn't belongs to any object, it exists in isolation. We can use Function as value using Lambda. Before Java 8 it is not
        // possible to assign the function to a variable. For example: var a = public void perform() {}; But here we some extra things which
        // we need to remove like public (access modifier) as whoever the variable 'a' can execute the code, giving function a name (perform),
        // removing a return type as return type will be automatically inferred by Java. So after removing all these things and adding an
        // additional arrow: var a = () -> {}

        // Type of lambda function ? We used an interface which has same only one function declaration and that too with same signature. Function
        // name doesn't matter (you can give any name as per your choice). You can have any number of function definitions (using default) you want.
        // This type of interface is called functional interface. Compiler will get confused if we have more than one method declarations as for
        // which method user want to create a Lambda expression. So we can say Lambda is the syntactical sugar for the Anonymous class.
        GreetingInterface myLambdaFunction = () -> System.out.println("Hello world from Lambda");

        // This is how we execute lambda expressions. By calling the interface method on it, just as if it were an instance of a class. This
        // lambda expression is kind a behaving like an implementation of GreetingInterface interface.
        // myLambdaFunction.perform();

        g.greet(innerClassGreeting);
        g.greet(myLambdaFunction);
    }
}
