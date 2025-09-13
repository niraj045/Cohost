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
  const [eventRsvps, setEventRsvps] = useState({});
  const [userRsvps, setUserRsvps] = useState({});
  const { user, isAuthenticated } = useAuth();

  const fetchEvents = async () => {
    try {
      setLoading(true);
      const data = await eventsAPI.getAllEvents();
      setEvents(data);
      
      // Fetch RSVP data for each event if user is authenticated
      if (isAuthenticated) {
        await fetchRsvpData(data);
      }
    } catch (err) {
      setError('Failed to fetch events');
    } finally {
      setLoading(false);
    }
  };

  const fetchRsvpData = async (eventsData) => {
    try {
      const rsvpPromises = eventsData.map(async (event) => {
        try {
          // Get all RSVPs for this event
          const eventRsvpData = await rsvpAPI.getEventRsvps(event.id);
          
          // Get user's RSVP for this event
          let userRsvp = null;
          try {
            userRsvp = await rsvpAPI.getUserEventRsvp(event.id);
          } catch (e) {
            // User hasn't RSVPed yet, which is fine
          }
          
          return {
            eventId: event.id,
            eventRsvps: eventRsvpData,
            userRsvp: userRsvp
          };
        } catch (err) {
          return {
            eventId: event.id,
            eventRsvps: [],
            userRsvp: null
          };
        }
      });

      const rsvpResults = await Promise.all(rsvpPromises);
      
      const eventRsvpsMap = {};
      const userRsvpsMap = {};
      
      rsvpResults.forEach(result => {
        eventRsvpsMap[result.eventId] = result.eventRsvps;
        if (result.userRsvp) {
          userRsvpsMap[result.eventId] = result.userRsvp;
        }
      });
      
      setEventRsvps(eventRsvpsMap);
      setUserRsvps(userRsvpsMap);
    } catch (err) {
      console.error('Failed to fetch RSVP data:', err);
    }
  };

  useEffect(() => {
    fetchEvents();
  }, [isAuthenticated]);
  
  const handleEventCreated = () => {
    setShowCreateForm(false);
    fetchEvents();
  };

  const handleRSVP = async (eventId, status = 'ATTENDING') => {
    if (!user) {
      alert('Please login to RSVP');
      return;
    }
    try {
      await rsvpAPI.createRsvp(eventId, { status });
      alert(`RSVP updated to ${status}!`);
      fetchEvents(); // Refresh to get updated RSVP data
    } catch (err) {
      alert('Failed to RSVP');
    }
  };

  const getRsvpCounts = (eventId) => {
    const rsvps = eventRsvps[eventId] || [];
    return {
      attending: rsvps.filter(r => r.status === 'ATTENDING').length,
      notAttending: rsvps.filter(r => r.status === 'NOT_ATTENDING').length,
      maybe: rsvps.filter(r => r.status === 'MAYBE').length
    };
  };

  const getUserRsvpStatus = (eventId) => {
    const userRsvp = userRsvps[eventId];
    return userRsvp ? userRsvp.status : null;
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
          events.map((event) => {
            const rsvpCounts = getRsvpCounts(event.id);
            const userStatus = getUserRsvpStatus(event.id);
            
            return (
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
                  
                  {/* RSVP Stats */}
                  {isAuthenticated && (
                    <div className="rsvp-stats">
                      <span className="rsvp-count attending">✅ {rsvpCounts.attending} attending</span>
                      <span className="rsvp-count maybe">❓ {rsvpCounts.maybe} maybe</span>
                      <span className="rsvp-count not-attending">❌ {rsvpCounts.notAttending} not attending</span>
                    </div>
                  )}
                </div>
                
                <div className="event-footer">
                  {user ? (
                    <div className="rsvp-buttons">
                      {userStatus && (
                        <div className="current-status">
                          Your status: <strong>{userStatus.replace('_', ' ')}</strong>
                        </div>
                      )}
                      <div className="rsvp-actions">
                        <button 
                          className={`rsvp-button attending ${userStatus === 'ATTENDING' ? 'active' : ''}`}
                          onClick={() => handleRSVP(event.id, 'ATTENDING')}
                        >
                          ✅ Attending
                        </button>
                        <button 
                          className={`rsvp-button maybe ${userStatus === 'MAYBE' ? 'active' : ''}`}
                          onClick={() => handleRSVP(event.id, 'MAYBE')}
                        >
                          ❓ Maybe
                        </button>
                        <button 
                          className={`rsvp-button not-attending ${userStatus === 'NOT_ATTENDING' ? 'active' : ''}`}
                          onClick={() => handleRSVP(event.id, 'NOT_ATTENDING')}
                        >
                          ❌ Can't Attend
                        </button>
                      </div>
                    </div>
                  ) : (
                    <button className="rsvp-button" disabled>
                      Login to RSVP
                    </button>
                  )}
                </div>
              </div>
            );
          })
        )}
      </div>
    </div>
  );
}