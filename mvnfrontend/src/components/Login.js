import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

function Login({ setIsLoggedIn, setUserRole }) {
  const [form, setForm] = useState({ username: '', password: '', role: '' });
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/auth/login', {
        email: form.username,
        password: form.password,
        role: form.role
      });

      // Assuming backend returns 200 OK with user role and success message
      setIsLoggedIn(true);
      setUserRole(form.role);
      localStorage.setItem('userRole', form.role);

      // Navigate based on role
      if (form.role === 'VehicleOwner') {
        navigate('/vehicleowner');
      } else if (form.role === 'FuelOwner') {
        navigate('/fuelownerdashboard');
      } else if (form.role === 'Mechanic') {
        navigate('/mechanicownerdashboard');
      }

    } catch (err) {
      if (err.response && err.response.status === 401) {
        setError('Invalid credentials. Please try again.');
      } else {
        setError('Login failed. Please try later.');
      }
    }
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleLogin}>
        {error && <p style={{ color: 'red' }}>{error}</p>}

        <input
          type="text"
          name="username"
          placeholder="Email"
          value={form.username}
          onChange={handleChange}
          required
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={form.password}
          onChange={handleChange}
          required
        />
        <select name="role" value={form.role} onChange={handleChange} required>
          <option value="">Select Role</option>
          <option value="VehicleOwner">Vehicle Owner</option>
          <option value="FuelOwner">Fuel Owner</option>
          <option value="Mechanic">Mechanic</option>
        </select>
        <button type="submit">Login</button>
      </form>
      <div style={{ textAlign: 'center', marginTop: '15px' }}>
        <p>
          <Link to="/forgot-password">Forgot Password?</Link>
        </p>
        <p>
          Not yet registered? <Link to="/signup">SignUp</Link>
        </p>
      </div>
    </div>
  );
}

export default Login;
