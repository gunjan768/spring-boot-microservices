package com.example.sb_config.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// @ConfigurationProperties(prefix_part): it will pick all those properties from the application.properties file which match the variable names
// in the class and pick only those properties whose prefix is mentioned as an argument to this annotation. For example here all properties
// start with "any" (any.*) examples: any.db, any.gunjan etc. And it will inject those values to these corresponding member variables automatically.

// @Configuration to create the bean of this class.
@Configuration
@ConfigurationProperties("any")
public class AnySettings {

    private String connection;
    private String host;
    private int port;

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
