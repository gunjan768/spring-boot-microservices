package com.gunjan768.microservice.currencyconversionservice.proxy;

import com.gunjan768.microservice.currencyconversionservice.models.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Argument passed to @FeignClient is the name of the microservice we want to call for example here we want to call "currency-exchange"
// microservice so we put it's name (see the application name of the currency-exchange).
// @FeignClient(name = "currency-exchange", url = "localhost:8000")    // By mentioning URL, Feign will not do the load balancing

// FeignClient will talk to Eureka (Naming Server) and pick up the instances of currency-exchange microservices and do load balancing b/w them.
// All that magic will happen just by removing this URL. Different time, different instance of currency-exchange microservice will be picked up.
// Here load balancing done by Feign is Client Side Load Balancing.

// There is a load balancer (spring-cloud-starter-loadbalancer) which is brought into the classpath by spring-cloud-starter-netflix-eureka-client
// and this is the load balancer framework that is used by Feign to actually distribute the load among the multiple instances which are returned
// by Eureka. In the earlier versions of SC, the load balancer which used was 'Ribbon' and in the recent versions, SC shifted to using 'SC
// Load balancer' as the load balancer.
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}