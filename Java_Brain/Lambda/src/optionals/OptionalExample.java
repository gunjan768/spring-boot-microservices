package optionals;

import java.util.Locale;
import java.util.Optional;

class Person {
    private final String firstName;
    private final String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Optional<String> getLastName() {
        return Optional.ofNullable(lastName);
    }
}

public class OptionalExample {
    public static void main(String[] args) {

        Optional<Object> empty = Optional.empty();

        System.out.println(
                empty + " " +
                empty.isEmpty() + " " +
                empty.isPresent() + " " +
                empty.orElse("Hey Emilia")
        );

        // You can't pass a null to Optional.of()
        Optional<Object> optional = Optional.of("Hey love");

        System.out.println(
                optional + " " +
                optional.isEmpty() + " " +
                optional.isPresent() + " " +
                optional.orElse("If not present")
        );

        // Here you can pass null to Optional.ofNullable()
        Optional<String> optional1 = Optional.ofNullable("Nothing personal love");
        Optional<String> optional2 = Optional.ofNullable(null);

        System.out.println(
                optional1
                .map(s -> s.toUpperCase(Locale.ROOT))
                .orElse("world")    // If null is passed to Optional.ofNullable(null)
        );

        System.out.println(
                optional2
                .map(s -> s.toUpperCase(Locale.ROOT))
                .orElseGet(() -> {
                    // Any extra computation to retrieve the value like from db
                    return "Love is great";
                })
        );

        System.out.println(
            optional1
            .map(s -> s.toUpperCase(Locale.ROOT))
            .orElseThrow(IllegalAccessError::new)
        );

        optional1
            .ifPresent(System.out::println);

        optional2
            .ifPresentOrElse(s -> System.out.println(s),
            () -> System.out.println("Null is passed"));

        // *********************************************************************************************************************************

        Person person = new Person("Gunjan", null);
        System.out.println(
                person.getLastName()
                .map(String::toUpperCase)
                .orElse("Person doesn't have a last name")
        );
    }
}
