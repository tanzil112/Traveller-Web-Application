import React from 'react';
import { Link } from 'react-router-dom';

function Dashboard() {
  return (
    <>
      <div className="main">
        <div className="nav1">
          <div className="background-video-wrapper">
            <video
              className="background-video"
              src="/video.mp4"  
              autoPlay
              loop
              muted
              playsInline
            />
          </div>

          <img src="/img4.png" alt="Logo" className='logo-img' />
          <div className="nav-links">
            <Link to="/login">Login</Link>
            <Link to="/register">Register</Link>
            <Link to="/about">About us</Link>
          </div>
        </div>
      </div>
    </>
  );
}

export default Dashboard;
