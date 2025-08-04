import React from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { HashLink } from 'react-router-hash-link';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import { Carousel } from 'react-responsive-carousel';

function Navbar({ isLoggedIn, setIsLoggedIn, userRole }) {
  const navigate = useNavigate();
  const location = useLocation();

  const isHomePage = location.pathname === '/';

  const handleLogout = () => {
    localStorage.removeItem('userRole');
    setIsLoggedIn(false);
    navigate('/');
  };

  const getDashboardPath = () => {
    if (userRole === 'VehicleOwner') return '/vehicleowner';
    if (userRole === 'FuelOwner') return '/fuelownerdashboard';
    if (userRole === 'Mechanic') return '/mechanicownerdashboard';
    return null;
  };

  return (
    <>
      {/* ---------- NAVBAR ---------- */}
      <nav id="home">
        <div style={{ display: 'flex', alignItems: 'center', gap: '1rem' }}>
          <img src="/img.jpg" alt="Logo" className="logo" id="img" />
        </div>
        <div id="txt">
          <h2>Mobile Vehicle Needs</h2>
        </div>
        <div id="t">
          <Link to="/">HOME</Link>
          <HashLink smooth to="/#about">ABOUT</HashLink>
          <HashLink smooth to="/#services">SERVICES</HashLink>

          {isLoggedIn ? (
            <>
              {getDashboardPath() && <Link to={getDashboardPath()}>REQUEST</Link>}
              <button onClick={handleLogout}>LOGOUT</button>
            </>
          ) : (
            <>
              <Link to="/login">LOGIN</Link>
              <Link to="/signup">
                <button>SIGNUP</button>
              </Link>
            </>
          )}
        </div>
      </nav>

      {/* ---------- CAROUSEL ONLY ON HOME PAGE ---------- */}
      {isHomePage && (
        <div className="carousel-container">
          <Carousel
            autoPlay
            infiniteLoop
            showThumbs={false}
            showStatus={false}
            interval={2000}
            transitionTime={1000}
          >
            <div>
              <img
                src="fuelserviceimage.jpg"
                alt="Fuel Delivery"
              />
              <p className="legend">Fuel Delivery at Your Location</p>
            </div>
            <div>
              <img
                src="MechanicService.jpg"
                alt="Mechanic Assistance"
              />
              <p className="legend">Quick Mechanic Assistance</p>
            </div>
            {/* <div>
              <img
                src="https://via.placeholder.com/1200x400/10b981/ffffff?text=24x7+Towing+Service"
                alt="Towing Service"
              />
              <p className="legend">24x7 Towing Service</p>
            </div> */}
          </Carousel>
        </div>
      )}
    </>
  );
}

export default Navbar;
