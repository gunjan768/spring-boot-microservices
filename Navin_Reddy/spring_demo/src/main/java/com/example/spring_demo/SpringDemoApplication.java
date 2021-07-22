package com.example.spring_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

// Spring provides a container called spring container which will have different objects. Which objects ? The object which Spring specifies
// (in fact the object we will create here), these objects are called spring beans. Then basically container contains spring beans.
// We have different scopes for the bean like Singleton, prototype.

// @SpringBootApplication annotation enables auto configuration. It is composed of following annotations:
// . @ComponentScan: Enables component scanning of current package. Also recursively scans sub packages.
// . @Configuration: Able to register extra beans with @Bean or import other configuration classes
// . @EnableAutoConfiguration: Enables Spring Boot's auto configuration support

// Due to recursive scanning, any sub packages under the root package will be automatically scanned irrespective of name of the folders
// (packages). But what if we have other packages which are not under the root package ? In this case we need to use @scanBasePackages
// annotation and specify all the packages name separated by comma
@SpringBootApplication(
		scanBasePackages = {

		}
)
public class SpringDemoApplication {

	public static void main(String[] args) {

		// run() method returns the 'ConfigurableApplicationContext' object. Here (run() method) spring container is initialized i.e. here
		// spring beans are created. Say we have many classes then objects of which classes we want ? Does here objects of all classes will
		// get initiated ? Nope. We as a developer has to specify which object(s) we want. By specifying @Component above the class, we
		// are saying that we want the object of this class. These objects are only called beans.
		ConfigurableApplicationContext context = SpringApplication.run(SpringDemoApplication.class, args);

		// We have to write the type of Class instance we want from the getBean() method and pass it as an argument to getBean(). In
		// spring container first it will be checked that do we have a bean of that class. If we have then it will be connected
		// automatically. Then spring boot will inject this object in our app. And the process of injecting is called Dependency
		// Injection (DI).
		Alien a = context.getBean(Alien.class);

		// Spring uses the Singleton pattern. By default even if you don't ask for any object (comment the above line where we ask
		// for specified bean 'context.getBean(Alien.class)') spring will create them and it will create one and only one instance
		// of every class.
		a.show();

		// You can check the proof for Singleton in the constructor of Alien class. There you will see one "Object created" message.
		// Alien a1 = context.getBean(Alien.class);

	}
}

// Spring boot will automatically load properties from  'application.properties'. This file is actually created by Spring Intializr.
// Initially it will be empty. You can write any spring properties like 'server.port=8080' to change the default port and also
// any additional properties like 'coach.name=Gunjan Kumar'. To read this properties you can use @Value annotation (see
// RestControllerExample.java).

// To load static resources create 'static' folder under 'resources' folder and place all the static resources inside that 'static'
// folder. Examples of static resources : HTML, Css, JS, images etc.

// Spring boot starter parent benefit
// . Default Maven configuration: Java version, UTF encoding etc.
// . Dependency management: Use version on parent only, spring-boot-starter-* dependencies inherit version from parent.
// . Default configuration of Spring boot plugin