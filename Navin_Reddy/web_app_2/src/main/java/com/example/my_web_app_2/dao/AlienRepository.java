package com.example.my_web_app_2.dao;

// This repository will have crud operations (methods).

import com.example.my_web_app_2.model.Alien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// Since we are using 'data-jpa' we can extend the 'CrudRepository<Table_type, Primary_key_type>' interface. Some of the CrudRepository methods
// return an Iterable instance for which we need to change it to String. Instead we have another class called JpaRepository which extends
// CrudRepository class plus it has some more features. See 'AlienRepositoryJpa.java' for more

public interface AlienRepository extends CrudRepository<Alien, Integer> {
    // Spring data-jpa is helping with the query. We need to follow some protocol to write the method name. Protocol is method name should
    // start with 'findBy' and end with any property name with upper case as first letter.
    List<Alien> findByTech(String tech);

    // You can also use method which ends with 'GreaterThan' or 'LessThan' to search for elements greater than or less than the given
    // element.
    List<Alien> findByIdGreaterThan(int id);

    // We can even write our own queries (complex query) using @Query annotation. As we are working with JPA so we will work with JPQL query
    // which is similar to HQL and HQL is similar to SQL with some changes like in JPQL we can avoid writing 'select *' as it is just a
    // boilerplate. Other change is that after question mark we need to number it as we can have multiple question marks.
    @Query("from Alien where tech=?1 order by name desc")
    List<Alien> findByTechSorted(String tech);
}