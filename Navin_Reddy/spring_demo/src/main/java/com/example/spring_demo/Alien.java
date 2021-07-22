package com.example.spring_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// By mentioning the @Scope value as prototype, spring will not create the instance by default i.e. beforehand. When you will ask
// for the instance 'context.getBean(Alien.class)' then at that time only it will create it. Now no Singleton pattern is followed.
// Everytime you ask for an object it will be created again.

// @Component is a class level annotation. During the component scan, Spring Framework automatically detects classes annotated with @Component.
// By default, the bean instances of this class has the same name as the class name with a lowercase initial. Since @Repository, @Service,
// @Configuration, and @Controller are all meta-annotations of @Component, they share the same bean naming behavior. Also, Spring automatically
// picks them up during the component scanning process.
@Component
//@Scope(value = "prototype")     // scope : singleton, prototype
public class Alien {

    private int aid;
    private String name, tech;

    // Alien class is dependent on Laptop class. How Alien class will know that the instance of Laptop class will be available and how
    // to search it though we have made Laptop class as Component class (i.e. instance of Laptop will be created as soon as App starts).
    // We have to say the Alien class hey there is an instance of Laptop class and search for it. How do we ask Alien to search for the
    // instance of Laptop class automatically ? We can do it by using annotation @Autowired.

    //  Try to search for the object in the spring container. It will search for the type (class) and not for the object name. Default
    // name of the instance is same as class with initial letter as lower case (here it will search for type 'Laptop' and not for
    // object name (laptop)). But what if want to search by name and not by type ? We can do it by using annotation @Qualifier.
    @Autowired
    @Qualifier("lap")   // See Laptop.java class (there we explicitly mentioned the name as "lap" and the same has to be mentioned here)
    private Laptop laptop;

    public Alien() {
        super();
        System.out.println("Object created");
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public void show() {
//        System.out.println("Hey Gunjan Emilia");

        laptop.compile();
    }
}