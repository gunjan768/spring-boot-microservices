package com.gunjan768.jpa.jpa_demo.repository;

import com.gunjan768.jpa.jpa_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

}