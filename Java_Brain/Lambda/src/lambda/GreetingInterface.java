package lambda;

// @FunctionalInterface annotation is completely optional. The Java compiler does not require it for your lambda types. But it is good practice to
// add it.
@FunctionalInterface
public interface GreetingInterface {
    void perform();
}
