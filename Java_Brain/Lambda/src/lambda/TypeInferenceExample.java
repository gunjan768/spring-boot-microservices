package lambda;

public class TypeInferenceExample {
    public static void main(String[] args) {

        // StringLengthLambda lengthLambda = String::length;
        StringLengthLambda lengthLambda = s -> s.length();
        System.out.println(lengthLambda.getLength("Hey Emilia Love"));

        // printLambda(String::length);
        printLambda(s -> s.length());
    }

    public static void printLambda(StringLengthLambda lengthLambda) {
        System.out.println(lengthLambda.getLength("Mallika loves Gunjan"));
    }

    interface StringLengthLambda {
        int getLength(String s);
    }
}
