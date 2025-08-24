import api from './config';

export const rsvpAPI = {
  createRsvp: async (eventId, rsvpData) => {
    const response = await api.post(`/events/${eventId}/rsvp`, rsvpData);
    return response.data;
  },

  updateRsvp: async (rsvpId, rsvpData) => {
    const response = await api.put(`/rsvp/${rsvpId}`, rsvpData);
    return response.data;
  },

  deleteRsvp: async (rsvpId) => {
    const response = await api.delete(`/rsvp/${rsvpId}`);
    return response.data;
  },

  getEventRsvps: async (eventId) => {
    const response = await api.get(`/events/${eventId}/rsvp`);
    return response.data;
  },

  getUserRsvps: async (userId) => {
    const response = await api.get(`/users/${userId}/rsvp`);
    return response.data;
  }
};