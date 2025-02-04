package com.example.demo;

import com.example.demo.model.Flights;
import com.example.demo.repository.FlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataIntializer implements CommandLineRunner {

    @Autowired
    private FlightsRepository flightsRepository;

    @Override
    public void run(String... args) throws Exception {
        // Add sample flight data without id and last two parameters
        flightsRepository.save(new Flights("New York", "Los Angeles", "2024-12-25"));
        flightsRepository.save(new Flights("Chicago", "Miami", "2024-12-25"));
        flightsRepository.save(new Flights("San Francisco", "Seattle", "2024-12-25"));
        flightsRepository.save(new Flights("Boston", "Dallas", "2024-12-26"));
        flightsRepository.save(new Flights("Houston", "Atlanta", "2024-12-26"));
        flightsRepository.save(new Flights("Las Vegas", "Denver", "2024-12-27"));
        flightsRepository.save(new Flights("Orlando", "Phoenix", "2024-12-28"));
        flightsRepository.save(new Flights("Washington", "San Diego", "2024-12-29"));
        flightsRepository.save(new Flights("Detroit", "Austin", "2024-12-30"));
        flightsRepository.save(new Flights("Philadelphia", "Nashville", "2024-12-31"));
        flightsRepository.save(new Flights("Seattle", "Las Vegas", "2025-01-01"));
        flightsRepository.save(new Flights("San Jose", "Portland", "2025-01-02"));
        flightsRepository.save(new Flights("Denver", "Chicago", "2025-01-03"));
    }
}
