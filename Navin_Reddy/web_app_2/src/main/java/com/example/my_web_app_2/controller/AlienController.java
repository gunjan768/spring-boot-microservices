package com.example.my_web_app_2.controller;

import com.example.my_web_app_2.dao.AlienRepository;
import com.example.my_web_app_2.dao.AlienRepositoryJpa;
import com.example.my_web_app_2.model.Alien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

// The Java Persistence API (JPA) is one possible approach to ORM. Via JPA the developer can map, store, update and retrieve
// data from relational databases to Java objects and vice versa. JPA can be used in Java-EE and Java-SE applications. JPA is
// a specification and several implementations are available.

// JDBC makes it possible to do establish a connection with a data source, send queries and update statements, and process
// the results. Simply, JDBC makes it possible to do the following things within a Java application: Establish a connection
// with a data source. Send queries and update statements to the data source.

// See 'data.sql' inside the 'resources' folder. There we have written sql query to insert into the db (here h2).
// But what if we want to insert from here using java code (using AlienRepository interface).
@Controller
public class AlienController {

    // We need to use the 'service layer' for best practice and security purpose. AlienController directly connecting
    // with the AlienRepository which is not the way we write for the enterprise scenario. So we will create a
    // 'service layer'. Controller (AlienController) is not sure that from where data come. They can cme from several
    // place like database, input fields (like here addAlienHandler() method gets invoked when we click submit button),
    // network etc. So we need to process something and processing is done in the service layer.
    @Autowired
    AlienRepository repository;

    @Autowired
    AlienRepositoryJpa alienRepositoryJpa;

    // 'h2' is an internal database and we can access it by going to 'http://localhost:8080/h2-console'
    @RequestMapping("/")
    public String home() {
        return "home.jsp";
    }

    @RequestMapping("/addAlien")
    public String addAlienHandler(Alien alien) {
        repository.save(alien);

        return "home.jsp";
    }

    @RequestMapping("/getAlien")
    public ModelAndView getAlienHandler(@RequestParam("aid") int id) {

        // We can directly specify the view name in the constructor itself.
        ModelAndView modelAndView = new ModelAndView("showAlien.jsp");

        // You can use Optional class also as repository.findById() return Optional class instance. Second way is to use orElse() which
        // is not a good practice but for this example it works.
        Alien alien = repository.findById(id).orElse(new Alien());

        System.out.println(repository.findByTech("Java"));
        System.out.println(repository.findByIdGreaterThan(121));
        System.out.println(repository.findByTechSorted("Java"));

        modelAndView.addObject(alien);

        return modelAndView;
    }

    // *************************************************** Using Rest Services **************************************************

    // By default if we are returning String then it will always be a view name. But here we want to return data so we will use
    // @ResponseBody i.e. we are informing our DispatcherServlet that we are not returning a view name instead we are returning data
    // so don't search for the view name directly print it whatever is returning.
//    @RequestMapping("/aliens")
//    @ResponseBody
//    public String wantAlienHandler() {
//
//        // As we are using Rest api so we just need data. Both JSON and XML are String format.
//
//        // repository.findAll() returns an Iterable so converted it to string.
//        return repository.findAll().toString();
//    }

    // alienRepositoryJpa.findAll() : returns a list of Alien and this list will be converted to JSON using library called 'Jackson'
    // which we don't need to install externally as it is already been there with Spring boot.
    @RequestMapping(path = "/aliens", produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Alien> getAliens() {
        return alienRepositoryJpa.findAll();
    }

    // Optional<Alien> simply returns an Alien object. In case if it don't have data it returns optional data. We can be more specific
    // and use @GetMapping instead of @RequestMapping.
//    @RequestMapping("/alien/{aid}")
    @GetMapping("/alien/{aid}")
    @ResponseBody
    public Optional<Alien> wantAlienHandler(@PathVariable("aid") int id) {
        return alienRepositoryJpa.findById(id);
    }

    // *************************************************** Using Rest Services **************************************************
}