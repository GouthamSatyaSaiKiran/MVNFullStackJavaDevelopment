import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Navbar from './components/Navbar';
import Login from './components/Login';
import Signup from './components/Signup';
import ForgotPassword from './components/ForgotPassword';
import VehicleOwner from './components/VehicleOwner';
import FuelOwnerDashboard from './components/FuelOwnerDashboard';
import MechanicOwnerDashboard from './components/MechanicOwnerDashboard';
import Home from './components/Home';
import About from './components/About';
import Services from './components/Services';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userRole, setUserRole] = useState(null);
  const [requests, setRequests] = useState(() => {
    const saved = localStorage.getItem('vehicleRequests');
    return saved ? JSON.parse(saved) : [];
  });

  useEffect(() => {
    const role = localStorage.getItem('userRole');
    if (role) {
      setIsLoggedIn(true);
      setUserRole(role);
    }
  }, []);

  useEffect(() => {
    localStorage.setItem('vehicleRequests', JSON.stringify(requests));
  }, [requests]);

  return (
    <Router>
      <Navbar isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} userRole={userRole} />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/services" element={<Services />} />
        <Route path="/fuelownerdashboard" element={<FuelOwnerDashboard />} />
        <Route path="/mechanicownerdashboard" element={<MechanicOwnerDashboard />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/login" element={<Login setIsLoggedIn={setIsLoggedIn} setUserRole={setUserRole} />} />
        <Route path="/forgot-password" element={<ForgotPassword />} />

        {/* Role-based routing */}
        <Route
          path="/vehicleowner"
          element={
            userRole === 'VehicleOwner' ? (
              <VehicleOwner requests={requests} setRequests={setRequests} />
            ) : (
              <Navigate to="/" />
            )
          }
        />
        <Route
          path="/fuelownerdashboard"
          element={
            userRole === 'FuelOwner' ? (
              <FuelOwnerDashboard requests={requests} setRequests={setRequests} />
            ) : (
              <Navigate to="/" />
            )
          }
        />
        <Route
          path="/mechanicownerdashboard"
          element={
            userRole === 'Mechanic' ? (
              <MechanicOwnerDashboard requests={requests} setRequests={setRequests} />
            ) : (
              <Navigate to="/" />
            )
          }
        />
      </Routes>
    </Router>

  );
}

export default App;
