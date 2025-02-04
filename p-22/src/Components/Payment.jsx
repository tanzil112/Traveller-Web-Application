import React, { useState } from "react";
import { useLocation } from "react-router-dom";
import axios from "axios";
// import './Payment.css'; 

const Payment = () => {
  const location = useLocation();
  const { flight, passengers } = location.state;
  const [showQR, setShowQR] = useState(false); // State to track if QR should be shown

  const handlePayment = async () => {
    try {
      // Call your backend to initiate payment process (if required)
      const response = await axios.get('http://localhost:8083/api/generate-phonepe-qr', {
        params: {
          amount: flight.price * passengers, // Send amount as query param
        },
      });

      alert("Payment initiated! Please scan the QR code to complete the payment.");

      // Show the QR code section after the payment is initiated
      setShowQR(true);
    } catch (error) {
      console.error("Payment error:", error);
      alert("Payment initiation failed!");
    }
  };

  return (
    <div className="payment-container">
      <h2>Payment</h2>
      <p>
        <strong>Flight:</strong> {flight.departureCity} to {flight.arrivalCity}
      </p>
      <p>
        <strong>Date:</strong> {flight.travelDate}
      </p>
      <p>
        <strong>Passengers:</strong> {passengers}
      </p>
      <p>
        <strong>Total Amount:</strong> ${flight.price * passengers}
      </p>

      <br></br>

      
        <div style={{ textAlign: 'center', marginTop: '20px' }}>
          <h5>Scan the QR Code to complete your payment</h5>
          <div
            style={{
              display: 'flex',
              justifyContent: 'center',
              alignItems: 'center',
              marginTop: '10px',
            }}
          >
            {/* Referencing the image directly after importing it */}
            <img
              src="public/PhonepeQr.png"// Using the imported image directly
              alt="PhonePe QR Code"
              style={{ width: '200px', height: '200px' }} // Set size if necessary
            />
          </div>
        </div>
      
        <button className="btn btn-success" onClick={handlePayment}>
        Pay Now
      </button>
    </div>
  );
};

export default Payment;
