package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.model.Flights;
import com.example.demo.model.Suggestion;
import com.example.demo.model.TravelInfo;
import com.example.demo.model.User;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.FlightsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.repository.TravelInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.logging.Logger;

@Service
public class UserService {
	
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightsRepository flightsRepository;

    @Autowired
    private BookingRepository bookingRepository;
    

    @Autowired
    private SuggestionRepository suggestionRepository;

    @Autowired
    private TravelInfoRepository travelInfoRepository;

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    // Regular Expression for password validation
    private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).{8,}$";

    // User Management Services
    public String register(User user, String confirmPassword) {
        // Validate password
        if (!Pattern.matches(PASSWORD_PATTERN, user.getPassword())) {
            return "Password must be alphanumeric and include at least one special character.";
        }

        // Validate confirmPassword
        if (!user.getPassword().equals(confirmPassword)) {
            return "Passwords do not match.";
        }

        // Check if email is already registered
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Email is already registered.";
        }

        try {
            userRepository.save(user);
        } catch (Exception e) {
            logger.severe("Error during user registration: " + e.getMessage());
            return "An error occurred during registration.";
        }

        return "User registered successfully!";
    }

    public Optional<User> login(String email, String password) {
        return userRepository.findByUsername(email)
                .filter(user -> user.getPassword().equals(password));
    }

    // Flights Management Services
    public List<Flights> searchFlights(String departureCity, String arrivalCity, String travelDate) {
        return flightsRepository.findByDepartureCityAndArrivalCityAndTravelDate(departureCity, arrivalCity, travelDate);
    }

    public List<Flights> getAllFlights() {
        return flightsRepository.findAll();
    }

    public String bookFlight(Long flightId, int passengers) {
        try {
            Flights flight = flightsRepository.findById(flightId).orElseThrow(() -> new Exception("Flight not found"));

            Booking booking = new Booking();
            booking.setFlightId(flightId);
            booking.setPassengers(passengers);
            booking.setConfirmationId(UUID.randomUUID().toString());
            bookingRepository.save(booking);

            return booking.getConfirmationId();
        } catch (Exception e) {
            logger.severe("Error booking flight: " + e.getMessage());
            return "An error occurred while booking the flight.";
        }
    }

    // Suggestion Services
    public List<Suggestion> getSuggestionsByLocation(String location) {
        return suggestionRepository.findByLocation(location);
    }

    // Travel Information Services
    public TravelInfo getTravelInfoByDestination(String destination) {
        TravelInfo info = travelInfoRepository.findByDestination(destination);
        if (info == null) {
            logger.warning("No travel information found for destination: " + destination);
        }
        return info;
    }
    public String getVisaRequirements(String destination) {
        TravelInfo info = travelInfoRepository.findByDestination(destination);
        return info != null ? info.getVisaRequirements() : "No visa requirements available.";
    }

    public String getSafetyAlerts(String destination) {
        TravelInfo info = travelInfoRepository.findByDestination(destination);
        return info != null ? info.getSafetyAlerts() : "No safety alerts available.";
    }
}
