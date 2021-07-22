package com.example.my_web_app_2.controller;

import com.example.my_web_app_2.dao.AlienRepositoryJpa;
import com.example.my_web_app_2.model.Alien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlienController1 {

    @Autowired
    AlienRepositoryJpa alienRepositoryJpa;

    // **************************** JSON is not working only 'form-data' works

    // As we are using @RestController and not simple @Controller so we there is no need to write @ResponseBody. In Postman if you send
    // send data using 'form-data' then it will work but if you send using "json" it will fail. To make JSON work we need to use annotation
    // called "@RequestBody" before parameter.
//    @PostMapping(path = "/alien")
//    public Alien addAlien(Alien alien) {
//
//        alienRepositoryJpa.save(alien);
//        System.out.println(alien);
//
//        return alien;
//    }

    // ****************************** Make JSON works by using "@RequestBody"annotation

    // As server is receiving data so use word 'consumes' and if server sends data then use 'produces'.
    @PostMapping(path = "/alien", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Alien addAlien(@RequestBody Alien alien) {

        alienRepositoryJpa.save(alien);
        // System.out.println(alien);

        return alien;
    }

    // ************************ Deleting Alien

    @DeleteMapping("/alien/{id}")
    public String deleteAlien(@PathVariable int id) {
        Alien alien = alienRepositoryJpa.getOne(id);
        alienRepositoryJpa.delete(alien);

        return "Deleted";
    }

    // ************************ Updating Alien

    // We can use like this: "/alien/{id}" but to keep it simple we take the data (Alien object) from 'body' and update that object.
    @PutMapping(path = "/alien", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Alien saveOrUpdateAlien(@RequestBody Alien alien) {

        alienRepositoryJpa.save(alien);
        // System.out.println(alien);

        return alien;
    }
}