package com.gunjan768.microservice.currencyconversionservice.controllers;

import com.gunjan768.microservice.currencyconversionservice.models.CurrencyConversion;
import com.gunjan768.microservice.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    // Calling api using RestTemplates
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);

        // For get request use getForEntity() and similarly for post use postForEntity(). Third parameter is the value of the path variables
        // in the form of HashMap
        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class,
                uriVariables
        );

        // getBody() is used to get the response value from the ResponseEntity<>
        CurrencyConversion currencyConversion = responseEntity.getBody();

        assert currencyConversion != null;
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
        currencyConversion.setEnvironment(currencyConversion.getEnvironment() + " rest template");

        return currencyConversion;
    }

    // *********************************************** Using Feign ***************************************************************

    // The Feign is a declarative web service (HTTP client) developed by Netflix. It's aim is to simplify the HTTP API clients.
    // It makes writing web service clients easier. To use Feign create an interface and annotate it.

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {
         CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);

        assert currencyConversion != null;
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
        currencyConversion.setEnvironment(currencyConversion.getEnvironment() + " feign");

        return currencyConversion;
    }
}
