package com.java.method_reference;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MethodReferenceExample2 {

    public static void main(String[] args) {

        List<Person> people = Arrays.asList(
                new Person("Gunjan", "Kumar", 21),
                new Person("Emilia", "Clarke", 32)
        );

        // performUsingPredicate(people, person -> person.getFirstName().startsWith("E"), person -> System.out.println(person));
        performUsingPredicate(people, person -> person.getFirstName().startsWith("E"), System.out::println);
    }


    // Consumer<T> is a functional interface provided out of box to deal with some common cases. It has one method which accepts one
    // argument and return type is void.
    private static void performUsingPredicate(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
        for(Person it: people) {
            if (predicate.test(it)) {
                consumer.accept(it);
            }
        }
    }
}
