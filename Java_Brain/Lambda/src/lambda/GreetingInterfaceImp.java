package lambda;

public class GreetingInterfaceImp implements GreetingInterface {
    @Override
    public void perform() {
        System.out.println("Hello world from GreetingInterface implementation");
    }
}
