import axios from 'axios';
import React, { useEffect, useState } from 'react';

function Hotels () {
  const [hotels, setHotels] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:3001/hotels")
      .then((Res) => {
        console.log(Res.data);
        setHotels(Res.data);
      });
  }, []);

  return (
    <>
      <div className="main-hotels">
        <div id="hotels">
          <h2>Hotels & places to stay</h2>
        </div>
        <div className="row row-cols-1 row-cols-md-4 row-cols-lg-3 row-cols-xl-4 g-4 main-card">
          {hotels.map((ele, index) => {
            return (
              <div className="col" key={index}>
                <div className="card">
                  <img src={ele.images[0]} className="card-img-top" alt="..." />
                  <div className="card-body">
                    <h5 className="card-title">Hotel name: {ele.hotel_name}</h5>
                    <p className="card-text">Amenities: {ele.amenities.join(", ")}</p>
                    <p className="card-text">
                      <b>Rating :  </b> {ele.rating} <i className="fa-solid fa-star rotating-star"></i>
                    </p>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </>
  );
}

export default Hotels;
