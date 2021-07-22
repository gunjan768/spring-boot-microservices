package com.example.my_web_app_2.dao;

import com.example.my_web_app_2.model.Alien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlienRepositoryJpa extends JpaRepository<Alien, Integer>
{

}