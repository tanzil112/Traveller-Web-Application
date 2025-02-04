import React, { useState } from "react";
import axios from "axios";
import { Card, Button } from "react-bootstrap";
import { FaPlaneDeparture, FaPlaneArrival } from "react-icons/fa";
import { useNavigate } from "react-router-dom"; // For navigation

const Flights = () => {
  const [flightSearch, setFlightSearch] = useState({
    departureCity: "",
    arrivalCity: "",
    travelDate: "",
    passengers: 1,
  });
  const [flightResults, setFlightResults] = useState([]);
  const navigate = useNavigate();

  // Function to handle flight search
  const handleSearchFlights = async () => {
    try {
      const response = await axios.get("http://localhost:8083/api/auth/all-flights", {
        headers: {
          "Authorization": "Bearer your_token_here",
          "Content-Type": "application/json",
        },
      });

      const filteredFlights = response.data.filter((flight) => {
        const isDepartureCityMatch = flight.departureCity
          .toLowerCase()
          .includes(flightSearch.departureCity.toLowerCase());
        const isArrivalCityMatch = flight.arrivalCity
          .toLowerCase()
          .includes(flightSearch.arrivalCity.toLowerCase());
        const isTravelDateMatch = flight.travelDate === flightSearch.travelDate;

        return isDepartureCityMatch && isArrivalCityMatch && isTravelDateMatch;
      });

      // Add mock prices if not provided in the API
      const flightsWithPrices = filteredFlights.map((flight) => ({
        ...flight,
        price: flight.price || Math.floor(Math.random() * 300) + 100, // Mock price between $100 and $400
      }));

      setFlightResults(flightsWithPrices);
    } catch (error) {
      console.error("Error fetching flights:", error);
    }
  };

  // Function to navigate to the payment page
  const handleBooking = (flight) => {
    navigate("/payment", {
      state: { flight, passengers: flightSearch.passengers, totalPrice: flight.price * flightSearch.passengers },
    });
  };

  return (
    <div className="flights-section">
      <div className="flight-search-container">
        <h2 className="text-center mb-4">Search Flights</h2>
        <div className="search-form row">
          <div className="col-lg-3 col-md-6 mb-3">
            <input
              type="text"
              placeholder="Departure City"
              value={flightSearch.departureCity}
              onChange={(e) =>
                setFlightSearch({ ...flightSearch, departureCity: e.target.value })
              }
              className="form-control"
            />
          </div>
          <div className="col-lg-3 col-md-6 mb-3">
            <input
              type="text"
              placeholder="Arrival City"
              value={flightSearch.arrivalCity}
              onChange={(e) =>
                setFlightSearch({ ...flightSearch, arrivalCity: e.target.value })
              }
              className="form-control"
            />
          </div>
          <div className="col-lg-2 col-md-6 mb-3">
            <input
              type="date"
              value={flightSearch.travelDate}
              onChange={(e) =>
                setFlightSearch({ ...flightSearch, travelDate: e.target.value })
              }
              className="form-control"
            />
          </div>
          <div className="col-lg-2 col-md-6 mb-3">
            <input
              type="number"
              placeholder="Passengers"
              min="1"
              value={flightSearch.passengers}
              onChange={(e) =>
                setFlightSearch({ ...flightSearch, passengers: e.target.value })
              }
              className="form-control"
            />
          </div>
          <div className="col-lg-2 col-md-6 mb-3">
            <button onClick={handleSearchFlights} className="btn btn-primary w-100">
              Search Flights
            </button>
          </div>
        </div>
      </div>

      <div className="flight-results mt-4">
        {flightResults.length > 0 ? (
          <div className="row">
            {flightResults.map((flight) => (
              <div key={flight.id} className="col-lg-4 col-md-6 col-sm-12 mb-4">
                <Card className="h-100 flight-card">
                  <div className="flight-image-wrapper">
                    <img
                      src="/flight.jpg"
                      alt="Flight"
                      className="card-img-top flight-card-img"
                    />
                  </div>
                  <Card.Body>
                    <Card.Title className="flight-title">
                      <FaPlaneDeparture /> {flight.departureCity} - {flight.arrivalCity}{" "}
                      <FaPlaneArrival />
                    </Card.Title>
                    <Card.Text>
                      <strong>Departure Date:</strong> {flight.travelDate}
                      <br />
                      <strong>Price:</strong> ${flight.price} per passenger
                    </Card.Text>
                    <Button
                      variant="primary"
                      onClick={() => handleBooking(flight)}
                      className="w-100"
                    >
                      Book Now
                    </Button>
                  </Card.Body>
                </Card>
              </div>
            ))}
          </div>
        ) : (
          <p>No flights found matching your search criteria.</p>
        )}
      </div>
    </div>
  );
};

export default Flights;
