package com.example.demo.student;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

// Each entity must have at least two annotations defined: @Entity and @Id . The @Entity annotation specifies that the class is an entity and
// is mapped to a database table. The @Table annotation specifies the name of the database table to be used for mapping. ... The @Repository
// annotation is used to define a repository.

// We have mapped this Student class the table in our database
@Entity     // For hibernate
@Table      // For table in a database
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;    // Assigning 'id' as a primary key

    private String name, email;
    private LocalDate dob;

    // @Transient : Says that for the property to which it is applied (here age) there is no need for it to be a column of our database.
    // It means that age will be calculated first.
    @Transient
    private Integer age;

    public Student() {
    }

    public Student(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this. dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}

