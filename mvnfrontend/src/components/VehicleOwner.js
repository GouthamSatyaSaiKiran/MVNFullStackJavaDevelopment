import React, { useState } from 'react';
import TrackingMap from './TrackingMap';

function VehicleOwnerForm({ requests, setRequests }) {
  const [form, setForm] = useState({
    name: '',
    vehicle: '',
    service: '',
    fuelType: '',
    fuelLiters: '',
    issue: '',
    location: '',
  });

  const userMobile = localStorage.getItem('userMobile'); // Get logged-in user's mobile number

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const newRequest = {
      ...form,
      mobile: userMobile, // associate mobile with the request
      id: Date.now(),
      status: 'Pending',
    };

    // Add to local state
    setRequests([...requests, newRequest]);

    try {
      if (form.service === 'Fuel Delivery') {
        const fuelRequest = {
          name: form.name,
          mobile: userMobile,
          fuelType: form.fuelType,
          quantity: parseFloat(form.fuelLiters),
          location: form.location,
          vehicleNumber: form.vehicle,
          status: 'Pending',
        };

        await fetch('http://localhost:8080/fuel/request', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(fuelRequest),
        });

      } else if (form.service === 'Mechanic Help') {
        const mechanicRequest = {
          name: form.name,
          mobile: userMobile,
          issueType: form.issue,
          location: form.location,
          vehicleNumber: form.vehicle,
          status: 'Pending',
        };

        await fetch('http://localhost:8080/mechanic/request', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(mechanicRequest),
        });
      }

      alert('Service request submitted successfully!');
    } catch (error) {
      console.error('Error submitting request:', error);
      alert('Failed to submit service request. Please try again.');
    }

    // Reset form
    setForm({
      name: '',
      vehicle: '',
      service: '',
      fuelType: '',
      fuelLiters: '',
      issue: '',
      location: '',
    });
  };

  return (
    <div className="container" style={{display:'flex', gap:'20px',padding:'20px',marginTop:'50px'}}>
      {/* LEFT: Form */}
      <div className="left">
        <h2 style={{ textAlign: 'center', marginBottom: '20px' }}>
          Request a Service
        </h2>
        <form onSubmit={handleSubmit}>
          <label>Your Name</label>
          <input name="name" value={form.name} onChange={handleChange} required />

          <label>Vehicle Number</label>
          <input name="vehicle" value={form.vehicle} onChange={handleChange} required />

          <label>Service Needed</label>
          <select
            name="service"
            value={form.service}
            onChange={handleChange}
            required
          >
            <option value="">-- Select Service --</option>
            <option value="Fuel Delivery">Fuel Delivery</option>
            <option value="Mechanic Help">Mechanic Help</option>
          </select>

          {form.service === 'Fuel Delivery' && (
            <>
              <label>Fuel Type</label>
              <select
                name="fuelType"
                value={form.fuelType}
                onChange={handleChange}
                required
              >
                <option value="">-- Select Fuel Type --</option>
                <option value="Petrol">Petrol</option>
                <option value="Diesel">Diesel</option>
              </select>

              <label>Liters Required</label>
              <input
                name="fuelLiters"
                type="number"
                value={form.fuelLiters}
                onChange={handleChange}
                required
              />
            </>
          )}

          {form.service === 'Mechanic Help' && (
            <>
              <label>Describe Issue</label>
              <textarea
                name="issue"
                value={form.issue}
                onChange={handleChange}
                required
              />
            </>
          )}

          <label>Your Location</label>
          <textarea
            name="location"
            value={form.location}
            onChange={handleChange}
            required
          />

          <button
            type="submit"
            style={{
              backgroundColor: '#000',
              color: '#fff',
              padding: '10px 20px',
              border: 'none',
              borderRadius: '4px',
              cursor: 'pointer',
              marginTop: '10px',
            }}
          >
            Submit Request
          </button>
        </form>
      </div>

      {/* RIGHT: Requests List */}
      <div className="right">
        <h2 style={{ textAlign: 'center', marginBottom: '20px' }}>
          Your Requests
        </h2>
        {requests.filter((r) => r.mobile === userMobile).length === 0 && (
          <p>No requests yet.</p>
        )}
        {requests
          .filter((r) => r.mobile === userMobile)
          .map((req) => (
            <div key={req.id} className="request-card">
              <h3>{req.service}</h3>
              <p>Status: {req.status}</p>
              {req.fuelType && <p>Fuel Type: {req.fuelType}</p>}
              {req.fuelLiters && <p>Liters: {req.fuelLiters}</p>}
              {req.issue && <p>Issue: {req.issue}</p>}
              {req.status === 'Accepted' && (
                <>
                  <p>ETA: {req.eta} mins</p>
                  <TrackingMap deliveryLocation={req.deliveryLocation} />
                </>
              )}
            </div>
          ))}
      </div>
    </div>
  );
}

export default VehicleOwnerForm;
