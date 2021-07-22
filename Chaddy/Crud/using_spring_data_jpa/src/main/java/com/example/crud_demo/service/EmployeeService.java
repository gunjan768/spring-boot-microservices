package com.example.crud_demo.service;


import com.example.crud_demo.entity.Employee;
import java.util.List;

public interface EmployeeService {

    // By default methods are public

    List<Employee> findAll();
    Employee findById(int theId);
    void save(Employee theEmployee);
    void deleteById(int theId);
}
