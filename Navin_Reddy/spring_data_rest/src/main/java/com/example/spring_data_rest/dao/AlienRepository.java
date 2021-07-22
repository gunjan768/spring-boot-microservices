package com.example.spring_data_rest.dao;

import com.example.spring_data_rest.model.Alien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// We don't need any Controller to work here in 'Spring Data Rest' as we did in Spring MVC. We can build rest services
// without using MVC. We can achieve (not using any Controller file) by using annotation @RepositoryRestResource.

// 'Spring Data Rest' is a magic. No methods nothing. All methods are inbuilt in 'Spring Data Rest'.
@RepositoryRestResource(
        collectionResourceRel = "aliens",    // Base url
        path = "aliens"
)
public interface AlienRepository extends JpaRepository<Alien, Integer>
{

}