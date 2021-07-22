package com.example.crud_demo.dao;


import com.example.crud_demo.entity.Employee;
import java.util.List;

public interface EmployeeDao {

    // By default methods are public

    List<Employee> findAll();
    Employee findById(int id);
    void save(Employee employee);
    void deleteById(int id);
}