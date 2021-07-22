package collections_api;

import com.java.method_reference.Person;

import java.util.Arrays;
import java.util.List;

public class CollectionsExample {

    public static void main(String[] args) {

        List<Person> people = Arrays.asList(
                new Person("Gunjan", "Kumar", 21),
                new Person("Emilia", "Clarke", 32)
        );

        // people.forEach(person -> System.out.println(person));
        people.forEach(System.out::println);

        // Every Collections methods comes with stream() method. Terminal (end) method causes stream() to start like here forEach() is a
        // terminal method and it causes stream() to start. Stream is an internal iteration.
        people.stream()
                .filter(person -> person.getFirstName().startsWith("E"))
                .forEach(person -> System.out.println(person.getLastName()));

        int cnt = (int) people.stream()
                .filter(person -> person.getFirstName().startsWith("E"))
                .count();

        System.out.println(
                // parallelStream() enables to distribute the work (if data is very large) into parallel streams if it finds that it is
                // more efficient than without distributing.
                people.parallelStream()
                .filter(person -> person.getFirstName().startsWith("E"))
                .count()
        );

        System.out.println(cnt);
    }
}
