package com.gunjan768.microservice.currencyexchangeservice.controllers;

import com.gunjan768.microservice.currencyexchangeservice.models.CurrencyExchange;
import com.gunjan768.microservice.currencyexchangeservice.repositories.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable("from") String from,
            @PathVariable("to") String to
    ) {
        // CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));

        Optional<CurrencyExchange> currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);

//        if(currencyExchange.isEmpty()) {
//            throw new RuntimeException("Unable to find data from " + from + " to " + to);
//        }
//
//        // Give the current port number on which this microservice is currently running on.
//        String port = environment.getProperty("local.server.port");
//
//        // If a value is present, returns the value, otherwise throws NoSuchElementException.
//        currencyExchange.get().setEnvironment(port);
//
//        return currencyExchange.get();

        // With orElseThrow() will return an instance of CurrencyExchange>
//        System.out.println(
//                currencyExchange
//                        .stream()
//                        .findAny()
//                        .orElseThrow(IllegalArgumentException::new)
//        );

        // Without orElseThrow() will return an Optional<CurrencyExchange>
//        System.out.println(
//                currencyExchange
//                        .stream()
//                        .findAny()
//        );

        // stream().peek(): Returns a stream consisting of the elements of this stream, additionally performing the provided action on each element
        // as elements are consumed from the resulting stream. This is an intermediate operation.
         return currencyExchange
                 .stream()
                 .peek(currencyExchange1 -> {
                     String port = environment.getProperty("local.server.port");
                     currencyExchange1.setEnvironment(port);
                 })
                 .findFirst()
                 .orElseThrow(() -> new RuntimeException("Unable to find the data"));
    }
}
