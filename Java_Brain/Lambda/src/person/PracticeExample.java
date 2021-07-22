package person;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

interface Condition {
    boolean test(Person p);
}

public class PracticeExample {

    public static void main(String[] args) {

        List<Person> people = Arrays.asList(
                new Person("Gunjan", "Kumar", 21),
                new Person("Emilia", "Clarke", 32)
        );

        // Sort list by last name
//        Collections.sort(people, new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return o1.getLastName().compareTo(o2.getLastName());
//            }
//        });

        Collections.sort(people, (o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));

        printOnCondition(people, new Condition() {
            @Override
            public boolean test(Person p) {
                return true;
            }
        });

        printOnCondition(people, new Condition() {
            @Override
            public boolean test(Person p) {
                return p.getLastName().startsWith("C");
            }
        });

        // Using Lambda
        printOnCondition(people, p -> true);
        printOnCondition(people, p -> p.getLastName().startsWith("C"));

        System.out.println("******************************* Print using Predicate<T> interface ***************************************");

        // For Lambda type we don't have any inbuilt type so we leverages interface for it as we did like Condition interface. For some common
        // use cases Java 8 provides some interfaces to deal with like we can replace Condition interface because for the same purpose (returning
        // true or false) we have Predicate<T> functional interface.
        printUsingPredicate(people, new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return person.getLastName().startsWith("K");
            }
        });

        printUsingPredicate(people, person -> person.getLastName().startsWith("K"));

        System.out.println("************************* Using Predicate<T> and Consumer<T> interfaces ***************************************");

//        performUsingPredicate(people, person -> person.getFirstName().startsWith("E"), new Consumer<Person>() {
//            @Override
//            public void accept(Person person) {
//                System.out.println(person)
//            }
//        });

        performUsingPredicate(people, person -> person.getFirstName().startsWith("E"), person -> System.out.println(person));
        // performUsingPredicate(people, person -> person.getFirstName().startsWith("E"), System.out::println);
    }

    // Using Condition interface
    private static void printOnCondition(List<Person> people, Condition condition) {
        for(Person it: people) {
            if (condition.test(it)) {
                System.out.println(it);
            }
        }

        System.out.println("**************************************");
    }

    // Replacing Condition interface and instead using Predicate<T> interface
    private static void printUsingPredicate(List<Person> people, Predicate<Person> predicate) {
        for(Person it: people) {
            if (predicate.test(it)) {
                System.out.println(it);
            }
        }
    }

    // Consumer<T> is a functional interface provided out of box to deal with some common cases. It has one method which doesn't accepts any
    // any argument and return type is void.
    private static void performUsingPredicate(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
        for(Person it: people) {
            if (predicate.test(it)) {
                consumer.accept(it);
            }
        }
    }
}
