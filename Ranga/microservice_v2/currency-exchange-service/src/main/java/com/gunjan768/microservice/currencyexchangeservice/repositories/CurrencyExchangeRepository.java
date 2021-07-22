package com.gunjan768.microservice.currencyexchangeservice.repositories;

import com.gunjan768.microservice.currencyexchangeservice.models.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    // Jpa will automatically provide the implementation of this method where we can query using "from" and "to" properties.
    Optional<CurrencyExchange> findByFromAndTo(String from, String to);
}
