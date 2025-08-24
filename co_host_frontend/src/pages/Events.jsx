import React, { useState, useEffect } from 'react';
import { eventsAPI } from '../api/events';
import { rsvpAPI } from '../api/rsvp';
import { useAuth } from '../context/AuthContext';

export default function Events() {
  const [events, setEvents] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const { user } = useAuth();

  useEffect(() => {
    fetchEvents();
  }, []);

  const fetchEvents = async () => {
    try {
      setLoading(true);
      const data = await eventsAPI.getAllEvents();
      setEvents(data);
    } catch (err) {
      setError('Failed to fetch events');
      console.error('Error fetching events:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleRSVP = async (eventId) => {
    if (!user) {
      alert('Please login to RSVP');
      return;
    }

    try {
      await rsvpAPI.createRsvp(eventId, { status: 'ATTENDING' });
      alert('RSVP successful!');
      fetchEvents(); // Refresh events
    } catch (err) {
      alert('Failed to RSVP');
      console.error('Error creating RSVP:', err);
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
      <h1>Events</h1>
      <p>Discover amazing events in your community</p>
      
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
                  
                  <div className="event-capacity">
                    <span>👥 Max Capacity: {event.maxCapacity}</span>
                  </div>
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
