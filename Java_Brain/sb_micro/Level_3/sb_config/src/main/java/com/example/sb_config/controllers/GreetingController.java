package com.example.sb_config.controllers;

import com.example.sb_config.configurations.AnySettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

// The @RefreshScope annotation is used to load the configuration properties value from the Config server. All the dependencies of this class
// will be refreshed (get the latest updates) after sending a POST request to the "http://localhost:port/actuator/refresh". To send to a
// request indeed you need to have a dependency for Actuator.
@RefreshScope
@RestController
public class GreetingController {

    // Since we are taking the value of the "greetingMessage" from application.properties file. If you build your app, then .jar file will be
    // created and application.properties will be inside the .jar file. Still we have not achieved the externalization ? So we need to
    // access the value from external 'properties' file and for the same purpose we have created another "application.properties" file in the
    // target folder where our "sb_config-0.0.1-SNAPSHOT.jar" is (it is created using cmd: mvnw install).

    // If property "my.greeting" is not present in the application.properties file then app gonna show error. But my mentioning the default
    // value (after colon :) will avoid the error.
    @Value("${my.greeting: any_default_value}")
    private String greetingMessage;

    // Using @Value("") annotation like this (not using the ${}) will just attach the message (whatever you wrote) to the variable. This is
    // not very helpful as value of the variable resides in this class only and not from any external source (like application.properties).
    @Value("this is the static message")
    private String staticMessage;

    @Value("${my.list.value}")
    private List<String> listOfNames;

    @Value("#{${dbValues}}")
    private Map<String, String> dbValues;

    @Autowired
    private AnySettings anySettings;

    @Autowired
    private Environment environment;

    @GetMapping("/greeting")
    public String greeting() {
        return greetingMessage + " " + staticMessage + " " + listOfNames + " " + dbValues + " " + anySettings.getHost() ;
    }

    @GetMapping("/env")
    public String getENV() {
        return environment.toString();
    }
}