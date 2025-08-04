import React, { useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom'; // Add this for navigation

const Register = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
    mobile: '',
    address: '',
    role: 'Select role'
  });

  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const handleChange = (e) => {
    setFormData(prev => ({
      ...prev,
      [e.target.name]: e.target.value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');
    setError('');

    try {
      const response = await axios.post('http://localhost:8080/auth/register', formData);
      setMessage(response.data); // adjust based on backend response format
    } catch (err) {
      setError(err.response?.data || 'Registration failed');
    }
  };

  return (
    <div style={{ maxWidth: '400px', margin: '0 auto', paddingTop: '50px' }}>
      <h2>Register</h2>
      {message && <p style={{ color: 'green' }}>{message}</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Name:</label><br />
          <input type="text" name="name" value={formData.name} onChange={handleChange} required />
        </div>
        <div>
          <label>Email:</label><br />
          <input type="email" name="email" value={formData.email} onChange={handleChange} required />
        </div>
        <div>
          <label>Mobile:</label><br />
          <input type="text" name="mobile" value={formData.mobile} onChange={handleChange} required />
        </div>
        <div>
          <label>Password:</label><br />
          <input type="password" name="password" value={formData.password} onChange={handleChange} required />
        </div>
        <div>
          <label>Address:</label><br />
          <input type="text" name="address" value={formData.address} onChange={handleChange} required />
        </div>
        <div>
          <label>Role:</label><br />
          <select name="role" value={formData.role} onChange={handleChange} required>
            <option value="">Select Role</option>
            <option value="FuelOwner">Fuel Owner</option>
            <option value="MechanicOwner">Mechanic Owner</option>
            <option value="VehicleOwner">Vehicle Owner</option>
          </select>
        </div>
        <br />
        <button type="submit">Register</button>
      </form>
      <div style={{ textAlign: 'center', marginTop: '10px' }}>
      <p style={{ marginTop: '10px' }}>
        Already Registered? <Link to="/login">Login</Link>
      </p>
      </div>
    </div>
  );
};

export default Register;
