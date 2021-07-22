package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// JpaRepository is generic class where 1st type passed is 'Student' which is the type we want for 'StudentRepository' to work upon and
// 2nd type passed is 'Long' which is the type of the primary key (id).
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // select * from student where email = ?
    @Query("select s from Student as s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}