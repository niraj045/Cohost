import React, { useState, useEffect } from 'react';
import { eventsAPI } from '../api/events';
import { communitiesAPI } from '../api/communities';

export default function CreateEventForm({ onEventCreated }) {
  const [formData, setFormData] = useState({
    title: '',
    description: '',
    eventDate: '',
    location: '',
    communityId: ''
  });
  const [communities, setCommunities] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchCommunities = async () => {
      try {
        const data = await communitiesAPI.getAllCommunities();
        setCommunities(data);
      } catch (err) {
        setError('Failed to load communities for the form.');
      }
    };
    fetchCommunities();
  }, []);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    if (!formData.communityId) {
      setError('Please select a community.');
      return;
    }
    try {
      await eventsAPI.createEvent(formData);
      alert('Event created successfully!');
      onEventCreated(); // Notify parent to refresh the event list
    } catch (err) {
      setError('Failed to create event.');
      console.error(err);
    }
  };

  return (
    <div className="auth-card" style={{ margin: '2rem 0' }}>
      <h2>Create a New Event</h2>
      <form onSubmit={handleSubmit} className="auth-form">
        {error && <div className="error-message">{error}</div>}
        <div className="form-group">
          <label>Title</label>
          <input type="text" name="title" value={formData.title} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label>Description</label>
          <textarea name="description" value={formData.description} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label>Date and Time</label>
          <input type="datetime-local" name="eventDate" value={formData.eventDate} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label>Location</label>
          <input type="text" name="location" value={formData.location} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label>Community</label>
          <select name="communityId" value={formData.communityId} onChange={handleChange} required>
            <option value="">Select a Community</option>
            {communities.map(community => (
              <option key={community.id} value={community.id}>{community.name}</option>
            ))}
          </select>
        </div>
        <button type="submit" className="auth-button">Create Event</button>
      </form>
    </div>
  );
}