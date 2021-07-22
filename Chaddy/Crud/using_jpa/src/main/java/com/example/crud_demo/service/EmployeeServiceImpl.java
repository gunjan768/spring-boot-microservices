package com.example.crud_demo.service;

import com.example.crud_demo.dao.EmployeeDao;
import com.example.crud_demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    // @Qualifier("name_of_the_bean): As there are more than one implementation for EmployeeDao so we need to explicitly specify which
    // implementation we want to use.
    @Autowired
    public EmployeeServiceImpl(@Qualifier("employeeDaoJpaImpl") EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    // @Transactional annotation handles transaction management so we don't have to manually start and commit transaction
    @Override
    @Transactional
    public List<Employee> findAll() {
        System.out.println("Inside EmployeeServiceImpl");
        return employeeDao.findAll();
    }

    @Override
    @Transactional
    public Employee findById(int theId) {
        return employeeDao.findById(theId);
    }

    @Override
    @Transactional
    public void save(Employee theEmployee) {
        employeeDao.save(theEmployee);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        employeeDao.deleteById(theId);
    }
}