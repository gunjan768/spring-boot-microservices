package com.example.crud_demo.dao;

import com.example.crud_demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// Access using : http://localhost:8080/spring_data_rest/members (rather than "employees")
@RepositoryRestResource(
        path = "members"
)
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
