package com.example.spring_demo;

// ********************************************** Spring boot dev tools ************************************************************

// . If you make changes to your source code then you have to manually restart your application. Solution to this solution is to use
// 'spring-boot-devtools' which will automatically restart your application. We can have this feature by simply adding dependency
// to your pom.xml file.

// ********************************************** Spring boot dev tools ************************************************************

// ********************************************** Spring boot Actuator ************************************************************

// Problem:
// . How can I monitor and manage my app ?
// . How can I check the app health ?
// . How can I access app metrics ?

// Solution: Spring Boot Actuator
// . Expose endpoints to monitor and manage your app
// . You easily get devOps functionality out-of-the-box
// . Simply add the dependency to your POM file
// . Rest endpoints are automatically added to your app
// . Endpoints are prefixed with: /actuator like '/actuator/health'
// '/health': Health information about your app (checks the status of your app, used by monitoring apps to see if app is up or down)

// '/info': Information about your project. Default when you type '/actuator/info' is an empty object. To add properties update the
// application.properties. Any property starts with 'info' like 'info.app.name' will add properties to info object.

// By default, only health abd info are exposed. To expose all actuator endpoints over HTTP add 'manage.endpoints.web.exposure.include=*'
// to application.properties. To get all the registered beans use /actuator/beans or to know all the threads use '/actuator/threaddump'
// or to get all the mappings use '/actuator/mappings'

// ********************************************** Spring boot Actuator ************************************************************


// ********************************************** Spring boot Security ************************************************************

// You may not want to expose all the information regarding actuator. Add Spring security to project and endpoints are secured except
// health and info. But you can disable both of them. To access the secured routes you need to enter the username and password. Default
// username is 'user' and for password see console logs under the heading 'Using generated security password: your_generated_password'.
// To override the default username and password add properties to application.properties. We can exclude endpoints also by again adding
// property to application.properties.

// ********************************************** Spring boot Security ************************************************************

// ********************************************** Running from CMD *****************************************************************

// There are two options for running the app using command line.
// 1) Use: java -jar your_app_name.jar 2) mvnw spring-boot:run (using Spring boot Maven plugin)

// Using Spring Maven plugin --> mvnw allows you to run a Maven project
// . No need to have Maven installed or present on your path
// . If correct version of Maven is not found then automatically downloads the correct version. mvnw.cmd is for windows.

// <plugin> <groupId>org.springframework.boot</groupId> <artifactId>spring-boot-maven-plugin</artifactId> </plugin> (see pom.xml)
// This plugin is used to package the executable jar or war archive (mvnw package) and easily run the app (mvnw spring-boot:run)
// for windows (use cmd).

// How to add JAVA_HOME variable to an environment: https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html

// ********************************************** Running from CMD *****************************************************************

// ********************************************** Some properties *****************************************************************

// ---> Log levels severity mapping: All are prefixed by 'logging.level.org.'
// springframework=DEBUG, hibernate=TRACE, luv2code=INFO
// Log file name: logging.file=any_name.log

// ---> Web properties:
// HTTP server port: server.port=7070
// Context path of execution: server.servlet.context-path=/any_thing (default is '\'). Now your app will run on localhost:8080\any_thing\
// Default HTTP session time out: server.servlet.session.timeout=15m (default is 30 min)

// ---> Actuator: Change the base path for actuator endpoints -> management.endpoints.web.base-path=/any_thing (localhost:808/any_thing/info)

// ---> Data Properties: All are prefixed with: 'spring.datasource.'
// For JDBC url of the DB: url=jdbc:mysql://localhost:8080/db_name, username of the db: username=Max, password: password=tiger

// ********************************************** Some properties *****************************************************************

public class Details {
}