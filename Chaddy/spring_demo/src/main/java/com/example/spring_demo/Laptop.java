package com.example.spring_demo;

import org.springframework.stereotype.Component;

// Default name for the instance created in the spring container is same as class with initial letter as small. But what if we want
// the different name for the bean (instance) ? We can do it by explicitly mentioning it after @Component("any_name"). Now here
// bean name that will get created is "lap" instead of "laptop" (default name).
@Component("lap")
public class Laptop {

    private int lid;
    private String brand;

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "laptop{" +
                "lid=" + lid +
                ", brand='" + brand + '\'' +
                '}';
    }

    public void compile() {
//        System.out.println("Compiling ");
    }
}