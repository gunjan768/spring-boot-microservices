package com.gunjan768.microservice.currencyexchangeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// Currency Exchange Service: http://localhost:8000/currency-exchange/from/USD/to/INR
// Currency Conversion Service:  http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10

@EnableEurekaClient		// Not required for this annotation but to explicitly specify that it is an Eureka Client
@SpringBootApplication
public class CurrencyExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
	}

}
