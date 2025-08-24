import React, { useState, useEffect } from 'react';
import { eventsAPI } from '../api/events';
import { rsvpAPI } from '../api/rsvp';
import { useAuth } from '../context/AuthContext';
import CreateEventForm from '../components/CreateEventForm';

export default function Events() {
  const [events, setEvents] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [showCreateForm, setShowCreateForm] = useState(false);
  const { user, isAuthenticated } = useAuth();

  const fetchEvents = async () => {
    try {
      setLoading(true);
      const data = await eventsAPI.getAllEvents();
      setEvents(data);
    } catch (err) {
      setError('Failed to fetch events');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchEvents();
  }, []);
  
  const handleEventCreated = () => {
    setShowCreateForm(false);
    fetchEvents();
  };

  const handleRSVP = async (eventId) => {
    if (!user) {
      alert('Please login to RSVP');
      return;
    }
    try {
      await rsvpAPI.createRsvp(eventId, { status: 'ATTENDING' });
      alert('RSVP successful!');
      fetchEvents();
    } catch (err) {
      alert('Failed to RSVP');
    }
  };

  if (loading) {
    return (
      <div className="content-section">
        <div className="loading-spinner">Loading events...</div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="content-section">
        <div className="error-message">{error}</div>
      </div>
    );
  }

  return (
    <div className="content-section">
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1rem' }}>
        <h1>Events</h1>
        {isAuthenticated && (
          <button className="odoo-primary-btn" onClick={() => setShowCreateForm(!showCreateForm)}>
            {showCreateForm ? 'Cancel' : 'Create Event'}
          </button>
        )}
      </div>
      <p>Discover amazing events in your community</p>

      {showCreateForm && <CreateEventForm onEventCreated={handleEventCreated} />}
      
      <div className="events-grid">
        {events.length === 0 ? (
          <div className="no-events">
            <p>No events available at the moment.</p>
          </div>
        ) : (
          events.map((event) => (
            <div key={event.id} className="event-card">
              <div className="event-header">
                <h3 className="event-title">{event.title}</h3>
                <span className="event-date">
                  {new Date(event.eventDate).toLocaleDateString()}
                </span>
              </div>
              
              <div className="event-body">
                <p className="event-description">{event.description}</p>
                <div className="event-details">
                  <div className="event-location">
                    <span>📍 {event.location}</span>
                  </div>
                  {event.community && (
                    <div className="event-community">
                      <span>🏢 {event.community.name}</span>
                    </div>
                  )}
                </div>
              </div>
              
              <div className="event-footer">
                <button 
                  className="rsvp-button"
                  onClick={() => handleRSVP(event.id)}
                  disabled={!user}
                >
                  {user ? 'RSVP' : 'Login to RSVP'}
                </button>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
}