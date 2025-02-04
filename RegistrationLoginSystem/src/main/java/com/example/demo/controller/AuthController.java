package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.print.attribute.standard.Destination;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserService userService;

    // Authentication and User Management
    @PostMapping("/auth/register")
    public ResponseEntity<String> registerUser(@RequestBody User user, @RequestParam String confirmPassword) {
        String response = userService.register(user, confirmPassword);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty() ||
            user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Username and password cannot be empty");
        }

        Optional<User> existingUser = userService.login(user.getUsername(), user.getPassword());
        return existingUser.isPresent()
                ? ResponseEntity.ok("Login successful!")
                : ResponseEntity.badRequest().body("Invalid username or password");
    }
    

    // Flights Management
    @GetMapping("/auth/flights")
    public ResponseEntity<List<Flights>> searchFlights(@RequestParam String departureCity,
                                                       @RequestParam String arrivalCity,
                                                       @RequestParam String travelDate) {
        List<Flights> flights = userService.searchFlights(departureCity, arrivalCity, travelDate);
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/auth/all-flights")
    public ResponseEntity<List<Flights>> getAllFlights() {
        List<Flights> flights = userService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @PostMapping("/auth/booking")
    public ResponseEntity<String> bookFlight(@RequestParam Long flightId, @RequestParam int passengers) {
        try {
            String confirmationId = userService.bookFlight(flightId, passengers);
            return ResponseEntity.ok("Booking successful! Confirmation ID: " + confirmationId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Travel Suggestions and Information
    @GetMapping("/travel/suggestions")
    public ResponseEntity<List<Suggestion>> getSuggestions(@RequestParam String location) {
        List<Suggestion> suggestions = userService.getSuggestionsByLocation(location);
        return ResponseEntity.ok(suggestions);
    }

    @GetMapping("/travel/info")
    public ResponseEntity<TravelInfo> getTravelInfo(@RequestParam String destination) {
        TravelInfo travelInfo = userService.getTravelInfoByDestination(destination);
        return ResponseEntity.ok(travelInfo);
    }

    @PostMapping("/travel/visa-requirements")
    public ResponseEntity<String> createVisaRequirements(@RequestBody Destination destination) {
        String visaRequirements = userService.getVisaRequirements(destination.getName());
        return ResponseEntity.ok(visaRequirements);
    }

    @PostMapping("/travel/safety-alerts")
    public ResponseEntity<String> createSafetyAlerts(@RequestBody Destination destination) {
        String safetyAlerts = userService.getSafetyAlerts(destination.getName());
        return ResponseEntity.ok(safetyAlerts);
    }
}
