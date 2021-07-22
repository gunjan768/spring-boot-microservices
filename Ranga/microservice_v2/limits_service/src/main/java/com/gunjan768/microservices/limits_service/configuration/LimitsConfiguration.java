package com.gunjan768.microservices.limits_service.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// When this microservice starts, it gets configuration from the application (extension can be anything .properties or .yml) file. As it also
// gets configuration from external file (from spring_cloud_config_server microservice). So all that properties from external configuration
// file if match with properties that are also in internal application file, then properties from external file will overwrite the properties
// of internal application file.
@Configuration
@ConfigurationProperties("limits-service")
public class LimitsConfiguration {

    private int minimum;
    private int maximum;
    private int average;

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }
}
